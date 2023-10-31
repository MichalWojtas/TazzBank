package com.gmail.wojtass.michal.components;

import com.gmail.wojtass.michal.model.AccountBank;
import com.gmail.wojtass.michal.model.User;
import com.gmail.wojtass.michal.services.AccountBankRepository;
import com.gmail.wojtass.michal.services.UserRepository;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.math.BigInteger;
import java.util.*;

@Component
public class AccountManagement {

    @Autowired
    UserRepository repo;

    @Autowired
    AccountBankRepository accountBankRepository;

    /**
     * For registration.
     * Method for adding new standard account if user need more than 1, there is max set as MAX_STANDARD_ACCOUNTS in User
     * Transactional added because if something goes wrong we don't want to change actualNumberOfStandardAccounts value and don't want to create new accountNumber
     * @param user User, user who is logged in application
     * @return boolean means true successful add, false means there was too much standard accounts created
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public boolean addNewStandardAccount(User user){
        if (user.getActualNumberOfStandardAccounts() < User.MAX_STANDARD_ACCOUNTS){
            AccountBank accountBank = new AccountBank(user,generateAccountNumber(), AccountBank.AccountType.STANDARD);
            user.setActualNumberOfStandardAccounts(user.getActualNumberOfStandardAccounts()+1);
            accountBank.setAccountName("Account 1");
            repo.save(user);
            accountBankRepository.save(accountBank);
            return true;
        }
        return false;
    }

    /**
     * For create next account in SettingController, addAccountForm.
     * Method for adding new standard account if user need more than 1, there is max set as MAX_STANDARD_ACCOUNTS in User
     * Transactional added because if something goes wrong we don't want to change actualNumberOfStandardAccounts value and don't want to create new accountNumber
     * @param user User, user who is logged in application
     */
    public void addNewStandardAccount(User user, AccountBank accountBank, BindingResult bindingResult){
        if (user.getActualNumberOfStandardAccounts() < User.MAX_STANDARD_ACCOUNTS){
            accountBank.setUser(user);
            accountBank.setAccountNumber(generateAccountNumber());
            user.setActualNumberOfStandardAccounts(user.getActualNumberOfStandardAccounts()+1);
        }else {
            bindingResult.rejectValue("addAccountSuccessful","error_code","You have reached your standard account limit");
        }
    }

    /**
     * For create next account in SettingController, addAccountForm.
     * Method for adding new saving account if user need more than 1, there is max set as MAX_SAVING_ACCOUNTS in User
     * Transactional added because if something goes wrong we don't want to change actualNumberOfSavingAccounts value and don't want to create new accountNumber
     * @param user User, user who is logged in application
     */
    public void addNewSavingAccount(User user, AccountBank accountBank, BindingResult bindingResult){
        if (user.getActualNumberOfSavingAccounts() < User.MAX_SAVING_ACCOUNTS){
            accountBank.setUser(user);
            accountBank.setAccountNumber(generateAccountNumber());
            user.setActualNumberOfSavingAccounts(user.getActualNumberOfSavingAccounts()+1);
        }else {
            bindingResult.rejectValue("addAccountSuccessful","error_code","You have reached your saving account limit");
        }
    }

    /**
     * Method to generate accountNumber its based on largest id in database, largest should be last added and lowest account number, if we change something manual we can ruin it
     * As i remember 2 first digits means bank number
     * As i remember catch with this exception means there is no id, then we create first account by it.
     * @return String as new account number
     */
    public String generateAccountNumber(){
        String accountNumberString;
        try {
            long id = accountBankRepository.findMaxAccountBankId();
            AccountBank accountBankId = accountBankRepository.findByAccountBankId(id);
            String accountNumber = accountBankId.getAccountNumber();
            BigInteger accountNumberBigInteger = new BigInteger(accountNumber);
            accountNumberBigInteger = accountNumberBigInteger.add(BigInteger.ONE);
            accountNumberString = accountNumberBigInteger.toString();
        }catch (AopInvocationException e){
            accountNumberString = "99111111111111111111111111";
        }
        return accountNumberString;
    }

    @Transactional
    public void updateAccountValue(double accountValue, long accountBankId) {
        accountBankRepository.updateAccountValue(accountValue, accountBankId);
        accountBankRepository.flush();
    }

    /**
     * It update value of all accounts belonging to the user
     * I wanted to make it without second value param, but it takes not updated value from Account Bank, before actually added value
     * So i add this param, to insert value for change in actually operation
     * @param user User
     * @param value double
     */
    @Transactional
    public void updateAllAccountValuesToUser(User user, double value){
        for (AccountBank accountBank :user.getAccountsBank()) {
            value += accountBank.getAccountValue();
        }
        user.setAllAccountsValue(value);
        repo.saveAndFlush(user);
    }

    @Transactional
    public void updateAllAccountValuesToGiverUserAfterTransaction(User user, double value){
        value = (-2*value)+value;
        for (AccountBank accountBank :user.getAccountsBank()) {
            value += accountBank.getAccountValue();
        }
        user.setAllAccountsValue(value);
    }

    @Transactional
    public void updateAllAccountValuesToRecipientUserAfterTransaction(User user, double value){
        for (AccountBank accountBank :user.getAccountsBank()) {
            value += accountBank.getAccountValue();
        }
        user.setAllAccountsValue(value);
    }

    public void sss(TreeSet<AccountBank> list, User loggedUser){
        Set<AccountBank> accountsBank = loggedUser.getAccountsBank();
        List<AccountBank> list1 = new ArrayList<>(list);
        list1.sort(new Comparator<AccountBank>() {
            @Override
            public int compare(AccountBank o1, AccountBank o2) {
                return Long.compare(o1.getAccountBankId(),o2.getAccountBankId());
            }
        });
    }
}
