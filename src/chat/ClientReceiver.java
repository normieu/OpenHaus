package chat;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

import javax.swing.JOptionPane;

import openhaus.ui.chat.ExtensionPanel;
import openhaus.ui.chat.side.MultiChatPanel;

public class ClientReceiver extends Thread {
	private MulticastSocket socket;
	private DatagramPacket packet;
	private byte[] data = new String("                                  ")
			.getBytes();
	private ChatClient client;
	private String username;

	public ClientReceiver(MulticastSocket socket, ChatClient client) {
		this.socket = socket;
		this.client = client;
		username = client.getUsername();
		packet = new DatagramPacket(data, data.length);
		start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				data = new String("                                                                               ")
						.getBytes();
				socket.receive(packet);
				String str = new String(packet.getData());
				System.out.println("str:" + str);
				if (str.startsWith("@")) {
					if (str.startsWith("@" + username)) {
						String[] s = str.substring(1).split("=");
//						client.setPrivateMessage(s[2], s[1]);
						
						if(ExtensionPanel.getPanel().getFriendExtension(s[1])==null)
							MultiChatPanel.getPanel().addNewTab(s[1]);
						ExtensionPanel.getPanel().getFriendExtension(s[1]).addMessage( username,s[2]);
						
						}
				} else if (str.trim().equals("=no" + username)) {
					JOptionPane
							.showMessageDialog(null,
									"Invalid Username. Username entered has duplicate.");
					username = JOptionPane.showInputDialog(null,
							"Enter username:", "LOGIN to CHAT",
							JOptionPane.QUESTION_MESSAGE);
					if (username == null) {
						System.exit(0);
					} else if (username.equals("") || username.contains("!")
							|| username.contains("@") || username.contains("#")
							|| username.contains("=")) {
						JOptionPane
								.showMessageDialog(
										null,
										"Invalid Username! Special Characters '!@#=' is not allowed.",
										"Warning", JOptionPane.WARNING_MESSAGE);
					} else {
						username += "=";
						client.validateUsername(username);
					}
				} else if (str.trim().equals("=yes" + username)) {
//					client.setVisible(true);
					username = username.split("=")[0];
					client.usernameAccepted(username);
				} else if (str.startsWith("arrange=")) {
					client.arrangeList();
				} else if (str.startsWith("namelist=")) {
					str = str.substring(9).trim();
					String[] username = str.split(",");
					for (int i = 0; i < username.length; i++) {
						if (client.getListmodel().isEmpty())
							client.addUserNameToList(username[i]);
						else {
							if (!client.getListmodel().contains(username[i]))
								client.addUserNameToList(username[i]);
						}
					}
				} else if (str.startsWith("!") || str.startsWith("=")) {

				} else if (str.startsWith("#")) {
					client.removeUserNameToList(str.substring(1).split(
							" has been disconnected")[0]);
//					client.removePrivateChat(str.substring(1).split(
//							" has been disconnected")[0]);
					client.setMessage(str.substring(1));
				} else
					client.setMessage(str);
				packet = new DatagramPacket(data, data.length);
				client.setSending(false);
				
			}
		} catch (IOException e) {
			System.out.println("Server is offline");
		}

	}

}
