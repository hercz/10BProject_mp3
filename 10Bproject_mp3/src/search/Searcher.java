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
		searchCriterion.add(new YearMatcher());

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

			if (isSearchFileName && file.getName().contains(pattern))
			{
				return true;
			}
		}
		return false;
	}
}
