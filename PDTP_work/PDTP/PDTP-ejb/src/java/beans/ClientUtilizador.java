package beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class ClientUtilizador implements ClientUtilizadorRemote {

    @EJB
    LeiloeiraLocal leiloeira;

    String myName;
    boolean admin;

//    @Override
//    public boolean loginUtilizador(String username, String password) {
//        if (myName != null)
//            return false;
//        if (leiloeira.loginUtilizador(username,password)){
//            myName = username; // faz login
//            if ("admin".equals(username)){
//                admin=true;
//            }
//            return true;
//        }
//        return false;
//    }
    @Override
    public boolean setMyName(String username, String password) {
        //verifica se foi previamente logado atraves do ClientVisitance
        if (leiloeira.getUtilizadores().get(username).isLogged()) {
            myName = username;
            if ("admin".equals(username)) {
                admin = true;
            }
            return true;
        }
        setLastAction();

        return false;

        //return !leiloeira.loginUtilizador(username,password);
    }

    @Override
    public boolean logOff() {
        if (leiloeira.logOff(myName)) { // Singleeton testa MyName == null
            myName = null;
            admin = false;
            return true;
        }
        setLastAction();

        return false;
    }

    @Override
    public boolean existeUsername(String username) {
        setLastAction();
        return leiloeira.existeUtilizador(username);
    }

    @Override
    public ArrayList getUsernameInscritos() {
        setLastAction();
        return leiloeira.getUsernameInscritos();
    }

    @Override
    public ArrayList getUsernamesOnline() {
        setLastAction();
        return leiloeira.getUsernamesOnline();
    }

    @Override
    public Double addSaldo(Double valor) {
        setLastAction();
        if (admin) {
            return null;
        }
        return leiloeira.addSaldo(valor, myName);
    }

    @Override
    public Double getSaldo() {
        setLastAction();
        if (admin) {
            return null;
        }
        return leiloeira.getSaldo(myName);
    }

    @Override
    public String getMyName() {
        setLastAction();
        return myName;
    }

//    @Override
//    public String toString() {
//        return leiloeira.getDadosUtilizador(myName);
//    }
    @Override
    public String getDados() {
        setLastAction();
        return leiloeira.getDadosUtilizador(myName);
    }

    @Override
    public boolean atualizaDados(String nome, String morada) {
        setLastAction();
        return leiloeira.atualizaDadosUtilizador(myName, nome, morada);
    }

    @Override
    public boolean pedirSuspensao(String razao) {
        setLastAction();
        return leiloeira.pedirSuspensaoUtilizador(myName, razao);
    }

    private void setLastAction() {
        leiloeira.setLastAction(myName);
    }

    @Override
    public boolean sendMensagem(String destinatario, String texto, String assunto) {
        setLastAction();
        if (leiloeira.existeUtilizador(destinatario)) {
            return leiloeira.addMensagem(myName, destinatario, texto, assunto);
        }
        return false;
    }

    @Override
    public ArrayList<Mensagem> consultarMensagens() {
        setLastAction();
        return leiloeira.getMensagensUtilizador(myName);
    }

    @Override
    public boolean verificaPassword(String password) {
        setLastAction();
        return leiloeira.verificaPassword(myName, password);
    }

    @Override
    public boolean alteraPassword(String password) {
        setLastAction();
        return leiloeira.alteraPassword(myName, password);
    }

    @Override
    public boolean addItem(String descricao, Double precoInicial, Double precoComprarJa, Timestamp dataLimite) {
        setLastAction();
        return leiloeira.addItem(myName, descricao, precoInicial, precoComprarJa, dataLimite);
    }

    @Override
    public List<String> getCategorias() {
        setLastAction();
        return leiloeira.obterCategorias();
    }

    @Override
    public List<String> getMeusItens() {
        setLastAction();
        return leiloeira.getItensUtilizador(myName);
    }

    @Override
    public int getTotalItens() {
        setLastAction();
        return leiloeira.getTotalItens();
    }

    @Override
    public List<String> getItens() {
        setLastAction();
        return leiloeira.getItens();
    }

    @Override
    public String mostraItem(int itemId) {
        setLastAction();
        return leiloeira.mostraItem(itemId);
    }

    @Override
    public String getVendedorItem(int itemId) {
        setLastAction();
        return leiloeira.getVendedorItem(itemId);
    }

    @Override
    public String consultarLicitacoes(int itemId) {
        setLastAction();
        return leiloeira.consultarLicitacoes(itemId);
    }

    @Override
    public boolean comprarJaItem(int itemid) {
        setLastAction();
        return leiloeira.comprarJaItem(itemid, myName);
    }

    @Override
    public boolean licitarItem(int itemid, Double valor) {
        setLastAction();
        return leiloeira.licitarItem(itemid, valor, myName);
    }

    @Override
    public boolean seguirItem(int itemId) {
        setLastAction();
        return leiloeira.seguirItem(myName, itemId);
    }

    @Override
    public List getItensSeguidos() {
        setLastAction();
        return leiloeira.getItensSeguidos(myName);
    }

    @Override
    public List getMeusItensPorPagar() {
        setLastAction();
        return leiloeira.getIensPorPagarUtilizador(myName);
    }

    @Override
    public boolean concluirTransacao(int itemId) {
        setLastAction();
        return leiloeira.concluirTransacao(myName, itemId);
    }

    @Override
    public boolean denunciarItem(int itemId, String razao) {
        setLastAction();
        return leiloeira.denunciarItem(itemId, myName, razao);
    }

    @Override
    public boolean denunciarVendedor(String vendedor, String razao) {
        setLastAction();
        return leiloeira.denunciarVendedor(myName, vendedor, razao);
    }

}
