package search;

import java.io.File;

public class Test
{
	public static void main(String[] args)
	{
		File mp3 = new File("C:" + File.separator + "*.mp3");
		ID3Tag x = ID3Tag.parse(mp3);
		System.out.println(x);

		Searcher s = new Searcher();
		System.out.println(s.matches("pattern", mp3));
	}

}
