
package menus;

import controladores.ControladorUtilizador;
import beans.ClientUtilizadorRemote;

public class MenuUtilizadorSaldo extends MenuUtilizadorAdmin {

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
