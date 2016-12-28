
package menus;

import controladores.ControladorUtilizador;
import pdtp.ClientUtilizadorRemote;

public class MenuUtilizadorSaldo extends Menu {

    public MenuUtilizadorSaldo(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
       super();
        titulo="Utilizador - Saldo";
       this.controlador=controlador;
        //opcoes.clear();
        opcoes.add(new OpcaoMenu("Ver Saldo", () -> controlador.verSaldo()));
        opcoes.add(new OpcaoMenu("Carregar Saldo", () -> controlador.carregarSaldo()));
        opcoes.add(new OpcaoMenu("Voltar", () -> controlador.mostrarMenu(new MenuUtilizador(ligacao,controlador))));
    }
}
