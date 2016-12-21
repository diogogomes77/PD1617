
package session;

import javax.ejb.Local;

@Local
public interface SistemaLocal {

    String addMembro(String nome, String morada, String username, String password);
    
}
