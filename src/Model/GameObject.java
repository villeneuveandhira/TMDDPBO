/*
 * Filename     : GameObject.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : Model for game object
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package Model;

import java.awt.Rectangle;

/**
 *
 * @author vil
 */
public abstract class GameObject {
    // Properties
    protected float x, y; // object coordinates
    protected int width, height; // object dimensions
    protected Rectangle hitbox; // collision detector
    
    public GameObject(float x, float y, int width, int height) {
        /**
         * Constructor for the GameObject class.
         * @param x The x-coordinate of the object.
         * @param y The y-coordinate of the object.
         * @param width The width of the object.
         * @param height The height of the object.
         */
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitbox = new Rectangle((int)x, (int)y, width, height); // create a new hitbox rectangle based on the provided coordinates and dimensions
    }
    
    public void updateHitbox(){
        // Update hitbox to match the object's current position
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public void setX(float x) {
        this.x = x; // set the x-coordinate of the object
    }
    
    public float getX() {
        return x; // get the x-coordinate of the object
    }
    
    public void setY(float y) {
        this.y = y; // set the y-coordinate of the object
    }
    
    public float getY() {
        return y; // get the y-coordinate of the object
    }
    
    public void setWidth(int width) {
        this.width = width; // set the width of the object
    }
    
    public int getWidth() {
        return width; // get the width of the object
    }
    
    public void setHeight(int height) {
        this.height = height; // set the height of the object
    }
    
    public int getHeight() {
        return height; // get the height of the object
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox; // set the hitbox rectangle
    }
    
    public Rectangle getHitbox() {
        return hitbox; // get the hitbox rectangle
    }
}
