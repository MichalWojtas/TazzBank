package com.gmail.wojtass.michal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddValueSuccessController {
	
	@RequestMapping("/addValueSuccess")
	public String addValueSuccess() {
		
		return "addValueSuccess";
	}

}
