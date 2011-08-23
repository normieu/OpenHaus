package openhaus.client;


import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class ClientSender extends Thread {

	private Socket socket;

	public ClientSender(Client client, Socket socket) throws IOException {
		super();
		this.socket = socket;
		start();
	}

	@Override
	public void run() {

		while (true) {

			
			try {
				Thread.sleep(50L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void send(Serializable serializable) throws IOException {
		
		
	}
	
}
