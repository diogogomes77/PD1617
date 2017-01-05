
package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import pdtp.Utilizador;
import pdtp.UtilizadorEstado;

@Local
public interface LeiloeiraLocal {
    
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

    ArrayList getUtilizadoresEstado(UtilizadorEstado estado);

    String getDadosUtilizador(String username);

    boolean atualizaDadosUtilizador(String username, String nome, String morada);

    boolean pedirSuspensaoUtilizador(String username,String razao);

    HashMap<String,String> getPedidosSuspensao();

    boolean suspendeUtilizador(String username);

    void setLastAction(String username);

    boolean addMensagem(String remetente, String destinatario, String texto,String assunto);

    ArrayList<Mensagem> getMensagensUtilizador(String username);

    boolean adicionarCategoria(String nomeCategoria);

    List<String> obterCategorias();

    boolean eliminaCategoria(String nomeCategoria);

    boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria);
    boolean pedirReativacaoUsername(String username,String password);

}
