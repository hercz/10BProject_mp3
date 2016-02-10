package search;

public class ArtistMatcher implements Matcher
{

	@Override
	public boolean matches(String pattern, ID3Tag tag)
	{
		return tag.getArtist().contains(pattern);
	}

}
