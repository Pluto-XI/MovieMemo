package movies;

import java.awt.Color;

import javax.swing.JButton;

public class StarButton extends JButton {
	
	private int rating;
	private boolean isSelected;
	private final Color DESELECTED_COLOR = new Color(210, 210, 210);
	private final Color SELECTED_COLOR = Color.ORANGE;

	public StarButton(int rating) {
		super(rating + "");
		
		this.rating = rating;
		this.isSelected = false;
		this.setOpaque(true);
		this.setBackground(DESELECTED_COLOR);
		//this.setBorderPainted(false);
	}
	
	public int getRating() {
		return this.rating;
	}
	
	public boolean isSelected() {
		return this.isSelected;
	}
	
	public void toggleColor() {
		if (!this.isSelected) {
			this.setBackground(SELECTED_COLOR);
			this.isSelected = true;
		} else {
			this.setBackground(DESELECTED_COLOR);
			this.isSelected = false;
		}
	}
	
	public void setDeselected() {
		this.setBackground(DESELECTED_COLOR);
		this.isSelected = false;
	}
	
	public void setSelected() {
		this.setBackground(SELECTED_COLOR);
		this.isSelected = true;
	}
}
