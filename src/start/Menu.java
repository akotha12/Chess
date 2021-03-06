package start;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import java.awt.*;
import javax.swing.*;

import chess.Game;
import chess.GameStyle;
import user.User;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

/**
 * This is the main menu that pops up when the application is launched.
 * @author edurso
 */
public class Menu extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Button that will exit the application
	 */
	private JButton quitButton;
	
	/**
	 * Button that launches the selection menu for single player options
	 */
	private JButton singleButton;
	
	/**
	 * Button that launches the selection menu for multiplayer options
	 */
	private JButton multiButton;
	
	/**
	 * Button that launches the selection menu for the player's setings
	 */
	private JButton settingsButton;
	
	/**
	 * Button that redirects to main menu
	 */
	private JButton back;
	
	/**
	 * Button that launches a local multiplayer game
	 */
	private JButton local;
	
	/**
	 * Button that launches an online multiplayer game
	 */
	private JButton online;
	
	/**
	 * Container for main menu buttons
	 */
	private JPanel menuPanel;
    
    /**
     * Container for multiplayer buttons
     */
    private JPanel multiPanel;
    
    /**
     * Container for settings components
     */
    private JPanel settingsPanel;
	
    /**
     * Image for the background of the menu
     */
	private JLabel background;
	
	/**
	 * settings for menu button
	 */
	private Settings settings;
	 
    /**
     * Universal font for project
     */
    public static Font f = new Font("TimesRoman", Font.PLAIN, 20);
    
    /**
     * Starts the menu at the main screen of the application
	 * pre: none
	 * post: menu initialized with buttons and things
     */
	public void start() {
		
		
		setTitle("Chess");
		
		menuPanel = new JPanel();
	    menuPanel.setLayout(null);
	    
	    multiPanel = new JPanel();
	    multiPanel.setLayout(null);
	    
	    settingsPanel = new JPanel();
	    settingsPanel.setLayout(null);
	    
	    getContentPane().add(menuPanel);
	    ImageIcon image = new ImageIcon(getClass().getResource("chess_background.png")); 
		background = new JLabel(image, JLabel.CENTER);
		background.setBounds(0,0,500,602);

	    initMainMenuButtons();
	    
	    settings = new Settings();
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(500,602);
	    setResizable(false);
	    setVisible(true);
	}
	
	/**
	 * gets active players from the list of players
	 * pre: none
	 * post: none
	 * @return the player who is playing on the white side
	 */
	public static User getActiveWhitePlayer() { return Settings.getActiveWhitePlayer(); }
	
	/**
	 * gets active players from the list of players
	 * pre: none
	 * post: none
	 * @return the player who is playing on the black side
	 */
	public static User getActiveBlackPlayer() { return Settings.getActiveBlackPlayer(); }
	
	/**
	 * Configures the components for the main menu
	 * pre: none
	 * post: buttons for main menu created
	 */
	private void initMainMenuButtons() {

		quitButton = new JButton("Quit");
	    quitButton.setBounds(162,500,175,50);
	    quitButton.setBackground(Color.RED);
	    quitButton.setFont(f);
	    menuPanel.add(quitButton);
	    quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0); 
			}
		});
	    
	    settingsButton = new JButton("Settings");
	    settingsButton.setBounds(162,400,175,50);
	    settingsButton.setBackground(Color.RED);
	    settingsButton.setFont(f);
	    menuPanel.add(settingsButton);
	    settingsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				getContentPane().removeAll();
				getContentPane().add(settingsPanel);
				initSettingsButtons();
			    setVisible(true);
			}
		});
	    
	    singleButton = new JButton("Single Player");
	    singleButton.setBounds(162,200,175,50);
	    singleButton.setBackground(Color.RED);
	    singleButton.setFont(f);
	    menuPanel.add(singleButton);
	    singleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.run(GameStyle.SINGLE);
			}
		});
	    
	    multiButton = new JButton("Multiplayer");
	    multiButton.setBounds(162,300,175,50);
	    multiButton.setBackground(Color.RED);
	    multiButton.setFont(f);
	    menuPanel.add(multiButton);
	    multiButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				getContentPane().removeAll();
				getContentPane().add(multiPanel);
				initMultiplayerButtons();
			    setVisible(true);
			}
		});
	    
		menuPanel.add(background);
		background.setVisible(true);
		
	}
	
	/**
	 * keeps track to make sure multiple go to home buttons are not added
	 */
	int count = 0;

	/**
	 * Configures the components for the settings menu
	 * pre: none
	 * post: settings menu created
	 */
	private void initSettingsButtons() {
		settings.setBounds(0, 150, 500, 452);
		settings.setVisible(true);
		settingsPanel.add(settings);
		settingsPanel.add(background);
		background.setVisible(true);
		count++;
	}
	
	/**
	 * Configures the components for the multiplayer menu
	 * pre: none
	 * post: multiplayer menu buttons created and shown
	 */
	private void initMultiplayerButtons() {
		
		local = new JButton("Local");
	    local.setBounds(162,300,175,50);
	    local.setBackground(Color.RED);
	    local.setFont(f);
	    multiPanel.add(local);
	    local.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.run(GameStyle.MULTI_LOCAL);
			}
		});
	    
	    online = new JButton("Online");
	    online.setBounds(162,400,175,50);
	    online.setBackground(Color.RED);
	    online.setFont(f);
	    multiPanel.add(online);
	    online.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.run(GameStyle.MULTI_ONLINE);
			}
		});
	    addBack(multiPanel);
	    multiPanel.add(background);
		background.setVisible(true);
	}
	
	/**
	 * Adds a "go home" button to the inputed panel.
	 * pre: panel is not null
	 * post: back button added
	 * @param panel the panel the home button will be added to
	 */
	private void addBack(JPanel panel) {
		back = new JButton("Go To Home");
	    back.setBounds(162,500,175,50);
	    back.setBackground(Color.RED);
	    back.setFont(f);
	    panel.add(back);
	    back.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e) { goToHome(); } });
	}
	
	/**
	 * Redirects to the main menu of the application
	 * pre: frame is not null
	 * post: redirected to main menu
	 */
	protected void goToHome() {
		setVisible(false);
		getContentPane().removeAll();
		getContentPane().add(menuPanel);
		initMainMenuButtons();
	    setVisible(true);
	}

}

