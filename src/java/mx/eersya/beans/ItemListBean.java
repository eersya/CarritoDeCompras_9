/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import mx.eersya.database.sssitems.actions.ItemList;
import mx.eersya.database.sssitems.models.SimpletItem;

/**
 *
 * @author eersya
 */
@Named(value = "ItemListBean")
@ViewScoped
public class ItemListBean implements Serializable {
    
    private ArrayList<SimpletItem> items = new ArrayList<>();

    /**
     * Creates a new instance of ItemListBean
     */
    public ItemListBean() {
        items = ItemList.loadList();
    }

    public ArrayList<SimpletItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<SimpletItem> items) {
        this.items = items;
    }
    
    
    
}
