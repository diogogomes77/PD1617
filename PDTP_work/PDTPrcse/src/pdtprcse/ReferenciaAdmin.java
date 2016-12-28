
package pdtprcse;

import javax.naming.NamingException;
import pdtp.ClientAdminRemote;
import static pdtprcse.Referencias.ctx;

public class ReferenciaAdmin extends Referencias{

    public ReferenciaAdmin() {
         super();
        String advremote
                = "java:global/PDTP/PDTP-ejb/ClientAdmin!pdtp.ClientAdminRemote";
        try {
            System.out.println("Iniciar lookup");
            Object x = ctx.lookup(advremote);
            ligacao = (ClientAdminRemote) x;
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public ClientAdminRemote getLigacao() {
        return (ClientAdminRemote) ligacao;
    }
    
}
