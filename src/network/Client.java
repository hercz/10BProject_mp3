package network;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import search.ID3TagProperty;

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
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	static class Helper
	{
		public static byte[] tail(File file)
		{
			try
			{
				RandomAccessFile fileHandler = new RandomAccessFile(file, "r");
				long fileLength = fileHandler.length() - 1;
				byte[] buffer = new byte[128];
				for (int i = 0; i < buffer.length; i++)
				{
					fileHandler.seek(fileLength - 127 + i);
					buffer[i] = fileHandler.readByte();
				}
				fileHandler.close();
				return buffer;
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException
	{

		// String ponyHost = "192.168.0.1";
		String ponyHost = "localhost";
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

			// Step 2: Create a hashmap and put your file's references into keys
			// and last 128 bytes into values
			Map<File, byte[]> hashMap = new HashMap<File, byte[]>();
			hashMap.put(f1, Helper.tail(f1));
			hashMap.put(f2, Helper.tail(f2));
			hashMap.put(f3, Helper.tail(f3));
			hashMap.put(f4, Helper.tail(f4));
			hashMap.put(f5, Helper.tail(f5));
			hashMap.put(f6, Helper.tail(f6));
			hashMap.put(f7, Helper.tail(f7));
			hashMap.put(f8, Helper.tail(f8));
			hashMap.put(f9, Helper.tail(f9));
			hashMap.put(f10, Helper.tail(f10));

			// Step 3: send hashmap to server
			send(oos, hashMap);

			// Step 4: Send your search criterium
			send(oos, "Happy");

			// Step 5: choose an option: default or custom search
			// 5.1 DEFAULT SEARCH
			// send(oos, Search.DEFAULT);
			// try
			// {
			// List<File> result = (List<File>) ois.readObject();
			// for (File file : result)
			// {
			// System.out.println(file);
			// }
			// } catch (ClassNotFoundException e)
			// {
			// e.printStackTrace();
			// }

			// Step 5.2: CUSTOM SEARCH
			// if custom search, fill the list with boolean values
			// boolean[] criteria = new boolean[7];
			// criteria[0] = true; // Name
			// criteria[1] = false; // Title
			// criteria[2] = false; // Artist
			// criteria[3] = false; // Album
			// criteria[4] = false; // Year
			// criteria[5] = true; // Comment
			// criteria[6] = false; // Genre

			Set<ID3TagProperty> criteriaSet = new HashSet<>();
			criteriaSet.add(ID3TagProperty.FILENAME);
			criteriaSet.add(ID3TagProperty.TITLE);
			criteriaSet.add(ID3TagProperty.ARTIST);
			criteriaSet.add(ID3TagProperty.ALBUM);
			criteriaSet.add(ID3TagProperty.YEAR);
			criteriaSet.add(ID3TagProperty.GENRE);
			criteriaSet.add(ID3TagProperty.COMMENT);

			// send your criteria in case of custom search
			send(oos, criteriaSet);

			socket.close();

		}

	}

}
