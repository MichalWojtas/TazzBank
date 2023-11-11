package com.gmail.wojtass.michal.data;

import com.gmail.wojtass.michal.components.AccountManagement;
import com.gmail.wojtass.michal.model.AccountBank;
import com.gmail.wojtass.michal.model.DepositPayout;
import com.gmail.wojtass.michal.model.Transaction;
import com.gmail.wojtass.michal.model.User;
import com.gmail.wojtass.michal.services.AccountBankRepository;
import com.gmail.wojtass.michal.services.DepositPayoutRepository;
import com.gmail.wojtass.michal.services.TransactionRepository;
import com.gmail.wojtass.michal.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.Month;



@Component
public class ExampleDataForDatabase {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountBankRepository accountBankRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    DepositPayoutRepository depositPayoutRepository;

    @Autowired
    AccountManagement accountManagement;

    /**
     * If database is empty, create in home if you want - uncomment
     */
    public void createExampleDB(){
        //
        //New users create, first account is created with user
        //
        //AccountManagement accountManagement = new AccountManagement();
        User user = new User();
        user.setUsername("Michal");
        user.setB4encryptPassword("qQ123456");
        user.setPassword(user.bcryptPasswordForExamples(user.getB4encryptPassword()));
        user.setEmail("michal.wojtass@gmail.com");
        user.setFirstName("Michal");
        user.setLastName("Wojtas");
        user.setPesel("98111233123");
        user.setPhoneNumber("555902133");
        user.setAddress("Urzednicza 6 Kielce");
        user.setAddressForCorrespondence("Urzednicza 6 Kielce");
        accountManagement.addNewStandardAccount(user);
        User user2 = new User();
        user2.setUsername("Michal2");
        user2.setB4encryptPassword("qQ123456");
        user2.setPassword(user2.bcryptPasswordForExamples(user2.getB4encryptPassword()));
        user2.setEmail("michal.wojtass2@gmail.com");
        user2.setFirstName("Michal");
        user2.setLastName("Wojtas");
        user2.setPesel("98111233123");
        user2.setPhoneNumber("555902133");
        user2.setAddress("Urzednicza 6 Kielce");
        user2.setAddressForCorrespondence("Urzednicza 6 Kielce");
        accountManagement.addNewStandardAccount(user2);
        User user3 = new User();
        user3.setUsername("Jakub1");
        user3.setB4encryptPassword("qQ123456");
        user3.setPassword(user3.bcryptPasswordForExamples(user3.getB4encryptPassword()));
        user3.setEmail("jakub.bak@gmail.com");
        user3.setFirstName("Jakub");
        user3.setLastName("Bak");
        user3.setPesel("98111233123");
        user3.setPhoneNumber("555902133");
        user3.setAddress("Urzednicza 6 Warszawa");
        user3.setAddressForCorrespondence("Urzednicza 6 Warszawa");
        accountManagement.addNewStandardAccount(user3);
        User user4 = new User();
        user4.setUsername("Robert");
        user4.setB4encryptPassword("qQ123456");
        user4.setPassword(user4.bcryptPasswordForExamples(user4.getB4encryptPassword()));
        user4.setEmail("robert.zakrzewski@gmail.com");
        user4.setFirstName("Robert");
        user4.setLastName("Zakrzewski");
        user4.setPesel("98111233123");
        user4.setPhoneNumber("555902133");
        user4.setAddress("Urzednicza 6 Kraków");
        user4.setAddressForCorrespondence("Urzednicza 6 Kraków");
        //
        //Additional accounts create for users
        //
        accountManagement.addNewStandardAccount(user4);
        AccountBank accountBankUser = new AccountBank();
        accountBankUser.setAccountName("Second standard");
        accountBankUser.setAccountType(AccountBank.AccountType.STANDARD);
        accountManagement.addNewStandardAccountForExamples(user,accountBankUser);
        AccountBank accountBank2User = new AccountBank();
        accountBank2User.setAccountName("Third standard");
        accountBank2User.setAccountType(AccountBank.AccountType.STANDARD);
        accountManagement.addNewStandardAccountForExamples(user,accountBank2User);
        AccountBank accountBank3User = new AccountBank();
        accountBank3User.setAccountName("Fourth standard");
        accountBank3User.setAccountType(AccountBank.AccountType.STANDARD);
        accountManagement.addNewStandardAccountForExamples(user,accountBank3User);
        AccountBank accountBank4User = new AccountBank();
        accountBank4User.setAccountName("First saving");
        accountBank4User.setAccountType(AccountBank.AccountType.SAVING);
        accountManagement.addNewSavingAccountForExamples(user,accountBank4User);
        AccountBank accountBank5User = new AccountBank();
        accountBank5User.setAccountName("Second saving");
        accountBank5User.setAccountType(AccountBank.AccountType.SAVING);
        accountManagement.addNewSavingAccountForExamples(user,accountBank5User);
        AccountBank accountBank6User = new AccountBank();
        accountBank6User.setAccountName("Third saving");
        accountBank6User.setAccountType(AccountBank.AccountType.SAVING);
        accountManagement.addNewSavingAccountForExamples(user,accountBank6User);
        AccountBank accountBank7User = new AccountBank();
        accountBank7User.setAccountName("Fourth saving");
        accountBank7User.setAccountType(AccountBank.AccountType.SAVING);
        accountManagement.addNewSavingAccountForExamples(user,accountBank7User);
        AccountBank accountBankUser2 = new AccountBank();
        accountBankUser2.setAccountName("Second standard");
        accountBankUser2.setAccountType(AccountBank.AccountType.STANDARD);
        accountManagement.addNewStandardAccountForExamples(user2,accountBankUser2);
        AccountBank accountBank2User2 = new AccountBank();
        accountBank2User2.setAccountName("Third standard");
        accountBank2User2.setAccountType(AccountBank.AccountType.STANDARD);
        accountManagement.addNewStandardAccountForExamples(user2,accountBank2User2);
        AccountBank accountBank3User2 = new AccountBank();
        accountBank3User2.setAccountName("Fourth standard");
        accountBank3User2.setAccountType(AccountBank.AccountType.STANDARD);
        accountManagement.addNewStandardAccountForExamples(user2,accountBank3User2);
        AccountBank accountBank4User2 = new AccountBank();
        accountBank4User2.setAccountName("First saving");
        accountBank4User2.setAccountType(AccountBank.AccountType.SAVING);
        accountManagement.addNewSavingAccountForExamples(user2,accountBank4User2);
        AccountBank accountBank5User2 = new AccountBank();
        accountBank5User2.setAccountName("Second saving");
        accountBank5User2.setAccountType(AccountBank.AccountType.SAVING);
        accountManagement.addNewSavingAccountForExamples(user2,accountBank5User2);
        AccountBank accountBankUser3 = new AccountBank();
        accountBankUser3.setAccountName("Second standard");
        accountBankUser3.setAccountType(AccountBank.AccountType.STANDARD);
        accountManagement.addNewStandardAccountForExamples(user3,accountBankUser3);
        AccountBank accountBank2User3 = new AccountBank();
        accountBank2User3.setAccountName("First saving");
        accountBank2User3.setAccountType(AccountBank.AccountType.SAVING);
        accountManagement.addNewSavingAccountForExamples(user3,accountBank2User3);
        //
        //Deposits
        //
        //Some accounts have added 10k always year ago: user - first standard account, user - second standard account, user2 - first standard, user2 - second standard
        //For first account standard of user 16000 added
        double deposit = 200D;
        System.out.println("User id check: " + user.getUserId());
        long firstIdAccountBankUser = accountBankRepository.findMinAccountBankIdByUserId(user.getUserId());
        AccountBank firstAccountBankUser = accountBankRepository.findByAccountBankId(firstIdAccountBankUser);
        accountManagement.updateAccountValueForExamples(firstAccountBankUser.getAccountValue() + deposit,firstAccountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,firstAccountBankUser.getAccountType());
        LocalDateTime localDateTime = LocalDateTime.of(2023, Month.NOVEMBER,9,9,17,12,0);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(firstAccountBankUser.getAccountValue() + deposit).
                        setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,firstAccountBankUser, DepositPayout.DepositType.DEPOSIT);
        deposit = 150D;
        accountManagement.updateAccountValueForExamples(firstAccountBankUser.getAccountValue() + deposit,firstAccountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,firstAccountBankUser.getAccountType());
        localDateTime = LocalDateTime.now();
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(firstAccountBankUser.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,firstAccountBankUser, DepositPayout.DepositType.DEPOSIT);
        deposit = 50D;
        accountManagement.updateAccountValueForExamples(firstAccountBankUser.getAccountValue() + deposit,firstAccountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,firstAccountBankUser.getAccountType());
        localDateTime = LocalDateTime.now().minusMonths(1);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(firstAccountBankUser.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,firstAccountBankUser, DepositPayout.DepositType.DEPOSIT);
        deposit = 10000D;
        accountManagement.updateAccountValueForExamples(firstAccountBankUser.getAccountValue() + deposit,firstAccountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,firstAccountBankUser.getAccountType());
        localDateTime = LocalDateTime.now().minusYears(1).minusMonths(1);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(firstAccountBankUser.getAccountValue()+deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,firstAccountBankUser, DepositPayout.DepositType.DEPOSIT);
        deposit = 600D;
        accountManagement.updateAccountValueForExamples(firstAccountBankUser.getAccountValue() + deposit,firstAccountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,firstAccountBankUser.getAccountType());
        localDateTime = LocalDateTime.now().minusDays(3);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(firstAccountBankUser.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,firstAccountBankUser, DepositPayout.DepositType.DEPOSIT);
        deposit = 4000D;
        accountManagement.updateAccountValueForExamples(firstAccountBankUser.getAccountValue() + deposit,firstAccountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,firstAccountBankUser.getAccountType());
        localDateTime = LocalDateTime.now().minusMonths(2);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(firstAccountBankUser.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,firstAccountBankUser, DepositPayout.DepositType.DEPOSIT);
        deposit = 1000D;
        accountManagement.updateAccountValueForExamples(firstAccountBankUser.getAccountValue() + deposit,firstAccountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,firstAccountBankUser.getAccountType());
        localDateTime = LocalDateTime.now().minusYears(1).minusMonths(1);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(firstAccountBankUser.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,firstAccountBankUser, DepositPayout.DepositType.DEPOSIT);
        //For second account standard of user 11000 added
        deposit = 10000D;
        accountManagement.updateAccountValueForExamples(accountBankUser.getAccountValue() + deposit,accountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,accountBankUser.getAccountType());
        localDateTime = LocalDateTime.now().minusYears(1).minusMonths(1);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(accountBankUser.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,accountBankUser, DepositPayout.DepositType.DEPOSIT);
        deposit = 100D;
        accountManagement.updateAccountValueForExamples(accountBankUser.getAccountValue() + deposit,accountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,accountBankUser.getAccountType());
        localDateTime = LocalDateTime.now().minusMinutes(5);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(accountBankUser.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,accountBankUser, DepositPayout.DepositType.DEPOSIT);
        deposit = 100D;
        accountManagement.updateAccountValueForExamples(accountBankUser.getAccountValue() + deposit,accountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,accountBankUser.getAccountType());
        localDateTime = LocalDateTime.now().minusMinutes(6).minusSeconds(15);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(accountBankUser.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,accountBankUser, DepositPayout.DepositType.DEPOSIT);
        deposit = 300D;
        accountManagement.updateAccountValueForExamples(accountBankUser.getAccountValue() + deposit,accountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,accountBankUser.getAccountType());
        localDateTime = LocalDateTime.now().minusDays(13);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(accountBankUser.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,accountBankUser, DepositPayout.DepositType.DEPOSIT);
        deposit = 500D;
        accountManagement.updateAccountValueForExamples(accountBankUser.getAccountValue() + deposit,accountBankUser);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,accountBankUser.getAccountType());
        localDateTime = LocalDateTime.now().minusMonths(2);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(accountBankUser.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,accountBankUser, DepositPayout.DepositType.DEPOSIT);
        //For first saving account of user  6000 added
        deposit = 5000D;
        accountManagement.updateAccountValueForExamples(accountBank4User.getAccountValue() + deposit,accountBank4User);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,accountBank4User.getAccountType());
        localDateTime = LocalDateTime.now().minusYears(1).minusDays(1);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(accountBank4User.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,accountBank4User, DepositPayout.DepositType.DEPOSIT);
        deposit = 1000D;
        accountManagement.updateAccountValueForExamples(accountBank4User.getAccountValue() + deposit,accountBank4User);
        accountManagement.updateAllAccountValuesToUserForExample(user,deposit,accountBank4User.getAccountType());
        localDateTime = LocalDateTime.now();
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(accountBank4User.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user,accountBank4User, DepositPayout.DepositType.DEPOSIT);
        //For first standard account of user2 10850 added
        long firstIdAccountBankUser2 = accountBankRepository.findMinAccountBankIdByUserId(user2.getUserId());
        AccountBank firstAccountBankUser2 = accountBankRepository.findByAccountBankId(firstIdAccountBankUser2);
        deposit = 10000D;
        accountManagement.updateAccountValueForExamples(firstAccountBankUser2.getAccountValue() + deposit,firstAccountBankUser2);
        accountManagement.updateAllAccountValuesToUserForExample(user2,deposit,firstAccountBankUser2.getAccountType());
        localDateTime = LocalDateTime.now().minusYears(1).minusMonths(4);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(firstAccountBankUser2.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user2,firstAccountBankUser2, DepositPayout.DepositType.DEPOSIT);
        deposit = 500D;
        accountManagement.updateAccountValueForExamples(firstAccountBankUser2.getAccountValue() + deposit,firstAccountBankUser2);
        accountManagement.updateAllAccountValuesToUserForExample(user2,deposit,firstAccountBankUser2.getAccountType());
        localDateTime = LocalDateTime.now();
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(firstAccountBankUser2.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user2,firstAccountBankUser2, DepositPayout.DepositType.DEPOSIT);
        deposit = 150D;
        accountManagement.updateAccountValueForExamples(firstAccountBankUser2.getAccountValue() + deposit,firstAccountBankUser2);
        accountManagement.updateAllAccountValuesToUserForExample(user2,deposit,firstAccountBankUser2.getAccountType());
        localDateTime = LocalDateTime.now().minusSeconds(30);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(firstAccountBankUser2.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user2,firstAccountBankUser2, DepositPayout.DepositType.DEPOSIT);
        deposit = 200D;
        accountManagement.updateAccountValueForExamples(firstAccountBankUser2.getAccountValue() + deposit,firstAccountBankUser2);
        accountManagement.updateAllAccountValuesToUserForExample(user2,deposit,firstAccountBankUser2.getAccountType());
        localDateTime = LocalDateTime.now().minusMinutes(3);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(firstAccountBankUser2.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user2,firstAccountBankUser2, DepositPayout.DepositType.DEPOSIT);
        //For second standard account of user2 10k
        deposit = 10000D;
        accountManagement.updateAccountValueForExamples(accountBankUser2.getAccountValue() + deposit,accountBankUser2);
        accountManagement.updateAllAccountValuesToUserForExample(user2,deposit,accountBankUser2.getAccountType());
        localDateTime = LocalDateTime.now().minusYears(1).minusMonths(3);
        accountManagement.createNewDepositPayoutForExamples(localDateTime,deposit, BigDecimal.valueOf(accountBankUser2.getAccountValue() + deposit).
                setScale(2, RoundingMode.HALF_UP).doubleValue(),"DEFAULT",user2,accountBankUser2, DepositPayout.DepositType.DEPOSIT);
        //
        //Transactions
        //
        //User transactions
        //From user standard1 to user2 standard1
        //1700 from user standard1 to user2 standard1
        Transaction transaction = new Transaction();
        transaction.setRecipientAccountNumber(firstAccountBankUser2.getAccountNumber());
        transaction.setRecipientNameAndAddress("asd");
        transaction.setAmountTransaction(100D);
        transaction.setTitle("asd");
        double giverValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()-transaction.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        double recipientValue = BigDecimal.valueOf(firstAccountBankUser2.getAccountValue()+transaction.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser.setAccountValue(giverValue);firstAccountBankUser2.setAccountValue(recipientValue);
        transaction.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction);
        transaction.getAccountBanks().add(firstAccountBankUser2);firstAccountBankUser2.getTransactions().add(transaction);
        transaction.setAmountGiverAfterTransaction(giverValue);transaction.setAmountRecipientAfterTransaction(recipientValue);
        transaction.getUsers().add(user2);user2.getTransactions().add(transaction);
        transaction.getUsers().add(user);user.getTransactions().add(transaction);
        transaction.setGiverAccountNumber(firstAccountBankUser.getAccountNumber());
        transactionRepository.save(transaction);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user,transaction.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2,transaction.getAmountTransaction(),firstAccountBankUser2.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction.getTransactionId(),LocalDateTime.now().minusMonths(1));
        //From user standard1 to user2 standard1
        Transaction transaction2 = new Transaction();
        transaction2.setRecipientAccountNumber(firstAccountBankUser2.getAccountNumber());
        transaction2.setRecipientNameAndAddress("asd");
        transaction2.setAmountTransaction(200D);
        transaction2.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()-transaction2.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(firstAccountBankUser2.getAccountValue()+transaction2.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser.setAccountValue(giverValue);firstAccountBankUser2.setAccountValue(recipientValue);
        transaction2.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction2);
        transaction2.getAccountBanks().add(firstAccountBankUser2);firstAccountBankUser2.getTransactions().add(transaction2);
        transaction2.setAmountGiverAfterTransaction(giverValue);transaction2.setAmountRecipientAfterTransaction(recipientValue);
        transaction2.getUsers().add(user2);user2.getTransactions().add(transaction2);
        transaction2.getUsers().add(user);user.getTransactions().add(transaction2);
        transaction2.setGiverAccountNumber(firstAccountBankUser.getAccountNumber());
        transactionRepository.save(transaction2);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user,transaction2.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2,transaction2.getAmountTransaction(),firstAccountBankUser2.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction2.getTransactionId(),LocalDateTime.now().minusMonths(1).minusMinutes(1));
        //From user standard1 to user2 standard1
        Transaction transaction3 = new Transaction();
        transaction3.setRecipientAccountNumber(firstAccountBankUser2.getAccountNumber());
        transaction3.setRecipientNameAndAddress("asd");
        transaction3.setAmountTransaction(150D);
        transaction3.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()- transaction3.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(firstAccountBankUser2.getAccountValue()+transaction3.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser.setAccountValue(giverValue);firstAccountBankUser2.setAccountValue(recipientValue);
        transaction3.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction3);
        transaction3.getAccountBanks().add(firstAccountBankUser2);firstAccountBankUser2.getTransactions().add(transaction3);
        transaction3.setAmountGiverAfterTransaction(giverValue);
        transaction3.setAmountRecipientAfterTransaction(recipientValue);
        transaction3.getUsers().add(user2);user2.getTransactions().add(transaction3);
        transaction3.getUsers().add(user);user.getTransactions().add(transaction3);
        transaction3.setGiverAccountNumber(firstAccountBankUser.getAccountNumber());
        transactionRepository.save(transaction3);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction3.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction3.getAmountTransaction(),firstAccountBankUser2.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction3.getTransactionId(),LocalDateTime.now().minusDays(5));
        //From user standard1 to user2 standard1
        Transaction transaction4 = new Transaction();
        transaction4.setRecipientAccountNumber(firstAccountBankUser2.getAccountNumber());
        transaction4.setRecipientNameAndAddress("asd");
        transaction4.setAmountTransaction(500D);
        transaction4.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()- transaction4.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(firstAccountBankUser2.getAccountValue()+transaction4.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser.setAccountValue(giverValue);firstAccountBankUser2.setAccountValue(recipientValue);
        transaction4.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction4);
        transaction4.getAccountBanks().add(firstAccountBankUser2);firstAccountBankUser2.getTransactions().add(transaction4);
        transaction4.setAmountGiverAfterTransaction(giverValue);
        transaction4.setAmountRecipientAfterTransaction(recipientValue);
        transaction4.getUsers().add(user2);user2.getTransactions().add(transaction4);
        transaction4.getUsers().add(user);user.getTransactions().add(transaction4);
        transaction4.setGiverAccountNumber(firstAccountBankUser.getAccountNumber());
        transactionRepository.save(transaction4);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction4.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction4.getAmountTransaction(),firstAccountBankUser2.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction4.getTransactionId(),LocalDateTime.now().minusDays(5).minusHours(4));
        //From user standard1 to user2 standard1
        Transaction transaction5 = new Transaction();
        transaction5.setRecipientAccountNumber(firstAccountBankUser2.getAccountNumber());
        transaction5.setRecipientNameAndAddress("asd");
        transaction5.setAmountTransaction(750D);
        transaction5.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()- transaction5.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(firstAccountBankUser2.getAccountValue()+transaction5.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser.setAccountValue(giverValue);firstAccountBankUser2.setAccountValue(recipientValue);
        transaction5.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction5);
        transaction5.getAccountBanks().add(firstAccountBankUser2);firstAccountBankUser2.getTransactions().add(transaction5);
        transaction5.setAmountGiverAfterTransaction(giverValue);
        transaction5.setAmountRecipientAfterTransaction(recipientValue);
        transaction5.getUsers().add(user2);user2.getTransactions().add(transaction5);
        transaction5.getUsers().add(user);user.getTransactions().add(transaction5);
        transaction5.setGiverAccountNumber(firstAccountBankUser.getAccountNumber());
        transactionRepository.save(transaction5);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction5.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction5.getAmountTransaction(),firstAccountBankUser2.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction5.getTransactionId(),LocalDateTime.now().minusHours(5));
        //From user standard1 to user2 standard2
        //1k from user standard1 to user2 standard2
        Transaction transaction6 = new Transaction();
        transaction6.setRecipientAccountNumber(accountBank2User2.getAccountNumber());
        transaction6.setRecipientNameAndAddress("asd");
        transaction6.setAmountTransaction(300D);
        transaction6.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()- transaction6.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(accountBank2User2.getAccountValue()+transaction6.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser.setAccountValue(giverValue);accountBank2User2.setAccountValue(recipientValue);
        transaction6.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction6);
        transaction6.getAccountBanks().add(accountBank2User2);accountBank2User2.getTransactions().add(transaction6);
        transaction6.setAmountGiverAfterTransaction(giverValue);
        transaction6.setAmountRecipientAfterTransaction(recipientValue);
        transaction6.getUsers().add(user2);user2.getTransactions().add(transaction6);
        transaction6.getUsers().add(user);user.getTransactions().add(transaction6);
        transaction6.setGiverAccountNumber(firstAccountBankUser.getAccountNumber());
        transactionRepository.save(transaction6);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction6.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction6.getAmountTransaction(),accountBank2User2.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction6.getTransactionId(),LocalDateTime.now().minusDays(1));
        //From user standard1 to user2 standard2
        Transaction transaction7 = new Transaction();
        transaction7.setRecipientAccountNumber(accountBank2User2.getAccountNumber());
        transaction7.setRecipientNameAndAddress("asd");
        transaction7.setAmountTransaction(600D);
        transaction7.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()- transaction7.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(accountBank2User2.getAccountValue()+transaction7.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser.setAccountValue(giverValue);accountBank2User2.setAccountValue(recipientValue);
        transaction7.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction7);
        transaction7.getAccountBanks().add(accountBank2User2);accountBank2User2.getTransactions().add(transaction7);
        transaction7.setAmountGiverAfterTransaction(giverValue);
        transaction7.setAmountRecipientAfterTransaction(recipientValue);
        transaction7.getUsers().add(user2);user2.getTransactions().add(transaction7);
        transaction7.getUsers().add(user);user.getTransactions().add(transaction7);
        transaction7.setGiverAccountNumber(firstAccountBankUser.getAccountNumber());
        transactionRepository.save(transaction7);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction7.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction7.getAmountTransaction(),accountBank2User2.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction7.getTransactionId(),LocalDateTime.now().minusDays(1).minusHours(7));
        //From user standard1 to user2 standard2
        Transaction transaction8 = new Transaction();
        transaction8.setRecipientAccountNumber(accountBank2User2.getAccountNumber());
        transaction8.setRecipientNameAndAddress("asd");
        transaction8.setAmountTransaction(75D);
        transaction8.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()- transaction8.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(accountBank2User2.getAccountValue()+transaction8.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser.setAccountValue(giverValue);accountBank2User2.setAccountValue(recipientValue);
        transaction8.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction8);
        transaction8.getAccountBanks().add(accountBank2User2);accountBank2User2.getTransactions().add(transaction8);
        transaction8.setAmountGiverAfterTransaction(giverValue);
        transaction8.setAmountRecipientAfterTransaction(recipientValue);
        transaction8.getUsers().add(user2);user2.getTransactions().add(transaction8);
        transaction8.getUsers().add(user);user.getTransactions().add(transaction8);
        transaction8.setGiverAccountNumber(firstAccountBankUser.getAccountNumber());
        transactionRepository.save(transaction8);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction8.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction8.getAmountTransaction(),accountBank2User2.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction8.getTransactionId(),LocalDateTime.now().minusMonths(1));
        //From user standard1 to user2 standard2
        Transaction transaction9 = new Transaction();
        transaction9.setRecipientAccountNumber(accountBank2User2.getAccountNumber());
        transaction9.setRecipientNameAndAddress("asd");
        transaction9.setAmountTransaction(25D);
        transaction9.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()- transaction9.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(accountBank2User2.getAccountValue()+transaction9.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser.setAccountValue(giverValue);accountBank2User2.setAccountValue(recipientValue);
        transaction9.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction9);
        transaction9.getAccountBanks().add(accountBank2User2);accountBank2User2.getTransactions().add(transaction9);
        transaction9.setAmountGiverAfterTransaction(giverValue);
        transaction9.setAmountRecipientAfterTransaction(recipientValue);
        transaction9.getUsers().add(user2);user2.getTransactions().add(transaction9);
        transaction9.getUsers().add(user);user.getTransactions().add(transaction9);
        transaction9.setGiverAccountNumber(firstAccountBankUser.getAccountNumber());
        transactionRepository.save(transaction9);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction9.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction9.getAmountTransaction(),accountBank2User2.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction9.getTransactionId(),LocalDateTime.now().minusMonths(1).minusDays(1));
        //From user standard1 to user2 first saving
        //1k from user standard1 to user2 first saving
        Transaction transaction10 = new Transaction();
        transaction10.setRecipientAccountNumber(accountBank4User2.getAccountNumber());
        transaction10.setRecipientNameAndAddress("asd");
        transaction10.setAmountTransaction(1000D);
        transaction10.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()- transaction10.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(accountBank4User2.getAccountValue()+transaction10.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser.setAccountValue(giverValue);accountBank4User2.setAccountValue(recipientValue);
        transaction10.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction10);
        transaction10.getAccountBanks().add(accountBank4User2);accountBank4User2.getTransactions().add(transaction10);
        transaction10.setAmountGiverAfterTransaction(giverValue);
        transaction10.setAmountRecipientAfterTransaction(recipientValue);
        transaction10.getUsers().add(user2);user2.getTransactions().add(transaction10);
        transaction10.getUsers().add(user);user.getTransactions().add(transaction10);
        transaction10.setGiverAccountNumber(firstAccountBankUser.getAccountNumber());
        transactionRepository.save(transaction10);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction10.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction10.getAmountTransaction(),accountBank4User2.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction10.getTransactionId(),LocalDateTime.now().minusMinutes(30));
        //User2 transactions
        //From user2 first standard to user first standard
        //1200 from user2 first standard to user first standard
        Transaction transaction11 = new Transaction();
        transaction11.setRecipientAccountNumber(firstAccountBankUser.getAccountNumber());
        transaction11.setRecipientNameAndAddress("asd");
        transaction11.setAmountTransaction(100D);
        transaction11.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser2.getAccountValue()- transaction11.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()+transaction11.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser2.setAccountValue(giverValue);firstAccountBankUser.setAccountValue(recipientValue);
        transaction11.getAccountBanks().add(firstAccountBankUser2);firstAccountBankUser2.getTransactions().add(transaction11);
        transaction11.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction11);
        transaction11.setAmountGiverAfterTransaction(giverValue);
        transaction11.setAmountRecipientAfterTransaction(recipientValue);
        transaction11.getUsers().add(user2);user2.getTransactions().add(transaction11);
        transaction11.getUsers().add(user);user.getTransactions().add(transaction11);
        transaction11.setGiverAccountNumber(firstAccountBankUser2.getAccountNumber());
        transactionRepository.save(transaction11);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction11.getAmountTransaction(),firstAccountBankUser2.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction11.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction11.getTransactionId(),LocalDateTime.now().minusMinutes(15));
        //From user2 first standard to user first standard
        Transaction transaction12 = new Transaction();
        transaction12.setRecipientAccountNumber(firstAccountBankUser.getAccountNumber());
        transaction12.setRecipientNameAndAddress("asd");
        transaction12.setAmountTransaction(500D);
        transaction12.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser2.getAccountValue()- transaction12.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()+transaction12.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser2.setAccountValue(giverValue);firstAccountBankUser.setAccountValue(recipientValue);
        transaction12.getAccountBanks().add(firstAccountBankUser2);firstAccountBankUser2.getTransactions().add(transaction12);
        transaction12.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction12);
        transaction12.setAmountGiverAfterTransaction(giverValue);
        transaction12.setAmountRecipientAfterTransaction(recipientValue);
        transaction12.getUsers().add(user2);user2.getTransactions().add(transaction12);
        transaction12.getUsers().add(user);user.getTransactions().add(transaction12);
        transaction12.setGiverAccountNumber(firstAccountBankUser2.getAccountNumber());
        transactionRepository.save(transaction12);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction12.getAmountTransaction(),firstAccountBankUser2.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction12.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction12.getTransactionId(),LocalDateTime.now().minusMonths(1).minusDays(2).minusMinutes(3));
        //From user2 first standard to user first standard
        Transaction transaction13 = new Transaction();
        transaction13.setRecipientAccountNumber(firstAccountBankUser.getAccountNumber());
        transaction13.setRecipientNameAndAddress("asd");
        transaction13.setAmountTransaction(400D);
        transaction13.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser2.getAccountValue()- transaction13.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()+transaction13.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser2.setAccountValue(giverValue);firstAccountBankUser.setAccountValue(recipientValue);
        transaction13.getAccountBanks().add(firstAccountBankUser2);firstAccountBankUser2.getTransactions().add(transaction13);
        transaction13.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction13);
        transaction13.setAmountGiverAfterTransaction(giverValue);
        transaction13.setAmountRecipientAfterTransaction(recipientValue);
        transaction13.getUsers().add(user2);user2.getTransactions().add(transaction13);
        transaction13.getUsers().add(user);user.getTransactions().add(transaction13);
        transaction13.setGiverAccountNumber(firstAccountBankUser2.getAccountNumber());
        transactionRepository.save(transaction13);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction13.getAmountTransaction(),firstAccountBankUser2.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction13.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction13.getTransactionId(),LocalDateTime.now().minusDays(2).minusMinutes(3));
        //From user2 first standard to user first standard
        Transaction transaction14 = new Transaction();
        transaction14.setRecipientAccountNumber(firstAccountBankUser.getAccountNumber());
        transaction14.setRecipientNameAndAddress("asd");
        transaction14.setAmountTransaction(200D);
        transaction14.setTitle("asd");
        giverValue = BigDecimal.valueOf(firstAccountBankUser2.getAccountValue()- transaction14.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()+transaction14.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        firstAccountBankUser2.setAccountValue(giverValue);firstAccountBankUser.setAccountValue(recipientValue);
        transaction14.getAccountBanks().add(firstAccountBankUser2);firstAccountBankUser2.getTransactions().add(transaction14);
        transaction14.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction14);
        transaction14.setAmountGiverAfterTransaction(giverValue);
        transaction14.setAmountRecipientAfterTransaction(recipientValue);
        transaction14.getUsers().add(user2);user2.getTransactions().add(transaction14);
        transaction14.getUsers().add(user);user.getTransactions().add(transaction14);
        transaction14.setGiverAccountNumber(firstAccountBankUser2.getAccountNumber());
        transactionRepository.save(transaction14);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction14.getAmountTransaction(),firstAccountBankUser2.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction14.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction14.getTransactionId(),LocalDateTime.now().minusMinutes(3));
        //From user2 second standard to user first standard
        //1500 from user2 second standard to user first standard
        Transaction transaction15 = new Transaction();
        transaction15.setRecipientAccountNumber(firstAccountBankUser.getAccountNumber());
        transaction15.setRecipientNameAndAddress("asd");
        transaction15.setAmountTransaction(500D);
        transaction15.setTitle("asd");
        giverValue = BigDecimal.valueOf(accountBankUser2.getAccountValue()- transaction15.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()+transaction15.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        accountBankUser2.setAccountValue(giverValue);firstAccountBankUser.setAccountValue(recipientValue);
        transaction15.getAccountBanks().add(accountBankUser2);accountBankUser2.getTransactions().add(transaction15);
        transaction15.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction15);
        transaction15.setAmountGiverAfterTransaction(giverValue);
        transaction15.setAmountRecipientAfterTransaction(recipientValue);
        transaction15.getUsers().add(user2);user2.getTransactions().add(transaction15);
        transaction15.getUsers().add(user);user.getTransactions().add(transaction15);
        transaction15.setGiverAccountNumber(accountBankUser2.getAccountNumber());
        transactionRepository.save(transaction15);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction15.getAmountTransaction(),accountBankUser2.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction15.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction15.getTransactionId(),LocalDateTime.now().minusMinutes(5));
        //From user2 second standard to user first standard
        Transaction transaction16 = new Transaction();
        transaction16.setRecipientAccountNumber(firstAccountBankUser.getAccountNumber());
        transaction16.setRecipientNameAndAddress("asd");
        transaction16.setAmountTransaction(1000D);
        transaction16.setTitle("asd");
        giverValue = BigDecimal.valueOf(accountBankUser2.getAccountValue()- transaction16.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        recipientValue = BigDecimal.valueOf(firstAccountBankUser.getAccountValue()+transaction16.getAmountTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        accountBankUser2.setAccountValue(giverValue);firstAccountBankUser.setAccountValue(recipientValue);
        transaction16.getAccountBanks().add(accountBankUser2);accountBankUser2.getTransactions().add(transaction16);
        transaction16.getAccountBanks().add(firstAccountBankUser);firstAccountBankUser.getTransactions().add(transaction16);
        transaction16.setAmountGiverAfterTransaction(giverValue);
        transaction16.setAmountRecipientAfterTransaction(recipientValue);
        transaction16.getUsers().add(user2);user2.getTransactions().add(transaction16);
        transaction16.getUsers().add(user);user.getTransactions().add(transaction16);
        transaction16.setGiverAccountNumber(accountBankUser2.getAccountNumber());
        transactionRepository.save(transaction16);
        accountManagement.updateAllAccountValuesToGiverUserAfterTransactionForExamples(user, transaction16.getAmountTransaction(),accountBankUser2.getAccountType());
        accountManagement.updateAllAccountValuesToRecipientUserAfterTransactionForExamples(user2, transaction16.getAmountTransaction(),firstAccountBankUser.getAccountType());
        accountManagement.updateDateTransactionByTransactionIdAndDateTransaction(transaction16.getTransactionId(),LocalDateTime.now().minusMonths(1).minusMinutes(5));

    }
}
