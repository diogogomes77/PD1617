package controladores;

import menus.MenuUtilizadorGerirConta;
import menus.MenuUtilizadorItens;
import menus.MenuUtilizadorSaldo;
import beans.ClientUtilizadorRemote;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import menus.MenuUtilizadorConsultarItem;
import menus.OpcaoMenu;

/**
 *
 * @author diogo
 */
public class ControladorUtilizador extends ControladorUserAdmin {

    private final ClientUtilizadorRemote ligacaoUtil;

    /**
     *
     * @param ligacao
     */
    public ControladorUtilizador(ClientUtilizadorRemote ligacao) {
        super(ligacao);
        this.ligacaoUtil = ligacao;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return ligacaoUtil.getMyName();
    }

    /**
     *
     */
    public void subMenuSaldo() {
        controlador = new ControladorUtilizador(ligacaoUtil);
        menu = new MenuUtilizadorSaldo(ligacaoUtil, (ControladorUtilizador) controlador);
    }

    /**
     *
     */
    public void historialItens() {
        System.out.println("historialItens");
    }

    /**
     *
     */
    public void consultarItensSeguidos() {
        System.out.println("Itens Seguidos");
        List<String> itens = ligacaoUtil.getItensSeguidos();
        for (String item : itens) {
            System.out.println(item);
        }
    }

    /**
     *
     */
    public void concluirTransacao() {

        System.out.println("concluir Transacao");
        System.out.print("ItemID: ");
        int itemId = sc.nextInt();
        sc.skip("\n");
        if (ligacaoUtil.concluirTransacao(itemId)) {
            System.out.println("Transacao Concluida");
        } else {
            System.out.println("ERRO: Transacao nao concluida");
        }
    }

    /**
     *
     */
    public void consultarItens() {
        System.out.println("Consultar Itens");
        List<String> itens = ligacaoUtil.getItens();
        for (String item : itens) {
            System.out.println(item);
        }
    }

    /**
     *
     */
    public void consultarItensMeus() {
        System.out.println("Meus itens");
        List<String> itens = ligacaoUtil.getMeusItens();
        for (String item : itens) {
            System.out.println(item);
        }
    }

    /**
     *
     */
    public void subMenuGerirConta() {
        menu = new MenuUtilizadorGerirConta(ligacaoUtil, (ControladorUtilizador) controlador);
    }

    /**
     *
     */
    public void colocarItemVenda() {

        System.out.println("Colocar Item a venda");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH");
        String input;
        String descricao = "";
        String categoria = "";
        double precoInicial;
        double precoComprarJa;
        Timestamp dataFinal = new Timestamp(new Date().getTime());
        List<String> categorias = ligacaoUtil.getCategorias();
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

        if (ligacaoUtil.addItem(descricao, precoInicial, precoComprarJa, dataFinal)) {
            System.out.println("Item adicionado com sucesso");
        } else {
            System.out.println("ERRO: item nao adicionado");
        }
    }

    /**
     *
     */
    public void verSaldo() {
        System.out.println("Saldo: " + ligacaoUtil.getSaldo());
    }

    /**
     *
     */
    public void carregarSaldo() {
        System.out.print("Valor a carregar (int): ");
        Double valor;
        valor = sc.nextDouble();
        sc.skip("\n");
        System.out.println("Saldo atual: " + ligacaoUtil.addSaldo(valor));
    }

    /**
     *
     */
    public void denunciarVendedor() {
        System.out.println("Denunciar Vendedor");
    }

    /**
     *
     */
    public void denunciarItem() {
        System.out.println("Denunciar Item");
        System.out.print("Razao: ");
        String razao = sc.nextLine();
        if (!"".equals(razao) && ligacaoUtil.denunciarItem(currentItemId, razao)) {
            System.out.println("Denuncia registada");
        } else {
            System.out.println("ERRO: Denuncia naoregistada");
        }
    }

    /**
     *
     */
    public void seguirItemCancelar() {
        System.out.println("seguirItemCancelar");
    }

    /**
     *
     */
    public void seguirItem() {
        System.out.println("Seguir Item");
        if (ligacaoUtil.seguirItem(currentItemId)) {
            System.out.println("Item a ser seguido");
        } else {
            System.out.println("ERRO: Item nao seguido");
        }
    }

    /**
     *
     */
    public void enviarMensagemVendedor() {
        System.out.println("enviar Mensagem ao Vendedor");
        String destinatario = ligacaoUtil.getVendedorItem(currentItemId);
        String texto = "";
        String assunto = "ItemID: " + Integer.toString(currentItemId);
        System.out.print("Mensagem: ");
        texto = sc.nextLine();
        if (ligacaoUtil.sendMensagem(destinatario, texto, assunto)) {
            System.out.println("Mensagem enviada");
        } else {
            System.out.println("ERRO: mensagem nao enviada");
        }
    }

    /**
     *
     */
    public void licitarItem() {
        System.out.println("Licitar Item");
        System.out.print("Valor: ");
        Double valor = sc.nextDouble();
        if (ligacaoUtil.licitarItem(currentItemId, valor)) {
            System.out.println("Licitacao registada");
        } else {
            System.out.println("ERRO: Licitacao nao registada");
        }
    }

    /**
     *
     */
    public void pedirSuspensao() {
        System.out.println("Pedido de suspensao");
        System.out.print("Indique a razao -> ");
        String razao = sc.nextLine();
        if (ligacaoUtil.pedirSuspensao(razao)) {
            System.out.println("Pedido suspensao registado");
        } else {
            System.out.println("ERRO: pedido de suspensao nao registado");
        }
    }

    /**
     *
     */
    public void atualizarDados() {
        System.out.println("Atualizar Dados do utilizador:");
        String s;
        String nome;
        String morada;
        System.out.print("Nome: ");
        nome = sc.nextLine();
        System.out.print("Morada: ");
        morada = sc.nextLine();
        if (ligacaoUtil.atualizaDados(nome, morada)) {
            System.out.println("Utilizador atualizado");
        } else {
            System.out.println("ERRO: utilizador nao atualizado");
        }
    }

    /**
     *
     */
    public void consultarDados() {
        System.out.println("Dados do utilizador:");
        System.out.println(ligacaoUtil.getDados());
    }

    /**
     *
     */
    public void subMenuItens() {
        menu = new MenuUtilizadorItens(ligacaoUtil, (ControladorUtilizador) controlador);

    }


    /**
     *
     */
    public void consultarItem() {
        System.out.println("Consultar Item");
        System.out.print("ItemID: ");
        int itemId = sc.nextInt();
        sc.skip("\n");
        System.out.println(ligacaoUtil.mostraItem(itemId));
        currentItemId = itemId;
        menu = new MenuUtilizadorConsultarItem(ligacaoUtil, (ControladorUtilizador) controlador);
    }

    /**
     *
     */
    public void comprarJaItem() {
        if (ligacaoUtil.comprarJaItem(currentItemId)) {
            System.out.println("Item comprado");
        } else {
            System.out.println("ERRO: item nao comprado");
        }
    }

    /**
     *
     */
    public void consultarLicitacoesItem() {
        System.out.println("Licitacoes Item:");
        System.out.println(ligacaoUtil.consultarLicitacoes(currentItemId));
    }

    /**
     *
     * @return
     */
    public OpcaoMenu consultarItensComprados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    public void consultarItensPorPagar() {
        System.out.println("Itens por pagar");
        List<String> itens = ligacaoUtil.getMeusItensPorPagar();
        if (itens == null) {
            System.out.println("Lista vazia");
            return;
        }
        for (String item : itens) {
            System.out.println(item);
        }
    }

}
