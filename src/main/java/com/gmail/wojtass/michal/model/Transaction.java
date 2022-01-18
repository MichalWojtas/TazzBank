package com.gmail.wojtass.michal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;


@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idTransaction;
	
	@NotNull
	@NotEmpty(message= "Can't be empty")
	@NotBlank(message= "Can't be blank")
	@Size(max = 254, message="You reach limit of characters")
	private String recipientNameAndAddress;
	
	@Min(value = 1, message = "Amount must be positive and at least 1")
	@Range(min=1, message="Wrong format. Amount should contains only digits.")
	private double amountTransaction;
	
	@NotNull
	@NotEmpty(message= "Can't be empty")
	@NotBlank(message= "Can't be blank")
	@Size(max = 254, message="You reach limit of characters")
	private String title;
	
	@Pattern(regexp = "[0-9]{26}", message = "Wrong format. Account number should contains 26 numbers and only digits.")
	private String recipientAccountNumber;
	
	@Pattern(regexp = "[0-9]{26}", message = "Wrong format. Account number should contains 26 numbers and only digits.")
	private String giverAccountNumber;
	
	@Transient
	private transient User recipientUser;
	
	@ManyToMany(mappedBy = "transactions", fetch=FetchType.EAGER)
	private List<User> users = new ArrayList<User>();
	
	public long getIdTransaction() {
		return idTransaction;
	}
	
	public void setIdTransaction(long idTransaction) {
		this.idTransaction = idTransaction;
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

	
	
}
