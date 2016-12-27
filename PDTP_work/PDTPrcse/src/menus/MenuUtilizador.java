/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controladores.ControladorUtilizador;

import pdtp.ClientRemote;

public class MenuUtilizador extends Menu {

//    private static final MenuUtilizador instancia = new MenuUtilizador();
//
//    public static MenuUtilizador getInstance() {
//        return instancia;
//    }
    public MenuUtilizador(ClientRemote ligacao, ControladorUtilizador controlador) {
        this.controlador = controlador;
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
        opcoes.add(new OpcaoMenu("Terminar Sessao", () -> controlador.logOff()));
    }

}
class MenuUtilizadorGerirConta extends MenuUtilizador {
    
    public MenuUtilizadorGerirConta(ClientRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
          opcoes.add(new OpcaoMenu("Consultar dados", () -> controlador.consultarDados()));
       opcoes.add(new OpcaoMenu("Atualizar dados", () -> controlador.atualizarDados()));
   opcoes.add(new OpcaoMenu("Pedir Suspensao", () -> controlador.pedirSuspensao()));
 
    }
}
class MenuUtilizadorConsultarItens extends MenuUtilizador {
    
    public MenuUtilizadorConsultarItens(ClientRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
    }
}
class MenuUtilizadorVisualizarItem extends MenuUtilizador {
    
    public MenuUtilizadorVisualizarItem(ClientRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
        opcoes.add(new OpcaoMenu("Licitar Item", () -> controlador.licitarItem()));
       opcoes.add(new OpcaoMenu("Enviar mensagem ao vendedor", () -> controlador.enviarMensagemVendedor()));
   opcoes.add(new OpcaoMenu("Seguir Item", () -> controlador.seguirItem()));
       opcoes.add(new OpcaoMenu("Cancelar seguir Item", () -> controlador.seguirItemCancelar()));
   opcoes.add(new OpcaoMenu("Denunciar Item", () -> controlador.denunciarItem()));
       opcoes.add(new OpcaoMenu("Denunciar vendedor", () -> controlador.denunciarVendedor()));
 
    }
}
class MenuUtilizadorSaldo extends MenuUtilizador {
    
    public MenuUtilizadorSaldo(ClientRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
        
        opcoes.add(new OpcaoMenu("Ver Saldo", () -> controlador.verSaldo()));
       opcoes.add(new OpcaoMenu("Carregar Saldo", () -> controlador.carregarSaldo()));
    }
}