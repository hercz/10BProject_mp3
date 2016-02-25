package matchers;

import search.ID3Tag;

public interface Matcher

{
	boolean matches(String pattern, ID3Tag tag);
}
