/*
 * Filename     : Obstacle.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : Model for obstacle object
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package Model;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author vil
 */
public class Obstacle extends GameObject {
    // Properties
    private Color color; // color of the obstacle
    private String[] colors = {
        // array of colors used for the obstacle
        "#1e0404",
        "#4d0804",
        "#781006",
        "#e83d03",
        "#fffb05",
        "#aa804a",
        "#9f7f56",
        "#8f7759",
        "#6a5e4f",
        "#534c42"
    };
    private final int score; // score value of the obstacle
    private boolean isScore = false; // flag to track if the obstacle's score has been counted
    
    public Obstacle(float x, float y, int width, int height, int score) {
        /**
         * Constructs an Obstacle object with the specified position, dimensions, and point value.
         *
         * @param x      the x-coordinate of the obstacle's position
         * @param y      the y-coordinate of the obstacle's position
         * @param width  the width of the obstacle
         * @param height the height of the obstacle
         * @param point  the point value of the obstacle
         */
        super(0, y, width, height); // call the superclass (GameObject) constructor and pass the necessary parameters
        this.score = score; // set the score value of the obstacle
        setColor(); // set the color of the obstacle
    }
    
    private void setColor() {
        /**
         * Sets a random color for the obstacle.
         * Randomly selects a color from the array of colors and assigns it to the color property.
         */
        Random rand = new Random(); // instantiate
        int index = rand.nextInt(colors.length); // generate a random index to select a color from the colors array
        String colorString = colors[index]; // get the color string at the randomly selected index
        color = Color.decode(colorString); // decode the color string and assign it to the color property
    }
    
    public Color getColor(){
        return this.color; // get the color of the obstacle
    }
    
    public int getScore() {
        return score; // get the point value of the obstacle
    }

    public void setIsScore(boolean isScore) {
        this.isScore = isScore; // set the flag indicating whether the obstacle's point has been counted or not
    }
    
    public boolean isScore() {
        return isScore; // check if the obstacle's point has been counted
    }
}
