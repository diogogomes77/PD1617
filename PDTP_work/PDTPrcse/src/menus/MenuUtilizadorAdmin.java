
package menus;

import controladores.ControladorUserAdmin;
import beans.ClientRemote;

/**
 *
 * @author diogo
 */
public class MenuUtilizadorAdmin extends Menu{

    /**
     *
     * @param ligacao
     * @param controlador
     */
    public MenuUtilizadorAdmin(ClientRemote ligacao, ControladorUserAdmin controlador) {
        this.controlador = controlador;
        opcoes.add(new OpcaoMenu("Terminar Sessao", () -> controlador.logOff()));
        
    }
    
}
