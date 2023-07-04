package com.sgcib.bankaccountkata.model;

public enum OperationSens {
	C("Credit"), D("Debit");

	public final String label;

	private OperationSens(String label) {
		this.label = label;
	}

}
