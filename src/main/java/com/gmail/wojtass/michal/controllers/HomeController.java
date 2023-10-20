package com.gmail.wojtass.michal.controllers;

import com.gmail.wojtass.michal.components.ResetLimitTransactions;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	ResetLimitTransactions resetLimitTransactions;

	private static boolean firstStartBool = false;

	/**
	 * Don't know did it work, not tested
	 */
	public void checkLastResetAndResetIfMissedMethod(){
		resetLimitTransactions.checkLastResetAndResetIfMissed();
	}

	/**
	 * Tested in another place, not here and without this if.
	 */
	public void createServerInfoObjectToDBMethod(){
		if (!firstStartBool){
			resetLimitTransactions.createServerInfoObjectToDB();
			firstStartBool = true;
		}

	}

	//comment
	@RequestMapping("/")
	public String home() {
		createServerInfoObjectToDBMethod();
		checkLastResetAndResetIfMissedMethod();

		return "home";
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "redirect:home";
	}

	@RequestMapping("/logout-success")
	public String logoutPage() {
		return "logout";
	}
	
}
