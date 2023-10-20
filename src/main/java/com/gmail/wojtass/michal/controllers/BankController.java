package com.gmail.wojtass.michal.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmail.wojtass.michal.otherMethods.WelcomeTextGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gmail.wojtass.michal.model.User;
import com.gmail.wojtass.michal.services.UserRepository;

@Controller
@SessionAttributes("loggedUser")
public class BankController {

	@Autowired
	UserRepository repo;

	/* //If token will be required, made when problems with AJAX
	@Autowired
	private CsrfTokenRepository csrfTokenRepository;

	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return csrfTokenRepository.loadToken(request);
	}
	 */
	
	@Transactional(readOnly = false)
	@PostMapping(value = "/bank",params = "addValue")
	public String postBank(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
		
		Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
		String username1 = auth1.getName();
		User user1 = repo.findByUsername(username1);
		double accountValue = user1.getAccountValue() + user.getAccountValue();
		double ac = BigDecimal.valueOf(accountValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
		repo.updateAccountValue(ac,user1.getId());
		repo.flush();
		
		return "redirect:addValueSuccess";
	}
	
	@GetMapping(value = "/bank")
	public String getBank(Model model, @ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User loggedUser = repo.findByUsername(username);
		HttpSession session = request.getSession();
		WelcomeTextGenerator welcomeTextGenerator = new WelcomeTextGenerator();
		session.setAttribute("welcomeText",welcomeTextGenerator.generate());
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("user", user);
		return "bank";
	}
}
