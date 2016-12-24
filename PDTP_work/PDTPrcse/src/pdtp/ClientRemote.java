
package pdtp;

import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface ClientRemote {
    
    boolean registerName(String name);

    int getMyScore();

    ArrayList<String> getHiScores();

    int getMyAttempts();

     TryResult tryNumber(int numero);

    boolean logOff();
}
