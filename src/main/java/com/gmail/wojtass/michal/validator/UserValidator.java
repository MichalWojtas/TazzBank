package com.gmail.wojtass.michal.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gmail.wojtass.michal.model.User;
import com.gmail.wojtass.michal.services.TransactionRepository;
import com.gmail.wojtass.michal.services.UserRepository;
/*
@Component
@Controller
public class UserValidator implements Validator{

	@Autowired
	UserRepository repo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		User user = (User) target;
		
		validate(ValidatorEnum.USERNAME,user.getUsername(),"username",errors);
		validate(ValidatorEnum.PASSWORD,user.getB4encryptPassword(),"b4encryptPassword",errors);
		validate(ValidatorEnum.EMAIL,user.getEmail(),"email",errors);
		
		String password = user.getB4encryptPassword(); 
		String confirmPassword = user.getConfirmPassword();
		if(!password.equals(confirmPassword)) {
			errors.rejectValue("b4encryptPassword", "err_code", "Passwords must be the same");
		}
		
		if(repo.existsByUsername(user.getUsername())) {
			errors.rejectValue("username", "err_code", "That username already exists.");
		}
		if(repo.existsByEmail(user.getEmail())) {
			errors.rejectValue("email", "err_code", "That email already exists.");
		}
		
		
	}
	
	private void validate(ValidatorEnum validator, String value, String fieldName, Errors errors) {
		String patternReg = validator.getPattern();
		Pattern pattern = Pattern.compile(patternReg);
		Matcher matcher = pattern.matcher(value);
		if(!matcher.matches()) {
			errors.rejectValue(fieldName, validator.getErrCode(), validator.getErrMessage());
		}
	}




}
*/

