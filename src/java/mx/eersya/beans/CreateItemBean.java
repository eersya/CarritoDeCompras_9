package mx.eersya.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import mx.eersya.database.carritoItems.actions.DBItem;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author eersya
 */
@ManagedBean(name = "CreateItemBean")
@ViewScoped
public class CreateItemBean implements Serializable {
    
    private String title;
    private String description;
    private BigDecimal price;
    private UploadedFile picture;
    
    protected String picturePath;

    /**
     * Creates a new instance of CreateItemBean
     */
    public CreateItemBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UploadedFile getPicture() {
        return picture;
    }

    public void setPicture(UploadedFile picture) {
        this.picture = picture;
    }
    
    public void validate(ComponentSystemEvent event) {
        
    }
    
    public String create() {
        savePicture();
        DBItem dbItem = new DBItem();
        dbItem.createItem(title, description, title, price, picturePath);
        return "home";
    }
    
    public void savePicture() {
        try {
            Path path = Paths.get("/home/eersya/Projects/W/");
            String filename = FilenameUtils.getBaseName(picture.getFileName());
            String extension = FilenameUtils.getExtension(picture.getFileName());
            Path file = Files.createTempFile(path, filename, "." + extension);
            picturePath = file.toString();
            try(InputStream in = picture.getInputstream()) {
                Files.copy(in, file, StandardCopyOption.REPLACE_EXISTING);
            } catch(IOException exa) {
                System.err.println(exa);
            }
            picturePath = file.toString();
            System.out.println("Picture:" + picturePath);
        } catch(IOException ex) {
            System.err.println(ex);
        }
    }
    
}
