package chat;

import java.awt.Point;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import openhaus.ui.chat.ExtensionPanel;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ChatClient {

	private static ClientThread clientThread;
	private ChatClient main;
	private DefaultListModel listmodel;
	private Point point = new Point();
	private JList list;
	private static String username = "";
	private boolean isSending;

	public static ClientThread getClientThread() {
		return clientThread;
	}

	public static String getUsername1() {
		return username;
	}

	public ChatClient(String username) {

		listmodel = new DefaultListModel();

		try {
			setUserName(username);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setListmodel(DefaultListModel listmodel) {
		this.listmodel = listmodel;
	}

	public DefaultListModel getListmodel() {
		return listmodel;
	}

	public void arrangeList() {
		ArrayList<String> a = new ArrayList<String>();
		for (int i = 0; i < listmodel.size(); i++) {
			if (!listmodel.get(i).equals(username))
				a.add(listmodel.getElementAt(i) + "");
		}
		ExtensionPanel.getPanel().getTabExtension().updateList(a);

	}

	public void addUserNameToList(String username) {
		listmodel.addElement(username);
	}

	public void removeUserNameToList(String username) {
		listmodel.removeElement(username);
	}

	protected void sendMessage() {
		setSending(true);
	}

	public void setUserName(String text) throws IOException {
		username = text + "=";
//		clientThread = new ClientThread(this, username);
	}

	public void setMessage(final String msg) {
		if (!(msg.equals(""))) {
			// textArea.setText(textArea.getText() + msg + "\n");
			// textField.setText("");
			setSending(false);
		}
	}

	public void setSending(boolean isSending) {
		this.isSending = isSending;
	}

	public boolean isSending() {
		return isSending;
	}

	public String getUsername() {
		return username;
	}

	public void validateUsername(String username) {
		clientThread.sendMessage("!" + username);
	}

	public void usernameAccepted(String username) {
		ChatClient.username = username;
		clientThread.setUsername(username);
		clientThread.sendMessage(username + " is now connected");
	}

	public ChatClient getThis() {
		return this;
	}
}
