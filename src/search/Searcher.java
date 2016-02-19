package search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Searcher
{
	private List<Matcher> searchCriterion = new ArrayList<Matcher>();

	public Searcher()
	{
		searchCriterion.add(new FileNameMatcher());
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
				return true;
			}
		}
		return false;
	}

	public void deleteListElement(int index)
	{
		searchCriterion.remove(index);
	}

	public List<Matcher> getSearchCriterion()
	{
		return searchCriterion;
	}

	public int getListSize()
	{
		return searchCriterion.size();
	}
}
