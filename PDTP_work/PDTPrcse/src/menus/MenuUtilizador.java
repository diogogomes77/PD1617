/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controladores.ControladorUtilizador;

import pdtp.ClientRemote;

public class MenuUtilizador extends Menu {

//    private static final MenuUtilizador instancia = new MenuUtilizador();
//
//    public static MenuUtilizador getInstance() {
//        return instancia;
//    }

    public MenuUtilizador(ClientRemote ligacao, ControladorUtilizador controlador) {
       // super();
       this.controlador=controlador;
        opcoes.add(new OpcaoMenu("Criar Leilao", () -> controlador.sair()));
        opcoes.add(new OpcaoMenu("Terminar Sessao", () -> controlador.logOff()));
    }

}
