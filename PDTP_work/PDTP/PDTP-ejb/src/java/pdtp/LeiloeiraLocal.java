
package pdtp;

import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface LeiloeiraLocal {
    
    ArrayList<String> getHiScores();

    int getMyScore(String name);

    int getMyAttempts(String name);

    boolean acceptNewUtilizador(String name);

    TryResult tryNumber (String name, int numero);

    boolean logOff(String name);
}
