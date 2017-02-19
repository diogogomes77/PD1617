
package beans;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author diogo
 */
@Stateless
public class ClientVisitante implements ClientVisitanteRemote {
    
    @EJB LeiloeiraLocal leiloeira;
    
    @Override
    public boolean loginUtilizador(String username, String password) {     
        return leiloeira.loginUtilizador(username,password);
    }
    @Override
    public boolean inscreveUtilizador(String nome, String morada, String username, String password) {
        return leiloeira.registaUtilizador(nome, morada, username, password);
    }
    @Override
    public boolean existeUsername(String username) {
        return leiloeira.existeUtilizador(username);
    }
    @Override
    public ArrayList<String> getUsernameInscritos() {
        return leiloeira.getUsernameInscritos();
    }
   
    @Override
    public ArrayList<String> getUsernamesOnline() {
         return leiloeira.getUsernamesOnline();
    }

    @Override
    public boolean pedirReativacaoUsername(String username,String password) {
        return leiloeira.pedirReativacaoUsername(username,password);
    }

  @Override
    public int getTotalItens() {
        return leiloeira.getTotalItens();
    }

}