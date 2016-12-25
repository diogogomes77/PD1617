
package pdtp;

import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface LeiloeiraLocal {
    
    ArrayList<String> getHiScores();


 

    boolean existeUtilizador(String name);

   

    boolean logOff(String name);

    boolean registaUtilizador(String nome, String morada, String username, String password);

    boolean loginUtilizador(String username, String password);

    ArrayList getLogged();
}
