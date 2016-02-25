package matchers;

import search.ID3Tag;

public class ArtistMatcher implements Matcher
{

	@Override
	public boolean matches(String pattern, ID3Tag tag)
	{
		return tag.getArtist().contains(pattern);
	}

}
