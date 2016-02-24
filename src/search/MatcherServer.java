package search;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MatcherServer
{
	static Map<File, ID3Tag> hashMap = new HashMap<File, ID3Tag>();
	String pattern;

	MatcherServer()
	{

		try
		{
			ServerSocket serverSocket = new ServerSocket(1234);
			System.out.println("Server started...");
			Socket server = serverSocket.accept();
			System.out.println("Client connected.");

			ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());

			while (true)
			{
				if (ois.read() > -1)
				{
					Object object = ois.readObject();

					if (object instanceof Map)
					{
						hashMap = (Map<File, ID3Tag>) object;
					}
					else if (object instanceof String)
					{
						pattern = (String) object;
					}
					else if (object instanceof Commands && ((Commands) object) == Commands.DEFAULT)
					{
						for (Entry<File, ID3Tag> entry : hashMap.entrySet())
						{
							System.out.println("Key: \n" + entry.getKey() + "\nValues:\n" + entry.getValue());
						}

					}
					else if (object instanceof Commands && ((Commands) object) == Commands.CUSTOM)
					{

					}
					else if (object instanceof Map)
					{

					}
					else if (object instanceof Commands && ((Commands) object) == Commands.GET)
					{

					}
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void main(String[] args)
	{
		new MatcherServer();
	}
}
