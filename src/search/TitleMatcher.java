package search;

public class TitleMatcher implements Matcher
{
	@Override
	public boolean matches(String pattern, ID3Tag tag)
	{
		return tag.getTitle().contains(pattern);
	}

}
