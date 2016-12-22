
package session;

import javax.ejb.Remote;

@Remote
public interface VisitanteStatelessRemote {

    String inscreverMembro(String nome, String morada, String username, String password);

    boolean loginMembro(String username, String password);
    
}
