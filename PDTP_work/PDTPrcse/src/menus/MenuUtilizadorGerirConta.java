
package menus;

import controladores.ControladorUtilizador;
import beans.ClientUtilizadorRemote;

public class MenuUtilizadorGerirConta extends MenuUtilizadorAdmin {

    public MenuUtilizadorGerirConta(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao,controlador);
         titulo="Utilizador - Conta";
         this.controlador=controlador;
        //opcoes.clear();
        opcoes.add(new OpcaoMenu("Consultar dados", () -> controlador.consultarDados()));
        opcoes.add(new OpcaoMenu("Atualizar dados", () -> controlador.atualizarDados()));
        opcoes.add(new OpcaoMenu("Pedir Suspensao", () -> controlador.pedirSuspensao()));
      opcoes.add(new OpcaoMenu("Voltar", () -> controlador.mostrarMenu(new MenuUtilizador(ligacao,controlador))));

    }
}
