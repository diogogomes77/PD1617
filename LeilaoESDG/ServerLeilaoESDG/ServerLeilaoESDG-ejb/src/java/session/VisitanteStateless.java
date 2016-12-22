
package session;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class VisitanteStateless implements VisitanteStatelessRemote {

    @EJB
    Sistema sistema;
    
    @Override
    public String inscreverMembro(String nome, String morada, String username, String password) {
        //return "nice";
        return sistema.addMembro(nome,morada,username,password);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean loginMembro(String username, String password) {
        return sistema.loginMembro(username,password);
    }
    
    
}
