/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import static menus.Menu.menuText;
import static pdtprcse.PDTPrcse.loginUtilizador;
import static pdtprcse.PDTPrcse.registarUtilizador;

public class MenuVisitante extends Menu {

    private static MenuVisitante instancia =  new MenuVisitante();
    public static MenuVisitante getInstance() {
        return instancia;
    }
    
    public MenuVisitante() {
        super();
        OpcaoMenu login = new OpcaoMenu("Login",() -> loginUtilizador());
        OpcaoMenu registo = new OpcaoMenu("Registo",() -> registarUtilizador());
        
        opcoes.add(login);
        opcoes.add(registo);
        
    }

   
   
}
