
package menus;

import controladores.ControladorUtilizador;
import beans.ClientUtilizadorRemote;

/**
 *
 * @author diogo
 */
public class MenuUtilizadorSaldo extends MenuUtilizadorAdmin {

    /**
     *
     * @param ligacao
     * @param controlador
     */
    public MenuUtilizadorSaldo(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
       super(ligacao,controlador);
        titulo="Utilizador - Saldo";
       this.controlador=controlador;
        //opcoes.clear();
        opcoes.add(new OpcaoMenu("Ver Saldo", () -> controlador.verSaldo()));
        opcoes.add(new OpcaoMenu("Carregar Saldo", () -> controlador.carregarSaldo()));
        opcoes.add(new OpcaoMenu("Voltar", () -> controlador.mostrarMenu(new MenuUtilizador(ligacao,controlador))));
    }
}
