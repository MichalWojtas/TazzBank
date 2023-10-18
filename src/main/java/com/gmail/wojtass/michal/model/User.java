package com.gmail.wojtass.michal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Transient;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "tazzuser")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Email(message ="Email format required")
	@NotEmpty(message= "Can't be empty")
	@NotNull
	@NotBlank(message= "Can't be blank")
	@Column(name = "email", unique = true)
	private String email;

	@Pattern(regexp = "[a-zA-Z0-9]{6,254}", message="Username should contain a minimum of 6 characters.")
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@NotNull
	@NotEmpty(message= "Can't be empty")
	@NotBlank(message= "Can't be blank")
	@Column(name = "firstname")
	@Size(max = 254, message="You reach limit of characters")
	private String firstName;
	
	@Size(max = 254, message="You reach limit of characters")
	@Column(name = "secondname")
	private String secondName;
	
	@NotEmpty(message= "Can't be empty")
	@NotBlank(message= "Can't be blank")
	@NotNull
	@Size(max = 254, message="You reach limit of characters")
	@Column(name = "lastname")
	private String lastName;
	
	@Pattern(regexp = "[0-9]{11}", message="Pesel should countains 11 digits.")
	@Column(name = "pesel")
	private String pesel;
	
	@Pattern(regexp = "[0-9]{9}", message="Wrong phone number format, whithout international prefix.")
	@Column(name = "phonenumber")
	private String phoneNumber;
	
	@NotNull
	@NotEmpty(message= "Can't be empty")
	@NotBlank(message= "Can't be blank")
	@Column(name = "address")
	@Size(max = 254, message="You reach limit of characters")
	private String address;
	
	@NotNull
	@NotEmpty(message= "Can't be empty")
	@NotBlank(message= "Can't be blank")
	@Column(name = "correspondenceaddress")
	@Size(max = 254, message="You reach limit of characters")
	private String addressForCorrespondence;


	@Transient
	@Column(name = "b4encryptPassword")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$", message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit and have 6 characters.")
	private transient String b4encryptPassword;
	
	@Transient
	@Column(name = "confirmPassword")
	private transient String confirmPassword;
	
	@Column(name = "account_value")
	@Min(value = 0,message = "Account value can't be negative")
	private double accountValue = 0;
	
	@Column(name = "account_number", unique = true)
	private String accountNumber;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	public User() {
		super();
	}
	
	
	public void bcryptPassword() {
		this.password = BCrypt.hashpw(b4encryptPassword, BCrypt.gensalt(10));
	}
	public String bcryptPassword(String password){
		return BCrypt.hashpw(password,BCrypt.gensalt(10));
	}
	
	public String getB4encryptPassword() {
		return b4encryptPassword;
	}
	
	public void setB4encryptPassword(String b4encryptPassword) {
		this.b4encryptPassword = b4encryptPassword;
	}
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getAccountValue() {
		return accountValue;
	}

	public void setAccountValue(double accountValue) {
		this.accountValue = accountValue;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressForCorrespondence() {
		return addressForCorrespondence;
	}

	public void setAddressForCorrespondence(String addressForCorrespondence) {
		this.addressForCorrespondence = addressForCorrespondence;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}
