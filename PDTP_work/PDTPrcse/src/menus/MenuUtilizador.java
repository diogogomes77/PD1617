
package menus;

import controladores.ControladorUserAdmin;
import controladores.ControladorUtilizador;

import pdtp.ClientUtilizadorRemote;

public class MenuUtilizador extends MenuUtilizadorAdministrador {

//    private static final MenuUtilizador instancia = new MenuUtilizador();
//
//    public static MenuUtilizador getInstance() {
//        return instancia;
//    }
    public MenuUtilizador(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao,controlador);
       titulo="Utilizador";
        this.controlador =  controlador;
        opcoes.add(new OpcaoMenu("Colocar Item a venda", () -> controlador.colocarItemVenda()));
        opcoes.add(new OpcaoMenu("Gerir Conta", () -> controlador.gerirConta()));
        opcoes.add(new OpcaoMenu("Consultar meus Itens a venda", () -> controlador.consultarItensMeus()));
        opcoes.add(new OpcaoMenu("Consultar minhas mensagens", () -> controlador.consultarMensagensMinhas()));
        opcoes.add(new OpcaoMenu("Enviar mensagem", () -> controlador.enviarMensagem()));
        opcoes.add(new OpcaoMenu("Consultar Itens por...", () -> controlador.consultarItens()));
        opcoes.add(new OpcaoMenu("Concluir transacao", () -> controlador.concluirTransacao()));
        opcoes.add(new OpcaoMenu("Consultar Itens seguidos", () -> controlador.consultarItensSeguidos()));
        opcoes.add(new OpcaoMenu("Historial de Itens", () -> controlador.historialItens()));
        opcoes.add(new OpcaoMenu("Consultar saldo", () -> controlador.consultarSaldo()));
      //  opcoes.add(new OpcaoMenu("Terminar Sessao", () -> controlador.logOff()));
    }

}






