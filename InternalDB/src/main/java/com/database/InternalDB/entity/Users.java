package com.database.InternalDB.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "users1")
public class Users {

	@Id
	@Column(name = "account_id")
	private Long accountID;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	

	public Users() {
		super();
	}

	public Users(Long accountID, String username, String password) {
		super();
		this.accountID = accountID;
		this.username = username;
		this.password = password;
	}

	public Long getAccountID() {
		return accountID;
	}

	public void setAccountID(Long accountID) {
		this.accountID = accountID;
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
	
}
