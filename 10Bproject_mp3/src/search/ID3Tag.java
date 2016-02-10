package search;

import java.io.File;
import java.io.RandomAccessFile;

public class ID3Tag
{
	private String title;
	private String artist;
	private String album;
	private int year;
	private String comment;
	private int genre;

	private ID3Tag()
	{
	}

	private static byte[] readXBytes(byte[] byteArray, int fromPos, int toPos)
	{
		byte[] resultArray = new byte[toPos - fromPos];
		for (int i = fromPos; i < toPos; i++)
		{
			resultArray[i - fromPos] = byteArray[i];
		}
		return resultArray;
	}

	public static ID3Tag parse(File file)
	{
		byte[] last128 = tail(file);
		byte[] baTitle = readXBytes(last128, 3, 33);
		String title = new String(baTitle).trim();
		ID3Tag tag = new ID3Tag();
		tag.setTitle(title);
		return tag;
	}

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
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	public String getAlbum()
	{
		return album;
	}

	public void setAlbum(String album)
	{
		this.album = album;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public int getGenre()
	{
		return genre;
	}

	public void setGenre(int genre)
	{
		this.genre = genre;
	}

	@Override
	public boolean equals(Object o)
	{
		ID3Tag tag = (ID3Tag) o;
		return ((title == null && tag.title == null) || title.equals(tag.title))
				&& ((artist == null && tag.artist == null) || artist.equals(tag.artist))
				&& ((album == null && tag.album == null) || album.equals(tag.album)) && (year == tag.year)
				&& ((comment == null && tag.comment == null) || comment.equals(tag.comment)) && (genre == tag.genre);
	}

	@Override
	public int hashCode()
	{
		return -1;
	}

	@Override
	public String toString()
	{
		return "\nArtist: " + (artist == null ? "NULL" : artist) + "\nAlbum: " + album + "Title: " + title + "\nYear: "
				+ year + "\nGenre: " + genre;
	}

	public static void main(String[] args)
	{
		ID3Tag tag1 = ID3Tag
				.parse(new File("c:" + File.separator + "mp3" + File.separator + "Robin Schulz - Sugar (Radio Edit)"));
		System.out.println(tag1);
	}
}