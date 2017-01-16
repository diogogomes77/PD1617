package beans;

import java.util.ArrayList;

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
    public ArrayList<String> getUsernamesOnline();

    /**
     *
     * @return
     */
    public int getTotalItens();
}
