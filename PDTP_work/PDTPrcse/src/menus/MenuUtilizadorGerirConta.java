
package menus;

import controladores.ControladorUtilizador;
import beans.ClientUtilizadorRemote;

/**
 *
 * @author diogo
 */
public class MenuUtilizadorGerirConta extends MenuUtilizadorAdmin {

    /**
     *
     * @param ligacao
     * @param controlador
     */
    public MenuUtilizadorGerirConta(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao,controlador);
         titulo="Utilizador - Conta";
         this.controlador=controlador;
        //opcoes.clear();
        opcoes.add(new OpcaoMenu("Consultar dados", () -> controlador.consultarDados()));
        opcoes.add(new OpcaoMenu("Atualizar dados", () -> controlador.atualizarDados()));
         opcoes.add(new OpcaoMenu("Alterar password", () -> controlador.alterarPassword()));
        opcoes.add(new OpcaoMenu("Pedir Suspensao", () -> controlador.pedirSuspensao()));
      
        opcoes.add(new OpcaoMenu("Voltar", () -> controlador.mostrarMenu(new MenuUtilizador(ligacao,controlador))));

    }
}
