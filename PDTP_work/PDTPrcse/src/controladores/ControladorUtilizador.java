package controladores;

import menus.MenuUtilizadorGerirConta;
import menus.MenuUtilizadorItens;
import menus.MenuUtilizadorSaldo;
import beans.ClientUtilizadorRemote;
import beans.SessionException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import menus.Menu;
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
     */
    protected int currentItemId;

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
    public String getUsername(){
        try {
            return ligacaoUtil.getMyName();
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
            return "";
        }
    }

    /**
     *
     */
    public void subMenuSaldo(Menu anterior) {
        controlador = new ControladorUtilizador(ligacaoUtil);
        menu = new MenuUtilizadorSaldo(ligacaoUtil, (ControladorUtilizador) controlador, anterior);
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
        List<String> itens;
        try {
            itens = ligacaoUtil.getItensSeguidos();
            for (String item : itens) {
                System.out.println(item);
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
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
        try {
            if (ligacaoUtil.concluirTransacao(itemId)) {
                System.out.println("Transacao Concluida");
            } else {
                System.out.println("ERRO: Transacao nao concluida");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void consultarItens() {
        System.out.println("Consultar Itens");
        List<String> itens;
        try {
            itens = ligacaoUtil.getItens();
            for (String item : itens) {
                System.out.println(item);
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void consultarItensMeus() {
        System.out.println("Meus itens");
        List<String> itens;
        try {
            itens = ligacaoUtil.getMeusItens();
            for (String item : itens) {
                System.out.println(item);
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void subMenuGerirConta(Menu anterior) {
        menu = new MenuUtilizadorGerirConta(ligacaoUtil, (ControladorUtilizador) controlador, anterior);
    }

    /**
     *
     */
    public void colocarItemVenda() {

        System.out.println("Colocar Item a venda");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH");
        String input;
        String descricao;
        String categoria = "";
        double precoInicial;
        double precoComprarJa;
        Timestamp dataFinal = new Timestamp(new Date().getTime());
        List<String> categorias;
        try {
            categorias = ligacaoUtil.getCategorias();
            if (categorias.isEmpty()) {
                System.out.print("Categorias nao disponiveis. Volte mais tarde");
                return;
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
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

        try {
            if (ligacaoUtil.addItem(categoria, descricao, precoInicial, precoComprarJa, dataFinal)) {
                System.out.println("Item adicionado com sucesso");
            } else {
                System.out.println("ERRO: item nao adicionado");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void verSaldo() {
        try {
            System.out.println("Saldo: " + ligacaoUtil.getSaldo());
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void carregarSaldo() {
        System.out.print("Valor a carregar (int): ");
        Double valor;
        valor = sc.nextDouble();
        sc.skip("\n");
        try {
            System.out.println("Saldo atual: " + ligacaoUtil.addSaldo(valor));
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void denunciarVendedor() {
        System.out.println("Denunciar Vendedor");
        System.out.print("Username do Vendedor: ");
        String username = sc.nextLine();
        System.out.print("Razão do pedido: ");
        String razao = sc.nextLine();
        try {
            ligacaoUtil.denunciarVendedor(username, razao);
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void denunciarItem() {
        System.out.println("Denunciar Item atual");
        System.out.print("Razao: ");
        String razao = sc.nextLine();
        try {
            if (!"".equals(razao) && ligacaoUtil.denunciarItem(currentItemId, razao)) {
                System.out.println("Denuncia registada");
            } else {
                System.out.println("ERRO: Denuncia naoregistada");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void seguirItem() {
        System.out.println("Seguir Item");
        try {
            if (ligacaoUtil.seguirItem(currentItemId)) {
                System.out.println("Item a ser seguido");
            } else {
                System.out.println("ERRO: Item nao seguido");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void seguirItemCancelar() {
        System.out.println("Deixar de Seguir Item");
        try {
            if (ligacaoUtil.seguirItemCancelar(currentItemId)) {
                System.out.println("O Item " + currentItemId + " deixou de ser seguido.");
            } else {
                System.out.println("ERRO: Não foi possivel cancelar o seguimento");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void enviarMensagemVendedor() {
        System.out.println("enviar Mensagem ao Vendedor");
        String destinatario;
        try {
            destinatario = ligacaoUtil.getVendedorItem(currentItemId);
            String texto;
            String assunto = "ItemID: " + Integer.toString(currentItemId);
            System.out.print("Mensagem: ");
            texto = sc.nextLine();
            if (ligacaoUtil.sendMensagem(destinatario, texto, assunto)) {
                System.out.println("Mensagem enviada");
            } else {
                System.out.println("ERRO: mensagem nao enviada");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void licitarItem() {
        System.out.println("Licitar Item");
        System.out.print("Valor: ");
        Double valor = sc.nextDouble();
        try {
            if (ligacaoUtil.licitarItem(currentItemId, valor)) {
                System.out.println("Licitacao registada");
            } else {
                System.out.println("ERRO: Licitacao nao registada");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void pedirSuspensao() {
        System.out.println("Pedido de suspensao");
        System.out.print("Indique a razao: ");
        String razao = sc.nextLine();
        try {
            if (ligacaoUtil.pedirSuspensao(razao)) {
                System.out.println("Pedido suspensao registado");
            } else {
                System.out.println("ERRO: pedido de suspensao nao registado");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
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
        try {
            if (ligacaoUtil.atualizaDados(nome, morada)) {
                System.out.println("Utilizador atualizado");
            } else {
                System.out.println("ERRO: utilizador nao atualizado");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void consultarDados() {
        System.out.println("Dados do utilizador:");
        try {
            System.out.println(ligacaoUtil.getDados());
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void subMenuItens(Menu anterior) {
        menu = new MenuUtilizadorItens(ligacaoUtil, (ControladorUtilizador) controlador, anterior);

    }

    /**
     *
     */
    public void consultarItem(Menu anterior) {
        System.out.println("Consultar Item");
        System.out.print("ItemID: ");
        int itemId = sc.nextInt();
        try {
            //sc.skip("\n");
            System.out.println(ligacaoUtil.mostraItem(itemId));
            currentItemId = itemId;
            menu = new MenuUtilizadorConsultarItem(ligacaoUtil, (ControladorUtilizador) controlador, anterior);
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void comprarJaItem() {
        try {
            if (ligacaoUtil.comprarJaItem(currentItemId)) {
                System.out.println("Item comprado");
            } else {
                System.out.println("ERRO: item nao comprado");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void consultarLicitacoesItem() {
        System.out.println("Licitacoes Item:");
        try {
            System.out.println(ligacaoUtil.consultarLicitacoes(currentItemId));
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
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
        List<String> itens;
        try {
            itens = ligacaoUtil.getMeusItensPorPagar();
            if (itens == null) {
                System.out.println("Lista vazia");
                return;
            }
            for (String item : itens) {
                System.out.println(item);
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

}
