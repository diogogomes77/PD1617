
package pdtp;

import remotebeans.ClientUtilizadorRemote;
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
        return false;
    }




    @Override
    public boolean existeUsername(String username) {
        return leiloeira.existeUtilizador(username);
    }

    @Override
    public ArrayList getUsernameInscritos() {
        return leiloeira.getUsernameInscritos();
    }
   
    @Override
    public ArrayList getUsernamesOnline() {
         return leiloeira.getUsernamesOnline();
    }

    @Override
    public Double addSaldo(Double valor) {
        if (admin)
            return null;
        return leiloeira.addSaldo(valor, myName);
    }

    @Override
    public Double getSaldo() {
        if (admin)
            return null;
        return leiloeira.getSaldo(myName);
    }

    @Override
    public String getMyName() {
        return myName;
    }



    
}
