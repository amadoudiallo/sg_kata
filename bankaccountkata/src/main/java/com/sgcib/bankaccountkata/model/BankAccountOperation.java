package com.sgcib.bankaccountkata.model;

import java.io.Serializable;
import java.util.Date;

import javax.money.MonetaryAmount;

public class BankAccountOperation implements Serializable {
	private static final long serialVersionUID = 1L;
	private long internalOperationId;
	private BankAccount bankAccount;
	private Date businessDate;
	private OperationSens sens; // D for debit and C for credit
	private MonetaryAmount operationAmount;
	private MonetaryAmount balanceAfter;
	private String opDescrition;

	public long getInternalOperationId() {
		return internalOperationId;
	}

	public void setInternalOperationId(long internalOperationId) {
		this.internalOperationId = internalOperationId;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public OperationSens getSens() {
		return sens;
	}

	public void setSens(OperationSens sens) {
		this.sens = sens;
	}

	public MonetaryAmount getOperationAmount() {
		return operationAmount;
	}

	public void setOperationAmount(MonetaryAmount operationAmount) {
		this.operationAmount = operationAmount;
	}

	public MonetaryAmount getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(MonetaryAmount balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	

	public String getOpDescrition() {
		return opDescrition;
	}

	public void setOpDescrition(String opDescrition) {
		this.opDescrition = opDescrition;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("\n[Bank account transaction -> \n" );
		sb.append(" Operation : "+opDescrition);
		sb.append("\n Account number : "+bankAccount.getAccountNumber());		
		sb.append("\n Date : "+businessDate);
		sb.append("\n Amount : "+operationAmount);
		sb.append("\n Type : "+sens.label);
		sb.append("\n Account balance : "+balanceAfter+"\n]");
		return sb.toString();
	}
}
