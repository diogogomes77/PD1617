
package session;

import javax.ejb.Stateless;

@Stateless
public class VisitanteStateless implements VisitanteStatelessRemote {

    Sistema sistema;
    
    @Override
    public String inscreverMembro(String nome, String morada, String username, String password) {
        return sistema.addMembro(nome,morada,username,password);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
