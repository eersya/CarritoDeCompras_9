/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.eersya.beans;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import mx.eersya.database.sssusers.actions.RegisterUser;

/**
 *
 * @author eersya
 */
@ManagedBean(name = "RegisterBean")
@ViewScoped
public class RegisterBean {
    
    private FacesContext fc;

    private String name;
    private String lastname;
    private Date birthday;
    private String email = "";
    private String city;
    private String password = "";
    private String address;

    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public void validate(ComponentSystemEvent event) {
        fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        validatePassword();
        validateInputs();
        validateEmail();
    }
    
    public void validateEmail() {
        UIComponent components = UIComponent.getCurrentComponent(fc);
        UIInput uiInputEmail = (UIInput) components.findComponent("email");
        String emailS = uiInputEmail.getLocalValue() == null ? "" 
                : uiInputEmail.getLocalValue().toString();
        String emailId = uiInputEmail.getClientId();
        if(emailS.isEmpty()) {
            return;
        }
        if(RegisterUser.existsUser(emailS) > 0) {
            FacesMessage msg = new FacesMessage("This email exists");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(emailId, msg);
            fc.renderResponse();
        }
    }
    
    private void validatePassword() {
        UIComponent components = UIComponent.getCurrentComponent(fc);
	// get password
	UIInput uiInputPassword = (UIInput) components.findComponent("pass");
	String passwordS = uiInputPassword.getLocalValue() == null ? "" 
                : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();

	// get confirm password
	UIInput uiInputConfirmPassword = (UIInput) components.findComponent("retypepass");
	String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

	// Let required="true" do its job.
	if (passwordS.isEmpty() || confirmPassword.isEmpty()) {
            return;
	 }

        if(!passwordS.equals(confirmPassword)) {
            FacesMessage msg = new FacesMessage("Password must match retype password");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg);
            fc.renderResponse();		
	}
    }
    
    private void validateInputs() {
        UIComponent components = UIComponent.getCurrentComponent(fc);
        UIInput uiInputName = (UIInput) components.findComponent("name");
        if(uiInputName.getLocalValue() == null)
            sendMessage("Name", uiInputName.getClientId());
        
        UIInput uiInputLast = (UIInput) components.findComponent("lastname");
        if(uiInputLast.getLocalValue() == null)
            sendMessage("Lastname", uiInputLast.getClientId());   
        
        UIInput uiInputEmail = (UIInput) components.findComponent("email");
        if(uiInputEmail.getLocalValue() == null)
            sendMessage("Email", uiInputEmail.getClientId());
        
        UIInput uiInputAddress = (UIInput) components.findComponent("address");
        if(uiInputAddress.getLocalValue() == null)
            sendMessage("Address", uiInputAddress.getClientId());
    }
    
    private void sendMessage(final String mssg, String fm) {
        FacesMessage fMssg = new FacesMessage(mssg);
        fMssg.setSeverity(FacesMessage.SEVERITY_ERROR);
        fc.addMessage(fm, fMssg);
        fc.renderResponse();
    }
    
    public String signUp() {
        mx.eersya.database.sssusers.actions.RegisterUser register = new mx.eersya.database.sssusers.actions.RegisterUser();
        register.register(name, lastname, email, password, address, city);
        return "home";
    }
    
}
