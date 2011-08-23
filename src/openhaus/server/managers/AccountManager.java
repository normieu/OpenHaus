package openhaus.server.managers;

import java.util.List;

import openhaus.common.Account;
import openhaus.server.managers.exceptions.NonUniqueException;
import openhaus.server.managers.exceptions.TransactionException;

public interface AccountManager {

	void addAccount(Account account) throws TransactionException, NonUniqueException;

	void updateAccount(Account account) throws TransactionException;

	Account getAccount(String username) throws TransactionException;

	void removeAccount(Account account) throws TransactionException;

	boolean containsAccount(Account account) throws TransactionException;

	boolean containsAccount(String username) throws TransactionException;

	List<Account> getAllAccounts() throws TransactionException;

}
