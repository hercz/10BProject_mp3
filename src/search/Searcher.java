package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import matchers.AlbumMatcher;
import matchers.ArtistMatcher;
import matchers.CommentMatcher;
import matchers.FileNameMatcher;
import matchers.GenreMatcher;
import matchers.Matcher;
import matchers.TitleMatcher;
import matchers.YearMatcher;

public class Searcher {
	private List<Matcher> matcherList = new ArrayList<Matcher>();

	static Map<ID3TagProperty, Matcher> matcherMap = new HashMap<>();

	static {

		matcherMap.put(ID3TagProperty.FILENAME, new FileNameMatcher());
		matcherMap.put(ID3TagProperty.TITLE, new TitleMatcher());
		matcherMap.put(ID3TagProperty.ARTIST, new ArtistMatcher());
		matcherMap.put(ID3TagProperty.ALBUM, new AlbumMatcher());
		matcherMap.put(ID3TagProperty.YEAR, new YearMatcher());
		matcherMap.put(ID3TagProperty.GENRE, new GenreMatcher());
		matcherMap.put(ID3TagProperty.COMMENT, new CommentMatcher());
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

	public boolean matches(String pattern, ID3Tag tag) {
		for (Matcher matcher : matcherList) {
			if (matcher.matches(pattern, tag)) {
				return true;
			}
		}
		return false;
	}

	public List<Matcher> getMatcherList() {
		return matcherList;
	}

	public int getMatcherListSize() {
		return matcherList.size();
	}
}
