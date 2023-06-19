/*
 * Filename     : ScoreProcess.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : View Model for processing score data in table score
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package ViewModel;

import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import Model.ScoreTable;

/**
 *
 * @author vil
 */
public class ScoreProcess {
    // Properties
    ScoreTable data; // scoreTable object for managing the score data

    public ScoreProcess() {
        // Constructor
        try {
            this.data = new ScoreTable(); // create a new ScoreTable instance
        } catch (Exception ex) {
            Logger.getLogger(ScoreProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DefaultTableModel getDataTable() {
        DefaultTableModel dataTabel = null;
        try {
            Object[] column = { "Username", "Score", "Standing" };
            dataTabel = new DefaultTableModel(null, column); // create a new DefaultTableModel
            this.data.getAllData(); // retrieve all the score data
            ResultSet rs = this.data.getResult(); // get the result set
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>();
                row.add(rs.getString("username")); // add the username to the row
                row.add(rs.getInt("score")); // add the score to the row
                row.add(rs.getInt("standing")); // add the standing to the row
                dataTabel.addRow(row); // add the row to the table model
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                this.data.closeResult(); // close the result set
            } catch (Exception ex) {
                Logger.getLogger(ScoreProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dataTabel; // return the table model
    }
}
