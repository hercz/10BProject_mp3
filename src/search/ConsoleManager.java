package search;

import java.util.Scanner;

public class ConsoleManager
{
	private String searchCriteria;
	private String answerSwitcher;
	private String answer;
	private String questionLine;
	private final String[] name = { "name", "title", "artist", "album", "year", "comment", "genre" };
	private Scanner console = new Scanner(System.in);

	public String getSearchCriteria()
	{
		return searchCriteria;
	}

	public void setSearchCriteria()
	{
		System.out.println("Searching for: ");
		String searchCriteria = console.nextLine();
		this.searchCriteria = searchCriteria;
	}

	public String getAnswerSwitcher()
	{
		return answerSwitcher;
	}

	public void setAnswerSwitcher()
	{
		String answerSwitcher;
		do
		{
			answerSwitcher = console.nextLine();
		} while (!(answerSwitcher.equals("yes") || answerSwitcher.equals("no")));
		this.answerSwitcher = answerSwitcher;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer()
	{
		String answer;
		do
		{
			answer = console.nextLine();
		} while (!(answer.equals("yes") || answer.equals("no")));
		this.answer = answer;
	}

	public String getQuestionLine()
	{
		return questionLine;
	}

	public void setQuestionLine(int index)
	{
		String questionLine = String.format("Would you like to search in the %s of the file?  [yes/no]", name[index]);
		this.questionLine = questionLine;
	}

	public String getDefaultLine()
	{
		return "Would you like to choose any searching options? [yes/no]";
	}

	public boolean isAnswerSwitcherYes()
	{
		return answerSwitcher.equals("yes");
	}

	public boolean isAnswerNo()
	{
		return answer.equals("no");
	}

	public void closeConsole()
	{
		console.close();
	}
}
