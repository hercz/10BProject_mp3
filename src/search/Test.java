package search;

import java.io.File;
import java.util.List;

public class Test
{
	public static void main(String[] args)
	{

		new Genre();
		Searcher s = new Searcher();

		// when we search in everything
		String searchingFor = s.getSearchCriterium();
		s.getConsole();
		int matchesCounter = 0;
		List<File> mp3files = ExampleFileListCreator.getList();
		for (int i = 0; i < mp3files.size(); i++)
		{
			File mp3 = mp3files.get(i);
			if (s.matches(searchingFor, mp3))
			{
				matchesCounter += 1;
			}
		}
		System.out.printf("\nCount: %d", matchesCounter);

	}

}
