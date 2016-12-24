
package pdtp;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class Client implements ClientRemote {

    String myName;
    
    @EJB LeiloeiraLocal leiloeira;
    @Override
    public boolean registerName(String name) {
        //if (MyName != null))
        //return false;
        if (leiloeira.acceptNewUtilizador(name)){
            myName = name;
            return true;
        }
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public int getMyScore() {
        return leiloeira.getMyScore(myName);
    }

    @Override
    public ArrayList<String> getHiScores() {
        return null;
    }

    @Override
    public int getMyAttempts() {
        return leiloeira.getMyAttempts(myName);
    }

    @Override
    public TryResult tryNumber(int numero) {
        if (myName == null)
            return TryResult.NoName; // testado no singleton
        
        return leiloeira.tryNumber(myName,numero);
    }

    @Override
    public boolean logOff() {
        myName = null;
        return leiloeira.logOff(myName); // Singleeton testa MyName == null
    }
}
