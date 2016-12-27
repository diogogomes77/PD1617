
package pdtp;

import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface ClientRemote {
    
    boolean loginUtilizador(String name, String password);

    boolean logOff();

    void test();

    boolean inscreveUtilizador(String nome, String morada, String username, String password);

    boolean existeUsername(String username);

    ArrayList getUsernameInscritos();

    ArrayList getUsernamesOnline();

    Double addSaldo(Double valor);

    Double getSaldo();
}
