package com.gmail.wojtass.michal.components;

import com.gmail.wojtass.michal.model.*;
import com.gmail.wojtass.michal.services.AccountBankRepository;
import com.gmail.wojtass.michal.services.DepositPayoutRepository;
import com.gmail.wojtass.michal.services.TransactionRepository;
import com.gmail.wojtass.michal.services.UserRepository;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Component
public class AccountManagement {

    @Autowired
    UserRepository repo;

    @Autowired
    AccountBankRepository accountBankRepository;

    @Autowired
    DepositPayoutRepository depositPayoutRepository;

    @Autowired
    TransactionRepository transactionRepository;

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
            accountBank.setAccountType(AccountBank.AccountType.STANDARD);
            user.getAccountsBank().add(accountBank);
            accountBank.setUser(user);
            accountBankRepository.save(accountBank);
            repo.save(user);
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
            user.getAccountsBank().add(accountBank);
        }else {
            bindingResult.rejectValue("addAccountSuccessful","error_code","You have reached your standard account limit");
        }
    }

    /**
     * Only for examples when creating db
     * @param user
     * @param accountBank
     */
    @Transactional
    public void addNewStandardAccountForExamples(User user, AccountBank accountBank){
        if (user.getActualNumberOfStandardAccounts() < User.MAX_STANDARD_ACCOUNTS){
            accountBank.setAccountNumber(generateAccountNumber());
            user.setActualNumberOfStandardAccounts(user.getActualNumberOfStandardAccounts()+1);
            accountBank.setAccountType(AccountBank.AccountType.STANDARD);
            user.getAccountsBank().add(accountBank);
            accountBank.setUser(user);
            accountBankRepository.save(accountBank);
            repo.save(user);
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
            user.getAccountsBank().add(accountBank);
        }else {
            bindingResult.rejectValue("addAccountSuccessful","error_code","You have reached your saving account limit");
        }
    }

    /**
     * Only for examples when creating db
     * @param user
     * @param accountBank
     */
    @Transactional
    public void addNewSavingAccountForExamples(User user, AccountBank accountBank){
        if (user.getActualNumberOfSavingAccounts() < User.MAX_SAVING_ACCOUNTS){
            accountBank.setAccountNumber(generateAccountNumber());
            user.setActualNumberOfSavingAccounts(user.getActualNumberOfSavingAccounts()+1);
            accountBank.setAccountType(AccountBank.AccountType.SAVING);
            user.getAccountsBank().add(accountBank);
            accountBank.setUser(user);
            accountBankRepository.save(accountBank);
            repo.save(user);
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
     * It update value of all accounts belonging to the user. Its for sum of all saving/standard account values for display.
     * I wanted to make it without second value param, but it takes not updated value from Account Bank, before actually added value
     * So i add this param, to insert value for change in actually operation
     * @param user User
     * @param value double
     */
    @Transactional
    public void updateAllAccountValuesToUser(User user, double value, AccountBank.AccountType typeOfAccount){
        double tmpStandardValue = value;
        double tmpSavingValue = value;
        for (AccountBank accountBank :user.getAccountsBank()) {
            value += accountBank.getAccountValue();
            if (typeOfAccount == AccountBank.AccountType.STANDARD && accountBank.getAccountType() == typeOfAccount){
                tmpStandardValue += accountBank.getAccountValue();
            }
            if(accountBank.getAccountType() == AccountBank.AccountType.SAVING && accountBank.getAccountType() == typeOfAccount){
                tmpSavingValue += accountBank.getAccountValue();
            }
        }
        user.setAllAccountsValue(value);
        if (typeOfAccount == AccountBank.AccountType.STANDARD) {
            user.setAllStandardAccountsValue(tmpStandardValue);
        }else if (typeOfAccount == AccountBank.AccountType.SAVING) {
            user.setAllSavingAccountsValue(tmpSavingValue);
        }

        repo.saveAndFlush(user);
    }

    @Transactional
    public void updateAllAccountValuesToGiverUserAfterTransaction(User user, double value, AccountBank.AccountType typeOfAccount){
        value = (-2*value)+value;
        double tmpStandardValue = (-2*value)+value;
        double tmpSavingValue = (-2*value)+value;
        //I can also make version where first we have if and check its that typeOfAccount Standard or Saving, and each if contains for, i guess can be faster, but will have more code, same for others method this type
        for (AccountBank accountBank :user.getAccountsBank()) {
            value += accountBank.getAccountValue();
            if (typeOfAccount == AccountBank.AccountType.STANDARD && accountBank.getAccountType() == typeOfAccount){
                tmpStandardValue += accountBank.getAccountValue();
            }
            if(accountBank.getAccountType() == AccountBank.AccountType.SAVING && accountBank.getAccountType() == typeOfAccount){
                tmpSavingValue += accountBank.getAccountValue();
            }
        }
        user.setAllAccountsValue(value);
        if (typeOfAccount == AccountBank.AccountType.STANDARD) {
            user.setAllStandardAccountsValue(tmpStandardValue);
        }else if (typeOfAccount == AccountBank.AccountType.SAVING) {
            user.setAllSavingAccountsValue(tmpSavingValue);
        }
        repo.save(user);
    }

    @Transactional
    public void updateAllAccountValuesToRecipientUserAfterTransaction(User user, double value, AccountBank.AccountType typeOfAccount){
        double tmpStandardValue = value;
        double tmpSavingValue = value;
        for (AccountBank accountBank :user.getAccountsBank()) {
            value += accountBank.getAccountValue();
            if (typeOfAccount == AccountBank.AccountType.STANDARD && accountBank.getAccountType() == typeOfAccount){
                tmpStandardValue += accountBank.getAccountValue();
            }
            if(accountBank.getAccountType() == AccountBank.AccountType.SAVING && accountBank.getAccountType() == typeOfAccount){
                tmpSavingValue += accountBank.getAccountValue();
            }
        }
        user.setAllAccountsValue(value);
        if (typeOfAccount == AccountBank.AccountType.STANDARD) {
            user.setAllStandardAccountsValue(tmpStandardValue);
        }else if (typeOfAccount == AccountBank.AccountType.SAVING) {
            user.setAllSavingAccountsValue(tmpSavingValue);
        }
        repo.save(user);
    }

    public void createNewDepositPayout(double tmpValue, double value, String nameAndLocationATM, User loggedUser, AccountBank selectedAccountFromTreeSet, DepositPayout.DepositType depositType){
        DepositPayout depositPayout = new DepositPayout();
        depositPayout.setDepositDate(LocalDateTime.now());
        depositPayout.setDepositValue(tmpValue);
        depositPayout.setDepositValueAfterChange(value);
        depositPayout.setNameAndLocationATM(nameAndLocationATM);
        depositPayout.setUser(loggedUser);
        depositPayout.setAccountBank(selectedAccountFromTreeSet);
        depositPayout.setDepositType(depositType);
        depositPayoutRepository.save(depositPayout);
        depositPayout.setSecondId(depositPayout.getDepositId());
        depositPayoutRepository.save(depositPayout);
    }

    /**
     * Only for Database examples, but different is one, we can set LocalDateTime manually
     * @param localDateTime
     * @param tmpValue
     * @param value
     * @param nameAndLocationATM
     * @param loggedUser
     * @param selectedAccountFromTreeSet
     * @param depositType
     */
    @Transactional
    public void createNewDepositPayoutForExamples(LocalDateTime localDateTime,double tmpValue, double value, String nameAndLocationATM, User loggedUser,
                                                  AccountBank selectedAccountFromTreeSet, DepositPayout.DepositType depositType){
        DepositPayout depositPayout = new DepositPayout();
        depositPayout.setDepositDate(localDateTime);
        depositPayout.setDepositValue(tmpValue);
        depositPayout.setDepositValueAfterChange(value);
        depositPayout.setNameAndLocationATM(nameAndLocationATM);
        depositPayout.setUser(loggedUser);
        depositPayout.setAccountBank(selectedAccountFromTreeSet);
        depositPayout.setDepositType(depositType);
        depositPayoutRepository.saveAndFlush(depositPayout);
        depositPayout.setSecondId(depositPayout.getDepositId());
        depositPayoutRepository.saveAndFlush(depositPayout);


    }

    @Transactional
    public void updateAccountValueForExamples(double accountValue, AccountBank accountBank) {
        accountBank.setAccountValue(accountValue);
        accountBankRepository.save(accountBank);
    }

    @Transactional
    public void updateAllAccountValuesToGiverUserAfterTransactionForExamples(User user, double value, AccountBank.AccountType typeOfAccount){
        value = (-2*value)+value;
        double tmpStandardValue = (-2*value)+value;
        double tmpSavingValue = (-2*value)+value;
        //I can also make version where first we have if and check its that typeOfAccount Standard or Saving, and each if contains for, i guess can be faster, but will have more code, same for others method this type
        for (AccountBank accountBank :user.getAccountsBank()) {
            value += accountBank.getAccountValue();
            if (typeOfAccount == AccountBank.AccountType.STANDARD && accountBank.getAccountType() == typeOfAccount){
                tmpStandardValue += accountBank.getAccountValue();
            }
            if(accountBank.getAccountType() == AccountBank.AccountType.SAVING && accountBank.getAccountType() == typeOfAccount){
                tmpSavingValue += accountBank.getAccountValue();
            }
        }
        user.setAllAccountsValue(value);
        if (typeOfAccount == AccountBank.AccountType.STANDARD) {
            user.setAllStandardAccountsValue(tmpStandardValue);
        }else if (typeOfAccount == AccountBank.AccountType.SAVING) {
            user.setAllSavingAccountsValue(tmpSavingValue);
        }
        repo.save(user);
    }

    @Transactional
    public void updateAllAccountValuesToRecipientUserAfterTransactionForExamples(User user, double value, AccountBank.AccountType typeOfAccount){
        double tmpStandardValue = value;
        double tmpSavingValue = value;
        for (AccountBank accountBank :user.getAccountsBank()) {
            value += accountBank.getAccountValue();
            if (typeOfAccount == AccountBank.AccountType.STANDARD && accountBank.getAccountType() == typeOfAccount){
                tmpStandardValue += accountBank.getAccountValue();
            }
            if(accountBank.getAccountType() == AccountBank.AccountType.SAVING && accountBank.getAccountType() == typeOfAccount){
                tmpSavingValue += accountBank.getAccountValue();
            }
        }
        user.setAllAccountsValue(value);
        if (typeOfAccount == AccountBank.AccountType.STANDARD) {
            user.setAllStandardAccountsValue(tmpStandardValue);
        }else if (typeOfAccount == AccountBank.AccountType.SAVING) {
            user.setAllSavingAccountsValue(tmpSavingValue);
        }
        repo.save(user);
    }

    @Transactional
    public void updateDateTransactionByTransactionIdAndDateTransaction(long transactionId,LocalDateTime localDateTime){
        transactionRepository.updateDateTransactionByTransactionIdAndDateTransaction(transactionId,localDateTime);
    }

    @Transactional
    public void updateAllAccountValuesToUserForExample(User user, double valuez, AccountBank.AccountType typeOfAccount){

        double value = valuez;
        double tmpStandardValue = value;
        double tmpSavingValue = value;
        for (AccountBank accountBank :user.getAccountsBank()) {
            value += accountBank.getAccountValue();
            if (typeOfAccount == AccountBank.AccountType.STANDARD && accountBank.getAccountType() == typeOfAccount){
                tmpStandardValue += accountBank.getAccountValue();
            }
            if(accountBank.getAccountType() == AccountBank.AccountType.SAVING && accountBank.getAccountType() == typeOfAccount){
                tmpSavingValue += accountBank.getAccountValue();
            }
        }
        user.setAllAccountsValue(value);
        if (typeOfAccount == AccountBank.AccountType.STANDARD) {
            user.setAllStandardAccountsValue(tmpStandardValue);
        }else if (typeOfAccount == AccountBank.AccountType.SAVING) {
            user.setAllSavingAccountsValue(tmpSavingValue);
        }

        repo.saveAndFlush(user);
    }

}
