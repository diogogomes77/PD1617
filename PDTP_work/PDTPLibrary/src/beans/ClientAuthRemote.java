package beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eugenio
 */
public interface ClientAuthRemote extends ClientRemote {

    /**
     *
     * @return @throws beans.SessionException
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
     * @return
     * @throws beans.SessionException
     */
    String getMyName() throws SessionException;

    //String toString();

    /**
     *
     * @return
     * @throws beans.SessionException
     */
    String getDados() throws SessionException;

    /**
     *
     * @param nome
     * @param morada
     * @return
     * @throws beans.SessionException
     */
    boolean atualizaDados(String nome, String morada) throws SessionException;

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
     * @return @throws beans.SessionException
     */
    ArrayList<Mensagem> consultarMensagens() throws SessionException;

    /**
     *
     * @return @throws beans.SessionException
     */
    List<Object> obtemMensagens() throws SessionException;

    /**
     *
     * @return @throws beans.SessionException
     */
    int obtemNumMensagens() throws SessionException;

    /**
     *
     * @param id
     * @return @throws beans.SessionException
     */
    Object obtemMensagemById(Integer id) throws SessionException;

    /**
     *
     * @param range
     * @return @throws beans.SessionException
     */
    List<Object> obtemMensagensRange(int[] range) throws SessionException;

    /**
     *
     * @param id
     * @param destinatario
     * @param texto
     * @param assunto
     * @return
     * @throws beans.SessionException
     */
    boolean alteraMensagem(Integer id, String destinatario, String texto, String assunto) throws SessionException;

    /**
     *
     * @param id
     * @return
     * @throws beans.SessionException
     */
    boolean alteraMensagemParLida(Integer id) throws SessionException;

}
