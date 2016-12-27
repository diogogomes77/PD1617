
package menus;

import controladores.ControladorUtilizador;
import pdtp.ClientRemote;

public class MenuUtilizadorGerirConta extends MenuUtilizador {

    public MenuUtilizadorGerirConta(ClientRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
        opcoes.clear();
        opcoes.add(new OpcaoMenu("Consultar dados", () -> controlador.consultarDados()));
        opcoes.add(new OpcaoMenu("Atualizar dados", () -> controlador.atualizarDados()));
        opcoes.add(new OpcaoMenu("Pedir Suspensao", () -> controlador.pedirSuspensao()));

    }
}
