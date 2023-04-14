package movies;

import javax.swing.JButton;

public class StarButton extends JButton {
	
	private int rating;

	public StarButton(int rating) {
		super(rating + "");
		
		this.rating = rating;
	}
	
	public int getRating() {
		return this.rating;
	}
}
