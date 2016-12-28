/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controladores.ControladorVisitante;

import pdtp.ClientVisitanteRemote;

public class MenuVisitante extends Menu {

//    private static MenuVisitante instancia =  new MenuVisitante();
//    public static MenuVisitante getInstance() {
//        return instancia;
//    }
    
    public MenuVisitante(ClientVisitanteRemote ligacao,ControladorVisitante controlador) {
        this.controlador = controlador;
        titulo="Visitante";
        opcoes.add(new OpcaoMenu("Login",() -> controlador.loginUtilizador()));
        opcoes.add(new OpcaoMenu("Registo",() -> controlador.registarUtilizador()));
        opcoes.add(new OpcaoMenu("Vendas recentes",() -> controlador.vendasRecentes()));
        opcoes.add(new OpcaoMenu("Reativar Conta",() -> controlador.reativarConta()));

        
    }

   
   
}
