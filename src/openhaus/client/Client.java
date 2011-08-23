package openhaus.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import openhaus.common.actions.Action;
import openhaus.common.actions.ActionType;
import openhaus.common.actions.ChatAction;
import openhaus.common.actions.LoginAction;
import openhaus.common.actions.LogoutAction;
import openhaus.common.logger.Log;

public class Client {

	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private static Client instance;
	public static String username;
	private static ClientReceiver receiver;

	public static Client getInstance() {
		if (instance == null)
			instance = new Client();
		return instance;
	}

	private Client() {
		super();

		socket = null;
		out = null;
		in = null;

		try {
			socket = new Socket("127.0.0.1", 4444);

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			receiver = new ClientReceiver(socket);

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection.");
			System.exit(1);
		}

	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}

	public void login(String username, String password) {
		LoginAction login = new LoginAction(username, "SERVER",
				ActionType.LOGIN_ACTION, username, password);
		Client.username = username;
		try {
			out.writeObject(login);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void logout(String username) {

		Action logout = new LogoutAction(username, "SERVER",
				ActionType.LOGIN_ACTION, username);

		try {
			out.writeObject(logout);
			out.flush();
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			Log.println("Logout attempt unsuccessful!");
			e.printStackTrace();
		}

	}

	public void send(ChatAction chatAction) {
		try {
			out.writeObject(chatAction);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send(Action action) throws IOException {

		out.writeObject(action);
		out.flush();
	}

	public Serializable receiveInput() throws IOException,
			ClassNotFoundException {
		return (Serializable) in.readObject();
	}

	public ClientReceiver getClientReceiver() {
		return receiver;
	}

}
