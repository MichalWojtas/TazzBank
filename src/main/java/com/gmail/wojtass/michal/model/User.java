package com.gmail.wojtass.michal.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.annotation.Transient;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Table(name = "tazzuser")
public class User {

	public final static int MAX_STANDARD_ACCOUNTS = 4;

	public final static int MAX_SAVING_ACCOUNTS = 4;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
	@Column(name = "user_id")
	private long userId;

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

	@Column(name = "actual_number_of_standard_accounts")
	private int actualNumberOfStandardAccounts;

	@Column(name = "actual_number_of_saving_accounts")
	private int actualNumberOfSavingAccounts;

	//I need more than 1 collection as fetch Eager so i must do one as Set
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	private Set<AccountBank> accountsBank = new TreeSet<>(new Comparator<AccountBank>() {
		@Override
		public int compare(AccountBank o1, AccountBank o2) {
			return Long.compare(o1.getAccountBankId(),o2.getAccountBankId());
		}
	});

	@Pattern(regexp = "[0-9]{11}", message="Pesel should countains 11 digits.")
	@Column(name = "pesel")
	private String pesel;

	//This i could split to contactNumber and authorizationNumber if necessary, should be with international prefix and if necessary with compare that phoneNumber exists in DB
	@Pattern(regexp = "[0-9]{9}", message="Wrong phone number format, without international prefix.")
	@Column(name = "phonenumber")
	private String phoneNumber;

	//This i could split to more variables, as countryAddress,zipCodeAddress, town/placeAddress, streetAddress, numberOfTheBuildingAddress, apartmentNumberAddress and merge it to one string address for show. Same for correspondence.
	//For now i stay with simple
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
	private transient String confirmPassword;

	@Column(name = "all_accounts_value", columnDefinition = "double precision default 0")
	private double allAccountsValue = 0;

	@Transient
	@Min(value = 0,message = "Accounts value can't be negative")
	private transient double tmpValue = 0;

	@Max(value = 10000000,message = "Max value for limit transaction is set as 10.000.000")
	@Min(value = 0,message = "Limit transaction can't be negative")
	@Column(name = "limit_transaction_for_day", columnDefinition = "double precision default 0")
	private double limitTransactionForDay = 0;

	@Max(value = 10000000,message = "Max value for limit transaction is set as 10.000.000")
	@Min(value = 0,message = "Limit transaction can't be negative")
	@Column(name = "limit_transaction_for_month", columnDefinition = "double precision default 0")
	private double limitTransactionForMonth = 0;

	@Column(name = "temp_limit_transaction_for_day", columnDefinition = "double precision default 0")
	private double tempLimitTransactionForDay = 0;

	@Column(name = "temp_limit_transaction_for_month", columnDefinition = "double precision default 0")
	private double tempLimitTransactionForMonth = 0;

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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public double getAllAccountsValue() {
		return allAccountsValue;
	}

	public void setAllAccountsValue(double allAccountsValue) {
		this.allAccountsValue = allAccountsValue;
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

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public double getLimitTransactionForDay() {
		return limitTransactionForDay;
	}

	public void setLimitTransactionForDay(double limitTransactionForDay) {
		this.limitTransactionForDay = limitTransactionForDay;
	}

	public double getLimitTransactionForMonth() {
		return limitTransactionForMonth;
	}

	public void setLimitTransactionForMonth(double limitTransactionForMonth) {
		this.limitTransactionForMonth = limitTransactionForMonth;
	}

	public double getTempLimitTransactionForDay() {
		return tempLimitTransactionForDay;
	}

	public void setTempLimitTransactionForDay(double tempLimitTransactionForDay) {
		this.tempLimitTransactionForDay = tempLimitTransactionForDay;
	}

	public double getTempLimitTransactionForMonth() {
		return tempLimitTransactionForMonth;
	}

	public void setTempLimitTransactionForMonth(double tempLimitTransactionForMonth) {
		this.tempLimitTransactionForMonth = tempLimitTransactionForMonth;
	}



	public int getActualNumberOfStandardAccounts() {
		return actualNumberOfStandardAccounts;
	}

	public void setActualNumberOfStandardAccounts(int actualNumberOfStandardAccounts) {
		this.actualNumberOfStandardAccounts = actualNumberOfStandardAccounts;
	}

	public int getActualNumberOfSavingAccounts() {
		return actualNumberOfSavingAccounts;
	}

	public void setActualNumberOfSavingAccounts(int actualNumberOfSavingAccounts) {
		this.actualNumberOfSavingAccounts = actualNumberOfSavingAccounts;
	}

	public Set<AccountBank> getAccountsBank() {
		return accountsBank;
	}


	public int getAccountBankIndex(long id){
		int index = 0;
		for (AccountBank account : accountsBank) {
			if (account.getAccountBankId() == id) {
				return index;
			}
			index++;
		}
		return -1; // Should never happen
	}

	public double getTmpValue() {
		return tmpValue;
	}

	public void setTmpValue(double tmpValue) {
		this.tmpValue = tmpValue;
	}

	public static int getMaxStandardAccounts() {
		return MAX_STANDARD_ACCOUNTS;
	}

	public static int getMaxSavingAccounts() {
		return MAX_SAVING_ACCOUNTS;
	}
}
