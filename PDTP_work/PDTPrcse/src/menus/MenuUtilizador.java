package menus;

import controladores.ControladorUtilizador;

import beans.ClientUtilizadorRemote;

/**
 *
 * @author diogo
 */
public class MenuUtilizador extends MenuUtilizadorAdmin {

//    private static final MenuUtilizador instancia = new MenuUtilizador();
//
//    public static MenuUtilizador getInstance() {
//        return instancia;
//    }

    /**
     *
     * @param ligacao
     * @param controlador
     */
    public MenuUtilizador(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
        titulo = controlador.getUsername().concat(" -> Utilizador");
        this.controlador = controlador;
        opcoes.add(new OpcaoMenu("> Minha Conta", () -> controlador.subMenuGerirConta(this)));
        opcoes.add(new OpcaoMenu("> Itens", () -> controlador.subMenuItens(this)));

        opcoes.add(new OpcaoMenu("Minhas mensagens", () -> controlador.consultarMensagensMinhas()));
        opcoes.add(new OpcaoMenu("Enviar mensagem", () -> controlador.enviarMensagem()));

        opcoes.add(new OpcaoMenu("> Saldo", () -> controlador.subMenuSaldo(this)));

    }

}
