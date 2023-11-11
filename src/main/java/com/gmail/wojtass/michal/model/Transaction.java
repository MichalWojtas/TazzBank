package com.gmail.wojtass.michal.model;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;


@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
	@SequenceGenerator(name = "transaction_id_seq", sequenceName = "transaction_id_seq", allocationSize = 1)
	@Column(name = "transaction_id")
	private long transactionId;
	
	@NotNull
	@NotEmpty(message= "Can't be empty")
	@NotBlank(message= "Can't be blank")
	@Size(max = 254, message="You reach limit of characters")
	@Column(name = "recipient_name_and_address")
	private String recipientNameAndAddress;
	
	@Min(value = 1, message = "Amount must be positive and at least 1")
	@Range(min=1, message="Wrong format. Amount should contains only digits.")
	@Column(name = "amount_transaction", columnDefinition = "double precision default 0")
	private double amountTransaction;

	@Column(name="amount_giver_after_transaction", columnDefinition = "double precision default 0")
	private double amountGiverAfterTransaction;

	@Column(name = "amount_recipient_after_transaction", columnDefinition = "double precision default 0")
	private double amountRecipientAfterTransaction;

	//This is field when we found user who sorting is Giver or Recipient. Take amonut from amountGiverAfterTransaction or Recipient.
	@org.springframework.data.annotation.Transient
	private transient double amountFoundAfterTransaction;

	@org.springframework.data.annotation.Transient
	private transient double amountTransactionToString;

	@org.springframework.data.annotation.Transient
	private transient String accountNameForSortHistory;

	@NotNull
	@NotEmpty(message= "Can't be empty")
	@NotBlank(message= "Can't be blank")
	@Size(max = 254, message="You reach limit of characters")
	@Column(name = "title")
	private String title;

	@Column(name = "date_transaction")
	private LocalDateTime dateTransaction;

	@PrePersist
	protected void prePersist() {
		if (dateTransaction == null){
			dateTransaction = LocalDateTime.now();
		}
	}

	@ManyToMany(mappedBy = "transactions")
	@NotNull
	@Column(name = "account_bank")
	private Set<AccountBank> accountBanks = new TreeSet<>(new Comparator<AccountBank>() {
		@Override
		public int compare(AccountBank o1, AccountBank o2) {
			return Long.compare(o1.getAccountBankId(),o2.getAccountBankId());
		}
	});
	
	@Pattern(regexp = "[0-9]{26}", message = "Wrong format. Account number should contains 26 numbers and only digits.")
	@Column(name = "recipient_account_number")
	private String recipientAccountNumber;
	
	@Pattern(regexp = "[0-9]{26}", message = "Wrong format. Account number should contains 26 numbers and only digits.")
	@Column(name = "giver_account_number")
	private String giverAccountNumber;

	@org.springframework.data.annotation.Transient
	private transient AccountBank recipientAccountBank;

	@org.springframework.data.annotation.Transient
	private transient User recipientUser;

	@org.springframework.data.annotation.Transient
	private transient User giverUser;

	//Should be that and saving on transaction object, not sure about @NotNull
	@ManyToMany()
	@NotNull
	@Column(name = "users")
	private List<User> users = new ArrayList<User>();

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getRecipientNameAndAddress() {
		return recipientNameAndAddress;
	}
	
	public void setRecipientNameAndAddress(String recipientNameAndAddress) {
		this.recipientNameAndAddress = recipientNameAndAddress;
	}
	
	public double getAmountTransaction() {
		return amountTransaction;
	}
	
	public void setAmountTransaction(double amountTransaction) {
		this.amountTransaction = amountTransaction;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getRecipientAccountNumber() {
		return recipientAccountNumber;
	}
	
	public void setRecipientAccountNumber(String recipientAccountNumber) {
		this.recipientAccountNumber = recipientAccountNumber;
	}
	
	public String getGiverAccountNumber() {
		return giverAccountNumber;
	}
	
	public void setGiverAccountNumber(String giverAccountNumber) {
		this.giverAccountNumber = giverAccountNumber;
	}
	
	public User getRecipientUser() {
		return recipientUser;
	}
	
	public void setRecipientUser(User recipientUser) {
		this.recipientUser = recipientUser;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public double getAmountGiverAfterTransaction() {
		return amountGiverAfterTransaction;
	}

	public void setAmountGiverAfterTransaction(double amountGiverAfterTransaction) {
		this.amountGiverAfterTransaction = amountGiverAfterTransaction;
	}

	public double getAmountRecipientAfterTransaction() {
		return amountRecipientAfterTransaction;
	}

	public void setAmountRecipientAfterTransaction(double amountRecipientAfterTransaction) {
		this.amountRecipientAfterTransaction = amountRecipientAfterTransaction;
	}

	public Set<AccountBank> getAccountBanks() {
		return accountBanks;
	}

	public void setAccountBanks(Set<AccountBank> accountBanks) {
		this.accountBanks = accountBanks;
	}

	public String getDateTransactionToString(){
		String dateInString = dateTransaction.getDayOfMonth() + "." + dateTransaction.getMonthValue() + "." + dateTransaction.getYear() + " " + dateTransaction.getHour() + ":" + dateTransaction.getMinute();
		return dateInString;
	}

	public double getAmountFoundAfterTransaction() {
		return amountFoundAfterTransaction;
	}

	public void setAmountFoundAfterTransaction(double amountFoundAfterTransaction) {
		this.amountFoundAfterTransaction = amountFoundAfterTransaction;
	}

	public String getAmountTransactionToString(){
		if (amountTransactionToString > 0){
			return "+ " + amountTransactionToString;
		}else {
			return String.valueOf(amountTransactionToString);
		}
	}

	public double getAmountTransactionToString2(){
		return amountTransactionToString;
	}

	public void setAmountTransactionToString(String operator){
		if (operator.equals("-")){
			this.amountTransactionToString = amountTransaction-(amountTransaction*2);
		}else {
			this.amountTransactionToString = amountTransaction;
		}
	}

	public LocalDateTime getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(LocalDateTime dateTransaction) {
		this.dateTransaction = dateTransaction;
	}

	public String getAccountNameForSortHistory() {
		return accountNameForSortHistory;
	}

	public void setAccountNameForSortHistory(String accountNameForSortHistory) {
		this.accountNameForSortHistory = accountNameForSortHistory;
	}

	public AccountBank getRecipientAccountBank() {
		return recipientAccountBank;
	}

	public void setRecipientAccountBank(AccountBank recipientAccountBank) {
		this.recipientAccountBank = recipientAccountBank;
	}
}
