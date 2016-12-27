package controladores;

import menus.MenuUtilizador;
import menus.MenuUtilizadorSaldo;
import menus.MenuVisitante;
import menus.OpcaoMenu;
import pdtp.ClientRemote;

public class ControladorUtilizador extends Controlador {

    public ControladorUtilizador(ClientRemote ligacao) {
        this.ligacao = ligacao;
    }

    public void logOff() {
        if (ligacao.logOff()) {
            System.out.println("\nlog off");
            controlador = new ControladorVisitante(ligacao);
            menu = new MenuVisitante(ligacao, (ControladorVisitante) controlador);

        } else {
            System.out.println("ERRO: accao nao aceite");
        }
    }

    public void consultarSaldo() {
        //controlador = new ControladorVisitante(ligacao);
        menu = new MenuUtilizadorSaldo(ligacao, (ControladorUtilizador) controlador);
    }

    public OpcaoMenu historialItens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu consultarItensSeguidos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu concluirTransacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu consultarItens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu enviarMensagem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu consultarMensagensMinhas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu consultarItensMeus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu gerirConta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu colocarItemVenda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void verSaldo() {  
         System.out.println(ligacao.getSaldo());
    }

    public void carregarSaldo() {
        System.out.println(ligacao.addSaldo(10.00));
    }

    public OpcaoMenu denunciarVendedor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu denunciarItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu seguirItemCancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu seguirItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu enviarMensagemVendedor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu licitarItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu pedirSuspensao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu atualizarDados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu consultarDados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
