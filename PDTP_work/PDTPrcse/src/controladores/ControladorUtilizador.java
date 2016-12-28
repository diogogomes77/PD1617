package controladores;

import menus.MenuUtilizadorSaldo;
import menus.MenuVisitante;
import menus.OpcaoMenu;
import remotebeans.ClientUtilizadorRemote;
import remotebeans.ClientVisitanteRemote;
import pdtprcse.ReferenciaVisitante;

public class ControladorUtilizador extends ControladorUserAdmin {

    private ClientUtilizadorRemote ligacao;
    
    public ControladorUtilizador(ClientUtilizadorRemote ligacao) {
        super(ligacao);
        this.ligacao = ligacao;
    }

    @Override
    public void logOff() {
        if (ligacao.logOff()) {
            System.out.println("\nlog off");
            ReferenciaVisitante refVisitante = new ReferenciaVisitante();
            ClientVisitanteRemote ligVisitante = refVisitante.getLigacao();
            controlador = new ControladorVisitante(ligVisitante);
            menu = new MenuVisitante(ligVisitante, (ControladorVisitante) controlador);

        } else {
            System.out.println("ERRO: accao nao aceite");
        }
    }

    public String getUsername(){
        return ligacao.getMyName();
    }
    public void consultarSaldo() {
        controlador = new ControladorUtilizador(ligacao);
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
         System.out.println("Saldo: " + ligacao.getSaldo());
    }

    public void carregarSaldo() {
        System.out.print("Valor a carregar (int): ");
        Double valor;    
        valor = sc.nextDouble();
            sc.skip("\n");
        System.out.println("Saldo atual: " + ligacao.addSaldo(valor));
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
