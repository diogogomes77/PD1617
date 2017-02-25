package beans;



import java.sql.Timestamp;
import java.util.ArrayList;
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
     */
    Double addSaldo(Double valor);

    /**
     *
     * @return
     */
    Double getSaldo();

    /**
     *
     * @param username
     * @param password
     * @return
     */
    boolean setMyName(String username, String password);

    /**
     *
     * @return
     */
    String getMyName();

    //String toString();

    /**
     *
     * @return
     */
    String getDados();

    /**
     *
     * @param nome
     * @param morada
     * @return
     */
    boolean atualizaDados(String nome, String morada);

    /**
     *
     * @param razao
     * @return
     */
    boolean pedirSuspensao(String razao);

    /**
     *
     * @param descricao
     * @param precoInicial
     * @param precoComprarJa
     * @param dataLimite
     * @return
     */
    boolean addItem(String descricao, Double precoInicial, Double precoComprarJa, Timestamp dataLimite);

    /**
     *
     * @return
     */
    List<String> getCategorias();

    /**
     *
     * @return
     */
    List<String> getMeusItens();

    /**
     *
     * @return
     */
    @Override
    int getTotalItens();

    /**
     *
     * @return
     */
    List<String> getItens();

    /**
     *
     * @param itemId
     * @return
     */
    String mostraItem(int itemId);

    /**
     *
     * @param itemId
     * @return
     */
    String getVendedorItem(int itemId);

    /**
     *
     * @param itemId
     * @return
     */
    String consultarLicitacoes(int itemId);

    /**
     *
     * @param itemid
     * @return
     */
    boolean comprarJaItem(int itemid);

    /**
     *
     * @param itemid
     * @param valor
     * @return
     */
    boolean licitarItem(int itemid, Double valor);

    /**
     *
     * @param itemId
     * @return
     */
    boolean seguirItem(int itemId);

    /**
     *
     * @return
     */
    List<String> getItensSeguidos();

    /**
     *
     * @return
     */
    List<String> getMeusItensPorPagar();

    /**
     *
     * @param itemId
     * @return
     */
    boolean concluirTransacao(int itemId);

    /**
     *
     * @param itemId
     * @param razao
     * @return
     */
    boolean denunciarItem(int itemId, String razao);

    /**
     *
     * @param username
     * @param razao
     * @return
     */
    boolean denunciarVendedor(String username, String razao);
}
