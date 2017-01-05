
package controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import menus.MenuAdminContas;
import menus.MenuVisitante;
import menus.OpcaoMenu;
import beans.ClientAdminRemote;
//import beans.ClientUtilizadorRemote;
import beans.ClientVisitanteRemote;
import java.util.Iterator;
import java.util.List;
import menus.MenuAdminCategorias;
import static pdtprcse.PDTPrcse.controlador;
import static pdtprcse.PDTPrcse.menu;
import pdtprcse.ReferenciaVisitante;

public class ControladorAdministrador extends ControladorUserAdmin{

    private ClientAdminRemote ligacao;
    public ControladorAdministrador(ClientAdminRemote ligacao) {
        super(ligacao);
        this.ligacao=ligacao;
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
    public OpcaoMenu consultarDenuncias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void consultarReativacoes() {
        ArrayList<String> pedidos = ligacao.getUtilizadoresPedidoReAtivacao();
        System.out.print("Pedidos de reativacao de conta: ");
        for (String pedido : pedidos){
            System.out.print(pedido.concat(" "));
        }
        System.out.print("\n");      
    }
    public OpcaoMenu cancelarItens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void suspenderContas() {
        System.out.print("Suspender username -> ");
        String username = sc.next();
        sc.skip("\n");
        if (ligacao.suspendeUsername(username)){
            System.out.println("Utilizador suspenso");
        }else{
            System.out.println("ERRO: Utilizador nao suspenso");
        }
    }

    public void reativarContas() {
      System.out.print("Reativar username -> ");
        String username = sc.next();
        sc.skip("\n");
        if (ligacao.ativaUtilizador(username)){
            System.out.println("Utilizador reativado");
        }else{
            System.out.println("ERRO: Utilizador nao reativado");
        }
    }

    public OpcaoMenu mudarPassword() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu enviarMensagens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu consultarUtilizador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu consultarItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void subMenuGerirCategorias() {
        menu = new MenuAdminCategorias(ligacao, (ControladorAdministrador) controlador);
    }

    public void ativarConta() {
        System.out.print("Ativar username -> ");
        String username = sc.next();
        sc.skip("\n");
        if (ligacao.ativaUtilizador(username)){
            System.out.println("Utilizador ativado");
        }else{
            System.out.println("ERRO: Utilizador nao ativado");
        }
    }
    public void consultarPedidosAtivacao() {
        ArrayList<String> pedidos = ligacao.getUtilizadoresPedidoAtivacao();
        System.out.print("Pedidos de ativacao de conta: ");
        for (String pedido : pedidos){
            System.out.print(pedido.concat(" "));
        }
        System.out.print("\n");
    }
    public void consultarPedidosSuspensao() {
        HashMap<String,String> pedidos = ligacao.getPedidosSuspensao();
//        System.out.print("Pedidos de suspensao de Utilizador: ");
//         for (String pedido : pedidos){
//            System.out.print(pedido.concat(" "));
//        }
        System.out.println("Pedidos de suspensao de conta:");
        Iterator entries = pedidos.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String)entry.getKey();
            String value = (String)entry.getValue();
            System.out.println("Username: " + key + ", Razao: " + value);
            //System.out.print("\n");
        }

//        for (Map.Entry<String,String> pedido : pedidos.entrySet()){
//            System.out.print(pedido.getKey().concat(": ").concat(pedido.getValue()));
//         System.out.print("\n");
//        }
        System.out.print("\n");
    }

    public void subMenuContas() {
        menu = new MenuAdminContas(ligacao, (ControladorAdministrador) controlador);
    }

    public void consultarCategorias() {
        //Obter o servidor as categorias
        List<String> categorias = ligacao.obtemCategorias();
        System.out.print("Categorias disponíveis: ");
        for (String categoria : categorias){
            System.out.print(categoria.concat(" "));
        }
        System.out.print("\n");
        //Listar o resultados
    }

    public void novaCategoria() {
        System.out.print("Nome da categoria -> ");
        String nomecat = sc.next();
        sc.skip("\n");
        if (ligacao.adicionarCategoria(nomecat)){
            System.out.println("Categoria Adicionada");
        }else{
            System.out.println("ERRO: Categoria não adicionada");
        }
    }

    public void eliminarCategoria() {
        System.out.print("Nome da categoria -> ");
        String nomecat = sc.next();
        sc.skip("\n");
        if (ligacao.eliminaCategoria(nomecat)){
            System.out.println("Categoria Eliminada");
        }else{
            System.out.println("ERRO: Categoria não eliminada");
        }
    }

        public void modificaCategoria() {
        System.out.print("Nome da categoria -> ");
        String nomecat = sc.next();
        sc.skip("\n");
        System.out.print("novo nome da categoria -> ");
        String novoNomeCat = sc.next();
        sc.skip("\n");
        if (ligacao.modificaCategoria(nomecat, novoNomeCat)){
            System.out.println("Categoria alterada");
        }else{
            System.out.println("ERRO: Categoria não alterada");
        }
    }

        
    @Override
    protected void finalize () {
        this.logOff();
    }



}
