package search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * The genre list as defined in
 * https://de.wikipedia.org/wiki/Liste_der_ID3v1-Genres
 */
public class Genre
{
	private int id;
	private String name;
	private static HashMap<Integer, String> genre = new HashMap<Integer, String>();

	Genre()
	{
		BufferedReader in;
		try
		{
			in = new BufferedReader(new FileReader("genre.txt"));
			String line = "";
			while ((line = in.readLine()) != null)
			{
				String parts[] = line.split("\t");
				Integer key = Integer.valueOf(parts[0]);
				genre.put(key, parts[1]);
			}
			in.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String getName()
	{
		return name;
	}

	public int getId()
	{
		return id;
	}

	public static String getGenreByByteId(int id)
	{
		if (id < -1)
		{
			id += 256;
		}
		if (id >= genre.size())
		{
			return genre.get(-1);
		}
		else
		{
			return genre.get(id);
		}
	}

}
