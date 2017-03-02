package beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diogo
 */
public interface ClientRemote {

    /**
     *
     * @return
     */
    public ArrayList<String> getUsernameInscritos();

    /**
     *
     * @return
     */
    public List<String> getNewsletter();

    /**
     *
     * @return
     */
    public List<Object> getNewsletter(int nLastNews);

    /**
     *
     * @return
     */
    public ArrayList<String> getUsernamesOnline();

    /**
     *
     * @return
     */
    public int getTotalItens();

    /**
     *
     * @return
     */
    List<String> getUltimosItensVendidos();

    /**
     *
     * @param lista
     * @return @throws beans.SessionException
     */
    List<Object> obtemUtilizadores(UtilizadorTipoLista lista) throws SessionException;

    /**
     *
     * @param lista
     * @return @throws beans.SessionException
     */
    int obtemNumUtilizador(UtilizadorTipoLista lista) throws SessionException;

    /**
     *
     * @param id
     * @return @throws beans.SessionException
     */
    Object obtemUtilizadorById(String id) throws SessionException;

    /**
     *
     * @param lista
     * @param range
     * @return @throws beans.SessionException
     */
    List<Object> obtemUtilizadoresRange(UtilizadorTipoLista lista, int[] range) throws SessionException;
}
