
package sessionbeans;

import javax.ejb.Remote;

@Remote
public interface UtilizadorStatefullRemote {

    int teste();

    String getMenu();
    
}
