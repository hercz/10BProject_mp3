package search;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	static String host;
	static int port;
	static Socket socket;
	static ObjectOutputStream oos;
	static ObjectInputStream ois;

	public static void send(ObjectOutputStream oos, Object object) throws IOException {
		oos.write(0);
		oos.writeObject(object);
	}

	public static void setHostAndPort(String string, int i) {
		Client.host = string;
		Client.port = i;
		try {
			Client.socket = new Socket(host, port);
			Client.oos = new ObjectOutputStream(socket.getOutputStream());
			Client.ois = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class Helper {
		// ide jönnek a segéd metódusok...
	}

	public static void main(String[] args) throws UnknownHostException, IOException {

		String ponyHost = "192.168.0.1";
		int ponyPort = 1234;

		Commands command = Commands.SEARCH;
		Object objectToSend = Helper.vmi();

		if (command.equals(Commands.SEARCH)) {
			Client.setHostAndPort(ponyHost, ponyPort);
			send(oos, objectToSend);
		}

	}

}
