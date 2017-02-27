/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;

import autenticacao.Util;
import static autenticacao.Util.getRequest;
import beans.ClientVisitanteRemote;
import java.io.Serializable;
import static java.rmi.server.LogStream.log;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import jpaentidades.TUtilizadores;

/**
 *
 * @author diogo
 */
@Named("VisitanteController")
@SessionScoped
public class VisitanteController extends TUtilizadoresController implements Serializable { // extends TUtilizadoresController

    @EJB
    private ClientVisitanteRemote client;
    
    private UIComponent loginButton;
    
    
    private boolean usernameCheck = true;
   
    protected  String seccao;
    protected ArrayList<Menu> menus;
    
    public VisitanteController() {
        super();
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
    public String login() {

        
        boolean ok = client.loginUtilizador(current.getUsername(), current.getPassword());
        if (ok) {
           //HttpSession session = SessionUtils.getSession();
            HttpSession session = Util.getSession();
            session.setAttribute("username", current.getUsername());
            session.setAttribute("password", current.getPassword());
            if (client.isAdmin(current.getUsername())) {
                return "administradorinicio";
            }
            return "utilizadorinicio";

        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login invalido", "Login invalido");
            ctx.addMessage(loginButton.getClientId(ctx), msg);
            return "visitanteinicio";
        }
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
        else return "ERRO: j� existe um username igual";
    }

    public void checkUsername() {
       // usernameCheck =false;
        //System.out.println("-------" + current.getUsername());
        if (client.existeUsername(getCurrent().getUsername())) {
            usernameCheck =false;
        } else usernameCheck = true;
    }

    public String create(){
         if (usernameCheck==true)
            return create(current);
        else return null;        
    }
}
