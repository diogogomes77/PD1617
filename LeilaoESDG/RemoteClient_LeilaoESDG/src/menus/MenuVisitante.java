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
import static menus.Menu.menuText;

public class MenuVisitante extends Menu {

    private static MenuVisitante instancia =  new MenuVisitante();
    public static MenuVisitante getInstance() {
        return instancia;
    }
    private MenuVisitante() {
        Menu.menuMap = new TreeMap<>();
        Menu.menuText = new StringBuilder("");
        // adicionar aqui as opçoes do menu
        menuMap.put(1, "Inscricao");
        menuMap.put(2, "Newsletter");
        menuMap.put(3, "Entrar");
        menuMap.put(0, "Sair");
    }

   
   
}
