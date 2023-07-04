package com.sgcib.bankaccountkata.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.javamoney.moneta.Money;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sgcib.bankaccountkata.dao.BankAccountDAO;
import com.sgcib.bankaccountkata.model.BankAccount;
import com.sgcib.bankaccountkata.model.BankAccountOperation;
import com.sgcib.bankaccountkata.model.Client;
import com.sgcib.bankaccountkata.service.BankAccountService;
import com.sgcib.bankaccountkata.service.IbankAccountService;

public class TestBankAccountService {

	Client c1;
	BankAccount b1;
	IbankAccountService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		c1 = new Client();
		c1.setClientId(1l);
		c1.setEmailAdress("c1.email@sgcib.com");
		c1.setName("Client 1");
		c1.setFullAdress("client 1 postal adress");
		c1.setPhoneNumer("+33101010101");
		b1 = new BankAccount();
		b1.setCountryIsoCode("FR");
		b1.setCountryKeyCode("76");
		b1.setBankCode("30003");
		b1.setAccountNumber("123565458");
		b1.setRibKey("12");
		b1.setClient(c1);
		b1.setOverdraftLimit(Money.of(100, "EUR"));
		b1.setCurrentBalance(Money.of(0, "EUR"));
		service = new BankAccountService();
		((BankAccountService) service).setAccountDAO(new BankAccountDAO());
	}

	@After
	public void tearDown() throws Exception {
		b1 = null;
		c1 = null;
	}

	@Test
	public void testDeposit() {
		assertTrue("Balance for account b1 is eqaul to 0 eur", b1.getCurrentBalance().isEqualTo(Money.of(0, "EUR")));
		assertTrue("Account history with 0 operation", b1.getMovementHistory().size() == 0);
		assertTrue("Account Overdraft Limit should be 100 eur", b1.getOverdraftLimit().isEqualTo(Money.of(100, "EUR")));

		service.doDeposit(b1, Money.of(50, "EUR"), "Deposit 1");
		assertTrue("Balnace for account b1 is eqaul to 50 eur", b1.getCurrentBalance().isEqualTo(Money.of(50, "EUR")));
		assertTrue("Account history with 1 operation", b1.getMovementHistory().size() == 1);

	}

	@Test
	public void testWithdrawal() {
		assertTrue("Balance for account b1 is eqaul to 0 eur", b1.getCurrentBalance().isEqualTo(Money.of(0, "EUR")));
		assertTrue("Account history with 0 operation", b1.getMovementHistory().size() == 0);
		assertTrue("Account Overdraft Limit should be 100 eur", b1.getOverdraftLimit().isEqualTo(Money.of(100, "EUR")));

		service.doWithdrawal(b1, Money.of(90, "EUR"),"Withdrawal 1");
		assertTrue("Balance for account b1 is eqaul to -90 eur",
				b1.getCurrentBalance().isEqualTo(Money.of(-90, "EUR")));
		assertTrue("Account history with 1 operation", b1.getMovementHistory().size() == 1);

		service.doWithdrawal(b1, Money.of(11, "EUR"),"Withdrawal 1");
		assertTrue("Balance for account b1 is eqaul to -90 eur",
				b1.getCurrentBalance().isEqualTo(Money.of(-90, "EUR")));
		assertTrue("Account history with 1 operation", b1.getMovementHistory().size() == 1);
		
		service.doDeposit(b1, Money.of(50, "EUR"), "Deposit 1");
		assertTrue("Balance for account b1 is eqaul to -40 eur",
				b1.getCurrentBalance().isEqualTo(Money.of(-40, "EUR")));
		assertTrue("Account history with 1 operation", b1.getMovementHistory().size() == 2);

		
		service.doWithdrawal(b1, Money.of(90, "EUR"),"Withdrawal 1");
		assertTrue("Balance for account b1 is eqaul to -40 eur",
				b1.getCurrentBalance().abs().isEqualTo(Money.of(40, "EUR")));
		assertTrue("Account history with 1 operation", b1.getMovementHistory().size() == 2);

	}

	@Test
	public void testAccountOpHistory() {
		assertTrue("Balance for account b1 is eqaul to 0 eur", b1.getCurrentBalance().isEqualTo(Money.of(0, "EUR")));
		assertTrue("Account history with 0 operation", b1.getMovementHistory().size() == 0);
		assertTrue("Account Overdraft Limit should be 100 eur", b1.getOverdraftLimit().isEqualTo(Money.of(100, "EUR")));

		service.doWithdrawal(b1, Money.of(90, "EUR"),"Withdrawal 1");

		service.doDeposit(b1, Money.of(50, "EUR"), "Deposit 1");
		
		service.doDeposit(b1, Money.of(300, "EUR"),"Deposit 2");

		service.doWithdrawal(b1, Money.of(260, "EUR"),"Withdrawal 2");
		
		service.doWithdrawal(b1, Money.of(90, "EUR"),"Withdrawal 3");
		assertTrue("Balance for account b1 is eqaul to -90 eur",
				b1.getCurrentBalance().isEqualTo(Money.of(-90, "EUR")));
		assertTrue("Account history with 5 operations", b1.getMovementHistory().size() == 5);
		List<BankAccountOperation> opHistories =  service.accountHistory(b1);
		for (BankAccountOperation op : opHistories) {
			System.out.println(op);
		}

	}

}
