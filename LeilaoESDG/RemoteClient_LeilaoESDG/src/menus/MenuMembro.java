/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import static menus.Menu.menuMap;

public class MenuMembro extends Menu {

    private static final MenuMembro instancia =  new MenuMembro();
    public static MenuMembro getInstance() {
        return instancia;
    }
   // private String nome;
    private MenuMembro() {
     //   this.nome=nome;
        Menu.menuMap = new TreeMap<>();
        Menu.menuText = new StringBuilder("");
        // adicionar aqui as opçoes do menu
        menuMap.put(1, "Inserir Leilao");
        menuMap.put(2, "Newsletter");
        menuMap.put(3, "Terminar sessao");
        menuMap.put(0, "Sair");
    }


   

}
