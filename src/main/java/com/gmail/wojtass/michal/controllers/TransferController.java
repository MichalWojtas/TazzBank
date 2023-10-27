package com.gmail.wojtass.michal.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


import com.gmail.wojtass.michal.components.AccountManagement;
import com.gmail.wojtass.michal.model.AccountBank;
import com.gmail.wojtass.michal.services.AccountBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

	@Autowired
	AccountBankRepository accountBankRepository;

	@Autowired
	AccountManagement accountManagement;
	
	@RequestMapping("/bank2")
	public String bank2(Model model, @SessionAttribute("loggedUser") User user) {
		model.addAttribute("loggedUser",user);
		return "bank2";
	}
	
	
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@PostMapping("/bank2")
	public String postBank2(@ModelAttribute("transaction") @Validated Transaction transaction,BindingResult bindingResult, @RequestParam("selectedAccount") String selectedAccount) {
		
		if(bindingResult.hasErrors()) {
			return "bank2";
		}
		long recipientUserId = accountBankRepository.findUserIdByAccountNumber(transaction.getRecipientAccountNumber());
		User userX = userRepo.findByUserId(recipientUserId);
		transaction.setRecipientUser(userX);
		if(transaction.getRecipientUser()==null) {
			bindingResult.rejectValue("recipientUser", "error_code", "That account is not exists.");
			return "bank2";
		}else {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); 
		User giverUser = userRepo.findByUsername(username);
		if(giverUser.getLimitTransactionForDay() != 0 && giverUser.getLimitTransactionForDay() < giverUser.getTempLimitTransactionForDay() + transaction.getAmountTransaction()){
			bindingResult.rejectValue("amountTransaction","error_code","This value exceeds the set daily limit");
			return "bank2";
		}
		if (giverUser.getLimitTransactionForMonth() != 0 && giverUser.getLimitTransactionForMonth() < giverUser.getTempLimitTransactionForMonth() + transaction.getAmountTransaction()){
			bindingResult.rejectValue("amountTransaction","error_code","This value exceeds the set monthly limit");
			return "bank2";
		}
		int selectedAccountForId = Integer.parseInt(selectedAccount);
		transferValue(transaction,giverUser,transaction.getRecipientUser(),selectedAccountForId);
		transaction.setGiverAccountNumber(giverUser.getAccountsBank().get(selectedAccountForId).getAccountNumber());
		accountManagement.updateAllAccountValuesToGiverUserAfterTransaction(giverUser,transaction.getAmountTransaction());
		accountManagement.updateAllAccountValuesToRecipientUserAfterTransaction(transaction.getRecipientUser(),transaction.getAmountTransaction());
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
	public void transferValue(Transaction transaction,User giverUser,User recipientUser, int selectedAccountForId) {
		double value = transaction.getAmountTransaction();
		long recipientId = accountBankRepository.findUserIdByAccountNumber(transaction.getRecipientAccountNumber());
		int recipientIndex = recipientUser.getAccountBankIndex(recipientId);
		List<AccountBank> accountsBankGiverUser = giverUser.getAccountsBank();
		List<AccountBank> accountsBankRecipientUser = recipientUser.getAccountsBank();
		double giverValue = accountsBankGiverUser.get(selectedAccountForId).getAccountValue();
		double recipientValue = accountsBankRecipientUser.get(recipientIndex).getAccountValue();
		double tempLimitTransactionForDay = giverUser.getTempLimitTransactionForDay();
		double tempLimitTransactionForMonth = giverUser.getTempLimitTransactionForMonth();
		boolean isDayLimitDiffThanZero = giverUser.getLimitTransactionForDay() != 0;
		boolean isMonthLimitDiffThanZero = giverUser.getLimitTransactionForMonth() != 0;
		if(giverValue >= value) {
			giverValue = giverValue - value;
			recipientValue = recipientValue + value;
			giverValue = BigDecimal.valueOf(giverValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
			recipientValue = BigDecimal.valueOf(recipientValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
			if (isDayLimitDiffThanZero){
				tempLimitTransactionForDay = tempLimitTransactionForDay + transaction.getAmountTransaction();
				System.out.println(tempLimitTransactionForDay);
			}
			if (isMonthLimitDiffThanZero){
				tempLimitTransactionForMonth = tempLimitTransactionForMonth + transaction.getAmountTransaction();
			}
		}else {
			throw new IllegalArgumentException("Must be possive value");
		}
		try {
			accountBankRepository.updateAccountValue(giverValue,giverUser.getAccountsBank().get(selectedAccountForId).getAccountBankId());
			accountBankRepository.updateAccountValue(recipientValue,recipientUser.getAccountsBank().get(recipientIndex).getAccountBankId());
			if (isDayLimitDiffThanZero){
				giverUser.setTempLimitTransactionForDay(tempLimitTransactionForDay);
			}
			if (isMonthLimitDiffThanZero){
				recipientUser.setTempLimitTransactionForMonth(tempLimitTransactionForMonth);
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
