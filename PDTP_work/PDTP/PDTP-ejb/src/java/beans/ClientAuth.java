/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eugenio
 */
public abstract class ClientAuth extends ClientBase implements ClientAuthRemote {

    String myName;

    protected void setLastAction() throws SessionException {
        if (leiloeira.isLogged(myName)) {
            leiloeira.setLastAction(myName);
        } else {
            throw new SessionException(SessionException.sessionStatus.LOGOUTSTAUS, "O utilizador não se encontra autenticado");
        }
    }

    /**
     *
     */
    protected void trySetLastAction() {
        try {
            setLastAction();
        } catch (SessionException ex) {
            Logger.getLogger(ClientUtilizador.class.getName()).log(Level.INFO, null, ex);
        }
    }

    //Métodos da base
    @Override
    public ArrayList<String> getUsernameInscritos() {
        trySetLastAction();
        return super.getUsernameInscritos();
    }

    @Override
    public List<String> getNewsletter() {
        trySetLastAction();
        return super.getNewsletter();
    }

    @Override
    public ArrayList<String> getUsernamesOnline() {
        trySetLastAction();
        return super.getUsernamesOnline();
    }

    @Override
    public List<String> getUltimosItensVendidos() {
        trySetLastAction();
        return super.getUltimosItensVendidos();
    }

    @Override
    public int getTotalItens() {
        trySetLastAction();
        return super.getTotalItens();
    }
    @Override
    public List<Object> obtemNewsletter(){
        trySetLastAction();
        return leiloeira.obtemNewsletterEnt();
    }

    @Override
    public int obtemNumNewsletter() {
        trySetLastAction();
        return leiloeira.obtemNumNewsletter();
    }

    @Override
    public Object obtemNewsletterById(Integer id) {
        trySetLastAction();
        return leiloeira.obtemNewsletterById(id);
    }

    @Override
    public List<Object> obtemNewsletterRange(int[] range){
        trySetLastAction();
        return leiloeira.obtemNewsletterRange(range);
    }
    //Fim do métodos da base

    //
    @Override
    public boolean logOff() throws SessionException {
        if (leiloeira.logOff(myName)) { // Singleeton testa MyName == null
            myName = "";
            return true;
        }
        setLastAction();
        return false;
    }

    @Override
    public Object getUser() throws SessionException {
        setLastAction();
        return leiloeira.obtemUserById(myName);
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
    public boolean setMyName(String username) throws SessionException {
        //verifica se foi previamente logado atraves do ClientVisitance
        if (leiloeira.isLogged(username)) {
            myName = username;
        } else {
            return false;
        }
        setLastAction();
        return true;

    }

    @Override
    public boolean setMyName(String username, String password) throws SessionException {
        //verifica se foi previamente logado atraves do ClientVisitance
        if (leiloeira.isLogged(username)) {
            myName = username;
        } else if (leiloeira.loginUtilizador(username, password)) {
            myName = username;
        } else {
            return false;
        }
        setLastAction();
        return true;

    }

    @Override
    public String getMyName() throws SessionException {
        setLastAction();
        return myName;
    }

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
    public List<Object> obtemMensagens() throws SessionException {
        setLastAction();
        return leiloeira.getTMensagensUtilizador(myName);
    }

    @Override
    public int obtemNumMensagens() throws SessionException {
        setLastAction();
        return leiloeira.obtemNumTMensagens(myName);
    }

    @Override
    public Object obtemMensagemById(Integer id) throws SessionException {
        setLastAction();
        return leiloeira.obtemMensagemById(myName, id);
    }

    @Override
    public List<Object> obtemMensagensRange(int[] range) throws SessionException {
        setLastAction();
        return leiloeira.obtemMensagensRange(myName, range);
    }

    @Override
    public boolean alteraMensagem(Integer id, String destinatario, String texto, String assunto) throws SessionException {
        setLastAction();
        return leiloeira.alteraMensagem(myName, id, destinatario, texto, assunto);
    }

    @Override
    public boolean alteraMensagemParaLida(Integer id, Boolean lida) throws SessionException {
        setLastAction();
        return leiloeira.alteraMensagemParaLida(myName, id, lida);
    }
    
}
