package com.gmail.wojtass.michal.controllers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmail.wojtass.michal.components.AccountManagement;
import com.gmail.wojtass.michal.model.AccountBank;
import com.gmail.wojtass.michal.otherMethods.WelcomeTextGenerator;
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

import com.gmail.wojtass.michal.model.User;
import com.gmail.wojtass.michal.services.UserRepository;

@Controller
@SessionAttributes("loggedUser")
public class BankController {

	@Autowired
	UserRepository repo;

	@Autowired
	AccountBankRepository accountBankRepository;

	@Autowired
	AccountManagement accountManagement;


	/* //If token will be required, made when problems with AJAX
	@Autowired
	private CsrfTokenRepository csrfTokenRepository;

	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return csrfTokenRepository.loadToken(request);
	}
	 */
	private User getUser2(){
		Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
		String username2 = auth2.getName();
		User user2 = repo.findByUsername(username2);
		return user2;
	}
	
	@Transactional(readOnly = false)
	@PostMapping(value = "/bank",params = "addValue")
	public String postBank(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, @RequestParam("selectedAccount") long selectedAccount) {
		User loggedUser = getUser2();
		double tmpValue = user.getTmpValue();
		long selectedAccountForId = selectedAccount;
		AccountBank selectedAccountFromTreeSet = null;
		for (AccountBank account : loggedUser.getAccountsBank()) {
			if (account.getAccountBankId() == selectedAccountForId) {
				selectedAccountFromTreeSet = account;
				break;
			}
		}
		AccountBank.AccountType typeOfAccount = selectedAccountFromTreeSet.getAccountType();

		double accountValue = selectedAccountFromTreeSet.getAccountValue() + tmpValue;
		double ac = BigDecimal.valueOf(accountValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
		accountManagement.updateAccountValue(ac,selectedAccountFromTreeSet.getAccountBankId());
		accountManagement.updateAllAccountValuesToUser(loggedUser,tmpValue,typeOfAccount);
		return "redirect:addValueSuccess";
	}
	
	@GetMapping(value = "/bank")
	public String getBank(Model model, @ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) {
		User loggedUser = getUser2();
		HttpSession session = request.getSession();
		WelcomeTextGenerator welcomeTextGenerator = new WelcomeTextGenerator();
		Set<AccountBank> list = loggedUser.getAccountsBank();
		List<AccountBank> list1 = new ArrayList<>(list);
		list1.sort(new Comparator<AccountBank>() {
			@Override
			public int compare(AccountBank o1, AccountBank o2) {
				return Long.compare(o1.getAccountBankId(),o2.getAccountBankId());
			}
		});
		session.setAttribute("welcomeText",welcomeTextGenerator.generate());
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("user", user);
		model.addAttribute("loggedUserSortedList",list1);
		return "bank";
	}

}
