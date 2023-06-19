/*
 * Filename     : Controller.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : View Model for player's control input with keyboard
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package ViewModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author vil
 */
public class Controller implements KeyListener {
    // Properties
    private final Game game; // reference to the game object
    private int keyCode; // stores the key code of the key that was pressed by the user

    public Controller(Game game) {
        /**
         * Constructor for the Controller class.
         * @param game The reference to the game object.
         */
        this.game = game;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Invoked when a key is typed (pressed and released)
        // No action needed in this implementation
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Invoked when a key is initially pressed down
        if (!game.isStarted()) {
            // start the game
            game.setRunning(true);
            game.setStarted(true);
            game.start();
        } else {
            keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_W -> game.getHandler().setMovementUp(true); // set player movement up (true)
                case KeyEvent.VK_UP -> game.getHandler().setMovementUp(true);
                case KeyEvent.VK_A -> game.getHandler().setMovementLeft(true); // set player movement left (true)
                case KeyEvent.VK_LEFT -> game.getHandler().setMovementLeft(true);
                case KeyEvent.VK_D -> game.getHandler().setMovementRight(true); // set player movement right (true)
                case KeyEvent.VK_RIGHT -> game.getHandler().setMovementRight(true);
                case KeyEvent.VK_SPACE -> game.stop(); // stop the game (complete the game)
                default -> {
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Invoked when a key is released (not pressed)
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            game.getHandler().setMovementUp(false); // set player movement up (false)
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            game.getHandler().setMovementLeft(false); // set player movement left (false)
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            game.getHandler().setMovementRight(false); // set player movement right (false)
        }
    } 
}
