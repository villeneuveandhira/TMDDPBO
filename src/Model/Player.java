/*
 * Filename     : Player.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : Model for player object
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package Model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * 
 * @author vil
 */
public class Player extends GameObject {
    // Properties
    private boolean Up; // flag indicating if the player should move up
    private boolean Left; // flag indicating if the player should move left
    private boolean Right; // flag indicating if the player should move right
    public static Image leftImg; // image representing the player's left image
    public static Image rightImg; // image representing the player's right image
    private Image currentImg; // current image of the player
    
    public Player(int x, int y) {
        /**
         * Constructor for the Player class.
         * It initializes the player object with the specified position (x, y) and loads the player's skin image.
         * 
         * @param x the x-coordinate of the player's position.
         * @param y the y-coordinate of the player's position.
         */
        super(x, y, 40, 40); // call the superclass (GameObject) constructor and pass the necessary parameters
        // create the player's skin image from a file
        leftImg = Toolkit.getDefaultToolkit().getImage("resources/image/ghast-skin-left.png");
        rightImg = Toolkit.getDefaultToolkit().getImage("resources/image/ghast-skin-right.png");
        currentImg = rightImg; // Set the initial image to the right-facing image
    }
    
    public void setPlayerUp(boolean moveUp) {
        this.Up = moveUp; // set the flag indicating if the player should move up
    }
    
    public boolean isPlayerUp() {
        return Up; // check if the player should move up
    }
    
    public void setPlayerLeft(boolean moveLeft) {
        this.Left = moveLeft; // set the flag indicating if the player should move left
        if (moveLeft) {
            currentImg = leftImg; // change the player's image to the left-facing image
        }
    }
    
    public boolean isPlayerLeft() {
        return Left; // check if the player should move left
    }

    public void setPlayerRight(boolean moveRight) {
        this.Right = moveRight; // set the flag indicating if the player should move right
        if (moveRight) {
            currentImg = rightImg; // change the player's image to the right-facing image
        }
    }
    
    public boolean isPlayerRight() {
        return Right; // check if the player should move right
    }
    
    public void setCurrentImg(Image currentImg) {
        this.currentImg = currentImg; // set the current image
    }
    
    public Image getCurrentImg() {
        return this.currentImg; // return the current image
    }

    /* Collision */
    public Rectangle getHitTop(){
        /**
         * Returns the top hitbox of the player for collision detection.
         * 
         * @return a Rectangle representing the top hitbox of the player.
         */        
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y), width/2, height/2); // Get the top hitbox of the player
    }
    
    public Rectangle getHitLeft(){
        /**
         * Returns the left hitbox of the player for collision detection.
         * 
         * @return a Rectangle representing the left hitbox of the player.
         */
        return new Rectangle((int) x, (int)y + 5, 5, height-10); // get the left hitbox of the player
    }
    
    public Rectangle getHitRight(){
        /**
         * Returns the right hitbox of the player for collision detection.
         * 
         * @return a Rectangle representing the right hitbox of the player.
         */
        return new Rectangle((int) x+width-5, (int)y + 5, 5, height-10); // get the right hitbox of the player
    }

    public Rectangle getHitBottom(){
        /**
         * Returns the bottom hitbox of the player for collision detection.
         * 
         * @return a Rectangle representing the bottom hitbox of the player.
         */
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y+(height/2)), width/2, height/2); // get the bottom hitbox of the player
    }
}
