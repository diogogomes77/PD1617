
package pdtprcse;

import javax.naming.NamingException;
import beans.ClientVisitanteRemote;
import static pdtprcse.PDTPrcse.ctx;

public class ReferenciaVisitante extends Referencias{

    public ReferenciaVisitante() {
         super();
        String advremote
                = "java:global/PDTP/PDTP-ejb/ClientVisitante!beans.ClientVisitanteRemote";
        try {
            System.out.println("Iniciar lookup");
            Object x = ctx.lookup(advremote);
            ligacao = (ClientVisitanteRemote) x;
        } catch (NamingException e) {
        //    System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
      //  System.out.println("JNDI Visitante lookup concluido");
    }
    
    @Override
    public ClientVisitanteRemote getLigacao(){
        return (ClientVisitanteRemote) ligacao;
    }
}