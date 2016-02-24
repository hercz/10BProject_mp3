package search;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MatcherServer
{
	private static Map<File, ID3Tag> hashMap = new HashMap<File, ID3Tag>();
	String pattern;
	private static List<File> result = new ArrayList<File>();

	MatcherServer()
	{
		Searcher searcher = new Searcher();
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
					else if (object instanceof Search && ((Search) object) == Search.DEFAULT)
					{
						for (Entry<File, ID3Tag> entry : hashMap.entrySet())
						{
							if (searcher.matches(pattern, entry.getValue()))
							{
								result.add(entry.getKey());
							}
						}

					}
					else if (object instanceof Search && ((Search) object) == Search.CUSTOM)
					{

					}
					else if (object instanceof Map)
					{

					}
					else if (object instanceof Search && ((Search) object) == Search.GET)
					{
						oos.writeObject(result);
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
