
package beans;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author diogo
 */
@Named
@Stateless
public class ClientVisitante extends ClientBase implements ClientVisitanteRemote {
    
    @Override
    public boolean loginUtilizador(String username, String password) {     
        return leiloeira.loginUtilizador(username,password);
    }
    @Override
    public boolean inscreveUtilizador(String nome, String morada, String username, String password) {
        return leiloeira.registaUtilizador(nome, morada, username, password);
    }

    @Override
    public boolean pedirReativacaoUsername(String username,String password) {
        return leiloeira.pedirReativacaoUsername(username,password);
    }
    @Override
    public boolean existeUsername(String username) {
        return leiloeira.existeUtilizador(username);
    }

    @Override
    public boolean isAdmin(String username) {
        return "admin".equals(username);
    }

}