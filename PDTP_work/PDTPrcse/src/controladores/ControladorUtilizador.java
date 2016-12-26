package controladores;

import menus.MenuUtilizador;
import menus.MenuVisitante;
import pdtp.ClientRemote;

public class ControladorUtilizador extends Controlador {

    public ControladorUtilizador(ClientRemote ligacao) {
       this.ligacao=ligacao;
    }
        
    public void logOff() {
        if (ligacao.logOff()) {
            System.out.println("\nlog off");
            controlador = new ControladorVisitante(ligacao);
            menu = new MenuVisitante(ligacao,(ControladorVisitante)controlador);
           
        } else {
            System.out.println("ERRO: accao nao aceite");
        }
    }


}
