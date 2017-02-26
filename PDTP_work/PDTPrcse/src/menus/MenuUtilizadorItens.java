
package menus;

import controladores.ControladorUtilizador;
import beans.ClientUtilizadorRemote;

/**
 *
 * @author diogo
 */
public class MenuUtilizadorItens extends MenuUtilizadorAdmin {

    /**
     *
     * @param ligacao
     * @param controlador
     */
    public MenuUtilizadorItens(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador, Menu anterior) {
       super(ligacao,controlador, anterior);
        titulo=anterior.titulo + " - Itens";
       this.controlador=controlador;
        //opcoes.clear();
        opcoes.add(new OpcaoMenu("Colocar Item a venda", () -> controlador.colocarItemVenda()));
        opcoes.add(new OpcaoMenu("Consultar meus Itens a venda", () -> controlador.consultarItensMeus()));
        opcoes.add(new OpcaoMenu("Consultar Itens seguidos", () -> controlador.consultarItensSeguidos()));
        opcoes.add(new OpcaoMenu("Historial de Itens", () -> controlador.historialItens()));
        opcoes.add(new OpcaoMenu("Consultar Lista de Itens", () -> controlador.consultarItens()));
        opcoes.add(new OpcaoMenu("Consultar Item", () -> controlador.consultarItem(this)));
        opcoes.add(new OpcaoMenu("Consultar Itens comprados", () -> controlador.consultarItensComprados()));
        opcoes.add(new OpcaoMenu("Consultar Itens por pagar", () -> controlador.consultarItensPorPagar()));
        opcoes.add(new OpcaoMenu("Concluir transacao", () -> controlador.concluirTransacao()));
        
        
        //opcoes.add(new OpcaoMenu("Voltar", () -> controlador.mostrarMenu(new MenuUtilizador(ligacao,controlador))));
    }
}
