/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.database;

import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eersya
 */
public class ConnectionTemplate {
    
    protected Connection conn;
    protected Statement st;
    protected ResultSet rs;
    protected PreparedStatement pst;
    protected CallableStatement cst;
    
    protected void openConnection(String dbURL, String dbUser, String dbPassword) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch(SQLException ex) {
            System.err.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void closeAll() {
        try {
            if(conn != null)
                conn.close();
            if(st != null)
                st.close();
            if(rs != null)
                rs.close();
            if(pst != null)
                pst.close();
            if(cst != null)
                cst.close();
        } catch(SQLException ex) {
            System.err.println(ex);
        }
    }
    
    public void status() {
        try {
            if(conn != null)
                if(!conn.isClosed())
                    System.out.println("Opened");
            else
                    System.out.println("Closed");
        } catch(SQLException ex) {
            System.err.println(ex);
        }
    }
    
}
