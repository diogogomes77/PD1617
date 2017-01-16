
package pdtprcse;

import javax.naming.NamingException;
import beans.ClientUtilizadorRemote;
import beans.ClientVisitanteRemote;
import static pdtprcse.PDTPrcse.ctx;
import static pdtprcse.Referencias.ligacao;

/**
 *
 * @author diogo
 */
public class ReferenciaUtilizador extends Referencias{

    /**
     *
     */
    public ReferenciaUtilizador() {
        super();
        String advremote
                = "java:global/PDTP/PDTP-ejb/ClientUtilizador!beans.ClientUtilizadorRemote";
        try {
          //  System.out.println("Iniciar lookup");
            Object x = ctx.lookup(advremote);
            ligacao = (ClientUtilizadorRemote) x;
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
    }

    /**
     *
     * @return
     */
    @Override
    public ClientUtilizadorRemote getLigacao(){
        return (ClientUtilizadorRemote) ligacao;
    }
}
