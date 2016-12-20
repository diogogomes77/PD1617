
package session;

import javax.ejb.Stateless;

@Stateless
public class VisitanteStateless implements VisitanteStatelessRemote {

    @Override
    public String inscreverMembro(String nome, String morada, String username, String password) {
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
