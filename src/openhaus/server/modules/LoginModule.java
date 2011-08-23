package openhaus.server.modules;

import openhaus.common.Account;
import openhaus.server.managers.AccountManager;
import openhaus.server.managers.exceptions.TransactionException;
import openhaus.server.managers.sessions.AccountManagerSession;
import openhaus.server.modules.exceptions.AlreadyLoggedInException;
import openhaus.server.util.LoggedInPool;

public class LoginModule {

	private AccountManager accManager;

	public LoginModule() {
		super();
		accManager = new AccountManagerSession();
	}

	public Account login(String username, String password) throws AlreadyLoggedInException {
		
		Account acc = null;
		try {
			acc = accManager.getAccount(username);
		} catch (TransactionException e) {
		}
		if (acc != null) {
			System.out.println("accoutn is not null");
			if (acc.comparePassword(password)) {
				LoggedInPool.getInstance();
				if (LoggedInPool.containsAccount(username)){
					throw new AlreadyLoggedInException();
				}
				return acc;
			}
		}
		return acc;
	}

}
