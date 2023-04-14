package movies;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * UserTitles store any titles that were favorited or rated by the user
 * 
 * @author Giovanni Soto
 *
 */
public class UserTitles {
	private HashMap<String, UserRating> userTitles;

	/**
	 * Create a new instance with an empty list
	 */
	public UserTitles() {
		userTitles = new HashMap<>();
	}
	
	/**
	 * Add a title to the user interacted titles
	 * @param titleName - Name of the title
	 * @param rating - StarRating of title
	 * @param isFavorite - Is the title favorited
	 */
	public void addTitleToUserList(String titleName, StarRating rating, boolean isFavorite) {
		userTitles.put(titleName, new UserRating(rating, isFavorite));
	}
	
	/**
	 * Add a title to the user interacted titles
	 * @param titleName - Name of the title
	 * @param rating - StarRating of title
	 * @param isFavorite - Is the title favorited
	 */
	public void addTitleToUserList(String titleName, int rating, boolean isFavorite) {
		userTitles.put(titleName, new UserRating(rating, isFavorite));
	}
	
	public void removeTitleFromUserList(String titleName) {
		userTitles.remove(titleName);
	}
	
	/**
	 * Gets the UserRating from a given title name
	 * @param titleName - Name of the title
	 * @return - The UserRating associated with the name, if not found, null
	 */
	public UserRating getRatingFromTitleName(String titleName) {
		return this.userTitles.get(titleName);
	}
	
	public boolean isTitleInUserList(String titleName) {
		return userTitles.containsKey(titleName);
	}
	
	/**
	 * Gets all of the titles that were favorited by user from the TitleDataStore.
	 * @return - Favorited titles
	 */
	public ArrayList<Title> getTitlesFromUserFavorites() {
		ArrayList<Title> favoriteList = new ArrayList<>();
		for (Title title: TitleDataStore.titlesArray) {

			String titleName = title.getTitleName();			
			if (userTitles.containsKey(titleName) && 
					userTitles.get(titleName).isFavorite()) {
				favoriteList.add(title);
			}
		}
		
		return favoriteList;
	}
	
	/**
	 * Creates a new .csv file in the directory of the specified pathname 
	 * with the list of the favorited movies and rating with a table header.
	 * @param pathName - pathnam
	 * @return
	 */
	public boolean exportFavoriteList(String pathName) {
		ArrayList<Title> favoriteList = getTitlesFromUserFavorites();
		
		int i = 1;
		File file = new File(pathName);
		try (FileWriter fileWriter = new FileWriter(file)) {
			String header = "Movie Name,Year,Director,Age Rating,User Rating\n";
			fileWriter.write(header);
			
			for (Title title : favoriteList) {
				String titleName = title.getTitleName();
				UserRating userRatingofTitle = userTitles.get(titleName);
				
				String format = formatExportedUserTitle(title, userRatingofTitle);
				fileWriter.write(format);
				
				if (i < userTitles.size()) {
					fileWriter.write("\n");
					i++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Formats a string with the Title's info minus image and
	 * an addition of the user rating.
	 * @param title
	 * @param userRating
	 * @return
	 */
	public String formatExportedUserTitle(Title title, UserRating userRating) {
		return String.format("%s,%d,%s,%s,%s", 
				title.getTitleName(), title.getReleaseYear(), 
				title.getDirector(), title.getAgeRating(), 
				userRating.formatStarRating());
	}
	
	/**
	 * Used for testing
	 * @param args - not used
	 */
	public static void main(String[] args) {
		TitleDataStore.readFromDataSource("movies");
		
		UserTitles titles = new UserTitles();
		titles.addTitleToUserList("The Shawshank Redemption", StarRating.NO_RATING, true);
		titles.addTitleToUserList("The Dark Knight", StarRating.THREE_STARS, true);
		titles.addTitleToUserList("The Godfather Part II", StarRating.THREE_STARS, false);
		titles.exportFavoriteList("./FavoriteMovies.csv");
		
	}
}
