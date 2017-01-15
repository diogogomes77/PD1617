package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface ClientAdminRemote extends ClientRemote {

    boolean logOff();

    ArrayList getUsernameInscritos();

    public ArrayList getUsernamesOnline();

    boolean ativaUtilizador(String username);

    boolean suspendeUsername(String username);

    boolean adicionarCategoria(String nomeCategoria);

    List<String> obtemCategorias();

    boolean eliminaCategoria(String nomeCategoria);

    ArrayList getUtilizadoresPedidoAtivacao();

    HashMap<String, String> getPedidosSuspensao();

    ArrayList getUtilizadoresPedidoReAtivacao();

    boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria);

    boolean verificaPassword(String password);

    boolean alteraPassword(String password);

    List obtemDenunciasVendedores();

    List obtemDenunciasItens();

    boolean sendMensagem(String destinatario, String texto, String assunto);

    ArrayList<Mensagem> consultarMensagens();

    String mostraItem(int itemId);

    public String getDados(String username);

    boolean cancelarItem(int itemId);
}
