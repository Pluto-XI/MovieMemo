package movies;

/**
 * A UserRating hold a StarRating and whether or 
 * not it is a favorite by the user for a title.
 * 
 * @author Giovanni Soto
 */
public class UserRating {
	private boolean isFavorite;
	private StarRating rating;
	
	/**
	 * Default constructor with a StarRating of NO_RATING and
	 * is not a favorite
	 */
	public UserRating() {
		this.rating = StarRating.NO_RATING;
		this.isFavorite = false;
	}
	
	/**
	 * Construct a new UserRating object with provide rating and favorite status
	 * @param rating - StarRating enum
	 * @param isFavorite - Is this favorited by user?
	 */
	public UserRating(StarRating rating, boolean isFavorite) {
		this.rating = rating;
		this.isFavorite = isFavorite;
	}
	
	public UserRating(int rating, boolean isFavorite) {
		this.rating = getStarRatingFromInt(rating);
		this.isFavorite = isFavorite;
	}
	
	public static StarRating getStarRatingFromInt(int rating) {
		switch(rating) {
			case 0:  return StarRating.NO_RATING;
			case 1:  return StarRating.ONE_STAR;
			case 2:  return StarRating.TWO_STARS;
			case 3:	 return StarRating.THREE_STARS;
			case 4:	 return StarRating.FOUR_STARS;
			case 5:	 return StarRating.FIVE_STARS;
			default: return StarRating.NO_RATING;
		}
	}
	
	public String starRatingToString() {
		return getStarRatingAsString(rating);
	}
	
	public static String getStarRatingAsString(StarRating rating) {
		switch(rating) {
			case NO_RATING:  	return "0";
			case ONE_STAR:   	return "1";
			case TWO_STARS:  	return "2";
			case THREE_STARS:	return "3";
			case FOUR_STARS:	return "4";
			case FIVE_STARS:	return "5";
			default: 			return "Invalid rating";
		}
	}
	
	public String formatStarRating() {
		switch(rating) {
			case NO_RATING:  	return "0/5";
			case ONE_STAR:   	return "1/5";
			case TWO_STARS:  	return "2/5";
			case THREE_STARS:	return "3/5";
			case FOUR_STARS:	return "4/5";
			case FIVE_STARS:	return "5/5";
			default: 			return "Invalid rating";
		}
	}
	
	/**
	 * Get the user StarRating
	 * @return StarRating
	 */
	public StarRating getRating() {
		return this.rating;
	}
	
	/**
	 * Is this rating favorited by user?
	 * @return - Is favorite?
	 */
	public boolean isFavorite() {
		return this.isFavorite;
	}
}
