package com.gmail.wojtass.michal.controllers;

import com.gmail.wojtass.michal.components.AccountManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.gmail.wojtass.michal.model.User;
import com.gmail.wojtass.michal.services.UserRepository;
//import com.gmail.wojtass.michal.validator.UserValidator;

@Controller
public class RegistrationController {
	
	@Autowired
	UserRepository repo;

	@Autowired
	AccountManagement accountConfiguration;
	/*
	@Autowired
	UserValidator userValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	 */
	
	@RequestMapping("/registration")
	public String registrationPage() {
		return "registration";
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@PostMapping(value="/registration")
	public String postRegister(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
		if(repo.existsByUsername(user.getUsername())) {
			bindingResult.rejectValue("username", "err_code", "That username already exists.");
		}
		if(repo.existsByEmail(user.getEmail())) {
			bindingResult.rejectValue("email", "err_code", "That email already exists.");
		}
		String password = user.getB4encryptPassword();
		String confirmPassword = user.getConfirmPassword();
		if(!password.equals(confirmPassword)) {
			bindingResult.rejectValue("b4encryptPassword", "err_code", "Passwords must be the same");
		}
		if(bindingResult.hasErrors()) {
			return "registration";
		}
		user.bcryptPassword();
		accountConfiguration.addNewStandardAccount(user);
		repo.save(user);
		return "home";
	}

	@GetMapping(value="/registration")
	public String getRegister(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
}
