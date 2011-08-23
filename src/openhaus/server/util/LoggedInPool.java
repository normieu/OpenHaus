package openhaus.server.util;

import java.util.Set;
import java.util.TreeSet;

import openhaus.common.Account;

public class LoggedInPool {

	private static LoggedInPool pool = null;
	private static Set<Account> loggedInAccounts = null;

	private LoggedInPool() {
		super();
		loggedInAccounts = new TreeSet<Account>();
	}

	public synchronized static Set<Account> getLoggedInAccounts() {
		return loggedInAccounts;
	}

	public synchronized static LoggedInPool getInstance() {
		if (pool == null)
			pool = new LoggedInPool();
		return pool;
	}

	public synchronized static void addAccount(Account account) {
		loggedInAccounts.add(account);
	}

	public synchronized static void removeAccount(Account account) {
		loggedInAccounts.remove(account);
	}

	public synchronized static void removeAccount(String username) {
		Set<Account> accs = getLoggedInAccounts();
		for (Account a : accs) {
			if (a.getUsername() == username) {
				accs.remove(a);
			}
		}
	}

	public synchronized static boolean containsAccount(Account account) {
		Set<Account> accs = getLoggedInAccounts();
		for (Account a : accs) {
			if (a.getUsername().equals(account.getUsername()))
				return true;
		}
		return false;
	}

	public synchronized static boolean containsAccount(String username) {
		Set<Account> accs = getLoggedInAccounts();
		for (Account a : accs) {
			if (a.getUsername().equals(username))
				return true;
		}
		return false;
	}

}
