/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controladores.ControladorVisitante;
import static controladores.ControladorVisitante.*;
import pdtp.ClientRemote;

public class MenuVisitante extends Menu {

//    private static MenuVisitante instancia =  new MenuVisitante();
//    public static MenuVisitante getInstance() {
//        return instancia;
//    }
    
    public MenuVisitante(ClientRemote ligacao,ControladorVisitante controlador) {
        //super();
        this.controlador = controlador;
        //controlador = (ControladorVisitante) controlador;
        OpcaoMenu login = new OpcaoMenu("Login",() -> controlador.loginUtilizador());
        OpcaoMenu registo = new OpcaoMenu("Registo",() -> controlador.registarUtilizador());
        
        opcoes.add(login);
        opcoes.add(registo);
        
    }

   
   
}
