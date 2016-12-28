
package pdtprcse;

import javax.naming.NamingException;
import remotebeans.ClientUtilizadorRemote;
import remotebeans.ClientVisitanteRemote;
import static pdtprcse.PDTPrcse.ctx;
import static pdtprcse.Referencias.ligacao;

public class ReferenciaUtilizador extends Referencias{

    public ReferenciaUtilizador() {
        super();
        String advremote
                = "java:global/PDTP/PDTP-ejb/ClientUtilizador!remotebeans.ClientUtilizadorRemote";
        try {
            System.out.println("Iniciar lookup");
            Object x = ctx.lookup(advremote);
            ligacao = (ClientUtilizadorRemote) x;
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
    }
    @Override
    public ClientUtilizadorRemote getLigacao(){
        return (ClientUtilizadorRemote) ligacao;
    }
}
