package com.database.InternalDB.entity;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="send_msg1")
public class SendMsg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	 
	@Column(name="mobile")
	private long mobile;
	
	@Column(name="message")
	private String message;
	
	@Column(name="status")
	private String status;
	
	@Column(name = "received_ts")
	private Date receivedTs;
	
	
	@Column(name="sent_ts")
	private Date sentTs;
	
	@Column(name="telco_response")
	private String telco_response;
	
	@Column(name="account_id")
	private Long accountId;
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getMobile() {
		return mobile;
	}


	public void setMobile(long mobile) {
		this.mobile = mobile;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getReceivedTs() {
		return receivedTs;
	}


	public void setReceivedTs(Date receivedTs) {
		this.receivedTs = receivedTs;
	}


	public Date getSentTs() {
		return sentTs;
	}


	public void setSentTs(Date sentTs) {
		this.sentTs = sentTs;
	}


	public String getTelco_response() {
		return telco_response;
	}


	public void setTelco_response(String telco_response) {
		this.telco_response = telco_response;
	}


	public Long getAccountId() {
		return accountId;
	}


	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}


	public SendMsg() {
		super();
	}


	public SendMsg(long mobile, String message, Long accountId) {
		super();
		this.mobile = mobile;
		this.message = message;
		this.accountId = accountId;
	}


	public SendMsg(long id, long mobile, String message, String status, Time receivedTs, Time sentTs,
			String telco_response, Long accountId) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.message = message;
		this.status = status;
		this.receivedTs = receivedTs;
		this.sentTs = sentTs;
		this.telco_response = telco_response;
		this.accountId = accountId;
	}

	

}
