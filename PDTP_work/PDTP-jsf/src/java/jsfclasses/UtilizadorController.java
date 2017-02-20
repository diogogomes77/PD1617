/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;

import beans.ClientUtilizadorRemote;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author diogo
 */
@Named("UtilizadorController")
@SessionScoped
public class UtilizadorController extends VisitanteController implements Serializable {

    @EJB
    private ClientUtilizadorRemote client;
    private ArrayList<Menu> menus;

    public UtilizadorController() {
        super();
        menus = new ArrayList<Menu>();
        Menu menuUtilizador = new Menu("menu1");
        menuUtilizador.setTituloMenu("Utilizador");
        menuUtilizador.addMenuPage(new MenuPage("/Utilizador/Inicio.xhtml", "Inicio"));
        menuUtilizador.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Minhas mensagens"));
        menuUtilizador.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Enviar mensagens"));
        menus.add(menuUtilizador);
        Menu menuUtilizadorConta = new Menu("menu2");
        menuUtilizadorConta.setTituloMenu("Minha Conta");
        menuUtilizadorConta.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Consultar dados"));
        menuUtilizadorConta.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Atualizar dados"));
        menuUtilizadorConta.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Alterar password"));
        menuUtilizadorConta.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Pedir Suspensao"));
        menus.add(menuUtilizadorConta);
        Menu menuUtilizadorSaldo = new Menu("menu3");
        menuUtilizadorSaldo.setTituloMenu("Itens");
        menuUtilizadorSaldo.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Colocar Item a venda"));
        menuUtilizadorSaldo.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Consultar meus Itens a venda"));
        menuUtilizadorSaldo.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Consultar Itens seguidos"));
        menuUtilizadorSaldo.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Historial de Itens"));
        menuUtilizadorSaldo.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Consultar Lista de Itens"));
        menuUtilizadorSaldo.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Consultar Item"));
        menuUtilizadorSaldo.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Consultar Itens comprados"));
        menuUtilizadorSaldo.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Consultar Itens por pagar"));
        menuUtilizadorSaldo.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Concluir transacao"));
        menus.add(menuUtilizadorSaldo);
        Menu menuUtilizadorItens = new Menu("menu4");
        menuUtilizadorItens.setTituloMenu("Saldo");
        menuUtilizadorItens.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Ver Saldo"));
        menuUtilizadorItens.addMenuPage(new MenuPage("/Utilizador/Login.xhtml", "Carregar Saldo"));
        menus.add(menuUtilizadorItens);
    }

    public ArrayList<Menu> getMenus() {

        return menus;
    }
}
