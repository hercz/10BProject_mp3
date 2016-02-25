package search;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MatcherServer {
	private static Map<File, byte[]> hashMap = new HashMap<File, byte[]>();
	String pattern;
	private static List<File> result = new ArrayList<File>();
	private static boolean[] list = new boolean[7];

	MatcherServer() {
		Searcher searcher = new Searcher();
		try {
			ServerSocket serverSocket = new ServerSocket(1234);
			System.out.println("Server started...");
			Socket server = serverSocket.accept();
			System.out.println("Client connected.");

			ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());

			while (true) {
				if (ois.read() > -1) {
					Object object = ois.readObject();

					if (object instanceof Map) {
						hashMap = (Map<File, byte[]>) object;
					}

					else if (object instanceof String) {
						pattern = (String) object;
					}

					else if (object instanceof Search && ((Search) object) == Search.DEFAULT) {
						for (Entry<File, byte[]> entry : hashMap.entrySet()) {
							if (searcher.matches(pattern, ID3Tag.parse(entry.getValue(), entry.getKey()))) {
								result.add(entry.getKey());
								oos.writeObject(result);
							}
						}

					}

					else if (object instanceof boolean[]) {

						list = (boolean[]) object;
						int k = 0;
						for (int i = 0; i < list.length; i++) {
							if (list[i] == false) {
								searcher.deleteMatcherListElement(k);
							}
							if (list[i] == true) {
								k += 1;
							}
						}
					}

					else if (object instanceof Search && ((Search) object) == Search.CUSTOM) {
						for (Boolean criteria : list) {

							System.out.println(criteria);

						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new MatcherServer();
	}
}
