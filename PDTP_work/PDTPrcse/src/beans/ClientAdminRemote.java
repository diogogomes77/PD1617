
package beans;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Remote;

@Remote
public interface ClientAdminRemote extends ClientRemote{
     boolean logOff();
        ArrayList getUsernameInscritos();

    public ArrayList getUsernamesOnline();

    ArrayList getUtilizadoresPedidos();

    boolean ativaUtilizador(String username);

    HashMap<String,String> getPedidosSuspensao();

    boolean suspendeUsername(String username);
}
