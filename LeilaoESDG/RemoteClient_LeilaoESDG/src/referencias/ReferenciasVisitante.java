
package referencias;

import referencias.Referencias;
import javax.naming.NamingException;
import session.VisitanteStatelessRemote;


public class ReferenciasVisitante extends Referencias {

    private static final ReferenciasVisitante instancia = new ReferenciasVisitante();
    
    public static ReferenciasVisitante getInstance(){
        return instancia;
    }
    
    private ReferenciasVisitante() {
        super();
        String visitante_class_name
                = "java:global/ServerLeilaoESDG/ServerLeilaoESDG-ejb/VisitanteStateless!session.VisitanteStatelessRemote";
        try {
            System.out.println("Iniciar lookup visitante");
            Object v = ctx.lookup(visitante_class_name);
            utilizador = (VisitanteStatelessRemote) v;
           
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
