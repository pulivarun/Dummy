package com.cg.WalletApplication.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="Customer_Wallet12")
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="Mobile_No",length=10)
	private String mobileNumber;
	@Column(name="Customer_Name",length=30)
	private String name;
	@Column(name="Customer_Password",length=20)
	private String password;
	@Column(name="Email_ID",length=30)
	private String EmailId;
	@Embedded
	private Wallet wallet ;
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private Set<Transaction> employees = new HashSet<Transaction>();		
	
/*	CREATE TABLE TRANSACTIONS
	(
	 ID int NOT NULL,
	 Mobile_no varchar2(10) references customer_wallet(MOBILE_NO),
	 TimeStampOfTrans Timestamp,
	 Type char(10),
	 Amount Number(19,2),
	  PRIMARY KEY (ID)
	);*/
	public Customer() {
		wallet = new Wallet();
		
	}
	



	public Customer(String mobileNumber, String name, String password, String emailId, Wallet wallet) {
		super();
		this.mobileNumber = mobileNumber;
		this.name = name;
		this.password = password;
		EmailId = emailId;
		this.wallet = wallet;
		
	}




	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}




	@OneToMany(targetEntity=Transaction.class,mappedBy="customer",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public Set<Transaction> getEmployees() {
		return employees;
	}




	public void setEmployees(Set<Transaction> employees) {
		this.employees = employees;
	}




	
	public void addEmployee(Transaction employee) {
		employee.setCustomer(this);			//this will avoid nested cascade
		this.getEmployees().add(employee);
	}


	




	
	
	
	
	



}