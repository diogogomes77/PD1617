package menus;

import controladores.ControladorUserAdmin;
import controladores.ControladorUtilizador;

import beans.ClientUtilizadorRemote;

public class MenuUtilizador extends MenuUtilizadorAdmin {

//    private static final MenuUtilizador instancia = new MenuUtilizador();
//
//    public static MenuUtilizador getInstance() {
//        return instancia;
//    }
    public MenuUtilizador(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
        titulo = "Utilizador ".concat(controlador.getUsername());
        this.controlador = controlador;
        opcoes.add(new OpcaoMenu("> Minha Conta", () -> controlador.subMenuGerirConta()));
        opcoes.add(new OpcaoMenu("> Itens", () -> controlador.subMenuItens()));

        opcoes.add(new OpcaoMenu("Minhas mensagens", () -> controlador.consultarMensagensMinhas()));
        opcoes.add(new OpcaoMenu("Enviar mensagem", () -> controlador.enviarMensagem()));

        opcoes.add(new OpcaoMenu("> Saldo", () -> controlador.subMenuSaldo()));

        //  opcoes.add(new OpcaoMenu("Terminar Sessao", () -> controlador.logOff()));
    }

}
