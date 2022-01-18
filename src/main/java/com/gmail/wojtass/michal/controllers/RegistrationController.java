package com.gmail.wojtass.michal.controllers;

import java.math.BigInteger;

import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.gmail.wojtass.michal.model.User;
import com.gmail.wojtass.michal.services.UserRepository;
import com.gmail.wojtass.michal.validator.UserValidator;

@Controller
public class RegistrationController {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	UserValidator userValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}
	
	@RequestMapping("/registration")
	public String registrationPage() {
		return "registration";
	}
	
	@PostMapping(value="/registration")
	public String postRegister(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "registration";
		}
		user.bcryptPassword();
		user.setAccountNumber(generateAccountNumber());
		repo.save(user);

		
		return "home";
	}

	@GetMapping(value="/registration")
	public String getRegister(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	private String generateAccountNumber() {
		String accountNumberString;
		try {
			long id = repo.findMaxId();
			User userId = repo.findById(id);
			String accountNumber = userId.getAccountNumber();
			BigInteger accountNumberBigInteger = new BigInteger(accountNumber);
	        accountNumberBigInteger = accountNumberBigInteger.add(BigInteger.ONE);
	        accountNumberString = accountNumberBigInteger.toString();
		}catch(AopInvocationException e) {
			accountNumberString = "99111111111111111111111111";
		}
		
		return accountNumberString;
	}
	
}
