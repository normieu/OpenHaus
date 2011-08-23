package openhaus.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import openhaus.common.Account;
import openhaus.common.actions.AccountCreatedAction;
import openhaus.common.actions.AccountNotCreatedAction;
import openhaus.common.actions.Action;
import openhaus.common.actions.ActionType;
import openhaus.common.actions.AuthenticationAction;
import openhaus.common.actions.CreateNewAccountAction;
import openhaus.common.actions.LoginAction;
import openhaus.common.actions.LogoutAction;
import openhaus.common.actions.UpdateListAction;
import openhaus.common.logger.Log;
import openhaus.server.managers.AccountManager;
import openhaus.server.managers.exceptions.NonUniqueException;
import openhaus.server.managers.exceptions.TransactionException;
import openhaus.server.managers.sessions.AccountManagerSession;
import openhaus.server.modules.LoginModule;
import openhaus.server.modules.exceptions.AlreadyLoggedInException;
import openhaus.server.util.LoggedInPool;

public class MultiServerThread extends Thread {

	private Socket socket = null;
	private static ArrayList<String> list;
	private ServerThread serverudp;
	
	public MultiServerThread(Socket socket,ServerThread serverudp) {
		super("MultiServerThread");
		this.socket = socket;
		this.serverudp = serverudp;
		list = new ArrayList<String>();
		Log.println("Socket is: " + socket.getPort());
	}

	@Override
	public void run() {

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(
					socket.getInputStream());

			while (true) {
				
//				System.out.println("listening for clients");

				try {
					Serializable ser = (Serializable) in.readObject();
					if (ser != null) {
						respondToAction(ser, out);
					}

				} catch (Exception e) {

				}

				try {
					Thread.sleep(50L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			// while ((inputLine = in.readLine()) != null) {
			// // break;
			// }
			// out.close();
			// in.close();
			// socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void respondToAction(Serializable serializable,
			ObjectOutputStream out) {

		if (serializable instanceof LoginAction) {
			
			
			LoginModule loginModule = new LoginModule();
			
			LoginAction login = (LoginAction) serializable;
			System.out.println("login details received from "+login.getSource());
			try {
				System.out.println("username: "+login.getUsername()+", pw: "+login.getPassword());
				Account a =loginModule.login(login.getUsername(), login.getPassword()); 
				if (a!=null) {
					// successful login
					list = serverudp.getGroupMembers();
					Action authAction = new AuthenticationAction("SERVER",
							login.getUsername(),
							ActionType.SERVER_AUTHENTICATION_ACTION, true);
					try {
						out.reset();

						out.writeObject(authAction);
						System.out.println("Server sent the login auth!");
												
//						LoggedInPool.getInstance().addAccount(a);
						
						UpdateListAction upList = new UpdateListAction("SERVER","ALL", ActionType.UPDATE_LIST_ACTION, list);
						out.reset();
						out.writeObject(upList);
						System.out.println("Name: "+list.get(0));
						System.out.println("login verified by server.");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
				}
			} catch (AlreadyLoggedInException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else

		if (serializable instanceof CreateNewAccountAction) {
			CreateNewAccountAction createAccount = (CreateNewAccountAction) serializable;
			AccountManager accManager = new AccountManagerSession();
			try {
				accManager.addAccount(createAccount.getAccount());
				AccountCreatedAction accountCreated = new AccountCreatedAction(
						"SERVER", createAccount.getAccount().getUsername(),
						ActionType.ACCOUNT_CREATED_ACTION,
						createAccount.getAccount());
				out.writeObject(accountCreated);
			} catch (TransactionException e) {
				try {
					AccountNotCreatedAction notCreated = new AccountNotCreatedAction(
							"SERVER", createAccount.getAccount().getUsername(),
							ActionType.ACCOUNT_NOT_CREATED_ACTION,
							"Username already exists!");
					out.writeObject(notCreated);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (NonUniqueException e) {
				try {
					AccountNotCreatedAction notCreated = new AccountNotCreatedAction(
							"SERVER", createAccount.getAccount().getUsername(),
							ActionType.ACCOUNT_NOT_CREATED_ACTION,
							"Username already used!");
					out.writeObject(notCreated);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (IOException e) {
				try {
					AccountNotCreatedAction notCreated = new AccountNotCreatedAction(
							"SERVER", createAccount.getAccount().getUsername(),
							ActionType.ACCOUNT_NOT_CREATED_ACTION,
							"An error occured!");
					out.writeObject(notCreated);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}

		else if (serializable instanceof LogoutAction) {
			LogoutAction logout = (LogoutAction) serializable;
			if (LoggedInPool.containsAccount(logout.getUsername())) {
				LoggedInPool.removeAccount(logout.getUsername());
			}
		}

	}
}
