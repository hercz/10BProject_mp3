package search;

import java.util.ArrayList;
import java.util.List;

public class Searcher
{
	private List<Matcher> matcherList = new ArrayList<Matcher>();

	public Searcher()
	{
		matcherList.add(new FileNameMatcher());
		matcherList.add(new TitleMatcher());
		matcherList.add(new ArtistMatcher());
		matcherList.add(new AlbumMatcher());
		matcherList.add(new YearMatcher());
		matcherList.add(new CommentMatcher());
		matcherList.add(new GenreMatcher());

	}

	public boolean matches(String pattern, ID3Tag tag)
	{
		for (Matcher matcher : matcherList)
		{
			if (matcher.matches(pattern, tag))
			{
				return true;
			}
		}
		return false;
	}

	public void deleteMatcherListElement(int index)
	{
		matcherList.remove(index);
	}

	public List<Matcher> getMatcherList()
	{
		return matcherList;
	}

	public int getMatcherListSize()
	{
		return matcherList.size();
	}
}
