/*
 * Filename     : Display.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : View for display the window's game
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package View;

import javax.swing.JFrame;

import ViewModel.BGM;
import ViewModel.Controller;
import ViewModel.Game;

/**
 *
 * @author vil
 */
public class Display extends JFrame {
    // Properties
    private Game game; // game panel
    private Menu menu; // menu
    private BGM bgm; // bgm
    
    public Display() {
        /**
         * Constructs a new Display object.
         * Initializes the game window settings.
         */
        super();
        initialize();
    }
    
    public Display(String username, Menu menu, String title) {
        /**
         * Constructs a new Display object with specified parameters.
         * Initializes the game window settings.
         *
         * @param username the username of the player
         * @param menu     the menu object
         * @param title    the title of the game window
         */
        super(title);
        initialize();
        this.menu = menu;
        open(username);
    }
    
    private void initialize() {
        /**
         * Initializes the game window settings.
         */
        setSize(800, 600); // set the size of the display
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the default close operation of the game to exit the application
        setLocationRelativeTo(null); // center the display on the screen
        setResizable(false); // disable display resizing
    }
    
    public void open(String username) {
        /**
         * Opens the game window with the specified username.
         *
         * @param username the username of the player
         */
        // start playing BGM
        bgm = new BGM(BGM.game);
        bgm.play();
        game = new Game(username, this); // create a new Game panel with the specified username and reference to this display
        add(game); // add the game panel to the display
        addKeyListener(new Controller(game)); // add a key listener to the game panel
        this.setVisible(true); // make the game visible
    }
    
    public void close() {
        /**
         * Closes the game window and returns to the menu.
         * Hides the game window, shows the menu, updates the table in the menu, and disposes the game window.
         */
        this.setVisible(false); // hide the game
        menu.setVisible(true); // show the KeepStanding menu
        menu.updateDataTable(); // update the table in the menu
        menu.playBGM(); // start playing the menu BGM
        bgm.stop(); // stop playing the game BGM
        this.dispose(); // dispose the game
    }
}
