package movies;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is our general data store for our application, it will read our source file and maintain our list of titles that populate the application.
 * 
 * @author Nick Delgado
 *
 */
public class TitleDataStore {
	public static void main(String[] args) {
		System.out.println("YO");
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
}
