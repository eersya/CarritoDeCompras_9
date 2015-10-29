/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mx.eersya.database.carritoUsers.actions.UserAcess;

/**
 *
 * @author eersya
 */
@ManagedBean(name = "SessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String email;
    private Integer curItem;
    private int counterPurchases;
    private ArrayList<Integer> ids_item = new ArrayList<>();

    /**
     * Creates a new instance of SessionBean
     */
    public SessionBean() {
    }

    public String getEmail() {
        return email;
    }

    public Integer getCurItem() {
        return curItem;
    }

    public int getCounterPurchases() {
        return counterPurchases;
    }
    
    public String login(String email, String password) {
        boolean result = UserAcess.login(email, password);
        System.out.println("email:"+email+":password:"+password);
        if(result) {
            this.email = email;
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("email", email);
            return "home";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login",
                    "User or password are wrong"));
            
        }
        return "SignIn";
    }
    
    public String viewItem(Integer id_item) {
        curItem = id_item;
        System.out.println("S:"+curItem);
        return "item";
    }
    
    public String sigin(){
        return "SignIn";
    }
    
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "home";
    }
    
    public String doPurchase(Integer id_item) {
        counterPurchases++;
        ids_item.add(id_item);
        return "home";
    }
    
    public boolean isInit() {
        return email != null;
    }
    
}
