/*
 * Filename     : Game.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : View Model for the game itself (IMPORTANT)
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package ViewModel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import View.Display;

/**
 *
 * @author vil
 */
public class Game extends JPanel implements Runnable {
    // Properties
    private String username; // stores the username of the player.
    private Display display; // reference to the game window
    private final Handler handler; // manages the game objects.
    private Thread thread; // The thread responsible for running the game loop.
    private boolean running = false; // indicates if the game is currently running
    private boolean started = false; // indicates if the game has started
    private BufferedImage backgroundImage; // stores the background image
    
    public Game(String username, Display display){
        /**
         * Constructs a new Game object with the specified username and game window.
         * 
         * @param username the username of the player
         * @param window the game window object
         */
        this.username = username; // set the username of the player
        this.display = display; // store the reference to the game window
        this.handler = new Handler(this); // instantiate handler
        thread = new Thread(this); // create a new thread with the current Game object as the target
        loadBackgroundImage();
    }
    
    private void loadBackgroundImage() {
        /**
         * Loads the background image from a file.
         */
        try {
            backgroundImage = ImageIO.read(new File("resources/image/Nether.jpg"));
        } catch (IOException e) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, "Failed to load background image", e);
        }
    }
    
    public synchronized void start(){
    /**
     * Starts the game by creating a new thread and calling the run method.
     * The method is synchronized to ensure that only one thread can access it at a time.
     * This helps prevent any concurrency issues when starting the game.
     */
       running = true; // set the running flag to true
       thread.start(); // start the thread, which will call the run method
    }
    
    public synchronized void stop() {
        /**
         * Stops the game and performs necessary cleanup tasks.
         * The method is synchronized to ensure that only one thread can access it at a time.
         * This helps prevent any concurrency issues when stopping the game.
         */
        try {
            running = false; // set the running flag to false
            handler.uploadData(username); // upload Score
            // message dialog
            String message = "Username: " + this.username + "\n"
                    + "Score: " + this.handler.getScore() + "\n"
                    + "Standing: " + this.handler.getStanding();
            JOptionPane.showMessageDialog(null, message, "You must keep standing!", JOptionPane.INFORMATION_MESSAGE);
            display.close(); // close the display game
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        /**
         * The main game loop that runs as long as the game is running.
         * It continuously updates and renders the game at a fixed frame rate.
         */
       while (running) {
           try {
               renderGame(); // render the game
               updateGame(); // update the game logic
               Thread.sleep(1000L / 60L); // pause the thread to achieve a target frame rate of 60 FPS
           } catch (InterruptedException ex) {
               Logger.getLogger(Game.class.getName()).log(Level.SEVERE, "Run terminated", ex);
           }
       }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        /**
         * Overrides the paintComponent() method of JPanel to render the game graphics.
         * 
         * @param g the Graphics object used for rendering
         */
        super.paintComponent(g); // Call the superclass method to ensure proper painting of the panel
        if (g != null) {
            // check if the Graphics object is not null
            g.drawImage(backgroundImage, 0, 0, display.getWidth(), display.getHeight(), null); // Draw the background image
            handler.renderObstacle(g); // Render obstacles
            handler.renderPlayer(g); // Render player
            handler.renderScore(g); // Render the score
            g.dispose(); // Dispose the graphics object to free up resources
        }
    }
    
    public void renderGame() {
        repaint(); // Trigger a repaint of the panel
    }
    
    public void updateGame() {
        handler.spawnObstacle(); // spawn new obstacles
        handler.updatePlayer(handler.getObstacles()); // update the player's position and check for collisions with obstacles
        handler.updateObstacle(); // update the positions of existing obstacles
    }
    
    public void setRunning(boolean running){
        this.running = running; // sets the running status of the game
    }
    
    public boolean isRunning(){
        return this.running; // returns the running status of the game
    }
    
    public void setStarted(boolean started){
        this.started = started; // sets the started status of the game
    }
    
    public boolean isStarted(){
        return this.started; // returns the started status of the game
    }
    
    public Handler getHandler() {
        return this.handler; // returns the handler object (no setter for 'final')
    }   
}
