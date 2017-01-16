
package beans;

import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface ClientVisitanteRemote extends ClientRemote{
    
    boolean loginUtilizador(String name, String password);

    boolean inscreveUtilizador(String nome, String morada, String username, String password);

    boolean existeUsername(String username);

    @Override
   public ArrayList<String> getUsernameInscritos();
    @Override
    public ArrayList<String> getUsernamesOnline();

    @Override
    public int getTotalItens();
    
    boolean pedirReativacaoUsername(String username,String password);

}
