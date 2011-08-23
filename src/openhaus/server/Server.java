package openhaus.server;

import java.io.IOException;
import java.net.ServerSocket;

import openhaus.common.logger.Log;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		ServerThread serverudp = null;
		
		boolean listening = true;
		try {
			serverSocket = new ServerSocket(4444);
			serverudp = new ServerThread();
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(-1);
		}

		while (listening) {
			Log.print("listening..");
			new MultiServerThread(serverSocket.accept(),serverudp).start();
		}
		serverSocket.close();
	}
}
