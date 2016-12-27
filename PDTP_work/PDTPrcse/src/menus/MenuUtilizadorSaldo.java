
package menus;

import controladores.ControladorUtilizador;
import pdtp.ClientRemote;

public class MenuUtilizadorSaldo extends MenuUtilizador {

    public MenuUtilizadorSaldo(ClientRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
        opcoes.clear();
        opcoes.add(new OpcaoMenu("Ver Saldo", () -> controlador.verSaldo()));
        opcoes.add(new OpcaoMenu("Carregar Saldo", () -> controlador.carregarSaldo()));
        opcoes.add(new OpcaoMenu("Voltar", () -> controlador.mostrarMenu(new MenuUtilizador(ligacao,controlador))));
    }
}
