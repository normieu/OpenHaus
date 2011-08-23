package openhaus.common.actions;

import java.io.Serializable;

import openhaus.common.Account;

public class AccountCreatedAction extends Action implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1431779273558458991L;
	
	private Account account;

	public AccountCreatedAction() {
		super();
	}

	public AccountCreatedAction(String source, String destination, String type, Account account) {
		super(source, destination, type);
		setAccount(account);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}