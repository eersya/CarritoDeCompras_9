/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.database.carritoItems.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import mx.eersya.database.ConnectionTemplate;
import mx.eersya.database.carritoItems.DatabaseProperties;
import mx.eersya.database.carritosItems.models.SimpletItem;

/**
 *
 * @author eersya
 */
public class ItemList extends ConnectionTemplate implements DatabaseProperties {
    
    public ItemList() {
        super();
    }
    
    public static ArrayList<SimpletItem> loadList() {
        ArrayList<SimpletItem> items = new ArrayList<>();
        ItemList il = new ItemList();
        try {
            il.openConnection(dbURL, dbUser, dbPassword);
            String sql = "select id_item, title, price from items";
            il.st = il.conn.createStatement();
            il.rs = il.st.executeQuery(sql);
            while(il.rs.next()) {
                SimpletItem curItem = new SimpletItem();
                curItem.setId_item(il.rs.getInt("id_item"));
                curItem.setTitle(il.rs.getString("title"));
                System.out.println("id:"+curItem.getId_item()+":title:"+curItem.getTitle());
                curItem.setPrice(il.rs.getBigDecimal("price"));
                items.add(curItem);
            }
        } catch(SQLException ex) {
            System.err.println(ex);
        } finally {
            il.closeAll();
        }
        return items;
    }
    
}
