
package pdtprcse;

import javax.naming.NamingException;
import beans.ClientAdminRemote;
import static pdtprcse.Referencias.ctx;

/**
 *
 * @author diogo
 */
public class ReferenciaAdmin extends Referencias{

    /**
     *
     */
    public ReferenciaAdmin() {
         super();
        String advremote
                = "java:global/PDTP/PDTP-ejb/ClientAdmin!beans.ClientAdminRemote";
        try {
         //   System.out.println("Iniciar lookup");
            Object x = ctx.lookup(advremote);
            ligacao = (ClientAdminRemote) x;
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
    public ClientAdminRemote getLigacao() {
        return (ClientAdminRemote) ligacao;
    }
    
}
