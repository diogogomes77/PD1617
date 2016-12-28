
package menus;

import controladores.ControladorUserAdmin;
import pdtp.ClientRemote;


public class MenuUtilizadorAdministrador extends Menu{

    public MenuUtilizadorAdministrador(ClientRemote ligacao, ControladorUserAdmin controlador) {
        this.controlador = controlador;
        opcoes.add(new OpcaoMenu("Terminar Sessao", () -> controlador.logOff()));
    }
    
}
