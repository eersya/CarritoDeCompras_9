/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author eersya
 */
@ManagedBean(name = "CredentialsBean")
@ViewScoped
public class CredentialsBean implements Serializable {

    private String email;
    private String password;

    /**
     * Creates a new instance of CredentialsBean
     */
    public CredentialsBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
