package search;

public class GenreMatcher implements Matcher
{

	@Override
	public boolean matches(String pattern, ID3Tag tag)
	{
		return String.valueOf(tag.getGenre()).contains(pattern);
	}

}
