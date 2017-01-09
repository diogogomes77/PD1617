
package menus;

import controladores.ControladorUtilizador;
import beans.ClientUtilizadorRemote;

public class MenuUtilizadorConsultarItem extends MenuUtilizadorAdmin {

    public MenuUtilizadorConsultarItem(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
       super(ligacao,controlador);
        titulo="Utilizador - Consultar Item";
       this.controlador=controlador;
        //opcoes.clear();
        opcoes.add(new OpcaoMenu("Licitar", () -> controlador.licitarItem()));
        opcoes.add(new OpcaoMenu("Ver licitacoes", () -> controlador.consultarLicitacoesItem()));
        opcoes.add(new OpcaoMenu("Comprar Ja", () -> controlador.comprarJaItem()));
        opcoes.add(new OpcaoMenu("Enviar mensagem", () -> controlador.enviarMensagemVendedor()));
         opcoes.add(new OpcaoMenu("Seguir Item", () -> controlador.seguirItem()));
     
        
        
        opcoes.add(new OpcaoMenu("Voltar", () -> controlador.mostrarMenu(new MenuUtilizador(ligacao,controlador))));
    }
}
