
package pdtp;

import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface LeiloeiraLocal {
    
    ArrayList<String> getHiScores();

    int getMyScore(String name);

    int getMyAttempts(String name);

    boolean existeUtilizador(String name);

    TryResult tryNumber (String name, int numero);

    boolean logOff(String name);

    boolean registaUtilizador(String nome, String morada, String username, String password);

    boolean loginUtilizador(String username, String password);
}
