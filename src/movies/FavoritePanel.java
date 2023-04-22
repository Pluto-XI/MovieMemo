package movies;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Panel that holds the list of favorites
 * @author Giovanni Soto
 */
public class FavoritePanel extends JPanel {
	
	//private UserTitles userTitles;

	/**
	 * Create the panel.
	 */
	public FavoritePanel(UserTitles userTitles) {
		super(new GridLayout(10, 1));
		
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		createControlPanel(userTitles);
		createTableHeader();
		createMovieTable(userTitles);
	}
	
	/**
	 * Create the Control Panel which is made up of the favorite movie label
	 * and the export button.
	 * @param userTitles - The User list of titles
	 */
	private void createControlPanel(UserTitles userTitles) {
		JPanel favoritePanelControls = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel myFavoritesLabel = new JLabel("My Favorite Movies:");
		JButton exportButton = new JButton("Export");
		exportButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    userTitles.exportFavoriteList("./FavoriteMovies.csv");
			}
		});
		
		favoritePanelControls.add(myFavoritesLabel);
		favoritePanelControls.add(exportButton);
		this.add(favoritePanelControls);
	}

	/**
	 * Creates the table headers
	 */
	private void createTableHeader() {
		JPanel tableHeaderPanel = new JPanel(new GridLayout(1, 5));
		
		JLabel userRatingHeaderLabel = new JLabel("Rating");
		tableHeaderPanel.add(userRatingHeaderLabel);
		
		JLabel titleNameHeaderLabel = new JLabel("Title");
		tableHeaderPanel.add(titleNameHeaderLabel);
		
		JLabel releaseYearHeaderLabel = new JLabel("Year");
		tableHeaderPanel.add(releaseYearHeaderLabel);
		
		JLabel directorHeaderLabel = new JLabel("Director");
		tableHeaderPanel.add(directorHeaderLabel);
		
		JLabel ageRatingHeaderLabel = new JLabel("Age Rating");
		tableHeaderPanel.add(ageRatingHeaderLabel);
		
		this.add(tableHeaderPanel);
	}
	
	/**
	 * Create a table which is made up of rows of the user title favorites
	 * @param userTitles - The user rated titles and favorites
	 */
	private void createMovieTable(UserTitles userTitles) {
		for (Title title : userTitles.getTitlesFromUserFavorites()) {
			String titleName = title.getTitleName();
			UserRating userRating = userTitles.getRatingFromTitleName(titleName);
			
			TitleTableRow row = new TitleTableRow(title, userRating);
			this.add(row);
		}
	}
	
	/**
	 * Update the rows to reflect new data entered in user titles.
	 * @param userTitles - The user rated titles and favorites
	 */
	public void updateMovieTable(UserTitles userTitles) {
		this.removeAll();
		this.revalidate();
	    this.repaint();
	    
	    createControlPanel(userTitles);
	    createTableHeader();
	    
		for (Title title : userTitles.getTitlesFromUserFavorites()) {
			String titleName = title.getTitleName();
			UserRating userRating = userTitles.getRatingFromTitleName(titleName);
			
			TitleTableRow row = new TitleTableRow(title, userRating);
			this.add(row);
		}
		
		this.revalidate();
	    this.repaint();
	}
}
