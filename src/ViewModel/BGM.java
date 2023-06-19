/*
 * Filename     : BGM.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : View Model for playing the background music
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package ViewModel;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author vil
 */
public class BGM {
    // Properties
    private Clip clip; // Clip for playing the background music
    public static final String menu = "resources/music/C418 - Mutation (Minecraft Volume Beta).wav";
    public static final String game = "resources/music/So Below (Soul Sand Valley + Basalt Deltas).wav";
    
    public BGM(String fileUrl) {
        /**
         * Constructor for the BGM class.
         * @param fileUrl The URL of the audio file to be played.
         */
        try {
            // Get the audio input stream from the file
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(fileUrl).getAbsoluteFile());
            clip = AudioSystem.getClip(); // Get resource
            clip.open(audioIn); // Open and load
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(BGM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void play() {   
        /**
         * Start playing loop the background music.
         */
        clip.setMicrosecondPosition(0); // Set the playback position to the beginning
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop() {
        /**
         * Stop playing the background music.
         */
        clip.stop();
    }
}
