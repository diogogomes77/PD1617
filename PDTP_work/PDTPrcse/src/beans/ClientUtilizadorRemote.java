
package beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface ClientUtilizadorRemote extends ClientRemote{
    
    //boolean loginUtilizador(String name, String password);

    boolean logOff();

    //boolean inscreveUtilizador(String nome, String morada, String username, String password);

    boolean existeUsername(String username);


    Double addSaldo(Double valor);

    Double getSaldo();
    
        
    @Override
    public ArrayList getUsernameInscritos();
    @Override
    public ArrayList getUsernamesOnline();

    boolean setMyName(String username, String password);

    String getMyName();

    //String toString();

    String getDados();

    boolean atualizaDados(String nome, String morada);

    boolean pedirSuspensao(String razao);

    boolean sendMensagem(String destinatario, String texto,String assunto);

    ArrayList<Mensagem> consultarMensagens();

    boolean verificaPassword(String password);

    boolean alteraPassword(String password);

    boolean addItem(String descricao, Double precoInicial, Double precoComprarJa,Timestamp dataLimite);

    List<String> getCategorias();
    List<String> getMeusItens();
    @Override
    int getTotalItens();
    List<String> getItens();

    String mostraItem(int itemId);
    String getVendedorItem(int itemId);
}
