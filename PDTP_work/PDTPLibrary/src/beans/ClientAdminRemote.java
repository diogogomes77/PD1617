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
public interface ClientAdminRemote extends ClientAuthRemote {

    /**
     *
     * @param username
     * @return
     * @throws beans.SessionException
     */
    boolean ativaUtilizador(String username) throws SessionException;

    /**
     *
     * @param username
     * @return
     * @throws beans.SessionException
     */
    boolean suspendeUsername(String username) throws SessionException;

    /**
     *
     * @param nomeCategoria
     * @return
     * @throws beans.SessionException
     */
    boolean adicionarCategoria(String nomeCategoria) throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    List<String> obtemCategorias() throws SessionException;

    /**
     *
     * @param nomeCategoria
     * @return
     * @throws beans.SessionException
     */
    boolean eliminaCategoria(String nomeCategoria) throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    ArrayList<String> getUtilizadoresPedidoAtivacao() throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    HashMap<String, String> getPedidosSuspensao() throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    ArrayList<String> getUtilizadoresPedidoReAtivacao() throws SessionException;

    /**
     *
     * @param nomeCategoria
     * @param novoNomeCategoria
     * @return
     * @throws beans.SessionException
     */
    boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria) throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    List<String> obtemDenunciasVendedores() throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    List<String> obtemDenunciasItens() throws SessionException;

    /**
     *
     * @param itemId
     * @return
     * @throws beans.SessionException
     */
    String mostraItem(int itemId) throws SessionException;

    /**
     *
     * @param username
     * @return
     * @throws beans.SessionException
     */
    public String getDados(String username) throws SessionException ;

    /**
     *
     * @param itemId
     * @return
     * @throws beans.SessionException
     */
    boolean cancelarItem(int itemId) throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    public ArrayList<String> getUtilizadoresPedidos() throws SessionException;
    
    /**
     *
     * @param id
     * @return
     * @throws SessionException
     */
    public Object getUserByID( String id) throws SessionException;
    
}
