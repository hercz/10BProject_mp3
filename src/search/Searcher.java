package search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Searcher
{
	List<Matcher> searchCriterion = new ArrayList<Matcher>();
	boolean isSearchFileName = true;
	Scanner console = new Scanner(System.in);

	public Searcher()
	{
		searchCriterion.add(new TitleMatcher());
		searchCriterion.add(new ArtistMatcher());
		searchCriterion.add(new AlbumMatcher());
		searchCriterion.add(new YearMatcher());
		searchCriterion.add(new CommentMatcher());
		searchCriterion.add(new GenreMatcher());

	}

	public String getSearchCriterium()
	{
		System.out.println("Searching for: ");
		String searchAnswer = console.nextLine();
		return searchAnswer;
	}

	public void getConsole()
	{
		String no = "no";
		int maxSize = searchCriterion.size();
		int deleteCounter = 0;

		System.out.println("Would you like to choose any searching options? [yes/no]");
		String defaultSearchAnswer = console.nextLine();

		if (defaultSearchAnswer.equals("yes"))
		{

			System.out.println("Would you like to search in the name of the file?  [yes/no]");
			String fileAnswer = console.nextLine();
			if (fileAnswer.equals(no))
			{
				isSearchFileName = false;
			}

			System.out.println("Would you like to search in the title of the mp3's tag?  [yes/no]");
			String titleAnswer = console.nextLine();
			if (titleAnswer.equals(no))
			{
				searchCriterion.remove(maxSize - maxSize);
				deleteCounter += 1;

			}

			System.out.println("Would you like to search in the artist of the mp3's tag?  [yes/no]");
			String artistAnswer = console.nextLine();
			if (artistAnswer.equals(no))
			{
				searchCriterion.remove(maxSize - 4 - deleteCounter); // 1
				deleteCounter += 1;
			}

			System.out.println("Would you like to search in the album of the mp3's tag?  [yes/no]");
			String albumAnswer = console.nextLine();
			if (albumAnswer.equals(no))
			{
				searchCriterion.remove(maxSize - 3 - deleteCounter); // 2
				deleteCounter += 1;
			}

			System.out.println("Would you like to search in the year of the mp3's tag?  [yes/no]");
			String yearAnswer = console.nextLine();
			if (yearAnswer.equals(no))
			{
				searchCriterion.remove(maxSize - 2 - deleteCounter); // 3
				deleteCounter += 1;
			}

			System.out.println("Would you like to search in the comment of the mp3's tag?  [yes/no]");
			String commentAnswer = console.nextLine();
			if (commentAnswer.equals(no))
			{
				searchCriterion.remove(maxSize - 1 - deleteCounter); // 4
				deleteCounter += 1;
			}

			System.out.println("Would you like to search in the genre of the mp3's tag?  [yes/no]");
			String genreAnswer = console.nextLine();
			if (genreAnswer.equals(no))
			{
				searchCriterion.remove(maxSize - deleteCounter); // 5
			}
		}
		console.close();

	}

	public boolean matches(String pattern, File file)
	{
		ID3Tag tag = ID3Tag.parse(file);
		for (Matcher matcher : searchCriterion)
		{
			if (matcher.matches(pattern, tag))
			{
				System.out.println(file);
				return true;

			}

			if (isSearchFileName && file.getName().contains(pattern))
			{
				System.out.println(file);
				return true;
			}
		}
		return false;
	}
}
