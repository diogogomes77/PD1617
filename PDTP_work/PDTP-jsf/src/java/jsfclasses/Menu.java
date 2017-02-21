/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diogo
 */
public class Menu {

    private ArrayList<MenuPage> menuPages;
    private String tituloMenu;
    private String id;
    private String realPath;
    private String seccao;
    
    public Menu(String id,String seccao) {
        menuPages = new ArrayList<MenuPage>();
        this.id = id;
        if (!"".equals(seccao))
            this.seccao = "/"+seccao;
        else this.seccao = seccao;
    }

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

    public ArrayList<MenuPage> getMenuPages() {
        return menuPages;
    }

    public void setMenuPages(ArrayList<MenuPage> menuPages) {
        this.menuPages = menuPages;
    }

    public void addMenuPage(String pagina) {
       
        String pagUrl = pagina.replaceAll("\\s+", "");
        String menuUrl = tituloMenu.replaceAll("\\s+", "");
        String fullUrl = seccao +"/"+ menuUrl + "/" + pagUrl + ".xhtml";
       
        MenuPage menuPage = new MenuPage(fullUrl, pagina);

        menuPages.add(menuPage);
    }

   
}
