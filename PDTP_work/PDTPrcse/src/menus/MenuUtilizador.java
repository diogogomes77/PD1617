/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import static menus.Menu.menuMap;
import static pdtprcse.PDTPrcse.loginUtilizador;
import static pdtprcse.PDTPrcse.registarUtilizador;

public class MenuUtilizador extends Menu {

    private static final MenuUtilizador instancia =  new MenuUtilizador();
    public static MenuUtilizador getInstance() {
        return instancia;
    }
   // private String nome;
    public MenuUtilizador() {
    super();
        opcoes.add(new OpcaoMenu("Criar Leilao",() -> loginUtilizador()));
         opcoes.add(new OpcaoMenu("Terminar Sessao",() -> registarUtilizador()));
       
        
    }


   

}
