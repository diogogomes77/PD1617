package remoteclient_leilaoesdg;

import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import remotebeans.UtilizadorStatefullRemote;

public class main {
static Scanner sc = new Scanner(System.in);

    static UtilizadorStatefullRemote utilizador;

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
        String rsing_class_name
                = "java:global/ServerLeilaoESDG/ServerLeilaoESDG-ejb/UtilizadorStatefull!remotebeans.UtilizadorStatefullRemote";

        try {
            System.out.println("Iniciar lookup");
            Object z = ctx.lookup(rsing_class_name);
            utilizador = (UtilizadorStatefullRemote) z;
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
        System.out.println("JNDI lookup conlcuido");
    }

    public static void main(String[] args) {
        obtemReferencias();
        Menu menu = new Menu();
        int opcao = Menu.getMenuVisitante();
        switch (opcao){
            case 0:
                System.out.println("opcao 0");
                break;
            case 1:
                System.out.println("opcao 1");
                break;
            case 2:
                System.out.println("opcao 2");
                break;
        }
        
        
        System.exit(0);
    }

}
