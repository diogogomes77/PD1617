
package menus;

import controladores.ControladorUtilizador;
import pdtp.ClientRemote;

public class MenuUtilizadorConsultarItens extends MenuUtilizador {

    public MenuUtilizadorConsultarItens(ClientRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
        opcoes.clear();
    }
}
