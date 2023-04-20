package movies;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;

/**
 * Panel that holds the user interaction for rating and bookmarking
 * @author Giovanni Soto
 *
 */
public class UserRatingAndBookmarkPanel extends JPanel {
	
	private String titleName;

	/**
	 * Create the panel.
	 */
	public UserRatingAndBookmarkPanel(String titleName, UserTitles userTitles) {
		super(new GridLayout(4, 1, 0, 0));
		//gridLayout.setVgap(-400);
		this.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));
		
		JLabel userRatingLabel = new JLabel("Your Rating:");
		
		JPanel StarButtonGrid = new JPanel(new GridLayout(1, 5));
		
		StarButton[] starButtons = new StarButton[5];
		for (int i = 0; i < 5; i++) {
			starButtons[i] = new StarButton(i + 1);
			if (userTitles.isTitleInUserList(titleName)) {
				UserRating userRating = userTitles.getRatingFromTitleName(titleName);
				if (userRating.starRatingToInt() == (i + 1)) {
					starButtons[i].toggleColor();
				}
			}
			
			starButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					StarButton sourceButton = ((StarButton)e.getSource());
					int sourceRating = sourceButton.getRating();
					
					for (int i = 0; i < 5; i++) {
						if ((i + 1) != sourceRating) {
							starButtons[i].setDeselected();
						}
					}
					sourceButton.setSelected();
					
					
					if (userTitles.isTitleInUserList(titleName)) {
						int numericRating = ((StarButton)e.getSource()).getRating();
						UserRating userRating = userTitles.getRatingFromTitleName(titleName);
						userTitles.addTitleToUserList(titleName, numericRating, userRating.isFavorite());
					} else {
						UserRating newRating = new UserRating(((StarButton)e.getSource()).getRating(), false);
						userTitles.addTitleToUserList(titleName, newRating.getRating(), newRating.isFavorite());
					}
				}
			});
			
			StarButtonGrid.add(starButtons[i]);
		}
		
		/*
		JTextField ratingField = new JTextField("Rate");
		if (userTitles.isTitleInUserList(titleName)) {
			UserRating userRating = userTitles.getRatingFromTitleName(titleName);
			ratingField.setText(userRating.starRatingToString());
		}
		
		ratingField.addKeyListener(new KeyAdapter() {
			@Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	            	String textInBox = ((JTextField)e.getSource()).getText();
	            	if (userTitles.isTitleInUserList(titleName)) {
						UserRating userRating = userTitles.getRatingFromTitleName(titleName);
						userTitles.addTitleToUserList(titleName, Integer.parseInt(textInBox), userRating.isFavorite());
					} else {
						StarRating rating = UserRating.getStarRatingFromInt(Integer.parseInt(textInBox));
						UserRating newRating = new UserRating(rating, false);
						userTitles.addTitleToUserList(titleName, newRating.getRating(), newRating.isFavorite());
					}
	            }
	        }
		});
		*/
		
		JPanel favoritesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel addToFavoritesLabel = new JLabel("Add to Favorites:");
		
		JCheckBox favoritedCheckbox = new JCheckBox();
		
		if (userTitles.isTitleInUserList(titleName)) {
			UserRating userRating = userTitles.getRatingFromTitleName(titleName);
			favoritedCheckbox.setSelected(userRating.isFavorite());
		}

		favoritedCheckbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if (userTitles.isTitleInUserList(titleName)) {
						UserRating userRating = userTitles.getRatingFromTitleName(titleName);
			            userTitles.addTitleToUserList(titleName, userRating.getRating(), true);
					} else {
						userTitles.addTitleToUserList(titleName, StarRating.NO_RATING, true);
					}
		        } else {
		        	UserRating userRating = userTitles.getRatingFromTitleName(titleName);
		        	userTitles.addTitleToUserList(titleName, userRating.getRating(), false);
		        }
				
			}
		});
		
		favoritesPanel.add(addToFavoritesLabel);
		favoritesPanel.add(favoritedCheckbox);

		this.add(userRatingLabel);
		this.add(StarButtonGrid);
		this.add(favoritesPanel);
		//this.add(ratingField);
		//this.add(addToFavoritesLabel);
		//this.add(favoritedCheckbox);
	}

}
