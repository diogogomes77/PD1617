/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controladores.ControladorUtilizador;
import remotebeans.ClientUtilizadorRemote;

/**
 *
 * @author diogo
 */
public class MenuUtilizadorVisualizarItem extends MenuUtilizador {

    public MenuUtilizadorVisualizarItem(ClientUtilizadorRemote ligacao, ControladorUtilizador controlador) {
        super(ligacao, controlador);
        titulo="Utilizador - Visualizar Item";
        opcoes.clear();
        opcoes.add(new OpcaoMenu("Licitar Item", () -> controlador.licitarItem()));
        opcoes.add(new OpcaoMenu("Enviar mensagem ao vendedor", () -> controlador.enviarMensagemVendedor()));
        opcoes.add(new OpcaoMenu("Seguir Item", () -> controlador.seguirItem()));
        opcoes.add(new OpcaoMenu("Cancelar seguir Item", () -> controlador.seguirItemCancelar()));
        opcoes.add(new OpcaoMenu("Denunciar Item", () -> controlador.denunciarItem()));
        opcoes.add(new OpcaoMenu("Denunciar vendedor", () -> controlador.denunciarVendedor()));

    }
}
