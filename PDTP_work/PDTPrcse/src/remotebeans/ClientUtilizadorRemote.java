
package remotebeans;

import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface ClientUtilizadorRemote extends ClientRemote{
    
    //boolean loginUtilizador(String name, String password);

    boolean logOff();

    //boolean inscreveUtilizador(String nome, String morada, String username, String password);

    boolean existeUsername(String username);


    Double addSaldo(Double valor);

    Double getSaldo();
    
        
    @Override
    public ArrayList getUsernameInscritos();
    @Override
    public ArrayList getUsernamesOnline();

    boolean setMyName(String username, String password);

    String getMyName();
}
