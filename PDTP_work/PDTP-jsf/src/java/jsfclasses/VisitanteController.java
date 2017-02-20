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

/**
 *
 * @author diogo
 */
@Named("VisitanteController")
@SessionScoped
public class VisitanteController extends TUtilizadoresController implements Serializable {

    @EJB
    private ClientVisitanteRemote client;
    private UIComponent loginButton;
    private String username;
    private String password;
    private String nome;
    private String morada;
    private boolean usernameCheck = true;
    private ArrayList<Menu> menus;
    
    public VisitanteController() {
        super();
        menus = new ArrayList<Menu>();
        Menu menuVisitante = new Menu("menu1");
        menuVisitante.setTituloMenu("Visitante");
        menuVisitante.addMenuPage(new MenuPage("/index.xhtml", "Inicio"));
        menuVisitante.addMenuPage(new MenuPage("/Visitante/Login.xhtml", "Minhas mensagens"));
        menuVisitante.addMenuPage(new MenuPage("/Visitante/Registo.xhtml", "Registo"));
        menuVisitante.addMenuPage(new MenuPage("/Visitante/VendasRecentes.xhtml", "VendasRecentes"));
        menuVisitante.addMenuPage(new MenuPage("/Visitante/ReativarConta.xhtml", "ReativarConta"));
        menuVisitante.addMenuPage(new MenuPage("/Visitante/Newsletter.xhtml", "Newsletter"));
        menus.add(menuVisitante);
    }
    public ArrayList<Menu> getMenus() {

        return menus;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
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

    public String login() {
        boolean ok = client.loginUtilizador(username, password);
        if (ok) {
            HttpSession session = SessionUtils.getSession();
//            if ("admin".equals(username)) {
//                return "/Admin/Inicio";
//            }
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
        if (client.existeUsername(current.getUsername())) {
            usernameCheck =false;
        } else usernameCheck = true;
    }
   
    @Override
    public String create(){
         if (usernameCheck==true)
            return super.create();
        else return null;        
    }


}
