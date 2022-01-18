package com.gmail.wojtass.michal.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(IllegalArgumentException.class)
	public String negativeValueException(IllegalArgumentException e) {
		
		return "negativeValueException";
	}
}
