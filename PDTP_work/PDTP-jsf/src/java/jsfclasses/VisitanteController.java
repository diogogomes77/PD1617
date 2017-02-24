/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;

import beans.ClientVisitanteRemote;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import jpaentidades.TUtilizadores;

/**
 *
 * @author diogo
 */
@Named("VisitanteController")
@SessionScoped
public class VisitanteController  implements Serializable { // extends TUtilizadoresController

    @EJB
    private ClientVisitanteRemote client;
    
    TUtilizadoresController tUtilizadorController ;
    
    private UIComponent loginButton;
    
    TUtilizadores current;
    
    private boolean usernameCheck = true;
    
    private ArrayList<Menu> menus;
    
    private String seccao;
    
    public VisitanteController() {
        super();
        this.tUtilizadorController = TUtilizadoresController.getInstance();
        this.seccao = "Visitante";
       
        menus = new ArrayList<>();
        Menu menuVisitante = new Menu("menu1","");
        menuVisitante.setTituloMenu("Visitante");
        menuVisitante.addMenuPage("Inicio");
        menuVisitante.addMenuPage("Registo");
        menuVisitante.addMenuPage("Vendas Recentes");
        menuVisitante.addMenuPage("Reativar Conta");
        menuVisitante.addMenuPage("Newsletter");
        menus.add(menuVisitante);
    }
    public ArrayList<Menu> getMenus() {

        return menus;
    }

    public TUtilizadores getSelected() {
        if (current == null) {
            current = new TUtilizadores();
            //selectedItemIndex = -1;
        }
        return current;
    }
    public String login() {
        boolean ok = client.loginUtilizador(current.getUsername(), current.getPassword());
        if (ok) {
            HttpSession session = SessionUtils.getSession();
            if ("admin".equals(current.getUsername())) {
                return "/Administrador/Inicio";
            }
            return "/Utilizador/Inicio";

        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login invalido", "Login invalido");
            ctx.addMessage(loginButton.getClientId(ctx), msg);
            return "";
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }

    public UIComponent getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(UIComponent loginButton) {
        this.loginButton = loginButton;
    }

    public String getUsernameCheck() {
        if (usernameCheck==true)
            return "Ajax check";
        else return "ERRO: já existe um username igual";
    }

    public void checkUsername() {
        //System.out.println("-------" + current.getUsername());
        if (client.existeUsername(tUtilizadorController.getCurrent().getUsername())) {
            usernameCheck =false;
        } else usernameCheck = true;
    }
   

    public String create(){
         if (usernameCheck==true)
            return tUtilizadorController.create(current);
        else return null;        
    }


}
