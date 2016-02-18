package search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExampleFileListCreator
{
	private static List<File> mp3files = new ArrayList<File>();

	private ExampleFileListCreator()
	{
		mp3files.add(new File("music\\1.mp3"));
		mp3files.add(new File("music\\2.mp3"));
		mp3files.add(new File("music\\3.mp3"));
		mp3files.add(new File("music\\4.mp3"));
		mp3files.add(new File("music\\5.mp3"));
		mp3files.add(new File("music\\6.mp3"));
		mp3files.add(new File("music\\7.mp3"));
		mp3files.add(new File("music\\8.mp3"));
		mp3files.add(new File("music\\9.mp3"));
		mp3files.add(new File("music\\10.mp3"));
	}

	public static List<File> getList()
	{
		ExampleFileListCreator mp3FileList = new ExampleFileListCreator();
		return mp3files;
	}
}
