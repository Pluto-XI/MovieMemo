package movies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is our general data store for our application, it will read our
 * source file and maintain our list of titles that populate the application.
 * 
 * @author Nick Delgado
 *
 */
public class TitleDataStore {
	public static ArrayList<Title> titlesArray = new ArrayList<Title>(); //Our global array for our titles pulled into the application.
	
	
//	public static void main(String[] args) {
//		readFromDataSource("movies"); //Read in our movies from the file.
//		System.out.println(findTitleFromName("offIce spaCE").toString());
//		List<String> titleNames = new ArrayList<String>();
//		titleNames.add("office space");
//		titleNames.add("the shawshank redemption");
//		titleNames.add("the dark knight");
//
//		System.out.println( findListOfTitlesFromNames(titleNames).toString());
//	}

	/**
	 * Uses a scanner to pull a string from our movie dataset.
	 * @param filename The name of our dataset file for movies.
	 * @return A String of all of our movies, including delimiters.
	 */
	public static void readFromDataSource(String filename) {
		String movieDump = "";
		
		InputStream fileStream = TitleDataStore.class.getResourceAsStream(filename);
		Scanner scanner = new Scanner(fileStream);
		movieDump = scanner.nextLine();
		scanner.close();
		
		addTitle(movieDump);
	}
	
	
	/**
	 * Adds all of our titles from the dataset into our titles array so that we can access it. Splits our strings based on delimiters and creates titles based on these.
	 * @param rawList The raw string from our read from data source method.
	 */
	public static void addTitle(String rawList) {
		String[] titles = rawList.split("\\|\\|"); // We need to escape | since it's a special char in regex.
		for (int i = 0; i < titles.length; i++) {  // Let's run through every individual title and create a title out of it to add to our title list.
			String[] title = titles[i].split(","); // Split the title again to get our individual params.
			Title movie = new Title(title[0], Integer.parseInt(title[1]), title[2], title[3], title[4]);
			titlesArray.add(movie);
		}

	}
	
	/**
	 * Method to find a title based on the name.
	 * @param titleName String, this is the name of the title you're looking for.
	 * @return Returns the title object or null if none is found.
	 */
	public static Title findTitleFromName(String titleName) {
		for (int i = 0; i < titlesArray.size(); i++) {
			if (titlesArray.get(i).getTitleName().equalsIgnoreCase(titleName)) {
				return titlesArray.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * Method to find a list of titles based on a list of names passed in.
	 * 
	 * @param titleNames A String list of the names you're looking for.
	 * @return Returns a List Interface of the movie objects.
	 */
	public static List<Title> findListOfTitlesFromNames(List<String> titleNames) {
	    List<Title> matchingTitles = new ArrayList<>();
	    for (String titleName : titleNames) {
	        Title matchingTitle = findTitleFromName(titleName);
	        if (matchingTitle != null) {
	            matchingTitles.add(matchingTitle);
	        }
	    }
	    return matchingTitles;
	}
}
