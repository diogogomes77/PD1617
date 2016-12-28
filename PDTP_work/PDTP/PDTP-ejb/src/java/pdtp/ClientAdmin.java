
package pdtp;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Singleton;

@Singleton
public class ClientAdmin implements ClientAdminRemote {
 @EJB LeiloeiraLocal leiloeira;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean logOff() {

        if (leiloeira.logOff("admin")){ // Singleeton testa MyName == null
             return true;
        }
        return false;
    }
    @Override
        public ArrayList getUsernameInscritos() {
        return leiloeira.getUsernameInscritos();
    }
   
    @Override
    public ArrayList getUsernamesOnline() {
         return leiloeira.getUsernamesOnline();
    }
}
