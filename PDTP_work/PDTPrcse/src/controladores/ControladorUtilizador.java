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
        System.out.println("consultarItensSeguidos");
    }

    public void concluirTransacao() {
        System.out.println("concluirTransacao");
    }

    public void consultarItens() {
        System.out.println("consultarItens");
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
        assunto = sc.next();
        sc.skip("\n");
        System.out.print("Texto: ");
        texto = sc.next();
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

    public String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }

    public void consultarItensMeus() {
        System.out.println("consultarItensMeus");
    }

    public void subMenuGerirConta() {
        menu = new MenuUtilizadorGerirConta(ligacao, (ControladorUtilizador) controlador);
    }

    public void colocarItemVenda() {

        System.out.println("Colocar Item a venda");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String input;

        String descricao = "";
        String categoria = "";
        double precoInicial;
        double precoComprarJa;
        Timestamp dataFinal = new Timestamp(new Date().getTime());
        List<String> categorias = ligacao.getCategorias();

        boolean ok = false;
        do {
            System.out.print("Categorias disponiveis: ");
            for (String cat : categorias) {
                System.out.print(cat.concat(" "));
            }
            System.out.println("");
            System.out.print("Indique categoria: ");
            input = sc.nextLine();
            for (String cat : categorias) {
                if (input.equals(cat)) {
                    ok = true;
                    categoria = input;
                }
            }
            if (!ok){
                System.out.println("ERRO: categoria invalida");
            }
        } while (ok == false);

        System.out.print("Descricao: ");
        input = sc.nextLine();
        descricao = input;
        System.out.print("Preco Inicial: ");
        input = sc.nextLine();
        precoInicial = Double.parseDouble(input);
        System.out.print("Preco Comprar Ja: ");
        input = sc.nextLine();
        precoComprarJa = Double.parseDouble(input);
        System.out.print("Data de fim (dd-MM-yyy HH:mm:ss): ");
        input = sc.nextLine();
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
        System.out.println("denunciarVendedor");
    }

    public void denunciarItem() {
        System.out.println("denunciarItem");
    }

    public void seguirItemCancelar() {
        System.out.println("seguirItemCancelar");
    }

    public void seguirItem() {
        System.out.println("pedirSuspensao");
    }

    public void enviarMensagemVendedor() {
        System.out.println("enviarMensagemVendedor");
    }

    public void licitarItem() {
        System.out.println("pedirSuspensao");
    }

    public void pedirSuspensao() {
        System.out.println("Pedido de suspensao");
        System.out.print("Indique a razao -> ");
        String razao = sc.next();
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
        nome = sc.next();
        sc.skip("\n");
        System.out.print("Morada: ");
        morada = sc.next();
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

}
