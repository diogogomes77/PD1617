/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;

import beans.ClientVisitanteRemote;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author diogo
 */
@Named("VisitanteController")
@SessionScoped
public class VisitanteController implements Serializable {

    @EJB
    private ClientVisitanteRemote clientVisitante;
    
    private String username;
    private String password;

    public VisitanteController() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String login(){
        if (username.equals("admin") && password.equals("admin")) {
            return "Utilizador/Inicio";
        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login invalido", "Login invalido");
            ctx.addMessage(null, msg);
            return "";  
        }
    }
    
}
