package matchers;

import search.ID3Tag;

public class CommentMatcher implements Matcher
{

	@Override
	public boolean matches(String pattern, ID3Tag tag)
	{
		return tag.getComment().contains(pattern);
	}

}
