package movies;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Row that holds JLabels for the User favorited titles
 * @author Giovanni Soto
 *
 */
public class TitleTableRow extends JPanel {

	/**
	 * Create the panel.
	 */
	public TitleTableRow(Title title, UserRating userRating) {
		super(new GridLayout(1, 5));
		
		JLabel userRatingLabel = new JLabel(userRating.formatStarRating());
		this.add(userRatingLabel);
		
		JLabel titleNameLabel = new JLabel(title.getTitleName());
		this.add(titleNameLabel);
		
		JLabel releaseYearLabel = new JLabel(Integer.toString(title.getReleaseYear()));
		this.add(releaseYearLabel);
		
		JLabel directorLabel = new JLabel(title.getDirector());
		this.add(directorLabel);
		
		JLabel ageRatingLabel = new JLabel(title.getAgeRating());
		this.add(ageRatingLabel);
	}

}
