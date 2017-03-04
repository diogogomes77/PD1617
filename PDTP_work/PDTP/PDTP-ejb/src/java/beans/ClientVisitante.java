package beans;

import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author diogo
 */
//@Named
@Stateless
public class ClientVisitante extends ClientBase implements ClientVisitanteRemote {

    @Override
    public boolean loginUtilizador(String username, String password) {
        return leiloeira.loginUtilizador(username, password);
    }

    @Override
    public boolean inscreveUtilizador(String nome, String morada, String username, String password) {
        return leiloeira.registaUtilizador(nome, morada, username, password);
    }

    @Override
    public boolean pedirReativacaoUsername(String username, String password) {
        return leiloeira.pedirReativacaoUsername(username, password);
    }

    @Override
    public boolean existeUsername(String username) {
        return leiloeira.existeUtilizador(username);
    }

    @Override
    public boolean isAdmin(String username) {
        return "admin".equals(username);
    }

    @Override
    public List<Object> obtemUtilizadores(UtilizadorTipoLista lista) throws SessionException {
        if (lista == UtilizadorTipoLista.LISTA_USER_ALL) {
            return super.obtemUtilizadores(lista);
        }
        return null;
    }

    @Override
    public int obtemNumUtilizador(UtilizadorTipoLista lista) throws SessionException {
        if (lista == UtilizadorTipoLista.LISTA_USER_ALL) {
            return super.obtemNumUtilizador(lista);
        }
        return 0;
    }

    @Override
    public Object obtemUtilizadorById(String id) throws SessionException {
        return super.obtemUtilizadorById(id);
    }

    @Override
    public List<Object> obtemUtilizadoresRange(UtilizadorTipoLista lista, int[] range) throws SessionException {
        if (lista == UtilizadorTipoLista.LISTA_USER_ALL) {
            return super.obtemUtilizadoresRange(lista, range);
        }
        return null;

    }

}
