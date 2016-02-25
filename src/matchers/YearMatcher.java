package matchers;

import search.ID3Tag;

public class YearMatcher implements Matcher
{

	@Override
	public boolean matches(String pattern, ID3Tag tag)
	{
		return String.valueOf(tag.getYear()).contains(pattern);
	}

}
