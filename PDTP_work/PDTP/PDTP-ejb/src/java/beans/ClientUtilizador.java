package beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Named;

/**
 *
 * @author diogo
 */
@Named
@Stateful
public class ClientUtilizador implements ClientUtilizadorRemote {

    @EJB
    LeiloeiraLocal leiloeira;

    String myName;
//    boolean admin;

    @Override
    public boolean setMyName(String username, String password) throws SessionException {
        //verifica se foi previamente logado atraves do ClientVisitance
        if (leiloeira.isLogged(username) ) {
            myName = username;
        }else if( leiloeira.loginUtilizador(username, password)){
            myName = username;
        }else{
            return false;
        }
        setLastAction();
        return true;

    }

    @Override
    public boolean logOff() throws SessionException {
        if (leiloeira.logOff(myName)) { // Singleeton testa MyName == null
            myName = null;
            return true;
        }
        setLastAction();

        return false;
    }

//    @Override
//    public boolean existeUsername(String username) {
//        setLastAction();
//        return leiloeira.existeUtilizador(username);
//    }

    @Override
    public ArrayList<String> getUsernameInscritos() {
        try {
            setLastAction();
        } catch (SessionException ex) {
            Logger.getLogger(ClientUtilizador.class.getName()).log(Level.INFO, null, ex);
        }
        return leiloeira.getUsernameInscritos();
    }

    @Override
    public List<String> getNewsletter() {
        try {
            setLastAction();
        } catch (SessionException ex) {
            Logger.getLogger(ClientUtilizador.class.getName()).log(Level.INFO, null, ex);
        }
        return leiloeira.obtemNewsletter();
    }

    @Override
    public ArrayList<String> getUsernamesOnline() {
        try {
            setLastAction();
        } catch (SessionException ex) {
            Logger.getLogger(ClientUtilizador.class.getName()).log(Level.INFO, null, ex);
        }
        return leiloeira.getUsernamesOnline();
    }

    @Override
    public Double addSaldo(Double valor) throws SessionException {
        setLastAction();
        return leiloeira.addSaldo(valor, myName);
    }

    @Override
    public Double getSaldo() throws SessionException {
        setLastAction();
        return leiloeira.getSaldo(myName);
    }

    @Override
    public String getMyName() throws SessionException {
        setLastAction();
        return myName;
    }

//    @Override
//    public String toString() {
//        return leiloeira.getDadosUtilizador(myName);
//    }
    @Override
    public String getDados() throws SessionException {
        setLastAction();
        return leiloeira.getDadosUtilizador(myName);
    }

    @Override
    public boolean atualizaDados(String nome, String morada) throws SessionException {
        setLastAction();
        return leiloeira.atualizaDadosUtilizador(myName, nome, morada);
    }

    @Override
    public boolean pedirSuspensao(String razao) throws SessionException {
        setLastAction();
        return leiloeira.pedirSuspensaoUtilizador(myName,myName, razao);
    }

    private void setLastAction() throws SessionException {
        if (leiloeira.isLogged(myName)) {
            leiloeira.setLastAction(myName);
        } else {
            throw new SessionException(SessionException.sessionStatus.LOGOUTSTAUS,"O utilizador não se encontra autenticado");
        }
    }

    @Override
    public boolean sendMensagem(String destinatario, String texto, String assunto) throws SessionException {
        setLastAction();
        if (leiloeira.existeUtilizador(destinatario)) {
            return leiloeira.addMensagem(myName, destinatario, texto, assunto);
        }
        return false;
    }

    @Override
    public ArrayList<Mensagem> consultarMensagens() throws SessionException {
        setLastAction();
        return leiloeira.getMensagensUtilizador(myName);
    }

    @Override
    public boolean verificaPassword(String password) throws SessionException {
        setLastAction();
        return leiloeira.verificaPassword(myName, password);
    }

    @Override
    public boolean alteraPassword(String password) throws SessionException {
        setLastAction();
        return leiloeira.alteraPassword(myName, password);
    }

    @Override
    public boolean addItem(String categoria, String descricao, Double precoInicial, Double precoComprarJa, Timestamp dataLimite) throws SessionException {
        setLastAction();
        return leiloeira.addItem(myName, categoria, descricao, precoInicial, precoComprarJa, dataLimite);
    }

    @Override
    public List<String> getCategorias() throws SessionException {
        setLastAction();
        return leiloeira.obterCategorias();
    }

    @Override
    public List<String> getMeusItens() throws SessionException {
        setLastAction();
        return leiloeira.getItensUtilizador(myName);
    }

    @Override
    public int getTotalItens() {
        try {
            setLastAction();
        } catch (SessionException ex) {
            Logger.getLogger(ClientUtilizador.class.getName()).log(Level.INFO, null, ex);
        }
        return leiloeira.getTotalItens();
    }

    @Override
    public List<String> getItens() throws SessionException {
        setLastAction();
        return leiloeira.getItens();
    }

    @Override
    public String mostraItem(long itemId) throws SessionException {
        setLastAction();
        return leiloeira.mostraItem(itemId);
    }

    @Override
    public String getVendedorItem(long itemId) throws SessionException {
        setLastAction();
        return leiloeira.getVendedorItem(itemId);
    }

    @Override
    public String consultarLicitacoes(long itemId) throws SessionException {
        setLastAction();
        return leiloeira.consultarLicitacoes(itemId);
    }

    @Override
    public boolean comprarJaItem(long itemid) throws SessionException {
        setLastAction();
        return leiloeira.comprarJaItem(itemid, myName);
    }

    @Override
    public boolean licitarItem(long itemid, Double valor) throws SessionException {
        setLastAction();
        return leiloeira.licitarItem(itemid, valor, myName);
    }

    @Override
    public boolean seguirItem(long itemId) throws SessionException {
        setLastAction();
        return leiloeira.seguirItem(myName, itemId);
    }

    @Override
    public List<String> getItensSeguidos() throws SessionException {
        setLastAction();
        return leiloeira.getItensSeguidos(myName);
    }

    @Override
    public List<String> getMeusItensPorPagar() throws SessionException {
        setLastAction();
        return leiloeira.getIensPorPagarUtilizador(myName);
    }

    @Override
    public boolean concluirTransacao(long itemId) throws SessionException {
        setLastAction();
        return leiloeira.concluirTransacao(myName, itemId);
    }

    @Override
    public boolean denunciarItem(long itemId, String razao) throws SessionException {
        setLastAction();
        return leiloeira.denunciarItem(itemId, myName, razao);
    }

    @Override
    public boolean denunciarVendedor(String vendedor, String razao) throws SessionException {
        setLastAction();
        return leiloeira.denunciarVendedor(myName, vendedor, razao);
    }

    @Override
    public boolean seguirItemCancelar(long itemId) throws SessionException {
        setLastAction();
        return leiloeira.seguirItemCancelar(myName, itemId);
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
