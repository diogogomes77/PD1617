
package referencias;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public abstract class Referencias {
    protected static InitialContext ctx = null;
    protected Properties prop;
    protected Object utilizador;

    public abstract Object getUtilizador();
    protected Referencias() {
        prop = new Properties();
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
    }
    
    
}
