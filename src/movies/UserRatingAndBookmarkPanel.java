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

public class UserRatingAndBookmarkPanel extends JPanel {
	
	private String titleName;

	/**
	 * Create the panel.
	 */
	public UserRatingAndBookmarkPanel(String titleName, UserTitles userTitles) {
		super(new GridLayout(4, 1));
		
		JLabel userRatingLabel = new JLabel("Your Rating:");
		JLabel addToFavoritesLabel = new JLabel("Favorite:");
		
		/*
		StarButton[] starButtons = new StarButton[5];
		for (int i = 0; i < 5; i++) {
			starButtons[i] = new StarButton(i);
			starButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (userTitles.isTitleInUserList(titleName)) {
						UserRating userRating = userTitles.getRatingFromTitleName(titleName);
						userTitles.addTitleToUserList(titleName, ((StarButton)e.getSource()).getRating(), userRating.isFavorite());
					} else {
						UserRating newRating = new UserRating(((StarButton)e.getSource()).getRating(), false);
						userTitles.addTitleToUserList(titleName, newRating.getRating(), newRating.isFavorite());
					}
				}
			});
			
			this.add(starButtons[i]);
		}
		*/
		
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

		this.add(userRatingLabel);
		this.add(ratingField);
		this.add(addToFavoritesLabel);
		this.add(favoritedCheckbox);
	}

}
