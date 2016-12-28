
package pdtp;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class Client implements ClientRemote {

    String myName;
    boolean admin;
    
    @EJB LeiloeiraLocal leiloeira;
    @Override
    public boolean loginUtilizador(String username, String password) {
        if (myName != null)
            return false;
        if (leiloeira.loginUtilizador(username,password)){
            myName = username; // faz login
            if ("admin".equals(username)){
                admin=true;
            }
            return true;
        }
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean logOff() {
        if (admin){
            admin=false;
            return true;
        }
        if (leiloeira.logOff(myName)){ // Singleeton testa MyName == null
             myName = null;
             admin = false;
             return true;
        }
        return false;
    }

    @Override
    public void test() {
    }

    @Override
    public boolean inscreveUtilizador(String nome, String morada, String username, String password) {
        if (myName==null)
            return leiloeira.registaUtilizador(nome, morada, username, password);
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
    
}
