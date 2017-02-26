package beans;

import javax.ejb.Remote;

/**
 *
 * @author diogo
 */
@Remote
public interface ClientVisitanteRemote extends ClientRemote{
    
    /**
     *
     * @param name
     * @param password
     * @return
     */
    boolean loginUtilizador(String name, String password);

    /**
     *
     * @param nome
     * @param morada
     * @param username
     * @param password
     * @return
     */
    boolean inscreveUtilizador(String nome, String morada, String username, String password);

    /**
     *
     * @param username
     * @return
     */
    boolean existeUsername(String username);
    
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
     * @return
     */
    boolean isAdmin(String username);
 
}
