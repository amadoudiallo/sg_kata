package com.sgcib.bankaccountkata.model;

import java.io.Serializable;

public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String fullAdress;
	private long clientId;
	private String phoneNumer;
	private String emailAdress;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullAdress() {
		return fullAdress;
	}

	public void setFullAdress(String fullAdress) {
		this.fullAdress = fullAdress;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getPhoneNumer() {
		return phoneNumer;
	}

	public void setPhoneNumer(String phoneNumer) {
		this.phoneNumer = phoneNumer;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

}
