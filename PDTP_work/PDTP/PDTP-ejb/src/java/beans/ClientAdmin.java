package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import pdtp.UtilizadorEstado;

@Singleton
public class ClientAdmin implements ClientAdminRemote {

    @EJB
    LeiloeiraLocal leiloeira;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean logOff() {

        if (leiloeira.logOff("admin")) { // Singleeton testa MyName == null
            return true;
        }
        setLastAction();
        return false;
    }

    @Override
    public ArrayList<String> getUsernameInscritos() {
        setLastAction();
        return leiloeira.getUsernameInscritos();
    }

    @Override
    public ArrayList<String> getUsernamesOnline() {
        setLastAction();
        return leiloeira.getUsernamesOnline();
    }

    @Override
    public ArrayList<String> getUtilizadoresPedidoAtivacao() {
        setLastAction();
        return leiloeira.getUtilizadoresEstado(UtilizadorEstado.ATIVO_PEDIDO);
    }

    @Override
    public HashMap<String, String> getPedidosSuspensao() {
        setLastAction();
        //return leiloeira.getUtilizadoresEstado(UtilizadorEstado.SUSPENDO_PEDIDO);
        return leiloeira.getPedidosSuspensao();
    }

    @Override
    public ArrayList<String> getUtilizadoresPedidoReAtivacao() {
        setLastAction();
        return leiloeira.getUtilizadoresEstado(UtilizadorEstado.REATIVACAO_PEDIDO);
    }

    @Override
    public boolean ativaUtilizador(String username) {
        setLastAction();
        return leiloeira.ativaUtilizador(username);
    }

    @Override
    public boolean suspendeUsername(String username) {
        setLastAction();

        return leiloeira.suspendeUtilizador(username);
    }

    private void setLastAction() {
        leiloeira.setLastAction("admin");
    }

    @Override
    public boolean adicionarCategoria(String nomeCategoria) {
        setLastAction();
        return leiloeira.adicionarCategoria(nomeCategoria);
    }

    @Override
    public List<String> obtemCategorias() {
        setLastAction();
        return leiloeira.obterCategorias();
    }

    @Override
    public boolean eliminaCategoria(String nomeCategoria) {
        setLastAction();
        return leiloeira.eliminaCategoria(nomeCategoria);
    }

    @Override
    public boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria) {
        setLastAction();
        return leiloeira.modificaCategoria(nomeCategoria, novoNomeCategoria);
    }

    @Override
    public boolean verificaPassword(String password) {
        setLastAction();
        return leiloeira.verificaPassword("admin", password);
    }

    @Override
    public boolean alteraPassword(String password) {
        setLastAction();
        return leiloeira.alteraPassword("admin", password);
    }

    @Override
    public int getTotalItens() {
        setLastAction();
        return leiloeira.getTotalItens();
    }

    @Override
    public List<String> obtemDenunciasVendedores() {
        setLastAction();
        return leiloeira.obtemDenunciasVendedores();
    }

    @Override
    public List<String> obtemDenunciasItens() {
        setLastAction();
        return leiloeira.obtemDenunciasItens();
    }

    @Override
    public boolean sendMensagem(String destinatario, String texto, String assunto) {
        setLastAction();
        if (leiloeira.existeUtilizador(destinatario)) {
            return leiloeira.addMensagem("admin", destinatario, texto, assunto);
        }
        return false;
    }

    @Override
    public ArrayList<Mensagem> consultarMensagens() {
        setLastAction();
        return leiloeira.getMensagensUtilizador("admin");
    }

    @Override
    public String mostraItem(int itemId) {
        setLastAction();
        return leiloeira.mostraItem(itemId);
    }
    @Override
    public String getDados(String username) {
        setLastAction();
        return leiloeira.getDadosUtilizador(username);
    }

    @Override
    public boolean cancelarItem(int itemId) {
        setLastAction();
        return leiloeira.cancelarItem(itemId);
    }

    @Override
    public ArrayList<String> getUtilizadoresPedidos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
