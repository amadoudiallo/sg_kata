package com.sgcib.bankaccountkata.dao;

import java.util.Calendar;
import java.util.List;

import javax.money.MonetaryAmount;

import com.sgcib.bankaccountkata.model.BankAccount;
import com.sgcib.bankaccountkata.model.BankAccountOperation;
import com.sgcib.bankaccountkata.model.Client;
import com.sgcib.bankaccountkata.model.OperationSens;

public class BankAccountDAO implements IBankAccountDAO {

	@Override
	public BankAccount create(String countryCode, String keyCode, String bankCode, String bankOfficeCode,
			String accountNumber, String ribKey, Client client) {
		BankAccount bankAccount = new BankAccount();

		bankAccount.setClient(client);

		bankAccount.setCountryIsoCode(countryCode);
		bankAccount.setCountryKeyCode(keyCode);
		bankAccount.setBankCode(bankCode);
		bankAccount.setBankOfficeCode(bankOfficeCode);
		bankAccount.setAccountNumber(accountNumber);
		bankAccount.setRibKey(ribKey);
		//save bank account in data repository
		return bankAccount;
	}

	@Override
	public List<BankAccountOperation> accountHistory(BankAccount bankAccount) {
		//load bankAccount from data repository
		return bankAccount.getMovementHistory();
	}

	@Override
	public void doDeposit(BankAccount bankAccount, MonetaryAmount amount, String operationLabe) {
		BankAccountOperation accountOperation = new BankAccountOperation();
		accountOperation.setBankAccount(bankAccount);
		accountOperation.setBusinessDate(Calendar.getInstance().getTime());
		accountOperation.setOperationAmount(amount);
		accountOperation.setSens(OperationSens.C);
		accountOperation.setBalanceAfter(bankAccount.getCurrentBalance().add(amount));
		accountOperation.setOpDescrition(operationLabe);
		bankAccount.setCurrentBalance(accountOperation.getBalanceAfter());
		bankAccount.getMovementHistory().add(accountOperation);
		//save in data repository and allow operation id
	}

	@Override
	public void doWithdrawal(BankAccount bankAccount, MonetaryAmount amount, String operationLabe) {
		if ( isWithDrawalAllowed(bankAccount, amount)) {			
			BankAccountOperation accountOperation = new BankAccountOperation();
			accountOperation.setBankAccount(bankAccount);
			accountOperation.setBusinessDate(Calendar.getInstance().getTime());
			accountOperation.setOperationAmount(amount.abs());
			accountOperation.setSens(OperationSens.D);
			accountOperation.setBalanceAfter(bankAccount.getCurrentBalance().subtract(amount.abs()));
			accountOperation.setOpDescrition(operationLabe);
			bankAccount.setCurrentBalance(accountOperation.getBalanceAfter());
			bankAccount.getMovementHistory().add(accountOperation);
			//save in data repository and allow operation id
		}

	}
	
	private boolean isWithDrawalAllowed(BankAccount bankAccount, MonetaryAmount amount) {
		 return (bankAccount != null && amount != null && bankAccount.getCurrentBalance().add(bankAccount.getOverdraftLimit().abs()).subtract(amount.abs()).isPositiveOrZero());
	}

}
