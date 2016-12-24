
package pdtprcse;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
        System.out.println(" 1 - Registar nome");
        System.out.println(" 2 - Tentar numero");
        System.out.println(" 3 - Consultar pontuacao");
        System.out.println(" 4 - Consultar tentativas");
        System.out.println(" 5 - Consultar hiscores");
        System.out.println(" 6 - Log Off");
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

    public static void tentarNumero() {
        int numero;
        String s;
        System.out.print("\nNumero (1-100) -> ");
        try {
            s = sc.nextLine();
            numero = Integer.parseInt(s);
            if ((numero < 1) || (numero > 100)) {
                System.out.println("Fora da gama. Nao vou enviar ao servidor");
            } else {
                System.out.println("Resultado: "
                        + ligacao.tryNumber(numero).msg());
            }
        } catch (NumberFormatException e) {
            System.out.println("Problema no teclado");
        }
    }

    public static void consultarPontuacao() {
        System.out.println("\nPontuacao actual:" + ligacao.getMyScore());
    }

    public static void consultarTentativas() {
        System.out.println("\nTentativas ate agora: " + ligacao.getMyAttempts());
    }

    public static void consultarHiscores() {
        ArrayList<String> hisc = ligacao.getHiScores();
        if ((hisc == null) || (hisc.isEmpty())) {
            System.out.println("Lista vazia (nem sequer este jogador?)");
        } else {
            for (String s : hisc) {
                System.out.println(s);
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

    public static void registarNome() {
        String s;
        System.out.print("\nNome -> ");
        s = sc.nextLine();
        if (ligacao.registerName(s)) {
            System.out.println("accao confirmada");
        } else {
            System.out.println("accao nao aceite");
        }
    }
     public static void main(String[] args) {
        int opcao;
        boolean continuar = true;
        obtemReferencias();
        while (continuar) {
            printMenu();
            opcao = obtemOpcaoMenu(6);
            switch (opcao) {
                case 1:
                    registarNome();
                    break;
                case 2:
                    tentarNumero();
                    break;
                case 3:
                    consultarPontuacao();
                    break;
                case 4:
                    consultarTentativas();
                    break;
                case 5:
                    consultarHiscores();
                    break;
                case 6:
                    logOff();
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
}
