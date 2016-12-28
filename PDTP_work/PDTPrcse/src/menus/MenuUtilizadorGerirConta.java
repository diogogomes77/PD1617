
package menus;

import controladores.ControladorUtilizador;
import remotebeans.ClientUtilizadorRemote;

public class MenuUtilizadorGerirConta extends MenuUtilizador {

    public MenuUtilizadorGerirConta(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
         titulo="Utilizador - Conta";
        opcoes.clear();
        opcoes.add(new OpcaoMenu("Consultar dados", () -> controlador.consultarDados()));
        opcoes.add(new OpcaoMenu("Atualizar dados", () -> controlador.atualizarDados()));
        opcoes.add(new OpcaoMenu("Pedir Suspensao", () -> controlador.pedirSuspensao()));

    }
}
