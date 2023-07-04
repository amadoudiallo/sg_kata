package com.sgcib.bankaccountkata.service;

import java.util.List;

import javax.money.MonetaryAmount;

import com.sgcib.bankaccountkata.dao.IBankAccountDAO;
import com.sgcib.bankaccountkata.model.BankAccount;
import com.sgcib.bankaccountkata.model.BankAccountOperation;
import com.sgcib.bankaccountkata.model.Client;

public class BankAccountService implements IbankAccountService {

	private IBankAccountDAO accountDAO;

	@Override
	public BankAccount create(String countryCode, String keyCode, String bankCode,String bankOfficeCode, String accountNumber,String ribKey, Client client) {
		return accountDAO.create(countryCode, keyCode, bankCode, bankOfficeCode, accountNumber,ribKey, client);
	}

	@Override
	public List<BankAccountOperation> accountHistory(BankAccount bankAccount) {
		return accountDAO.accountHistory(bankAccount);
	}

	@Override
	public void doDeposit(BankAccount bankAccount, MonetaryAmount amount, String operationLabe) {
		accountDAO.doDeposit(bankAccount, amount,operationLabe);
	}

	@Override
	public void doWithdrawal(BankAccount bankAccount, MonetaryAmount amount, String operationLabe) {
		accountDAO.doWithdrawal(bankAccount, amount,operationLabe);
	}

	public IBankAccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(IBankAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

}
