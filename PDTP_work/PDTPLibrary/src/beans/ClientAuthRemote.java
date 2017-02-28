package beans;



import java.util.ArrayList;

/**
 *
 * @author eugenio
 */
public interface ClientAuthRemote extends ClientRemote {

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    boolean logOff() throws SessionException;

    /**
     *
     * @param password
     * @return
     * @throws beans.SessionException
     */
    boolean verificaPassword(String password) throws SessionException;

    /**
     *
     * @param password
     * @return
     * @throws beans.SessionException
     */
    boolean alteraPassword(String password) throws SessionException;
    
    /**
     *
     * @param username
     * @return
     * @throws beans.SessionException
     */
    boolean setMyName(String username) throws SessionException;
    
    /**
     *
     * @param username
     * @param password
     * @return
     * @throws beans.SessionException
     */
    boolean setMyName(String username, String password) throws SessionException;

    /**
     *
     * @param destinatario
     * @param texto
     * @param assunto
     * @return
     * @throws beans.SessionException
     */
    boolean sendMensagem(String destinatario, String texto, String assunto) throws SessionException;

    /**
     *
     * @return
     */
    ArrayList<Mensagem> consultarMensagens() throws SessionException;
}
