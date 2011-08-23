package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class ClientThread extends Thread {

	public static DatagramPacket packet;

	private MulticastSocket socket;
	private InetAddress address;
	private int port = 4446;

	private String byteString;
	private String identity = "Unknown";
	private String username;
	private ChatClient parent;
	private byte[] data;
	byte[] byteData;

	public ClientThread(String username) {
		this.start();
		this.username = username;
	}

	@Override
	public void run() {
		try {
			address = InetAddress.getByName("234.0.0.1");
			socket = new MulticastSocket(port);
			socket.joinGroup(address);
			// new ClientReceiver(socket,parent);

			byteString = "!" + username;
			sendMessage(byteString);

//			while (true) {
//				// if(parent.isSending()){
//				identity = username;
//
//				// byteString = parent.getMessage();
//				byteString = identity + ": " + byteString;
//
//				byte[] byteData = new byte[byteString.getBytes().length];
//				byteData = byteString.getBytes();
//
//				packet = new DatagramPacket(byteData, byteString.length(),
//						address, port);
//				socket.send(packet);
////				parent.setSending(false);
//				// }
//			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}

	public void sendMessage(String string) {
		byteString = string;
		byteData = new byte[byteString.getBytes().length];
		byteData = byteString.getBytes();
		packet = new DatagramPacket(byteData, byteString.length(), address,
				port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String receiveMessage() {
		data = new String(
				"                                                              ")
				.getBytes();
		packet = new DatagramPacket(data, data.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(packet.getData());
	}

	public void disconnectSocket() {
		socket.disconnect();
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
