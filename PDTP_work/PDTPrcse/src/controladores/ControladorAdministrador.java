
package controladores;

import menus.MenuAdminDenuncias;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import menus.MenuAdminContas;
import menus.MenuVisitante;
import menus.OpcaoMenu;
import beans.ClientAdminRemote;
//import beans.ClientUtilizadorRemote;
import beans.ClientVisitanteRemote;
import beans.Mensagem;
import static controladores.Controlador.sc;
import java.util.Iterator;
import java.util.List;
import menus.MenuAdminCategorias;
import menus.MenuUtilizadorConsultarItem;
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
    public void consultarDenuncias() {
        menu = new MenuAdminDenuncias(ligacao, (ControladorAdministrador) controlador);       
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

    public void mudarPassword() {
        String password = "";
        System.out.print("Antiga password: ");
        password = sc.next();
        sc.skip("\n");
        if (ligacao.verificaPassword(password)){
             System.out.print("Nova password: ");
             password = sc.next();
             sc.skip("\n");
              if (ligacao.alteraPassword(password)){
                  System.out.println("Password alterada com sucesso");
              }else{
                  System.out.println("ERRO: Password nao alterada");
              }
        } else {
            System.out.println("ERRO: Password antiga incorreta");
        }
           
    }

    public void enviarMensagens() {
        System.out.println("enviar Mensagem a utilizador");
        String destinatario = "";
        String texto = "";
        String assunto = "";
        System.out.print("Destinatario: ");
        destinatario = sc.next();
        sc.skip("\n");
        System.out.print("Assunto: ");
        assunto = sc.nextLine();
        sc.skip("\n");
        System.out.print("Texto: ");
        texto = sc.nextLine();
        sc.skip("\n");
        if (ligacao.sendMensagem(destinatario, texto, assunto)) {
            System.out.println("Mensagem enviada");
        } else {
            System.out.println("ERRO: mensagem nao enviada");
        }
    }
    public void consultarMensagensMinhas() {
        System.out.println("Minhas mensagems:");
        ArrayList<Mensagem> mensagens = ligacao.consultarMensagens();
        for (Mensagem msg : mensagens) {
            System.out.println("Enviada: ".concat(convertTime(msg.getData())).concat(" por: ").concat(msg.getDestinatario()).concat(" Assunto: ").concat(msg.getAssunto()));
        }
    }
    
    public OpcaoMenu consultarUtilizador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void consultarItem() {
         System.out.println("Consultar Item");
        System.out.print("ItemID: ");
        int itemId = sc.nextInt();
        sc.skip("\n");
        System.out.println(ligacao.mostraItem(itemId));
        currentItemId=itemId;
        //menu = new MenuUtilizadorConsultarItem(ligacao, (ControladorUtilizador) controlador);

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

    public void consultarDenunciasVendedores() {
        System.out.print("Denuncias de Vendedores");
        List<String> denunciasvendedores = ligacao.obtemDenunciasVendedores();
        
        for (String denuncia : denunciasvendedores){
            System.out.println(denuncia);
        }       
    }

    public void consultarDenunciasItens() {
        System.out.print("Denuncias de Itens");
        List<String> denunciasItens = ligacao.obtemDenunciasItens();
        
        for (String denuncia : denunciasItens){
            System.out.println(denuncia);
        }

    }



}
