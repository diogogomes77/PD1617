package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import pdtp.UtilizadorEstado;

/**
 *
 * @author diogo
 */
@Singleton
public class ClientAdmin implements ClientAdminRemote {

    @EJB
    LeiloeiraLocal leiloeira;

    @Override
    public boolean setMyName(String username, String password) throws SessionException {
        //verifica se foi previamente logado atraves do ClientVisitance
        if (leiloeira.isLogged(username)) {
            if (!"admin".equals(username)) {
                return false;
            }
        } else if (!"admin".equals(username) || !leiloeira.loginUtilizador(username, password)) {
            return false;
        }
        setLastAction();
        return true;
    }

    @Override
    public boolean logOff() throws SessionException {

        if (leiloeira.logOff("admin")) { // Singleeton testa MyName == null
            return true;
        }
        setLastAction();
        return false;
    }

    @Override
    public ArrayList<String> getUsernameInscritos() {
        try {
            setLastAction();
        } catch (SessionException ex) {
            Logger.getLogger(ClientAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return leiloeira.getUsernameInscritos();
    }

    @Override
    public List<String> getNewsletter() {
        try {
            setLastAction();
        } catch (SessionException ex) {
            Logger.getLogger(ClientAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return leiloeira.obtemNewsletter();
    }

    @Override
    public ArrayList<String> getUsernamesOnline() {
        try {
            setLastAction();
        } catch (SessionException ex) {
            Logger.getLogger(ClientAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return leiloeira.getUsernamesOnline();
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

    private void setLastAction() throws SessionException {
        if (leiloeira.isLogged("admin")) {
            leiloeira.setLastAction("admin");
        } else {
            throw new SessionException(SessionException.sessionStatus.LOGOUTSTAUS, "O utilizador não se encontra autenticado");
        }
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
    public boolean verificaPassword(String password) throws SessionException {
        setLastAction();
        return leiloeira.verificaPassword("admin", password);
    }

    @Override
    public boolean alteraPassword(String password) throws SessionException {
        setLastAction();
        return leiloeira.alteraPassword("admin", password);
    }

    @Override
    public int getTotalItens() {
        try {
            setLastAction();
        } catch (SessionException ex) {
            Logger.getLogger(ClientAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return leiloeira.getTotalItens();
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
    public boolean sendMensagem(String destinatario, String texto, String assunto) throws SessionException {
        setLastAction();
        if (leiloeira.existeUtilizador(destinatario)) {
            return leiloeira.addMensagem("admin", destinatario, texto, assunto);
        }
        return false;
    }

    @Override
    public ArrayList<Mensagem> consultarMensagens() throws SessionException {
        setLastAction();
        return leiloeira.getMensagensUtilizador("admin");
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

    @Override
    public List<String> getUltimosItensVendidos() {
        try {
            setLastAction();
        } catch (SessionException ex) {
            Logger.getLogger(ClientUtilizador.class.getName()).log(Level.INFO, null, ex);
        }
        return leiloeira.getUltimosItensVendidos();
    }
}
