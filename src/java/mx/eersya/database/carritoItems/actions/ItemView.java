/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.database.carritoItems.actions;

import java.sql.SQLException;
import mx.eersya.database.ConnectionTemplate;
import mx.eersya.database.carritoItems.DatabaseProperties;
import mx.eersya.database.carritosItems.models.Items;

/**
 *
 * @author eersya
 */
public class ItemView extends ConnectionTemplate implements DatabaseProperties {
    
    public ItemView() {
        super();
    }
    
    public static Items loadItem(Integer id_item) {
        Items item = new Items();
        ItemView iv = new ItemView();
        try {
            iv.openConnection(dbURL, dbUser, dbPassword);
            String sql = "select * from items where id_item="+id_item;
            iv.st = iv.conn.createStatement();
            iv.rs = iv.st.executeQuery(sql);
            while(iv.rs.next()) {
                item.setIdItem(iv.rs.getInt("id_item"));
                item.setTitle(iv.rs.getString("title"));
                item.setDescription(iv.rs.getString("description"));
                item.setPrice(iv.rs.getLong("price"));
                item.setVendor(iv.rs.getInt("vendor"));
                item.setPicturepath(iv.rs.getString("picturepath"));
                System.out.println("New image:" + item.getPicturepath());
            }
        } catch(SQLException ex) {
            System.err.println(ex);
        } finally {
            iv.closeAll();
        }
        return item;
    }
    
}
