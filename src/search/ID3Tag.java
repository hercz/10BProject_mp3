package search;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class ID3Tag implements Serializable
{
	private String fileName;
	private String title;
	private String artist;
	private String album;
	private String year;
	private String comment;
	private String genre;
	private int genreIndex;

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
		new Genre();

		byte[] last128 = tail(file);

		byte[] baTitle = readXBytes(last128, 3, 33);
		byte[] baArtist = readXBytes(last128, 33, 63);
		byte[] baAlbum = readXBytes(last128, 63, 93);
		byte[] baYear = readXBytes(last128, 93, 97);
		byte[] baComment = readXBytes(last128, 97, 127);
		byte[] baGenre = readXBytes(last128, 127, 128);
		int intGenre = baGenre[0];

		String title = new String(baTitle).trim();
		String artist = new String(baArtist).trim();
		String album = new String(baAlbum).trim();
		String year = new String(baYear).trim();
		String comment = new String(baComment).trim();
		String genre = new String(Genre.getGenreByByteId(intGenre).toString()).trim();

		ID3Tag tag = new ID3Tag();

		tag.setFileName(file.getName());
		tag.setTitle(title);
		tag.setArtist(artist);
		tag.setAlbum(album);
		tag.setYear(year);
		tag.setComment(comment);
		tag.setGenre(genre);
		tag.setGenreIndex(intGenre);

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

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
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

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
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

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public int getGenreIndex()
	{
		return genreIndex;
	}

	public void setGenreIndex(int genreIndex)
	{
		this.genreIndex = genreIndex;
	}

	@Override
	public boolean equals(Object o)
	{
		ID3Tag tag = (ID3Tag) o;
		return title.equals(tag.title) && artist.equals(tag.artist) && album.equals(tag.album) && year.equals(tag.year)
				&& comment.equals(tag.comment) && genre.equals(tag.genre);
	}

	@Override
	public String toString()
	{

		return "Artist: " + (artist.equals("") ? "NULL" : artist) + "\nAlbum: " + (album.equals("") ? "NULL" : album)
				+ "\nTitle: " + (title.equals("") ? "NULL" : title) + "\nYear: " + (year.equals("") ? "NULL" : year)
				+ "\nGenre: " + (genre.equals("") ? "NULL" : genre) + "\nGenreindex: " + genreIndex + "\nComment: "
				+ (comment.equals("") ? "NULL" : comment) + "\n";
	}

}