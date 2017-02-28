package beans;



import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author diogo
 */
@Remote
public interface ClientUtilizadorRemote extends ClientAuthRemote {

    //boolean loginUtilizador(String name, String password);

    /**
     *
     * @param valor
     * @return
     * @throws beans.SessionException
     */
    Double addSaldo(Double valor) throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    Double getSaldo() throws SessionException;

    /**
     *
     * @param razao
     * @return
     * @throws beans.SessionException
     */
    boolean pedirSuspensao(String razao) throws SessionException;

    /**
     *
     * @param categria
     * @param descricao
     * @param precoInicial
     * @param precoComprarJa
     * @param dataLimite
     * @return
     * @throws beans.SessionException
     */
    boolean addItem(String categria, String descricao, Double precoInicial, Double precoComprarJa, Timestamp dataLimite) throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    List<String> getCategorias() throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    List<String> getMeusItens() throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    List<String> getItens() throws SessionException;

    /**
     *
     * @param itemId
     * @return
     * @throws beans.SessionException
     */
    String mostraItem(long itemId) throws SessionException;

    /**
     *
     * @param itemId
     * @return
     * @throws beans.SessionException
     */
    String getVendedorItem(long itemId) throws SessionException;

    /**
     *
     * @param itemId
     * @return
     * @throws beans.SessionException
     */
    String consultarLicitacoes(long itemId) throws SessionException;

    /**
     *
     * @param itemid
     * @return
     * @throws beans.SessionException
     */
    boolean comprarJaItem(long itemid) throws SessionException;

    /**
     *
     * @param itemid
     * @param valor
     * @return
     * @throws beans.SessionException
     */
    boolean licitarItem(long itemid, Double valor) throws SessionException;

    /**
     *
     * @param itemId
     * @return
     * @throws beans.SessionException
     */
    boolean seguirItem(long itemId) throws SessionException;

    /**
     *
     * @param itemId
     * @return
     * @throws beans.SessionException
     */
    boolean seguirItemCancelar(long itemId) throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    List<String> getItensSeguidos() throws SessionException;

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    List<String> getMeusItensPorPagar( ) throws SessionException ;

    /**
     *
     * @param itemId
     * @return
     * @throws beans.SessionException
     */
    boolean concluirTransacao(long itemId) throws SessionException;

    /**
     *
     * @param itemId
     * @param razao
     * @return
     * @throws beans.SessionException
     */
    boolean denunciarItem(long itemId, String razao) throws SessionException;

    /**
     *
     * @param username
     * @param razao
     * @return
     * @throws beans.SessionException
     */
    boolean denunciarVendedor(String username, String razao) throws SessionException;
}
