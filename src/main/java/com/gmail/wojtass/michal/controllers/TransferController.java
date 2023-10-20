package com.gmail.wojtass.michal.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;



import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.gmail.wojtass.michal.model.Transaction;
import com.gmail.wojtass.michal.model.User;
import com.gmail.wojtass.michal.services.TransactionRepository;
import com.gmail.wojtass.michal.services.UserRepository;

@Controller
public class TransferController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	TransactionRepository repo;
	
	@RequestMapping("/bank2")
	public String bank2(Model model, @SessionAttribute("loggedUser") User user) {
		model.addAttribute("loggedUser",user);
		return "bank2";
	}
	
	
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@PostMapping("/bank2")
	public String postBank2(@ModelAttribute("transaction") @Validated Transaction transaction,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "bank2";
		}
		transaction.setRecipientUser(userRepo.findByAccountNumber(transaction.getRecipientAccountNumber()));
		if(transaction.getRecipientUser()==null) {
			bindingResult.rejectValue("recipientUser", "error_code", "That account is not exists.");
			return "bank2";
		}else {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); 
		User giverUser = userRepo.findByUsername(username);
		if(giverUser.getLimitTransactionForDay() < giverUser.getTempLimitTransactionForDay() + transaction.getAmountTransaction()){
			bindingResult.rejectValue("amountTransaction","error_code","This value exceeds the set daily limit");
			return "bank2";
		}
		if (giverUser.getLimitTransactionForMonth() < giverUser.getTempLimitTransactionForMonth() + transaction.getAmountTransaction()){
			bindingResult.rejectValue("amountTransaction","error_code","This value exceeds the set monthly limit");
			return "bank2";
		}
		transferValue(transaction,giverUser,transaction.getRecipientUser());
		transaction.setGiverAccountNumber(giverUser.getAccountNumber());
		repo.save(transaction);
		
		return "redirect:bank";
		}
	}
	
	@GetMapping(value="/bank2")
	public String getBank2(Model model) {
		model.addAttribute("transaction", new Transaction());
		return "bank2";
	}
	
	
	
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	public void transferValue(Transaction transaction,User giverUser,User recipientUser) {
		double value = transaction.getAmountTransaction();
		long giverId = giverUser.getId();
		long recipientId = recipientUser.getId();
		double giverValue = giverUser.getAccountValue();
		double recipientValue = recipientUser.getAccountValue();
		double tempLimitTransactionForDay = giverUser.getTempLimitTransactionForDay();
		double tempLimitTransactionForMonth = giverUser.getTempLimitTransactionForMonth();
		boolean isDayLimitDiffThanZero = giverUser.getLimitTransactionForDay() != 0;
		boolean isMonthLimitDiffThanZero = giverUser.getLimitTransactionForMonth() != 0;
		if(giverUser.getAccountValue() >= value) {
			giverValue = giverValue - value;
			recipientValue = recipientValue + value;
			giverValue = BigDecimal.valueOf(giverValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
			recipientValue = BigDecimal.valueOf(recipientValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
			if (isDayLimitDiffThanZero){
				tempLimitTransactionForDay = tempLimitTransactionForDay + transaction.getAmountTransaction();
			}
			if (isMonthLimitDiffThanZero){
				tempLimitTransactionForMonth = tempLimitTransactionForMonth + transaction.getAmountTransaction();
			}
		}else {
			throw new IllegalArgumentException("Must be possive value");
		}
		try {
			userRepo.updateAccountValue(giverValue, giverId);
			userRepo.updateAccountValue(recipientValue, recipientId);
			if (isDayLimitDiffThanZero){
				userRepo.updateTempLimitTransactionForDay(tempLimitTransactionForDay,giverId);
			}
			if (isMonthLimitDiffThanZero){
				userRepo.updateTempLimitTransactionForMonth(tempLimitTransactionForMonth,giverId);
			}
			transaction.getUsers().add(recipientUser);
			recipientUser.getTransactions().add(transaction);
			transaction.getUsers().add(giverUser);
			giverUser.getTransactions().add(transaction);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
