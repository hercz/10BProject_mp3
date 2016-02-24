package search;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Client
{

	static String host;
	static int port;
	static Socket socket;
	static ObjectOutputStream oos;
	static ObjectInputStream ois;

	public static void send(ObjectOutputStream oos, Object object) throws IOException
	{
		oos.write(0);
		oos.writeObject(object);
	}

	public static void setHostAndPort(String string, int i)
	{
		Client.host = string;
		Client.port = i;
		try
		{
			Client.socket = new Socket(host, port);
			Client.oos = new ObjectOutputStream(socket.getOutputStream());
			Client.ois = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	static class Helper
	{
		// ide jönnek a segéd metódusok...
	}

	public static void main(String[] args) throws UnknownHostException, IOException
	{

		String ponyHost = "192.168.0.1";
		int ponyPort = 1234;

		Commands command = Commands.SEARCH;
		// Object objectToSend = Helper.vmi();

		if (command.equals(Commands.SEARCH))
		{
			// Step 0: Set host and port
			Client.setHostAndPort(ponyHost, ponyPort);

			// Step 1: Collect all your mp3 files
			File f1 = new File("music\\1.mp3");
			File f2 = new File("music\\2.mp3");
			File f3 = new File("music\\3.mp3");
			File f4 = new File("music\\4.mp3");
			File f5 = new File("music\\5.mp3");
			File f6 = new File("music\\6.mp3");
			File f7 = new File("music\\7.mp3");
			File f8 = new File("music\\8.mp3");
			File f9 = new File("music\\9.mp3");
			File f10 = new File("music\\10.mp3");

			// Step 2: Create a hashmap and put your file references into keys
			// and ID3tags into values
			HashMap<File, ID3Tag> hashMap = new HashMap<File, ID3Tag>();
			hashMap.put(f1, ID3Tag.parse(f1));
			hashMap.put(f2, ID3Tag.parse(f2));
			hashMap.put(f3, ID3Tag.parse(f3));
			hashMap.put(f4, ID3Tag.parse(f4));
			hashMap.put(f5, ID3Tag.parse(f5));
			hashMap.put(f6, ID3Tag.parse(f6));
			hashMap.put(f7, ID3Tag.parse(f7));
			hashMap.put(f8, ID3Tag.parse(f8));
			hashMap.put(f9, ID3Tag.parse(f9));
			hashMap.put(f10, ID3Tag.parse(f10));

			// Step 3: send yout hashmap to server
			send(oos, hashMap);

			// Step 4: Send your search criterium
			send(oos, "Fill this String");

			// Step 5: choose your option: default or custom search
			send(oos, Commands.DEFAULT);
			// or
			send(oos, Commands.CUSTOM);

			// Step 5.1: if custom search, fill the hashmap with boolean values
			HashMap<String, Boolean> criteria = new HashMap<String, Boolean>();
			criteria.put("File name", true);
			criteria.put("Title", true);
			criteria.put("Artist", true);
			criteria.put("Album", true);
			criteria.put("Year", true);
			criteria.put("Comment", true);
			criteria.put("Genre", true);

			// Step 5.2: send your criteria in case of custom search
			send(oos, criteria);

			// Step 6: send a command to get your result(s)
			send(oos, Commands.GET);

		}

	}

}
