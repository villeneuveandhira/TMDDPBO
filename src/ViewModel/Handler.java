/*
 * Filename     : Handler.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : View Model for handling objects
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package ViewModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Model.Obstacle;
import Model.Player;
import Model.ScoreTable;

/**
 *
 * @author vil
 */
public class Handler {
    // Properties
    private Game game; // // reference to the game
    private final Random random = new Random();
    /* Obstacle Handler */
    private int centerX; // center position x of the obstacle
    private int centerY; // center position y of the obstacle
    private int textWidth; // width of the score text
    private int textHeight; // height of the score text
    private int borderX; // position coordinate x of border
    private int borderY; // position coordinate y of border
    private int borderWidth; // border width dimension
    private int borderHeight; // border height dimension
    private int num; // current number of obstacles
    private float randomX; // random value of x coordinate
    private float randomY; // random value of y coordinate
    private int randomWidth; // random value of obstacle's width
    private int setScore; // score value of obstacle
    private Obstacle previousObstacle; // the previous obstacle
    private float previousObstacleWidth; // the previous obstacle width
    private float multiplier; // disctance multiplier
    private float distance; // disctance between obstacle
    private final ArrayList<Obstacle> obstacles = new ArrayList<>(); // list of obstacles
    /* Player Handler */
    private Player player; // reference to the player object
    private float velocityX = 0; // horizontal speed
    private float velocityY = 0; // vertical speed
    private int jumpsRemaining = 1; // number of jumps remaining for the player
    private boolean isFalling = true; // flag indicating if the player is in the air
    /* Score Handler */
    private int score; // total score
    private int point; // current point value
    private int standing; // number of times player is standing
    
    public Handler(Game game) {
        /**
         * Constructs a handler object with the specified game.
         * Sets the initial values for num, player, score, and point
         * 
         * @param game the reference to the game
         */
        this.game = game;
        this.num = 0;
        this.player = new Player(360, 300);        
        score = 0;
        point = 0;
    }
    
    /* render methods */
    public void renderObstacle(Graphics g) {
        // Obstacles
        for (Obstacle o : obstacles) {
            // render an obstacle
            g.setColor(o.getColor());
            g.fillRect((int) o.getX(), (int) o.getY(), o.getWidth(), o.getHeight());

            // calculate the center position of the obstacle
            centerX = (int) o.getX() + o.getWidth() / 2;
            centerY = (int) o.getY() + o.getHeight() / 2;

            // draw the assigned point value at the center of the obstacle
            String pointText = Integer.toString(o.getScore());
            
            FontMetrics fontMetrics = g.getFontMetrics(new Font("Obrbitron", Font.BOLD, 12));
            textWidth = fontMetrics.stringWidth(pointText);
            textHeight = fontMetrics.getHeight();

            // calculate the border dimensions
            borderX = centerX - textWidth / 2 - 2;
            borderY = centerY - textHeight / 2 - 2;
            borderWidth = textWidth + 2 * 2;
            borderHeight = textHeight + 2 * 2;

            // draw the colored rectangle
            g.setColor(Color.BLACK); // Modify the color as desired
            g.fillRect(borderX, borderY, borderWidth, borderHeight);

            // draw the point value text
            g.setColor(Color.WHITE); // Modify the color as desired
            g.setFont(new Font("Obrbitron", Font.BOLD, 12));
            g.drawString(pointText, centerX - textWidth / 2, centerY + textHeight / 2);
        }
    }
    
    public void renderPlayer(Graphics g) {
        // player with image
        g.drawImage(player.getCurrentImg(), (int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight(), null);
    }
    
    public void renderScore(Graphics g) {
        // score point on the screenv
        g.setColor(Color.WHITE);
        g.setFont(new Font("Obrbitron", Font.BOLD, 12));
        g.drawString("Score: " + score, 20, 30);
        g.drawString("Standing: " + standing, 20, 60);
    }
    
    private float getRNG(int min, int max) {
        return random.nextInt(max - min) + min;
    }
    
    public void spawnObstacle() {
        // init
        randomWidth = 0;
        setScore = 0;
        // add an obstacle if the maximum limit is not reached
        if (num < 10) {
            // initialize coordinates
            randomX = getRNG(10, 500); // randomize the width of the obstacle
            randomY = 0;

            if (!obstacles.isEmpty()) {
                // Get the previous obstacle
                previousObstacle = obstacles.get(obstacles.size() - 1);
                previousObstacleWidth = previousObstacle.getWidth();
                // Determine the distance multiplier using a random number generator
                multiplier = getRNG(1, 2);
                // Calculate the distance based on the previous obstacle width and the distance multiplier
                distance = previousObstacleWidth * multiplier;
                distance = Math.max(distance, 10); // Ensure the distance is not less than the minimum distance
                distance = Math.min(distance, 200); // Ensure the distance is not greater than the maximum distance
                // Determine the y position by adding the distance to the previous obstacle's y position
                randomY = previousObstacle.getY() + distance;
            }
            
            // assign socre point values based on obstacle width ranges
            randomWidth = (int) getRNG(100, 500);
            if (randomWidth >= 100 && randomWidth <= 150) {
                setScore = 100;
            } else if (randomWidth > 150 && randomWidth <= 200) {
                setScore = 90;
            } else if (randomWidth > 200 && randomWidth <= 250) {
                setScore = 80;
            } else if (randomWidth > 250 && randomWidth <= 300) {
                setScore = 70; 
            } else if (randomWidth > 300 && randomWidth <= 350) {
                setScore = 60;
            } else {
                // Default score value for widths above 350
                setScore = 50;
            }

            // create the raandomized position of obstacle with the assigned score point value
            Obstacle o = new Obstacle(randomX, randomY, randomWidth, 50, setScore);
            obstacles.add(o);
            num++; // iterate number of obstacles
        }
    }
    
    public void updateObstacle() {
        // update the obstacle so that it moves
        Iterator<Obstacle> itr = obstacles.iterator();
        while (itr.hasNext()) {
            Obstacle o = itr.next();
            if (o.getY() + o.getHeight() < 0) {
                itr.remove(); // remove obstacles that have gone off the top of the screen
                num--;
            } else {
                // update obstacle position
                if (o.getScore() > 0 || o.getScore() == 0 || (o.getScore() < 0 && o.getY() < 0)) {
                    // move the obstacle
                    o.setY(o.getY() - 1.5f);
                }
                o.updateHitbox(); // update obstacle's hitbox
            }
        }
    }
    
    public void updatePlayer(ArrayList<Obstacle> arr) {
        if (player.getY() > 600 || player.getY() < 0) {
            // check if the player is outside the screen (bottom or top)
            this.game.stop();
        }
        
        if (player.isPlayerUp() && jumpsRemaining >= 0) {
            velocityY = -7.0f; // jump speed
            jumpsRemaining--; // reduce the number of jumps remaining
        }

        if (player.isPlayerLeft()) {
            // player's speed
            velocityX = -7.0f; // move left
        } else if (player.isPlayerRight()) {
            velocityX = 7.0f; // move right
        }

        if (isFalling()) {
            // if the player is currently falling, increase the vertical speed
            velocityY += 0.2f; // to simulate the effect of gravity
        } else {
            // otherwise, if the player is on the obstacle, reset the vertical speed
            velocityY = 0;
        }

        if (!isFalling()) {
            // if the player is not currently falling and not standing on any obstacles,
            setIsFalling(true); // update the falling flag to indicate the player is now falling
        }
        
        // Check collision between the player and each obstacle
        for (Obstacle o : arr) {
            if (player.getHitLeft().x < 0) {
                // prevent the player from moving off the left edge of the screen
                player.setX(0);
            } else if (player.getHitRight().x + player.getHitRight().width > 800) {
                // prevent the player from moving off the right edge of the screen
                player.setX(800 - player.getWidth());
            }

            if (player.getHitBottom().intersects(o.getHitbox())) {
                // bottom collision
                if (velocityY >= 0) {
                    jumpsRemaining = 1; // reset the number of jumps remaining when the player is on the obstacle
                    velocityY = 0; // stop vertical movement
                    // position the player just above the obstacle after a bottom collision
                    player.setY(o.getHitbox().y - player.getHeight());
                }
                // scoring system
                if (o.getScore() == 0) {
                    this.game.getHandler().scoring(player, arr, true);
                } else {
                    this.game.getHandler().scoring(player, arr, false);
                }
            } else if (player.getHitTop().intersects(o.getHitbox())) {
                // top collision
                velocityY = 0; // stop vertical movement
                // position the player just below the obstacle after a top collision
                player.setY(o.getHitbox().y + o.getHitbox().height);
            } else if (player.getHitRight().intersects(o.getHitbox())) {
                // right collision
                velocityX = 1.5f;
                // position the player just side of the obstacle after a right collision
                player.setX(o.getHitbox().x - player.getWidth() - 1);
            } else if (player.getHitLeft().intersects(o.getHitbox())) {
                // left collision
                velocityX = 1.5f;
                // position the player just side of the obstacle after a right collision
                player.setX(o.getHitbox().x + o.getHitbox().width + 1);
            }
        }
        // update player position
        player.setX(player.getX() + velocityX);
        player.setY(player.getY() + velocityY);
        velocityX = 0; // reset speeds after updating position (handle sliding)
        player.updateHitbox(); // update player hitbox
    }
     
    public void scoring(Player p, ArrayList<Obstacle> arr, boolean isFalling) {
        if (isFalling) {
            // Player is not standing, reset the standing
            point = 0;
        } else {
            // Player is standing, update the standing based on the assigned point values of obstacles
            point = 0;
            for (Obstacle o : arr) {
                if (o.getX() + o.getWidth() >= p.getX() && p.getY() + p.getHeight() == o.getY()) {
                    if (!o.isScore()) {
                        point = o.getScore(); // Get the point value of the obstacle
                        score += point; // Update the total score
                        standing++; // Increment the standing count
                        o.setIsScore(true); // Mark the obstacle as counted
                    }
                }
            }
        }
    }
    
    public void uploadData(String username) throws Exception {
        // Upload the score to the database
        ScoreTable tp = new ScoreTable();
        tp.data(username, score, standing);
    }
    
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
    
    public void setMovementUp(boolean playerUp) {
        player.setPlayerUp(playerUp);
    }
    
    public void setMovementLeft(boolean playerLeft) {
        player.setPlayerLeft(playerLeft);
    }
    
    public void setMovementRight(boolean playerRight) {
        player.setPlayerRight(playerRight);
    }
    
    public void setIsFalling(boolean isFalling) {
        this.isFalling = isFalling; // Set the flag indicating if the player is in the air
    }

    public boolean isFalling() {
        return this.isFalling; // Check if the player is in the air
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }

    public void setStanding(int standing) {
        this.standing = standing;
    }
    
    public int getStanding() {
        return standing;
    }  
}
