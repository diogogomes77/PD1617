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
}
