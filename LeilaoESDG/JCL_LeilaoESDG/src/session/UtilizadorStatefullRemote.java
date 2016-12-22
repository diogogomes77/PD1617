
package session;

import javax.ejb.Remote;

@Remote
public interface UtilizadorStatefullRemote {

    int teste();

    String getNome(String username);

    String getMorada(String username);
    
}
