package openhaus.server.managers.sessions;

import java.util.List;

import openhaus.common.Account;
import openhaus.server.managers.AbstractManager;
import openhaus.server.managers.AccountManager;
import openhaus.server.managers.exceptions.TransactionException;

public class AccountManagerSession extends AbstractManager implements AccountManager{

	@Override
	public void addAccount(Account account) throws TransactionException {
		if (getAccount(account.getUsername()) == null)
			add(account);
		else
			throw new TransactionException("Username already exists!");
		
	}
	
	@Override
	public void updateAccount(Account account) throws TransactionException {
		update(account);
	}

	@Override
	public Account getAccount(String username) throws TransactionException {
		return (Account) get(Account.class, username);
	}

	@Override
	public void removeAccount(Account account) throws TransactionException {
		remove(account);
	}

	@Override
	public boolean containsAccount(Account account) throws TransactionException {
		return getAccount(account.getUsername()) != null;
	}

	@Override
	public boolean containsAccount(String username) throws TransactionException {
		return getAccount(username) != null;
	}

	@Override
	public List<Account> getAllAccounts() throws TransactionException {
		return getAll(Account.class);
	}

}
