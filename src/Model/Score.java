/*
 * Filename     : Score.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : Model for tscore table
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package Model;

/**
 *
 * @author vil
 */
public class Score {
    // Properties
    private String username;
    private int score;
    private int standing;
    
    public Score(String username, int score, int standing) {
        // Constructor
        this.username = username;
        this.score = score;
        this.standing = standing;
    }
    
    public void setUsername(String username) {
        this.username = username; // set value of username
    }
    
    public String getUsername() {
        return this.username; // get value of username
    }
    
    public void setScore(int score) {
        this.score = score; // set value of score
    }
    
    public int getScore() {
        return this.score; // get value of score
    }
    
    public void setStanding(int standing) {
        this.standing = standing; // set value of score
    }
    
    public int getStanding() {
        return this.standing; // get value of score
    }
}
