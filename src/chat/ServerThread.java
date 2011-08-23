package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ServerThread extends Thread {

	public static DatagramPacket packet;
	private MulticastSocket socket;
	private InetAddress address;
	private int port = 4446;
	private int ctr = 0;
	private String[] clients;
	private String byteString;
	private byte[] byteData;
	private ArrayList<String> groupMembers;
	private boolean listening;

	public ServerThread() {
		
		this.start();
		listening = true;
	}

	@Override
	public void run() {
		try {
			address = InetAddress.getByName("234.0.0.1");
			socket = new MulticastSocket(port);
			socket.joinGroup(address);
			groupMembers = new ArrayList<String>();
			while (listening) {
				byteData = new String("                                     ")
						.getBytes();
				packet = new DatagramPacket(byteData, byteData.length);
				socket.receive(packet);
				String str = new String(packet.getData());
				processClient(str, address, port);
				
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}

	public void processClient(String msg, InetAddress addr, int port) {
		if (msg.startsWith("!")) {
			String username = msg.substring(1).trim().split("=")[0];
			if (username != null && isUniqueName(username)) {
				groupMembers.add(username);
				sendMessage(username+" has been added to server");
				sendMessage("=yes"+username+"=");
				sendMessage(listNames());
				sendMessage("arrange=");
			} else
				sendMessage("=no"+username+"=");
		} else if (msg.startsWith("bye=")) {
			String name = msg.substring(3).trim();
			if (name != null)
				removeName(name);
		}else if(msg.contains(" has been disconnected") && msg.startsWith("#")){
			groupMembers.remove(msg.substring(1).split(" has been disconnected")[0]);
			sendMessage(listNames());
			sendMessage("arrange=");
		}else if(msg.startsWith("=")){
			
		}
		else{

		}
	}

	private String listNames() {
		String namelist = "namelist=";
		for (int i = 0; i < groupMembers.size(); i++) {
			if(i>0)
				namelist = namelist.concat(",");
			namelist = namelist.concat(groupMembers.get(i));
		}
		return namelist;
	}

	private void removeName(String name) {
		groupMembers.remove(name);
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

	private boolean isUniqueName(String name) {
		if (groupMembers.contains(name))
			return false;
		return true;
	}

	public void closeSocket() {
		socket.close();
	}

	public void setClients(String[] clients) {
		this.clients = clients;
	}

	public String[] getClients() {
		return clients;
	}

	public void setCtr(int ctr) {
		this.ctr = ctr;
	}

	public int getCtr() {
		return ctr;
	}
}
