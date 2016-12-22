
package referencias;

import referencias.Referencias;
import javax.naming.NamingException;
import session.UtilizadorStatefullRemote;


public class ReferenciasMembro extends Referencias {

    private static final ReferenciasMembro instancia = new ReferenciasMembro();
    
    public static ReferenciasMembro getInstance(){
        return instancia;
    }
    
    private ReferenciasMembro() {
        super();
        String utilizador_class_name
                = "java:global/ServerLeilaoESDG/ServerLeilaoESDG-ejb/UtilizadorStatefull!session.UtilizadorStatefullRemote";
         try {
            System.out.println("Iniciar lookup utilizador");
            Object u = ctx.lookup(utilizador_class_name);
            utilizador = (UtilizadorStatefullRemote) u;
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
         System.out.println("JNDI lookup conlcuido");
    }

    @Override
   public  Object getUtilizador() {
        return utilizador;
    }

    
}
