
package remotebeans;

import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface ClientAdminRemote extends ClientRemote{
     boolean logOff();
        ArrayList getUsernameInscritos();

    public ArrayList getUsernamesOnline();

    ArrayList getUtilizadoresPedidos();

    boolean ativaUtilizador(String username);
}
