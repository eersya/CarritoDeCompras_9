/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.beans;

import java.io.File;
import java.io.FileInputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.eersya.database.carritoItems.actions.ItemView;
import mx.eersya.database.carritosItems.models.Items;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author eersya
 */
@ManagedBean(name = "IteamBean")
@ViewScoped
public class ItemBean {
    
    private Items item;
    private StreamedContent chart;

    /**
     * Creates a new instance of ItemBean
     */
    public ItemBean() {
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public StreamedContent getChart() {
        return chart;
    }

    public void setChart(StreamedContent chart) {
        this.chart = chart;
    }
    
    public void loadData(Integer id_tem) {
        System.out.println(id_tem);
        System.out.println(id_tem);
        System.out.println(id_tem);
        item = ItemView.loadItem(id_tem);
        try {
            File chartFile = new File(item.getPicturepath());
            chart = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
        } catch(Exception ex) {
            
        }
    }
        
}
