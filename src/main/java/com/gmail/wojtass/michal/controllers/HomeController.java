package com.gmail.wojtass.michal.controllers;

import com.gmail.wojtass.michal.components.ServerManagement;
import com.gmail.wojtass.michal.data.ExampleDataForDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	ExampleDataForDatabase exampleDataForDatabase;

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
	 * Not work, to check later
	 */
	public void checkLastResetAndResetIfMissedMethod(){
		serverManagement.checkLastResetAndResetIfMissed();
	}

	@RequestMapping("/")
	public String home() {
		//This should be in better place, because we can start from login panel, and it can not trigger
		createServerInfoObjectToDBMethod();
		checkLastResetAndResetIfMissedMethod();
		//Comment if you don't want to have example database and comment after create OR Uncomment if you want create it
		exampleDataForDatabase.createExampleDB();

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
