package search;

import java.io.File;
import java.util.List;

public class Test
{
	public static void main(String[] args)
	{

		new Genre();
		ConsoleManager console = new ConsoleManager();
		Searcher searcher = new Searcher();
		List<File> mp3files = ExampleFileListCreator.getList();

		console.setSearchCriteria();
		System.out.println(console.getDefaultLine());
		console.setAnswerSwitcher();

		int maxListSize = searcher.getMatcherListSize();
		if (console.isAnswerSwitcherYes())
		{
			for (int i = 0; i < maxListSize; i++)
			{
				int j = searcher.getMatcherListSize();
				console.setQuestionLine(i);
				System.out.println(console.getQuestionLine());
				console.setAnswer();
				if (console.isAnswerNo())
				{
					searcher.deleteMatcherListElement(i + j - maxListSize);
				}
			}
		}
		console.closeConsole();

		int matchesCounter = 0;
		for (File file : mp3files)
		{
			if (searcher.matches(console.getSearchCriteria(), file))
			{
				System.out.println(file.getName());
				matchesCounter += 1;
			}
		}
		System.out.printf("\nCount: %d", matchesCounter);

	}

}
