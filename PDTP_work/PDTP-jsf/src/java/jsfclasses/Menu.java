/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;

import java.util.ArrayList;

/**
 *
 * @author diogo
 */
public class Menu {
    private ArrayList<MenuPage> menuPages;
    private String tituloMenu;
    private String id;

    public String getTituloMenu() {
        return tituloMenu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTituloMenu(String tituloMenu) {
        this.tituloMenu = tituloMenu;
    }
    public Menu(String id) {
        menuPages = new ArrayList<MenuPage>();
        this.id=id;
    }

    public ArrayList<MenuPage> getMenuPages() {
        return menuPages;
    }

    public void setMenuPages(ArrayList<MenuPage> menuPages) {
        this.menuPages = menuPages;
    }
     
    public void addMenuPage(MenuPage page){
         menuPages.add(page);
    }
}
