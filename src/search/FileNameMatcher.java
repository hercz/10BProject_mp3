package search;

public class FileNameMatcher implements Matcher
{
	@Override
	public boolean matches(String pattern, ID3Tag tag)
	{
		return tag.getFileName().contains(pattern);
	}
}
