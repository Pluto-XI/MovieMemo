package movies;

import java.awt.Color;

import javax.swing.JButton;

/**
 * A StarButton adds a integer rating and label and 
 * can be highlighted if clicked.
 * @author Giovanni Soto
 *
 */
public class StarButton extends JButton {
	
	private int rating;
	private boolean isSelected;
	private final Color DESELECTED_COLOR = new Color(210, 210, 210);
	private final Color SELECTED_COLOR = Color.ORANGE;

	/**
	 * Create a new StarButton
	 * @param rating - integer rating
	 */
	public StarButton(int rating) {
		super(rating + "");
		
		this.rating = rating;
		this.isSelected = false;
		this.setOpaque(true);
		this.setBackground(DESELECTED_COLOR);
	}
	
	/**
	 * Get the integer rating
	 * @return
	 */
	public int getRating() {
		return this.rating;
	}
	
	/**
	 * If the button state has been selected
	 */
	public boolean isSelected() {
		return this.isSelected;
	}
	
	/**
	 * Toggle the selection status color. Highlighted
	 */
	public void toggleColor() {
		if (!this.isSelected) {
			this.setBackground(SELECTED_COLOR);
			this.isSelected = true;
		} else {
			this.setBackground(DESELECTED_COLOR);
			this.isSelected = false;
		}
	}
	
	/**
	 * Set the button as deselected
	 */
	public void setDeselected() {
		this.setBackground(DESELECTED_COLOR);
		this.isSelected = false;
	}
	
	/**
	 * Set the button as selected
	 */
	public void setSelected() {
		this.setBackground(SELECTED_COLOR);
		this.isSelected = true;
	}
}
