package movies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is our general data store for our application, it will read our
 * source file and maintain our list of titles that populate the application.
 * 
 * @author Nick Delgado
 *
 */
public class TitleDataStore {
	ArrayList<Title> titles = new ArrayList<Title>();
	public static void main(String[] args) {
		String movieDump = readFromDataSource("movies");
		System.out.println(movieDump);
		addTitle(movieDump);
	}

	public static String readFromDataSource(String filename) {
		String content = "";
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			content = scanner.nextLine();
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static void addTitle(String rawList) {
		String[] titles = rawList.split("\\|\\|"); // We need to escape | since it's a special char in regex. Returns
		for (int i = 0; i < titles.length; i++) {  // Let's run through every individual title and create a title out of it to add to our title list.
			String[] title = titles[i].split(","); // Split the title again to get our individual params.
			Title movie = new Title(title[0], Integer.parseInt(title[1]), title[2], title[3], title[4]);
			System.out.println(movie.toString());
		}


//		System.out.println();

	}
}
