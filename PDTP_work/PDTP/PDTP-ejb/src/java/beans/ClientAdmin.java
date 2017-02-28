package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.inject.Named;
import pdtp.UtilizadorEstado;

/**
 *
 * @author diogo
 */
@Named
@Singleton
public class ClientAdmin extends ClientAuth implements ClientAdminRemote {
    
    @Override
    public boolean setMyName(String username) throws SessionException {
        //verifica se foi previamente logado atraves do ClientVisitance
        if ("admin".equals(username)) {
            return super.setMyName(username);
        }
        return false;
    }
    
    @Override
    public boolean setMyName(String username, String password) throws SessionException {
        //verifica se foi previamente logado atraves do ClientVisitance
        if ("admin".equals(username)) {
            return super.setMyName(username, password);
        }
        return false;
    }
    
    @Override
    public ArrayList<String> getUtilizadoresPedidoAtivacao() throws SessionException {
        setLastAction();
        return leiloeira.getUtilizadoresEstado(UtilizadorEstado.ATIVO_PEDIDO);
    }
    
    @Override
    public HashMap<String, String> getPedidosSuspensao() throws SessionException {
        setLastAction();
        //return leiloeira.getUtilizadoresEstado(UtilizadorEstado.SUSPENDO_PEDIDO);
        return leiloeira.getPedidosSuspensao();
    }
    
    @Override
    public ArrayList<String> getUtilizadoresPedidoReAtivacao() throws SessionException {
        setLastAction();
        return leiloeira.getUtilizadoresEstado(UtilizadorEstado.REATIVACAO_PEDIDO);
    }
    
    @Override
    public boolean ativaUtilizador(String username) throws SessionException {
        setLastAction();
        return leiloeira.ativaUtilizador(username);
    }
    
    @Override
    public boolean suspendeUsername(String username) throws SessionException {
        setLastAction();
        return leiloeira.suspendeUtilizador(username);
    }
    
    @Override
    public boolean adicionarCategoria(String nomeCategoria) throws SessionException {
        setLastAction();
        return leiloeira.adicionarCategoria(nomeCategoria);
    }
    
    @Override
    public List<String> obtemCategorias() throws SessionException {
        setLastAction();
        return leiloeira.obterCategorias();
    }
    
    @Override
    public boolean eliminaCategoria(String nomeCategoria) throws SessionException {
        setLastAction();
        return leiloeira.eliminaCategoria(nomeCategoria);
    }
    
    @Override
    public boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria) throws SessionException {
        setLastAction();
        return leiloeira.modificaCategoria(nomeCategoria, novoNomeCategoria);
    }
    
    @Override
    public List<String> obtemDenunciasVendedores() throws SessionException {
        setLastAction();
        return leiloeira.obtemDenunciasVendedores();
    }
    
    @Override
    public List<String> obtemDenunciasItens() throws SessionException {
        setLastAction();
        return leiloeira.obtemDenunciasItens();
    }
        
    @Override
    public String mostraItem(int itemId) throws SessionException {
        setLastAction();
        return leiloeira.mostraItem(itemId);
    }
    
    @Override
    public String getDados(String username) throws SessionException {
        setLastAction();
        return leiloeira.getDadosUtilizador(username);
    }
    
    @Override
    public boolean cancelarItem(int itemId) throws SessionException {
        setLastAction();
        return leiloeira.cancelarItem(itemId);
    }
    
    @Override
    public ArrayList<String> getUtilizadoresPedidos() throws SessionException {
        setLastAction();
        return leiloeira.getUtilizadoresEstado(UtilizadorEstado.SUSPENDO_PEDIDO);
    }
}
