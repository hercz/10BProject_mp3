package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Searcher
{
	private List<Matcher> matcherList = new ArrayList<Matcher>();

	static Map<ID3TagProperty, Matcher> matcherMap = new HashMap<>();

	static {
		matcherMap.put(ID3TagProperty.TITLE, new TitleMatcher());
	}

	public Searcher(Set<ID3TagProperty> criteriaSet) {
		if (criteriaSet.size() == 0) {
			for (ID3TagProperty id3TagProperty : matcherMap.keySet()) {
				matcherList.add(matcherMap.get(id3TagProperty));
			}

		}
		else {
			for (ID3TagProperty id3TagProperty : criteriaSet) {
				matcherList.add(matcherMap.get(id3TagProperty));
			}
		}
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

	public List<Matcher> getMatcherList()
	{
		return matcherList;
	}

	public int getMatcherListSize()
	{
		return matcherList.size();
	}
}
