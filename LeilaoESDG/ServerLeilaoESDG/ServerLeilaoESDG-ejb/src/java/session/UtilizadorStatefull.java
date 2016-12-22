
package session;

import entity.Membro;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class UtilizadorStatefull implements UtilizadorStatefullRemote {

    @EJB
    Sistema sistema;
    
    @Override
    public int teste() {
        return 123;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String getNome(String username) {
        return sistema.getMembro(username).getNome();
    }

    @Override
    public String getMorada(String username) {
        return sistema.getMembro(username).getMorada();
    }
    
}
