package matchers;

import search.ID3Tag;

public class AlbumMatcher implements Matcher
{

	@Override
	public boolean matches(String pattern, ID3Tag tag)
	{
		return tag.getAlbum().contains(pattern);
	}

}
