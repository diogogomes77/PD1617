
package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface ClientAdminRemote extends ClientRemote{
     boolean logOff();
        ArrayList getUsernameInscritos();

    public ArrayList getUsernamesOnline();

    ArrayList getUtilizadoresPedidos();

    boolean ativaUtilizador(String username);

    HashMap<String,String> getPedidosSuspensao();

    boolean suspendeUsername(String username);

    boolean adicionarCategoria(String nomeCategoria);

    List<String> obtemCategorias();

    boolean eliminaCategoria(String nomeCategoria);

    boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria);
}
