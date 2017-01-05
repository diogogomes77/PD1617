
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class ClientUtilizador implements ClientUtilizadorRemote {
    
    @EJB LeiloeiraLocal leiloeira;
    
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
        if (leiloeira.getUtilizadores().get(username).isLogged()){
             myName=username;
             if ("admin".equals(username))
                 admin=true;
             return true;
        }
                setLastAction();

        return false;
           
        //return !leiloeira.loginUtilizador(username,password);
    }

    @Override
    public boolean logOff() {
        if (leiloeira.logOff(myName)){ // Singleeton testa MyName == null
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
        if (admin)
            return null;
        return leiloeira.addSaldo(valor, myName);
    }

    @Override
    public Double getSaldo() {
        setLastAction();
        if (admin)
            return null;
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
        return leiloeira.atualizaDadosUtilizador(myName,nome,morada);
    }

    @Override
    public boolean pedirSuspensao(String razao) {
        setLastAction();
        return leiloeira.pedirSuspensaoUtilizador(myName, razao);
    }
   
    private void setLastAction(){
        leiloeira.setLastAction(myName);
    }

    @Override
    public boolean sendMensagem(String destinatario, String texto,String assunto) {
        if (leiloeira.existeUtilizador(destinatario))
            return leiloeira.addMensagem(myName, destinatario, texto, assunto);
        return false;
    }

    @Override
    public ArrayList<Mensagem> consultarMensagens() {
        
        return leiloeira.getMensagensUtilizador(myName);
    }

    @Override
    public boolean verificaPassword(String password) {
        return leiloeira.verificaPassword(myName,password);
    }

    @Override
    public boolean alteraPassword(String password) {
        return leiloeira.alteraPassword(myName,password);
    }
    
    
}
