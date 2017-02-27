/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;


import autenticacao.Util;
import beans.ClientAdminRemote;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author diogo
 */
@Named("AdminController")
@SessionScoped
public class AdminController extends VisitanteController implements Serializable {

    @EJB
    private ClientAdminRemote client;
    private ArrayList<Menu> menus;
    private String seccao;


    public AdminController() {
        super();
        seccao="Administrador";
        String subseccao = new String();
        ArrayList<String> paginas = new ArrayList<>();
        menus = new ArrayList<Menu>();
        
        Menu menuAdmin = new Menu("menu1","");
        menuAdmin.setTituloMenu("Administrador");
        menuAdmin.addMenuPage("Inicio");
        menuAdmin.addMenuPage("Minhas Mensagens");
        menuAdmin.addMenuPage("Enviar Mensagem");
        menuAdmin.addMenuPage("Consultar Utilizador");
        menuAdmin.addMenuPage("Consultar Item");
        menus.add(menuAdmin);
        Menu menuAdminCategorias = new Menu("menu2",seccao);
        menuAdminCategorias.setTituloMenu("Categorias");
        menuAdminCategorias.addMenuPage("Listar categorias");
        menuAdminCategorias.addMenuPage("Nova categoria");
        menuAdminCategorias.addMenuPage("Alterar Categoria");
        menuAdminCategorias.addMenuPage("Eliminar Categoria");
        menus.add(menuAdminCategorias);
        Menu menuAdminContas = new Menu("menu3",seccao);
        menuAdminContas.setTituloMenu("Contas");
        menuAdminContas.addMenuPage("Pedidos de Adesao");
        menuAdminContas.addMenuPage("Activar Contas");
        menuAdminContas.addMenuPage("Pedidos de Reativacao");
        menuAdminContas.addMenuPage("Reactivar Contas");
        menuAdminContas.addMenuPage("Pedidos de Suspensao");
        menus.add(menuAdminContas);
        Menu menuAdminDenuncias = new Menu("menu4",seccao);
        menuAdminDenuncias.setTituloMenu("Denuncias");
        menuAdminDenuncias.addMenuPage("Denuncias de vendedores");
        menuAdminDenuncias.addMenuPage("Denuncias de itens");
        menus.add(menuAdminDenuncias);
    }

    public ArrayList<Menu> getMenus() {

        return menus;
    }
    
public String logout() {
        //HttpSession session = SessionUtils.getSession();
        
         HttpSession session = Util.getSession();
        session.invalidate();
        client.logOff();
        return "/Inicio.xhtml";
    }
}
