/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.database.sssitems.actions;

import java.math.BigDecimal;
import java.sql.SQLException;
import mx.eersya.database.ConnectionTemplate;
import mx.eersya.database.sssitems.Constant;

/**
 *
 * @author eersya
 */
public class DBItem extends ConnectionTemplate implements Constant {
    
    public DBItem() {
        super();
    }
    
    public int createItem(String title, String description, String email, BigDecimal price, String picturePath) {
        try {
            openConnection(dbURL, dbUser, dbPassword);
            String add_item = "{ call add_item(?,?,?,?,?) }";
            cst = conn.prepareCall(add_item);
            cst.setString("title_s", title);
            cst.setString("description_s", description);
            cst.setString("email_s", email);
            cst.setBigDecimal("price_s", price);
            cst.setString("picturepath_s", picturePath);
            cst.executeQuery();
            return 0;
        } catch(SQLException ex) {
            System.err.println(ex);
        } finally {
            closeAll();
        }
        return -1;
    }
}
