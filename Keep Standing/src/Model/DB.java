/*
 * Filename     : DB.java
 * Programmer   : Villeneuve Andhira Suwandhi
 * Email        : v.andhira@upi.edu
 * Description  : Model for access the database
 */

/*
 * Saya Villeneuve Andhira Suwandhi (2108067) mengerjakan Tugas Masa Depan
 * dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
 * untuk keberkahanNya maka saya tidak melakukan kecurangan seperti
 * yang telah dispesifikasikan. Aamiin.
 */

package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author vil
 */
public class DB {
    // Properties
    private String connAddress = "jdbc:mysql://localhost/db_keep_standing?user=root&password="; // connection config
    private Statement stmt = null; // query statement
    private Connection conn = null; // MySQL database connection
    private ResultSet rs = null; // query result
    
    public DB() throws Exception, SQLException {
        /*
         * Constructor establishes a connection to MySQL database
         * Accepts a string input for the MySQL database connection address
         */
        try {
            // create MySQL driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // establish connection to MySQL database
            conn = DriverManager.getConnection(connAddress);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        } catch (SQLException es) {
            // error if connection fails
            throw es;
        }
    }
    
    public void createQuery(String Query) throws Exception, SQLException {
        /*
         * Executes a query without modifying the data
         * Accepts a string input for the query
         */
        try {
            stmt = conn.createStatement();
            // execute query
            rs = stmt.executeQuery(Query);
            if(stmt.execute(Query)) {
                // retrieve query result
                rs = stmt.getResultSet();
            }
        } catch (SQLException e) {
            // error if query execution fails
            throw e;
        }
    }
    
    public int createUpdate(String Query)throws Exception, SQLException {
        /*
         * Executes a query that modifies the data (update, insert, delete)
         * Accepts a string input for the query
         */
        try {
            stmt = conn.createStatement();
            // execute query
            return stmt.executeUpdate(Query);
        } catch (SQLException e) {
            // error if query execution fails
            throw e;
        }
    }
    
    public ResultSet getResult() throws Exception {
        /*
         * Retrieves the query result
         */
        return rs;
    }
    
    public void closeResult() throws Exception, SQLException {
        /*
         * Closes the query execution connection
         */
        if(rs != null) {
            try {
                rs.close();
            }
            catch(SQLException es){
                rs = null;
                throw es;
            }
        }
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException es) {
                stmt = null;
                throw es;
            }
        }
    }
    
    public void closeConnection() throws Exception, SQLException {
        /*
         * Closes the connection to MySQL database
         */
        if(conn != null) {
            try {
                conn.close();
            }
            catch(SQLException es){
                conn = null;
            }
        }
    }
}
