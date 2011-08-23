package openhaus.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JOptionPane;

import chat.ClientThread;

import openhaus.common.Account;
import openhaus.common.actions.AccountCreatedAction;
import openhaus.common.actions.Action;
import openhaus.common.actions.AuthenticationAction;
import openhaus.common.actions.ChatAction;
import openhaus.common.actions.UpdateListAction;
import openhaus.common.logger.Log;
import openhaus.server.util.LoggedInPool;
import openhaus.ui.Swapper;
import openhaus.ui.chat.ExtensionPanel;
import openhaus.ui.chat.side.MultiChatPanel;

public class ClientReceiver extends Thread {

	private Socket socket;
//	private ObjectInputStream in;
//	private ObjectOutputStream out;

	public ClientReceiver(Socket socket) {
		this.socket = socket;
		start();
	}

	@Override
	public void run() {
		while (!socket.isClosed()) {
			try {

				// int n = in.available();

				Serializable ser = (Serializable)Client.getInstance().getIn().readObject();

				System.out.println("Trying to Respond.");

				if (ser != null) {
					// Action action = (Action) ser;
					// Log.println("Dest: "+action.getDestination());
					// Log.println("Source: "+action.getSource());
					// Log.println("USer: "+Client.username);
					// if ((action.getDestination().equalsIgnoreCase(
					// "all") || action.getDestination()
					// .equalsIgnoreCase(Client.username))
					// && !action.getSource().equalsIgnoreCase(
					// Client.username))
					//
					System.out.println("Responding.");
					respondToAction(ser, Client.getInstance().getOut());
				}

				Thread.sleep(50L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void respondToAction(Serializable serializable, ObjectOutputStream out) {

		if (serializable instanceof AccountCreatedAction) {
			AccountCreatedAction ac = (AccountCreatedAction)serializable;
			
			Log.print("account was created!");
			
		} else

		if (serializable instanceof ChatAction) {
			System.out.println("Responding to chatAction.");

			ChatAction chat = (ChatAction) serializable;
			Log.println("Dest: " + chat.getDestination());
			Log.println("Source: " + chat.getSource());
			Log.println("USer: " + Client.username);
			//
			if (chat.getDestination().equalsIgnoreCase(Client.username) && chat.getSource().equalsIgnoreCase(Client.username)) {
				if (ExtensionPanel.getPanel().getFriendExtension(chat.getDestination()) == null)
					MultiChatPanel.getPanel().addNewTab(chat.getDestination());
				ExtensionPanel.getPanel().getFriendExtension(chat.getDestination()).addMessage(Client.username, chat.getMessage());

			}
		} else if (serializable instanceof UpdateListAction) {
			System.out.println("Responding to updateListAction.");

			UpdateListAction upListAction = (UpdateListAction) serializable;

			ExtensionPanel.getPanel().getTabExtension().updateList(upListAction.getList());
			if(!upListAction.getList().isEmpty())
			System.out.println("list#: " + upListAction.getList().size());
		}

		else if (serializable instanceof AuthenticationAction) {
			AuthenticationAction authAction = (AuthenticationAction) serializable;
			System.out.println("Responding to authenListAction.");

			Log.println("Dest: " + authAction.getDestination());
			Log.println("Source: " + authAction.getSource());
			Log.println("USer: " + Client.username + " auth: " + authAction.isAuthenticated());
			//
			if (authAction.getDestination().equalsIgnoreCase(Client.username) && !authAction.getSource().equalsIgnoreCase(Client.username)) {
				if (authAction.isAuthenticated()) {
					Log.println("login successful!");
					Swapper.INSTANCE.swap(Swapper.USER_SCREEN);
					new ClientThread(Client.username);
//					JOptionPane.showMessageDialog(null, "Login successful!");

				} else {

					Swapper.INSTANCE.swap(Swapper.LOGIN_SCREEN);
					JOptionPane.showMessageDialog(null, "Login failed");
					Log.println("Login attempt unsuccessful!");

				}
			}
		}

	}

	
}