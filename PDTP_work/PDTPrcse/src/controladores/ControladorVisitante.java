package controladores;

import menus.MenuAdmin;
import menus.MenuUtilizador;
import beans.ClientAdminRemote;
import beans.ClientUtilizadorRemote;
import beans.ClientVisitanteRemote;
import beans.SessionException;
import pdtprcse.ReferenciaAdmin;
import pdtprcse.ReferenciaUtilizador;

/**
 *
 * @author diogo
 */
public class ControladorVisitante extends Controlador {

    private ClientVisitanteRemote ligacao;

    /**
     *
     * @param ligacao
     */
    public ControladorVisitante(ClientVisitanteRemote ligacao) {
        super(ligacao);
        this.ligacao = ligacao;
    }

    /**
     *
     */
    public void registarUtilizador() {

        String s;
        String nome;
        String morada;
        String username;
        String password;
        System.out.print("Nome: ");
        nome = sc.nextLine();
        System.out.print("Morada: ");
        morada = sc.nextLine();
        boolean freeUsername = false;
        username = "";
        while (!freeUsername) {
            System.out.print("Username: ");
            username = sc.nextLine();
            if (ligacao.existeUsername(username)) {
                System.out.println("ERRO: Username ja existe, escolha outro");
            } else {
                freeUsername = true;
            }
        }

        boolean okPassword = false;
        password = "";
        while (!okPassword) {
            System.out.print("Password: ");
            password = sc.nextLine();
            System.out.print("Repita password: ");

            if (password.compareTo(sc.nextLine()) == 0) {
                okPassword = true;
            } else {
                System.out.println("ERRO: Password nao coincide! Tente novamente... ");
            }
        }

        if (ligacao.inscreveUtilizador(nome, morada, username, password)) {
            System.out.println("Utilizador inscrito");
        } else {
            System.out.println("ERRO: Utilizador nao inscrito");
        }
    }

    /**
     *
     */
    public void loginUtilizador() {
        String username;
        String password;
        System.out.print("\nUsername: ");
        username = sc.nextLine();
        System.out.print("\nPassword: ");
        password = sc.nextLine();
        try {
            if (ligacao.loginUtilizador(username, password)) {
                System.out.println("Login valido");
                if (ligacao.isAdmin(username)) {
                    ReferenciaAdmin refAdmin = new ReferenciaAdmin();
                    ClientAdminRemote ligAdmin = refAdmin.getLigacao();

                    if (ligAdmin.setMyName(username, password)) {
                        controlador = new ControladorAdministrador(ligAdmin);
                        menu = new MenuAdmin(ligAdmin, (ControladorAdministrador) controlador);
                    }
                } else {
                    ReferenciaUtilizador refUtilizador = new ReferenciaUtilizador();
                    ClientUtilizadorRemote ligUtilizador = refUtilizador.getLigacao();

                    if (ligUtilizador.setMyName(username, password)) { // o user ja esta autenticado no EBJ Local Leiloeira
                        controlador = new ControladorUtilizador(ligUtilizador);
                        menu = new MenuUtilizador(ligUtilizador, (ControladorUtilizador) controlador);
                    } else {
                        System.out.println("ERRO: problema no login...");
                    }
                }

            } else {
                System.out.println("ERRO: Login invalido");
            }
        }catch( SessionException e){
            System.out.println("ERRO: Login invalido");
        }
    }

    /**
     *
     */
    public void reativarConta() {
        String s;
        String username = "";
        String password = "";
        System.out.println("Pedido de reativacao de conta");
        System.out.print("Username: ");
        username = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        if (ligacao.pedirReativacaoUsername(username, password)) {
            System.out.println("Pedido de reativacao efetuado");
        } else {
            System.out.println("ERRO: pedido de reativacao invalido");
        }

    }

    /**
     *
     */
    @Override
    public void logOff() {
        return;
    }

}
