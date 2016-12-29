
package beans;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Local;
import pdtp.Utilizador;

@Local
public interface LeiloeiraLocal {
    
    ArrayList<String> getHiScores();


 

    boolean existeUtilizador(String name);

   

    boolean logOff(String name);

    boolean registaUtilizador(String nome, String morada, String username, String password);

    boolean loginUtilizador(String username, String password);

    ArrayList getUsernameInscritos();

    ArrayList getUsernamesOnline();

    Double addSaldo(Double valor,String username);

    Double getSaldo(String username);
    
     public HashMap<String, Utilizador> getUtilizadores();

    boolean ativaUtilizador(String username);

    ArrayList getUtilizadoresPedidos();

    String getDadosUtilizador(String username);

    boolean atualizaDadosUtilizador(String username, String nome, String morada);
}
