package openhaus.common.actions;

import java.io.Serializable;

import openhaus.common.Account;

public class CreateNewAccountAction extends Action implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1431779273558458991L;
	
	private Account account;

	public CreateNewAccountAction() {
		super();
	}

	public CreateNewAccountAction(String source, String destination, String type, String username, String password) {
		super(source, destination, type);
		account = new Account(username, password);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
