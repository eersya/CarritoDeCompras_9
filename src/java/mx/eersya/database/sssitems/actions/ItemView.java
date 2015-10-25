/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.database.sssitems.actions;

import java.sql.SQLException;
import mx.eersya.database.ConnectionTemplate;
import mx.eersya.database.sssitems.Constant;
import mx.eersya.database.sssitems.models.Item;

/**
 *
 * @author eersya
 */
public class ItemView extends ConnectionTemplate implements Constant {
    
    public ItemView() {
        super();
    }
    
    public static Item loadItem(Integer id_item) {
        Item item = new Item();
        ItemView iv = new ItemView();
        try {
            iv.openConnection(dbURL, dbUser, dbPassword);
            String sql = "select * from items where id_item="+id_item;
            iv.st = iv.conn.createStatement();
            iv.rs = iv.st.executeQuery(sql);
            while(iv.rs.next()) {
                item.setId_item(iv.rs.getInt("id_item"));
                item.setTitle(iv.rs.getString("title"));
                item.setDescription(iv.rs.getString("description"));
                item.setPrice(iv.rs.getBigDecimal("price"));
                item.setVendor(iv.rs.getInt("vendor"));
                item.setPicturePath(iv.rs.getString("picturepath"));
                System.out.println("PPPPP:" + item.getPicturePath());
            }
        } catch(SQLException ex) {
            System.err.println(ex);
        } finally {
            iv.closeAll();
        }
        return item;
    }
    
}
