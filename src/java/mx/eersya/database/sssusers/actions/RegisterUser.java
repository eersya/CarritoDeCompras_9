/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.database.sssusers.actions;

import java.sql.SQLException;
import mx.eersya.database.ConnectionTemplate;
import mx.eersya.database.sssusers.Constant;

/**
 *
 * @author eersya
 */
public class RegisterUser extends ConnectionTemplate implements Constant {

    public RegisterUser() {
        super();
    }
    
    public static int existsUser(String email) {
        RegisterUser ru = new RegisterUser();
        try {
            ru.openConnection(dbURL, dbUser, dbPassword);
            String email_ = (email.length() <= 22) ? email : email.substring(0,21);
            String exists_user = "select id_user from sssusers.users where email='" + email_ +"'";
            ru.st = ru.conn.createStatement();
            ru.rs = ru.st.executeQuery(exists_user);
            int counter = 0;
            while(ru.rs.next()) {
                counter++;
            }
            return counter;
        } catch(SQLException ex) {
            System.err.println(ex);
        } finally {
            ru.closeAll();
        }
        return -1;
    }
    
    
    public int register(String name, String lastname, String email, String password,
            String address, String city) {
        
        try {
            this.openConnection(dbURL, dbUser, dbPassword);
            String add_user = "{ call add_user(?,?,?,?,?,?) }";
            cst = conn.prepareCall(add_user);
            cst.setString("name_s", name);
            cst.setString("lastname_s", lastname);
            cst.setString("email_s", email);
            cst.setString("password_s", password);
            cst.setString("address_s", address);
            cst.setString("city_s", city);
            cst.execute();
            return 0;
        } catch (SQLException ex) {
            System.err.println(ex);
        } finally {
            this.closeAll();
        }
        return -1;
    }
    
}
