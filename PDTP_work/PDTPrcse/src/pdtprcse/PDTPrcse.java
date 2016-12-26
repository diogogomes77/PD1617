
package pdtprcse;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import menus.Menu;
import menus.MenuUtilizador;
import menus.MenuVisitante;
import pdtp.ClientRemote;

public class PDTPrcse {

  static ClientRemote ligacao;
    static Scanner sc = new Scanner(System.in);
    public static void obtemReferencias() {
        InitialContext ctx = null;
        Properties prop = new Properties();

        prop.setProperty("java.naming.factory.initial",
                "com.sun.enterprise.naming.SerialInitContextFactory");
        prop.setProperty("java.naming.factory.url.pkgs",
                "com.sun.enterprise.naming");
        prop.setProperty("java.naming.factory.state",
                "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        prop.setProperty("org.omg.CORBA.ORBInitialHost", "glassfixe");
        prop.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

        try {
            ctx = new InitialContext(prop);
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("InitialContxt Criado");

        String advremote
                = "java:global/PDTP/PDTP-ejb/Client!pdtp.ClientRemote";
        try {
            System.out.println("Iniciar lookup");
            Object x = ctx.lookup(advremote);
            ligacao = (ClientRemote) x;
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("JNDI lookup concluido");
    }
    
    public static void printMenu() {
        System.out.println("\n------------------------");
        System.out.println("Adivinha numero");
        System.out.println(" 1 - Login Utilizador");
        System.out.println(" 6 - Log Off");
        System.out.println(" 7 - Registar Utilizador");
        System.out.println("____");
        System.out.println(" Sair -> 0");
        System.out.println("\n------------------------");
    }
    
    public static int obtemOpcaoMenu(int max) {
        int opcao;
        String texto;
        while (true) {
            try {
                // opcao = sc.nextInt(); -- mais falivel e dificil de controlar
                System.out.print("opcao -> ");
                texto = sc.nextLine();
                opcao = Integer.parseInt(texto);
                if ((opcao >= 0) && (opcao <= max)) {
                    return opcao;
                }
                System.out.println("Opcao errada. Ver melhor as instrucoes");
            } catch (NumberFormatException e) {
                System.out.println("Problema no teclado");
            }
        }
    }

    public static void logOff() {
        if (ligacao.logOff()) {
            System.out.println("accao confirmada");
        } else {
            System.out.println("accao nao aceite");
        }
    }

    public static void loginUtilizador() {
        String username;
        String password;
        System.out.print("\nUsername -> ");
        username = sc.nextLine();
        System.out.print("\nPassword -> ");
        password = sc.nextLine();
        if (ligacao.loginUtilizador(username,password)) {
            System.out.println("Login valido");
        } else {
            System.out.println("ERRO: Login invalido");
        }
    }
    public static void printLogged(){
        ArrayList <String> logados = ligacao.getLogged();
        
         System.out.print("Utilizadores online: ");
        logados.forEach((user)->{
            System.out.print(user +" ");
        });
         System.out.print("\n");
    }
    public static void main(String[] args) {
        Menu menu = new MenuVisitante();
        obtemReferencias();
         boolean continuar = true;
        while (continuar) {
            printLogged();
            continuar=menu.escolheMenu();
        }
    }
    public static void main_(String[] args) {
        int opcao;
        boolean continuar = true;
        obtemReferencias();
        while (continuar) {
            printMenu();
            printLogged();
            opcao = obtemOpcaoMenu(7);
            switch (opcao) {
                case 1:
                    loginUtilizador();
                    break;
                case 2:
                    
                    break;
                case 3:
           
                    break;
                case 4:
            
                    break;
                case 5:

                    break;
                case 6:
                    logOff();
                    break;
                case 7:
                    registarUtilizador();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Isto nao er suposto aparecer");
                    break;
            }
        }
        System.out.println("\nlog off");
        logOff();
        System.out.println("fim do programa");
    }

    public static void registarUtilizador() {
        
        String s;        
        String nome="";
        String morada="";
        String username="";
        String password="";
        System.out.print("Nome: ");
        nome = sc.next();
        sc.skip("\n");
        System.out.print("Morada: ");
        morada = sc.next();
        sc.skip("\n");
        boolean freeUsername = false;
        while (!freeUsername){
            System.out.print("Username: ");
            username = sc.next();
            sc.skip("\n");
            if (ligacao.existeUsername(username))
                System.out.println("ERRO: Username ja existe, escolha outro");
            else
                freeUsername=true;
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
}
