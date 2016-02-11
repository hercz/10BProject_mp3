package search;

public class YearMatcher implements Matcher
{

	@Override
	public boolean matches(String pattern, ID3Tag tag)
	{
		return String.valueOf(tag.getYear()).contains(pattern);
	}

}
