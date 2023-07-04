package com.sgcib.bankaccountkata.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.money.MonetaryAmount;

public class BankAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	private long internalAccountId;
	private String iban;
	private String countryIsoCode;
	private String countryKeyCode;
	private String bankCode;
	private String bankOfficeCode;
	private String accountNumber;
	private String ribKey;
	private Client client;
	private MonetaryAmount currentBalance;
	private MonetaryAmount overdraftLimit;
	private List<BankAccountOperation> movementHistory;

	public long getInternalAccountId() {
		return internalAccountId;
	}

	public void setInternalAccountId(long internalAccountId) {
		this.internalAccountId = internalAccountId;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public MonetaryAmount getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(MonetaryAmount currentBalance) {
		this.currentBalance = currentBalance;
	}

	public MonetaryAmount getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(MonetaryAmount overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	public List<BankAccountOperation> getMovementHistory() {
		if (movementHistory == null) {
			setMovementHistory(new ArrayList<BankAccountOperation>());
		}
		return movementHistory;
	}

	public void setMovementHistory(List<BankAccountOperation> movementHistory) {

		this.movementHistory = movementHistory;
	}

	public String getBankOfficeCode() {
		return bankOfficeCode;
	}

	public void setBankOfficeCode(String bankOfficeCode) {
		this.bankOfficeCode = bankOfficeCode;
	}

	public String getCountryIsoCode() {
		return countryIsoCode;
	}

	public void setCountryIsoCode(String countryIsoCode) {
		this.countryIsoCode = countryIsoCode;
	}

	public String getCountryKeyCode() {
		return countryKeyCode;
	}

	public void setCountryKeyCode(String countryKeyCode) {
		this.countryKeyCode = countryKeyCode;
	}

	public String getRibKey() {
		return ribKey;
	}

	public void setRibKey(String ribKey) {
		this.ribKey = ribKey;
	}

}
