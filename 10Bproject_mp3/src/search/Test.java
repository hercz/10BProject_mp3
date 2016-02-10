package search;

import java.io.File;

public class Test
{
	public static void main(String[] args)
	{
		File mp3 = new File("C:\\*.mp3");
		Searcher s = new Searcher();
		ID3Tag x = ID3Tag.parse(mp3);
		System.out.println(x);

		System.out.println(s.matches("pattern", mp3));
	}

}
