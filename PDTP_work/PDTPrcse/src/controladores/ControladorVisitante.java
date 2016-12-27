package controladores;

import java.util.ArrayList;
import java.util.Scanner;
import menus.MenuUtilizador;
import menus.OpcaoMenu;
import pdtp.ClientRemote;

public class ControladorVisitante extends Controlador{

    public ControladorVisitante(ClientRemote ligacao) {
       this.ligacao=ligacao;
    }

    public void registarUtilizador() {

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
        boolean freeUsername = false;
        while (!freeUsername) {
            System.out.print("Username: ");
            username = sc.next();
            sc.skip("\n");
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
            password = sc.next();
            sc.skip("\n");
            System.out.print("Repita password: ");

            if (password.compareTo(sc.next()) == 0) {
                okPassword = true;
            } else {
                System.out.println("ERRO: Password nao coincide! Tente novamente... ");
            }
            sc.skip("\n");
        }

        if (ligacao.inscreveUtilizador(nome, morada, username, password)) {
            System.out.println("Utilizador inscrito");
        } else {
            System.out.println("ERRO: Utilizador nao inscrito");
        }
    }

    public void loginUtilizador() {
        String username;
        String password;
        System.out.print("\nUsername -> ");
        username = sc.nextLine();
        System.out.print("\nPassword -> ");
        password = sc.nextLine();
        if (ligacao.loginUtilizador(username, password)) {
            System.out.println("Login valido");
            controlador = new ControladorUtilizador(ligacao);
            menu = new MenuUtilizador(ligacao,(ControladorUtilizador)controlador);
        } else {
            System.out.println("ERRO: Login invalido");
        }
    }

    public OpcaoMenu vendasRecentes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpcaoMenu reativarConta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
