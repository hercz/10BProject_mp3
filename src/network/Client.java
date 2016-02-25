package network;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import search.ID3Tag;
import search.ID3TagProperty;

public class Client {

	String ponyHost;
	int ponyPort;

	public Client(String ponyHost, int ponyPort) {

		Socket socket;
		this.ponyHost = ponyHost;
		this.ponyPort = ponyPort;

		try {
			// String ponyHost = "192.168.0.1";

			socket = new Socket(ponyHost, ponyPort);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

			Commands command = Commands.SEARCH;
			// Object objectToSend = Helper.vmi();

			if (command.equals(Commands.SEARCH)) {
				// Step 0: Set host and port

				// Step 1: Collect all your mp3 files
				File f1 = new File("music\\1.mp3");
				File f2 = new File("music\\2.mp3");
				File f3 = new File("music\\3.mp3");
				File f4 = new File("music\\4.mp3");
				File f5 = new File("music\\5.mp3");
				File f6 = new File("music\\6.mp3");
				File f7 = new File("music\\7.mp3");
				File f8 = new File("music\\8.mp3");
				File f9 = new File("music\\9.mp3");
				File f10 = new File("music\\10.mp3");

				// Step 2: Create a hashmap and put your file's references into
				// keys
				// and ID3Tags into values
				Map<File, ID3Tag> hashMap = new HashMap<File, ID3Tag>();
				hashMap.put(f1, ID3Tag.parse(f1));
				hashMap.put(f2, ID3Tag.parse(f2));
				hashMap.put(f3, ID3Tag.parse(f3));
				hashMap.put(f4, ID3Tag.parse(f4));
				hashMap.put(f5, ID3Tag.parse(f5));
				hashMap.put(f6, ID3Tag.parse(f6));
				hashMap.put(f7, ID3Tag.parse(f7));
				hashMap.put(f8, ID3Tag.parse(f8));
				hashMap.put(f9, ID3Tag.parse(f9));
				hashMap.put(f10, ID3Tag.parse(f10));

				// Step 3: send hashmap to server

				send(oos, hashMap);

				// Step 4: Send your search criterium
				send(oos, "mp3");


				// Step 5: choose an option: default or custom search

				Set<ID3TagProperty> criteriaSet = new HashSet<>();
				// criteriaSet.add(ID3TagProperty.FILENAME);
				// criteriaSet.add(ID3TagProperty.TITLE);
				criteriaSet.add(ID3TagProperty.ARTIST);
				// criteriaSet.add(ID3TagProperty.ALBUM);
				// criteriaSet.add(ID3TagProperty.YEAR);
				criteriaSet.add(ID3TagProperty.GENRE);
				criteriaSet.add(ID3TagProperty.COMMENT);

				// send your criteria in case of custom search
				send(oos, criteriaSet);

				List<File> result = (List<File>) ois.readObject();
				for (File file : result) {
					System.out.println(file);
				}

				System.out.println((String) ois.readObject());
				send(oos, "6");

				Set<ID3TagProperty> criteriaSet2 = new HashSet<>();
				criteriaSet2.add(ID3TagProperty.FILENAME);
				criteriaSet2.add(ID3TagProperty.TITLE);
				criteriaSet2.add(ID3TagProperty.ARTIST);
				criteriaSet2.add(ID3TagProperty.ALBUM);
				criteriaSet2.add(ID3TagProperty.YEAR);
				// criteriaSet2.add(ID3TagProperty.GENRE);
				// criteriaSet2.add(ID3TagProperty.COMMENT);

				send(oos, criteriaSet2);

				List<File> result2 = (List<File>) ois.readObject();
				for (File file : result2) {
					System.out.println(file);
				}

				System.out.println((String) ois.readObject());

				send(oos, Commands.EXIT);

				System.out.println("Client disconnected!");
				ois.close();
				oos.close();
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(ObjectOutputStream oos, Object object) throws IOException {
		oos.write(-1);
		oos.writeObject(object);
	}

	public static void main(String[] args) {

		new Client("localhost", 1234);
	}
}
