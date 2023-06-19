/*
 * Filename     : ScoreTable.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : Model for queries tscore table
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package Model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author vil
 */
public class ScoreTable extends DB {
    // Properties
    private int rowsAffected; // affected any rows
    private boolean updated; // update status
    
    public ScoreTable() throws Exception, SQLException {
        /**
         * Constructor for the ScoreTable class.
         * It calls the superclass (DB) constructor to establish a database connection.
         * 
         * @throws Exception if an error occurs during the database connection.
         * @throws SQLException if a SQL-related error occurs.
         */
        super();
    }
    
    public void getAllData() {
        /**
         * Retrieves all records from the tscore table in descending order of score.
         * It executes the query to retrieve the records.
         * 
         * @throws SQLException if a SQL-related error occurs.
         */
        String query = "SELECT * FROM tscore ORDER BY score DESC";
        try {
            createQuery(query); // execute the query to retrieve the records
        } catch (Exception ex) {
            Logger.getLogger(ScoreTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertData(Score data) throws Exception {
        /**
         * Inserts a new row for the given username, score, and standing into the tscore table.
         * 
         * @param data the Score object containing username, score, and standing.
         * @throws Exception if an error occurs during the database operation.
         */
        try {
            String query = "INSERT INTO tscore (username, score, standing) VALUES ('" + data.getUsername() + "', " + data.getScore() + ", " + data.getStanding() + ")";
            createUpdate(query);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public boolean updateData(Score data) throws Exception {
        /**
         * Updates the score and standing for the given username in the tscore table.
         * 
         * @param data the Score object containing username, score, and standing.
         * @return true if the update affected any rows, false otherwise.
         * @throws Exception if an error occurs during the database operation.
         */
        try {
            String query = "UPDATE tscore SET score = score + " + data.getScore() + ", standing = standing + " + data.getStanding() + " WHERE username = '" + data.getUsername() + "'";
            rowsAffected = createUpdate(query);
            return rowsAffected > 0;
        } catch (Exception e) {
            throw e;
        }
    }
 
    public void data(String username, int score, int standing) {
        /**
         * Updates or inserts a record for the given username, score, and standing in the tscore table.
         * If the username already exists, it updates the score and standing.
         * If the username doesn't exist, it inserts a new row with the username, score, and standing.
         * 
         * @param username the username to update or insert.
         * @param score the score to update or insert.
         * @param standing the standing to update or insert.
         */
        try {
            Score data = new Score(username, score, standing); // create a Score object
            updated = updateData(data);
            if (!updated) {
                insertData(data);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
