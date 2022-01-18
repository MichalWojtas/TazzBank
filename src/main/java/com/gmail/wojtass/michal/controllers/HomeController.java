package com.gmail.wojtass.michal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gmail.wojtass.michal.model.User;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	@RequestMapping("/logout-success")
	public String logoutPage() {
		return "logout";
	}
	
}
