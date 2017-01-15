package controladores;

import menus.MenuUtilizadorGerirConta;
import menus.MenuUtilizadorItens;
import menus.MenuUtilizadorSaldo;
import menus.MenuVisitante;
import beans.ClientUtilizadorRemote;
import beans.ClientVisitanteRemote;
import beans.Mensagem;
import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import menus.MenuUtilizadorConsultarItem;
import menus.OpcaoMenu;
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

    public String getUsername() {
        return ligacao.getMyName();
    }

    public void subMenuSaldo() {
        controlador = new ControladorUtilizador(ligacao);
        menu = new MenuUtilizadorSaldo(ligacao, (ControladorUtilizador) controlador);
    }

    public void historialItens() {
        System.out.println("historialItens");
    }

    public void consultarItensSeguidos() {
        System.out.println("Itens Seguidos");
        List<String> itens = ligacao.getItensSeguidos();
        for (String item : itens) {
            System.out.println(item);
        }
    }

    public void concluirTransacao() {

        System.out.println("concluir Transacao");
        System.out.print("ItemID: ");
        int itemId = sc.nextInt();
        sc.skip("\n");
        if (ligacao.concluirTransacao(itemId)) {
            System.out.println("Transacao Concluida");
        } else {
            System.out.println("ERRO: Transacao nao concluida");
        }
    }

    public void consultarItens() {
        System.out.println("Consultar Itens");
        List<String> itens = ligacao.getItens();
        for (String item : itens) {
            System.out.println(item);
        }
    }

    public void enviarMensagem() {
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

    public void consultarItensMeus() {
        System.out.println("Meus itens");
        List<String> itens = ligacao.getMeusItens();
        for (String item : itens) {
            System.out.println(item);
        }
    }

    public void subMenuGerirConta() {
        menu = new MenuUtilizadorGerirConta(ligacao, (ControladorUtilizador) controlador);
    }

    public void colocarItemVenda() {

        System.out.println("Colocar Item a venda");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH");
        String input;
        String descricao = "";
        String categoria = "";
        double precoInicial;
        double precoComprarJa;
        Timestamp dataFinal = new Timestamp(new Date().getTime());
        List<String> categorias = ligacao.getCategorias();
        if (categorias.isEmpty()) {
            System.out.print("Categorias nao disponiveis. Volte mais tarde");
            return;
        }
        boolean ok = false;
        do {
            System.out.print("Categorias disponiveis: ");
            for (String cat : categorias) {
                System.out.print(cat.concat(" "));
            }
            System.out.println("");
            System.out.print("Indique categoria: ");
            input = sc.nextLine();
            if ("x".equals(input)) {
                return;
            }
            for (String cat : categorias) {
                if (input.equals(cat)) {
                    ok = true;
                    categoria = input;
                }
            }
            if (!ok) {
                System.out.println("ERRO: categoria invalida");
            }
        } while (ok == false);

        System.out.print("Descricao: ");
        input = sc.nextLine();
        if ("x".equals(input)) {
            return;
        }
        descricao = input;
        System.out.print("Preco Inicial: ");
        input = sc.nextLine();
        if ("x".equals(input)) {
            return;
        }
        precoInicial = Double.parseDouble(input);
        System.out.print("Preco Comprar Ja: ");
        input = sc.nextLine();
        if ("x".equals(input)) {
            return;
        }
        precoComprarJa = Double.parseDouble(input);
        System.out.print("Data e hora de fim (dd-MM-yyy HH): ");
        input = sc.nextLine();
        if ("x".equals(input)) {
            return;
        }
        Date data;
        try {
            data = sdf.parse(input);
            dataFinal = new Timestamp(data.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ControladorUtilizador.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (ligacao.addItem(descricao, precoInicial, precoComprarJa, dataFinal)) {
            System.out.println("Item adicionado com sucesso");
        } else {
            System.out.println("ERRO: item nao adicionado");
        }
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

    public void denunciarVendedor() {
        System.out.println("Denunciar Vendedor");
    }

    public void denunciarItem() {
        System.out.println("Denunciar Item");
        System.out.print("Razao: ");
        String razao = sc.nextLine();
        sc.skip("\n");
        if (!"".equals(razao) && ligacao.denunciarItem(currentItemId, razao)) {
            System.out.println("Denuncia registada");
        } else {
            System.out.println("ERRO: Denuncia naoregistada");
        }
    }

    public void seguirItemCancelar() {
        System.out.println("seguirItemCancelar");
    }

    public void seguirItem() {
        System.out.println("Seguir Item");
        if (ligacao.seguirItem(currentItemId)) {
            System.out.println("Item a ser seguido");
        } else {
            System.out.println("ERRO: Item nao seguido");
        }
    }

    public void enviarMensagemVendedor() {
        System.out.println("enviar Mensagem ao Vendedor");
        String destinatario = ligacao.getVendedorItem(currentItemId);
        String texto = "";
        String assunto = "ItemID: " + Integer.toString(currentItemId);
        System.out.print("Mensagem: ");
        texto = sc.nextLine();
        sc.skip("\n");
        if (ligacao.sendMensagem(destinatario, texto, assunto)) {
            System.out.println("Mensagem enviada");
        } else {
            System.out.println("ERRO: mensagem nao enviada");
        }
    }

    public void licitarItem() {
        System.out.println("Licitar Item");
        System.out.print("Valor: ");
        Double valor = sc.nextDouble();
        if (ligacao.licitarItem(currentItemId, valor)) {
            System.out.println("Licitacao registada");
        } else {
            System.out.println("ERRO: Licitacao nao registada");
        }
    }

    public void pedirSuspensao() {
        System.out.println("Pedido de suspensao");
        System.out.print("Indique a razao -> ");
        String razao = sc.nextLine();
        sc.skip("\n");
        if (ligacao.pedirSuspensao(razao)) {
            System.out.println("Pedido suspensao registado");
        } else {
            System.out.println("ERRO: pedido de suspensao nao registado");
        }
    }

    public void atualizarDados() {
        System.out.println("Atualizar Dados do utilizador:");
        String s;
        String nome = "";
        String morada = "";
        String username = "";
        String password = "";
        System.out.print("Nome: ");
        nome = sc.nextLine();
        sc.skip("\n");
        System.out.print("Morada: ");
        morada = sc.nextLine();
        sc.skip("\n");
        if (ligacao.atualizaDados(nome, morada)) {
            System.out.println("Utilizador atualizado");
        } else {
            System.out.println("ERRO: utilizador nao atualizado");
        }
    }

    public void consultarDados() {
        System.out.println("Dados do utilizador:");
        System.out.println(ligacao.getDados());
    }

    public void subMenuItens() {
        menu = new MenuUtilizadorItens(ligacao, (ControladorUtilizador) controlador);

    }

    @Override
    protected void finalize() {
        this.logOff();
    }

    public void alterarPassword() {
        String password = "";
        System.out.print("Antiga password: ");
        password = sc.next();
        sc.skip("\n");
        if (ligacao.verificaPassword(password)) {
            System.out.print("Nova password: ");
            password = sc.next();
            sc.skip("\n");
            if (ligacao.alteraPassword(password)) {
                System.out.println("Password alterada com sucesso");
            } else {
                System.out.println("ERRO: Password nao alterada");
            }
        } else {
            System.out.println("ERRO: Password antiga incorreta");
        }
    }

    public void consultarItem() {
        System.out.println("Consultar Item");
        System.out.print("ItemID: ");
        int itemId = sc.nextInt();
        sc.skip("\n");
        System.out.println(ligacao.mostraItem(itemId));
        currentItemId = itemId;
        menu = new MenuUtilizadorConsultarItem(ligacao, (ControladorUtilizador) controlador);
    }

    public void comprarJaItem() {
        if (ligacao.comprarJaItem(currentItemId)) {
            System.out.println("Item comprado");
        } else {
            System.out.println("ERRO: item nao comprado");
        }
    }

    public void consultarLicitacoesItem() {
        System.out.println("Licitacoes Item:");
        System.out.println(ligacao.consultarLicitacoes(currentItemId));
    }

    public OpcaoMenu consultarItensComprados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void consultarItensPorPagar() {
        System.out.println("Itens por pagar");
        List<String> itens = ligacao.getMeusItensPorPagar();
        if (itens == null) {
            System.out.println("Lista vazia");
            return;
        }
        for (String item : itens) {
            System.out.println(item);
        }
    }

}
