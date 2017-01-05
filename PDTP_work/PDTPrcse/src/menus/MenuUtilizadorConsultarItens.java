
package menus;

import controladores.ControladorUtilizador;
import beans.ClientUtilizadorRemote;

public class MenuUtilizadorConsultarItens extends MenuUtilizadorAdmin {

    public MenuUtilizadorConsultarItens(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
         titulo="Utilizador - Itens";
        opcoes.clear();
    }
}
