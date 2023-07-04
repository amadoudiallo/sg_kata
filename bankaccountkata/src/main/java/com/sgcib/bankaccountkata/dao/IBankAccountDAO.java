package com.sgcib.bankaccountkata.dao;

import java.util.List;

import javax.money.MonetaryAmount;

import com.sgcib.bankaccountkata.model.BankAccount;
import com.sgcib.bankaccountkata.model.BankAccountOperation;
import com.sgcib.bankaccountkata.model.Client;

public interface IBankAccountDAO {
	BankAccount create(String countryCode, String keyCode, String bankCode, String bankOfficeCode,String accountNumber,String ribKey, Client client);

	List<BankAccountOperation> accountHistory(BankAccount bankAccount);

	void doDeposit(BankAccount bankAccount, MonetaryAmount amount, String operationLabe);

	void doWithdrawal(BankAccount bankAccount, MonetaryAmount amount, String operationLabe);

}
