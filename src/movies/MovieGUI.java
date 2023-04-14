package movies;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JScrollPane;

public class MovieGUI extends JFrame {

	private JPanel contentPane;
	private boolean isInMainListView;
	public static UserTitles userTitles;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		TitleDataStore.readFromDataSource("movies");
		userTitles = new UserTitles();
		userTitles.addTitleToUserList("The Shawshank Redemption", StarRating.THREE_STARS, true);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieGUI frame = new MovieGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MovieGUI() {
		super("Movie GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 912, 518);

		// Create the content pane
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		// Create the top panel
		JPanel topPanel = new JPanel(new BorderLayout());
		contentPane.add(topPanel, BorderLayout.NORTH);

		// Create the Logo label and add it to the top panel
		JLabel logoLabel = createLogoLbl();
		topPanel.add(logoLabel, BorderLayout.WEST);

		// Create the links panel and add it to the top panel
		FlowLayout fl_linksPanel = createLinkPanel();
		JPanel linksPanel = new JPanel(fl_linksPanel);
		topPanel.add(linksPanel, BorderLayout.EAST);
		
		//
		// Card view with content panel
		CardLayout cardLayout = new CardLayout(0, 0);
		JPanel contentPanelCardStack = new JPanel();
		contentPanelCardStack.setLayout(cardLayout);

		// Create our panel for the cards
		JPanel cardPanel = new JPanel(new GridLayout(0, 4));
		// contentPane.add(cardPanel, BorderLayout.CENTER);
		contentPanelCardStack.add(cardPanel, "MainPanel"); // CHANGED

		// Add the cards to our GUI
		createMovieGrid(cardPanel);

		// Create a scroll pane and add the card panel to it
		JScrollPane scrollPane = new JScrollPane(cardPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);

		// Add the scroll pane to the content pane
		//contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPanelCardStack.add(scrollPane, "MainPanel"); // CHANGED
		contentPane.add(contentPanelCardStack, BorderLayout.CENTER);
		
		
		// Favorites
		this.isInMainListView = true;
		FavoritePanel favoritePanel = new FavoritePanel(userTitles);
		contentPanelCardStack.add(favoritePanel, "FavoritePanel");
		
		JLabel favoriteListLbl = new JLabel("My List");
		favoriteListLbl.setForeground(new Color(200, 200, 200));
		favoriteListLbl.setFont(new Font("Arial", Font.BOLD, 26));
		
		// Create the links labels and add them to the links panel
		JLabel movieListLbl = new JLabel("Movie List");
		movieListLbl.setFont(new Font("Arial", Font.BOLD, 26));
		
		movieListLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    if (!isInMainListView) {
			    	isInMainListView = true;
			    	movieListLbl.setForeground(Color.BLACK);
			    	favoriteListLbl.setForeground(new Color(200, 200, 200));
			    	cardLayout.show(contentPanelCardStack, "MainPanel");
			    }
			}
		});
		
		favoriteListLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    if (isInMainListView) {
			    	isInMainListView = false;
			    	movieListLbl.setForeground(new Color(200, 200, 200));
			    	favoriteListLbl.setForeground(Color.BLACK);
			    	favoritePanel.updateMovieTable(userTitles);
			    	cardLayout.show(contentPanelCardStack, "FavoritePanel");
			    }
			}
		});
		
		linksPanel.add(movieListLbl);
		linksPanel.add(favoriteListLbl);

		setSize(912, 560);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void createMovieGrid(JPanel cardPanel) {
	    cardPanel.revalidate();
	    cardPanel.repaint();
		for (Title title : TitleDataStore.titlesArray) {
			JPanel card = new JPanel();
			card.setPreferredSize(new Dimension(210, 300)); // increased the height to make room for the label
			JLabel cardLabel = new JLabel(title.getTitleName());

			// Load the image from a file
			String imgPath = "src/movies/img/" + title.getImgURL();
			ImageIcon imageIcon = new ImageIcon(imgPath);

			// Scale the image to fit the label
			int labelWidth = 160;
			int labelHeight = 230;
			Image originalImage = imageIcon.getImage();
			Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);

			// Create a label with the image and add it to the card panel
			JLabel imageLabel = new JLabel(scaledIcon);
			card.add(imageLabel);
			card.setCursor(new Cursor(Cursor.HAND_CURSOR));

			// Add the title label to the card panel
			card.add(cardLabel, BorderLayout.SOUTH);

			// Add an event listener to each card panel
			card.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// Clear the movie grid
					cardPanel.removeAll();
					cardPanel.revalidate();
					cardPanel.repaint();

					// Display the detail view of the selected movie
					JPanel detailView = new JPanel(new BorderLayout());

					// create the movie card panel on the left
					JPanel movieCard = new JPanel(new BorderLayout());

					// Load the image from a file
					String imgPath = "src/movies/img/" + title.getImgURL();
					ImageIcon imageIcon = new ImageIcon(imgPath);

					// scale the image to fit the label
					int labelWidth = 300;
					int labelHeight = 400;
					Image originalImage = imageIcon.getImage();
					Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);

					// create a label with the image and add it to the movie card panel
					JLabel movieImageLabel = new JLabel(scaledIcon);
					movieCard.add(movieImageLabel, BorderLayout.CENTER);

					// add the movie card panel to the detail view panel
					detailView.add(movieCard, BorderLayout.WEST);

					// create the information panel on the right
					GridLayout gridLayout = new GridLayout(5, 1, 0, 10);
					gridLayout.setVgap(-400);
					JPanel infoPanel = new JPanel(gridLayout);

					infoPanel.setBorder(BorderFactory.createEmptyBorder(-200, 100, 10, 10));

					// add the movie information labels to the info panel
					JLabel titleLabel = new JLabel(title.getTitleName());
					titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
					JLabel releaseDateLabel = new JLabel("Release Date: " + title.getReleaseYear());
					JLabel directorLabel = new JLabel("Director: " + title.getDirector());
					JLabel ratingLabel = new JLabel("Rating: " + title.getAgeRating());
					infoPanel.add(titleLabel);
					infoPanel.add(releaseDateLabel);
					infoPanel.add(directorLabel);
					infoPanel.add(ratingLabel);
					
					// User Rating
					UserRatingAndBookmarkPanel userRatingSection = new UserRatingAndBookmarkPanel(title.getTitleName(), userTitles);
					detailView.add(userRatingSection, BorderLayout.SOUTH);

					// add the info panel to the detail view panel
					detailView.add(infoPanel, BorderLayout.CENTER);
					
					// Create a new JLabel button for going back to the original view
					JLabel backButton = new JLabel("<< Back to Movies");
					backButton.setFont(new Font("Arial", Font.BOLD, 11));
					backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

					// Add a mouse listener to the back button to go back to the movie grid
					backButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
						    // Display the movie grid
						    cardPanel.removeAll();
						    createMovieGrid(cardPanel);
						}
					});

					// Create a panel to hold the back button
					JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					backButtonPanel.add(backButton);

					// add the info panel to the detail view panel
					detailView.add(infoPanel, BorderLayout.CENTER);
					detailView.add(backButtonPanel, BorderLayout.NORTH);

					// Add the detail view to the card panel
					cardPanel.add(detailView, BorderLayout.NORTH);
					cardPanel.revalidate();
					cardPanel.repaint();
				}
			});

			cardPanel.add(card);
		}
	}

	private FlowLayout createLinkPanel() {
		FlowLayout fl_linksPanel = new FlowLayout(FlowLayout.RIGHT);
		fl_linksPanel.setVgap(30);
		fl_linksPanel.setHgap(20);
		return fl_linksPanel;
	}

	private JLabel createLogoLbl() {
		JLabel logoLabel = new JLabel();
		logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		logoLabel.setIcon(new ImageIcon(MovieGUI.class.getResource("/movies/img/Logo.png")));
		return logoLabel;
	}

}
