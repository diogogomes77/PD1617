
package controladores;

import menus.MenuVisitante;
import pdtp.ClientRemote;

public class ControladorUserAdmin extends Controlador{

     public ControladorUserAdmin(ClientRemote ligacao) {
        this.ligacao = ligacao;
    }
        public void logOff() {
        if (ligacao.logOff()) {
            System.out.println("\nlog off");
            controlador = new ControladorVisitante(ligacao);
            menu = new MenuVisitante(ligacao, (ControladorVisitante) controlador);

        } else {
            System.out.println("ERRO: accao nao aceite");
        }
    }
}
