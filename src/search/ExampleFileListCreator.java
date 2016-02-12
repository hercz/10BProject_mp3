package search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExampleFileListCreator
{
	private static List<File> mp3files = new ArrayList<File>();

	private ExampleFileListCreator()
	{
		mp3files.add(new File("music\\01-infected_mushroom-pink_nightmares.mp3"));
		mp3files.add(new File("music\\01-moby_-_lift_me_up_(indra_remix)-2006-psychedelic.mp3"));
		mp3files.add(new File("music\\02-1200_micrograms_-_numberstruck-ncr.mp3"));
		mp3files.add(new File("music\\03 Gatimo - Psytisfaction.mp3"));
		mp3files.add(new File("music\\03-braincell_-_discovered_by_aliens-upe.mp3"));
		mp3files.add(new File("music\\04-alternative_control_-_logic_choise-mycel.mp3"));
		mp3files.add(new File("music\\05-alternative_control_-_alt_plus_ctrl-mycel.mp3"));
		mp3files.add(new File("music\\05-tesla_principle-soul_river.mp3"));
		mp3files.add(new File("music\\05. zDay - V.V.V.V.V.mp3"));
		mp3files.add(new File("music\\06 - Energize.mp3"));
		mp3files.add(new File("music\\07-cosmo_chaos_-_design_of_a_shiny_mind-upe.mp3"));
		mp3files.add(new File("music\\08. Spectral Seagulls.mp3"));
		mp3files.add(new File("music\\3 Hope Was Closed.mp3"));
	}

	public static List<File> getList()
	{
		ExampleFileListCreator mp3FileList = new ExampleFileListCreator();
		return mp3files;
	}
}
