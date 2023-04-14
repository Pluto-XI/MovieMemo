package movies;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	
	private void createControlPanel(UserTitles userTitles) {
		JPanel favoritePanelControls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton exportButton = new JButton("Export");
		exportButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    userTitles.exportFavoriteList("./FavoriteMovies.csv");
			}
		});
		
		favoritePanelControls.add(exportButton);
		this.add(favoritePanelControls);
	}

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
	
	private void createMovieTable(UserTitles userTitles) {
		for (Title title : userTitles.getTitlesFromUserFavorites()) {
			String titleName = title.getTitleName();
			UserRating userRating = userTitles.getRatingFromTitleName(titleName);
			
			TitleTableRow row = new TitleTableRow(title, userRating);
			this.add(row);
		}
	}
	
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
