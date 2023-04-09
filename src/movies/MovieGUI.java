package movies;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JScrollPane;

public class MovieGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
        
        
        // Create the links labels and add them to the links panel
        JLabel movieListLbl = new JLabel("Movie List");
        movieListLbl.setFont(new Font("Arial", Font.BOLD, 26));
        JLabel favoriteListLbl = new JLabel("My List");
        favoriteListLbl.setFont(new Font("Arial", Font.BOLD, 26));
        linksPanel.add(movieListLbl);
        linksPanel.add(favoriteListLbl);
        
        
        //Create our panel for the cards
        JPanel cardPanel = new JPanel(new GridLayout(0, 4));
        contentPane.add(cardPanel, BorderLayout.CENTER);

        //Add the cards to our GUI
        for (int i = 1; i <= 15; i++) {
            JPanel card = new JPanel();
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            card.setPreferredSize(new Dimension(200, 200));
            JLabel cardLabel = new JLabel("Card " + i);
            card.add(cardLabel);
            cardPanel.add(card);
        }

        // Create a scroll pane and add the card panel to it
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add the scroll pane to the content pane
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        setSize(912, 518);
        setLocationRelativeTo(null);
        setVisible(true);
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
