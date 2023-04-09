package movies;

/**
 * This class represents our actual title/movie object, all it does is store information about the title itself.
 * 
 * @author Nick Delgado
 *
 */
public class Title {
	private String titleName;
	private int releaseYear;
	private String director;
	private String ageRating;
	
	/**
	 * Creates a new title.
	 * 
	 * @param titleName String, the name of the movie.
	 * @param releaseYear int, the year the movie was released.
	 * @param director String, the director of the film.
	 * @param ageRating String, the age rating of the film.
	 */
	public Title(String titleName, int releaseYear, String director, String ageRating) {
		this.titleName = titleName;
		this.releaseYear = releaseYear;
		this.director = director;
		this.ageRating = ageRating;
	}

	/**
	 * Gets the title of the film.
	 * 
	 * @return String representing title.
	 */
	public String getTitleName() {
		return titleName;
	}

	/**
	 * Gets the release date of the film.
	 * 
	 * @return int representing released year.
	 */
	public int getReleaseYear() {
		return releaseYear;
	}

	/**
	 * Gets the director who made the film.
	 * 
	 * @return String representing the name of the director.
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Gets the age rating from the film. R, PG-13, etc.
	 * 
	 * @return String representing rating.
	 */
	public String getAgeRating() {
		return ageRating;
	}
	
	
}
