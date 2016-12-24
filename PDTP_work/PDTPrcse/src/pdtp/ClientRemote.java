
package pdtp;

import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface ClientRemote {
    
    boolean LoginUtilizador(String name, String password);

    int getMyScore();

    ArrayList<String> getHiScores();

    int getMyAttempts();

     TryResult tryNumber(int numero);

    boolean logOff();

    void test();

    boolean inscreveUtilizador(String nome, String morada, String username, String password);

    boolean existeUsername(String username);
}
