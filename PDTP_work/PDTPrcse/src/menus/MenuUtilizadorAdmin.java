
package menus;

import controladores.ControladorUserAdmin;
import beans.ClientRemote;


public class MenuUtilizadorAdmin extends Menu{

    public MenuUtilizadorAdmin(ClientRemote ligacao, ControladorUserAdmin controlador) {
        this.controlador = controlador;
        opcoes.add(new OpcaoMenu("Terminar Sessao", () -> controlador.logOff()));
        
    }
    
}
