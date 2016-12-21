
package remoteclient_leilaoesdg;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import static remoteclient_leilaoesdg.main.visitante;
import session.UtilizadorStatefullRemote;
import session.VisitanteStatelessRemote;

public abstract class Referencias {
    protected static InitialContext ctx = null;
    protected static Properties prop = new Properties();
    protected String remote_class_name;
    protected Object remote_object;
    

    static void obtemReferencias() {
       // InitialContext ctx = null;
      //  Properties prop = new Properties();
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
                ="java:global/ServerLeilaoESDG/ServerLeilaoESDG-ejb/VisitanteStateless!session.VisitanteStatelessRemote";
        try {
            System.out.println("Iniciar lookup");
            Object remote_object = ctx.lookup(visitante_class_name);
            remote_object = (VisitanteStatelessRemote) remote_object;
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
        System.out.println("JNDI lookup conlcuido");
    }
}
