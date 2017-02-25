
package beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import jpaentidades.DAOLocal;
import jpaentidades.TUtilizadores;
import pdtp.UtilizadorEstado;

/**
 *
 * @author diogo
 */
@Local
public interface LeiloeiraLocal {
    
    /**
     *
     * @param name
     * @return
     */
    boolean existeUtilizador(String name);

    /**
     *
     * @param name
     * @return
     */
    boolean logOff(String name);

    /**
     *
     * @param nome
     * @param morada
     * @param username
     * @param password
     * @return
     */
    boolean registaUtilizador(String nome, String morada, String username, String password);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    boolean loginUtilizador(String username, String password);

    /**
     *
     * @return
     */
    ArrayList<String> getUsernameInscritos();

    /**
     *
     * @return
     */
    ArrayList<String> getUsernamesOnline();

    /**
     *
     * @param valor
     * @param username
     * @return
     */
    Double addSaldo(Double valor,String username);

    /**
     *
     * @param username
     * @return
     */
    Double getSaldo(String username);
    
    /**
     *
     * @return
     */
    public HashMap<String, TUtilizadores> getUtilizadores();

    /**
     *
     * @param username
     * @return
     */
    boolean ativaUtilizador(String username);

    /**
     *
     * @param estado
     * @return
     */
    ArrayList<String> getUtilizadoresEstado(UtilizadorEstado estado);

    /**
     *
     * @param username
     * @return
     */
    String getDadosUtilizador(String username);

    /**
     *
     * @param username
     * @param nome
     * @param morada
     * @return
     */
    boolean atualizaDadosUtilizador(String username, String nome, String morada);

    /**
     *
     * @param denunciador
     * @param denunciado
     * @param razao
     * @return
     */
    boolean pedirSuspensaoUtilizador(String denunciador,String denunciado,String razao);

    /**
     *
     * @return
     */
    HashMap<String,String> getPedidosSuspensao();

    /**
     *
     * @param username
     * @return
     */
    boolean suspendeUtilizador(String username);

    /**
     *
     * @param username
     */
    void setLastAction(String username);

    /**
     *
     * @param remetente
     * @param destinatario
     * @param texto
     * @param assunto
     * @return
     */
    boolean addMensagem(String remetente, String destinatario, String texto,String assunto);

    /**
     *
     * @param username
     * @return
     */
    ArrayList<Mensagem> getMensagensUtilizador(String username);

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
    List<String> obterCategorias();

    /**
     *
     * @param nomeCategoria
     * @return
     */
    boolean eliminaCategoria(String nomeCategoria);

    /**
     *
     * @param nomeCategoria
     * @param novoNomeCategoria
     * @return
     */
    boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    boolean pedirReativacaoUsername(String username,String password);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    boolean verificaPassword(String username, String password);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    boolean alteraPassword(String username, String password);

    /**
     *
     * @param username
     * @param descricao
     * @param precoInicial
     * @param precoComprarJa
     * @param dataLimite
     * @return
     */
    boolean addItem(String username,String descricao, Double precoInicial, Double precoComprarJa,Timestamp dataLimite);

    /**
     *
     * @param username
     * @return
     */
    List<String> getItensUtilizador(String username);
    
    /**
     *
     * @return
     */
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
     * @param itemid
     * @return
     */
    String consultarLicitacoes(int itemid);

    /**
     *
     * @param itemId
     * @param comprador
     * @return
     */
    boolean comprarJaItem(int itemId, String comprador);

    /**
     *
     * @param itemId
     * @param value
     * @param username
     * @return
     */
    boolean licitarItem(int itemId, Double value, String username);

    /**
     *
     * @param username
     * @param itemId
     * @return
     */
    boolean seguirItem(String username,int itemId);

    /**
     *
     * @param username
     * @param itemId
     * @return
     */
    boolean seguirItemCancelar(String username,int itemId);

    /**
     *
     * @param username
     * @return
     */
    List<String> getItensSeguidos(String username);

    /**
     *
     * @param username
     * @return
     */
    List<String> getIensPorPagarUtilizador(String username);

    /**
     *
     * @param username
     * @param itemId
     * @return
     */
    boolean concluirTransacao(String username, int itemId);

    /**
     *
     * @param itemId
     * @param denunciador
     * @param razao
     * @return
     */
    boolean denunciarItem(int itemId,String denunciador,String razao);

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
     * @param username
     * @param vendedor
     * @param razao
     * @return
     */
    boolean denunciarVendedor(String username, String vendedor, String razao);

    /**
     *
     * @param itemId
     * @return
     */
    boolean cancelarItem(int itemId);
    
    public DAOLocal getDAO() ;

    boolean isLogged(String username);

    List<String> obtemNewsletter();
}
