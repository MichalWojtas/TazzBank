package com.gmail.wojtass.michal.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "account_bank")
public class AccountBank {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_bank_id_seq")
    @SequenceGenerator(name = "account_bank_id_seq", sequenceName = "account_bank_id_seq", allocationSize = 1)
    @Column(name = "account_bank_id")
    private long accountBankId;

    @NotNull
    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(name = "account_value")
    @Min(value = 0,message = "Account value can't be negative")
    private double accountValue = 0;

    @ManyToOne()
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "account_name")
    private String accountName;

    @org.springframework.data.annotation.Transient
    private transient String confirmPassword;

    @org.springframework.data.annotation.Transient
    private transient boolean addAccountSuccessful = false;

    public enum AccountType{
        STANDARD,SAVING
    }

    @OneToMany(mappedBy = "accountBank",fetch = FetchType.LAZY)
    private Set<DepositPayout> deposits = new TreeSet<>(new Comparator<DepositPayout>() {
        @Override
        public int compare(DepositPayout o1, DepositPayout o2) {
            return Long.compare(o1.getDepositId(),o2.getDepositId());
        }
    });

    @ManyToMany
    private Set<Transaction> transactions = new TreeSet<>(new Comparator<Transaction>() {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            return Long.compare(o1.getTransactionId(),o2.getTransactionId());
        }
    });

    public AccountBank() {
    }

    public AccountBank(User user, String accountNumber, AccountType accountType) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    public long getAccountBankId() {
        return accountBankId;
    }

    public void setAccountBankId(long accountBankId) {
        this.accountBankId = accountBankId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(double accountValue) {
        this.accountValue = accountValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isAddAccountSuccessful() {
        return addAccountSuccessful;
    }

    public void setAddAccountSuccessful(boolean addAccountSuccessful) {
        this.addAccountSuccessful = addAccountSuccessful;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
