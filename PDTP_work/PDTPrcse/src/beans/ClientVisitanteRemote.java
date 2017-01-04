
package beans;

import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface ClientVisitanteRemote extends ClientRemote{
    
    boolean loginUtilizador(String name, String password);

    boolean inscreveUtilizador(String nome, String morada, String username, String password);

    boolean existeUsername(String username);

    @Override
   public ArrayList getUsernameInscritos();
    @Override
    public ArrayList getUsernamesOnline();

    boolean pedirReativacaoUsername(String username);

}
