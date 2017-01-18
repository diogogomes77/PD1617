package beans;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author diogo
 */
@Remote
public interface ClientAdminRemote extends ClientRemote {

    /**
     *
     * @return
     */
    boolean logOff();

    /**
     *
     * @return
     */
    @Override
    ArrayList<String> getUsernameInscritos();

    /**
     *
     * @return
     */
    @Override
    public ArrayList<String> getUsernamesOnline();

    /**
     *
     * @param username
     * @return
     */
    boolean ativaUtilizador(String username);

    /**
     *
     * @param username
     * @return
     */
    boolean suspendeUsername(String username);

    /**
     *
     * @param nomeCategoria
     * @return
     */
    boolean adicionarCategoria(String nomeCategoria);

    /**
     *
     * @return
     */
    List<String> obtemCategorias();

    /**
     *
     * @param nomeCategoria
     * @return
     */
    boolean eliminaCategoria(String nomeCategoria);

    /**
     *
     * @return
     */
    ArrayList<String> getUtilizadoresPedidoAtivacao();

    /**
     *
     * @return
     */
    HashMap<String, String> getPedidosSuspensao();

    /**
     *
     * @return
     */
    ArrayList<String> getUtilizadoresPedidoReAtivacao();

    /**
     *
     * @param nomeCategoria
     * @param novoNomeCategoria
     * @return
     */
    boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria);

    /**
     *
     * @param password
     * @return
     */
    boolean verificaPassword(String password);

    /**
     *
     * @param password
     * @return
     */
    boolean alteraPassword(String password);

    /**
     *
     * @return
     */
    List<String> obtemDenunciasVendedores();

    /**
     *
     * @return
     */
    List<String> obtemDenunciasItens();

    /**
     *
     * @param destinatario
     * @param texto
     * @param assunto
     * @return
     */
    boolean sendMensagem(String destinatario, String texto, String assunto);

    /**
     *
     * @return
     */
    ArrayList<Mensagem> consultarMensagens();

    /**
     *
     * @param itemId
     * @return
     */
    String mostraItem(int itemId);

    /**
     *
     * @param username
     * @return
     */
    public String getDados(String username);

    /**
     *
     * @param itemId
     * @return
     */
    boolean cancelarItem(int itemId);

    /**
     *
     * @return
     */
    public ArrayList<String> getUtilizadoresPedidos();
}
