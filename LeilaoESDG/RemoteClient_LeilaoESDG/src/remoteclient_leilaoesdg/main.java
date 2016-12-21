package remoteclient_leilaoesdg;

import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.UtilizadorStatefullRemote;
import session.VisitanteStatelessRemote;

public class main {

    static Scanner sc = new Scanner(System.in);

    static UtilizadorStatefullRemote utilizador;
    static VisitanteStatelessRemote visitante;

    static void obtemReferencias() {
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
        System.out.println("InitialContext Criado");
        String utilizador_class_name
                = "java:global/ServerLeilaoESDG/ServerLeilaoESDG-ejb/UtilizadorStatefull!session.UtilizadorStatefullRemote";
        String visitante_class_name
                = "java:global/ServerLeilaoESDG/ServerLeilaoESDG-ejb/VisitanteStateless!session.VisitanteStatelessRemote";
        try {
            System.out.println("Iniciar lookup");
            Object v = ctx.lookup(visitante_class_name);
            visitante = (VisitanteStatelessRemote) v;
            Object u = ctx.lookup(utilizador_class_name);
            utilizador = (UtilizadorStatefullRemote) u;
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
        System.out.println("JNDI lookup conlcuido");
    }

    public void main(String[] args) {
        obtemReferencias();
        Menu menu = new Menu();
        do {
            int opcao = Menu.getMenuVisitante();
            switch (opcao) {
                case 0:
                    System.out.println("Sair");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Inscricao");
                    inscreveMembro();
                    break;
                case 2:
                    System.out.println("Newsletter");
                    break;
            }
        } while (true);

    }

    private void inscreveMembro() {
        String nome;
        String morada;
        String username;
        String password;
        System.out.println("Nome: ");
            nome = sc.next();
            sc.skip("\n");
        System.out.println("Morada: ");
        morada = sc.next();
            sc.skip("\n");
        System.out.println("Username: ");
            username = sc.next();
            sc.skip("\n"); 
        boolean ok = false;
        while (!ok){
            System.out.println("Password: ");
            password = sc.next();
            sc.skip("\n");
            System.out.println("Repita password: ");
            if (password.compareTo(sc.next())==0){
                ok = true;
            } else {
                System.out.println("Password nao coincide! Tente novamente... ");
            }
            sc.skip("\n");
        }
        utilizador.teste();
    }


}
