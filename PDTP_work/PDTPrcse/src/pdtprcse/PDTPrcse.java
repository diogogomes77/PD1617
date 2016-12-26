package pdtprcse;

import controladores.*;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import menus.Menu;
import menus.MenuVisitante;
import pdtp.ClientRemote;

public class PDTPrcse {
    protected static Controlador controlador;
    protected static Menu menu;
    static ClientRemote ligacao;
   
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

    public static void main(String[] args) {
        obtemReferencias();
        controlador = new ControladorVisitante(ligacao);
        menu = new MenuVisitante(ligacao,(ControladorVisitante)controlador);
        //Menu menu = new MenuVisitante(ligacao,controlador);
        
        boolean continuar = true;
        while (continuar) {
            continuar = menu.escolheOpcao();
        }
    }  
}
