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
     */
    boolean logOff();

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
}
