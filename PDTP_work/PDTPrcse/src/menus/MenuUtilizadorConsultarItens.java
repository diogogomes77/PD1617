
package menus;

import controladores.ControladorUtilizador;
import remotebeans.ClientUtilizadorRemote;

public class MenuUtilizadorConsultarItens extends MenuUtilizador {

    public MenuUtilizadorConsultarItens(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
         titulo="Utilizador - Itens";
        opcoes.clear();
    }
}
