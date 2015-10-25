/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.database.sssusers.actions;

import java.sql.SQLException;
import mx.eersya.database.ConnectionTemplate;

/**
 *
 * @author eersya
 */
public class UserAcess extends ConnectionTemplate  implements mx.eersya.database.sssusers.Constant {
    
    public static boolean login(String email, String password) {
        UserAcess x = new UserAcess();
        try {
            x.openConnection(dbURL, dbUser, dbPassword);
            String sql = "Select * from users where email='" + email + "' AND "
                    + "password='" + password +"'";
            x.pst = x.conn.prepareStatement(sql);
            x.rs = x.pst.executeQuery();
            
            int count = 0;
            while(x.rs.next())
                count++;
            
            return count == 1;
        } catch(SQLException  ex) {
            System.err.println(ex);
            return false;
        } finally {
            x.closeAll();
        }
    }
    
}
