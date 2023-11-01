package com.gmail.wojtass.michal.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


import com.gmail.wojtass.michal.components.AccountManagement;
import com.gmail.wojtass.michal.model.AccountBank;
import com.gmail.wojtass.michal.services.AccountBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
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
		AccountBank selectedAccountFromTreeSet = null;
		double giverUserAccountValue = 0;
		for (AccountBank account : giverUser.getAccountsBank()) {
			if (account.getAccountBankId() == selectedAccountForId) {
				selectedAccountFromTreeSet = account;
				break;
			}
		}
		long recipientId = accountBankRepository.findUserIdByAccountNumber(transaction.getRecipientAccountNumber());
		AccountBank recipientUserSelectedAccountFromTreeSet = null;
		for (AccountBank account : transaction.getRecipientUser().getAccountsBank()) {
			if (account.getAccountBankId() == recipientId) {
				recipientUserSelectedAccountFromTreeSet = account;
				break;
			}
		}
		AccountBank.AccountType typeOfAccountGiverUser = selectedAccountFromTreeSet.getAccountType();
		AccountBank.AccountType typeOfAccountRecipientUser = recipientUserSelectedAccountFromTreeSet.getAccountType();
		if (selectedAccount != null) {
			giverUserAccountValue = selectedAccountFromTreeSet.getAccountValue();
		}
		if (giverUserAccountValue < transaction.getAmountTransaction()){
			bindingResult.rejectValue("amountTransaction","error_code","You don't have enough funds in your account.");
			return "bank2";
		}
		transferValue(transaction,giverUser,transaction.getRecipientUser(),selectedAccountForId);
		transaction.setGiverAccountNumber(selectedAccountFromTreeSet.getAccountNumber());
		accountManagement.updateAllAccountValuesToGiverUserAfterTransaction(giverUser,transaction.getAmountTransaction(),typeOfAccountGiverUser);
		accountManagement.updateAllAccountValuesToRecipientUserAfterTransaction(transaction.getRecipientUser(),transaction.getAmountTransaction(),typeOfAccountRecipientUser);
		repo.save(transaction);
		
		return "redirect:bank";
		}
	}
	
	@GetMapping(value="/bank2")
	public String getBank2(Model model,@SessionAttribute("loggedUser") User user) {
		Set<AccountBank> list = user.getAccountsBank();
		List<AccountBank> list1 = new ArrayList<>(list);
		list1.sort(new Comparator<AccountBank>() {
			@Override
			public int compare(AccountBank o1, AccountBank o2) {
				return Long.compare(o1.getAccountBankId(),o2.getAccountBankId());
			}
		});
		model.addAttribute("transaction", new Transaction());
		model.addAttribute("loggedUserSortedList",list1);
		return "bank2";
	}
	
	
	
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public void transferValue(Transaction transaction,User giverUser,User recipientUser, int selectedAccountForId) {
		double value = transaction.getAmountTransaction();
		long recipientId = accountBankRepository.findUserIdByAccountNumber(transaction.getRecipientAccountNumber());

		long selectedAccountForId1 = selectedAccountForId;
		AccountBank giverUserSelectedAccountFromTreeSet = null;
		for (AccountBank account : giverUser.getAccountsBank()) {
			if (account.getAccountBankId() == selectedAccountForId1) {
				giverUserSelectedAccountFromTreeSet = account;
				break;
			}
		}


		AccountBank recipientUserSelectedAccountFromTreeSet = null;
		for (AccountBank account : recipientUser.getAccountsBank()) {
			if (account.getAccountBankId() == recipientId) {
				recipientUserSelectedAccountFromTreeSet = account;
				break;
			}
		}
		double giverValue = giverUserSelectedAccountFromTreeSet.getAccountValue();
		double recipientValue = recipientUserSelectedAccountFromTreeSet.getAccountValue();
		double tempLimitTransactionForDay = giverUser.getTempLimitTransactionForDay();
		double tempLimitTransactionForMonth = giverUser.getTempLimitTransactionForMonth();
		boolean isDayLimitDiffThanZero = giverUser.getLimitTransactionForDay() != 0;
		boolean isMonthLimitDiffThanZero = giverUser.getLimitTransactionForMonth() != 0;
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
		try {
			accountBankRepository.updateAccountValue(giverValue,giverUserSelectedAccountFromTreeSet.getAccountBankId());
			accountBankRepository.updateAccountValue(recipientValue,recipientUserSelectedAccountFromTreeSet.getAccountBankId());
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
