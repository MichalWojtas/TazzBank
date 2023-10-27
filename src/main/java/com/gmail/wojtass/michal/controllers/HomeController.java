package com.gmail.wojtass.michal.controllers;

import com.gmail.wojtass.michal.components.ServerManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	ServerManagement serverManagement;

	private static boolean firstStartBool = false;

	/**
	 * Tested and work.
	 */
	public void createServerInfoObjectToDBMethod(){
		if (!firstStartBool){
			serverManagement.createServerInfoObjectToDB();
			firstStartBool = true;
		}

	}

	/**
	 * Don't know did it work, not tested
	 */
	public void checkLastResetAndResetIfMissedMethod(){
		serverManagement.checkLastResetAndResetIfMissed();
	}

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
