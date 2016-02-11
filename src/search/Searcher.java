package search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Searcher
{
	List<Matcher> searchCriterion = new ArrayList<Matcher>();
	boolean isSearchFileName = true;

	public Searcher()
	{
		searchCriterion.add(new TitleMatcher());
		searchCriterion.add(new ArtistMatcher());
		searchCriterion.add(new AlbumMatcher());
		searchCriterion.add(new YearMatcher());
		searchCriterion.add(new CommentMatcher());
		searchCriterion.add(new GenreMatcher());

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
