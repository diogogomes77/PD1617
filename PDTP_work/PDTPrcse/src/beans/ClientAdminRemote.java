package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface ClientAdminRemote extends ClientRemote {

    boolean logOff();

    @Override
    ArrayList<String> getUsernameInscritos();

    @Override
    public ArrayList<String> getUsernamesOnline();

    boolean ativaUtilizador(String username);

    boolean suspendeUsername(String username);

    boolean adicionarCategoria(String nomeCategoria);

    List<String> obtemCategorias();

    boolean eliminaCategoria(String nomeCategoria);

    ArrayList<String> getUtilizadoresPedidoAtivacao();

    HashMap<String, String> getPedidosSuspensao();

    ArrayList<String> getUtilizadoresPedidoReAtivacao();

    boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria);

    boolean verificaPassword(String password);

    boolean alteraPassword(String password);

    List<String> obtemDenunciasVendedores();

    List<String> obtemDenunciasItens();

    boolean sendMensagem(String destinatario, String texto, String assunto);

    ArrayList<Mensagem> consultarMensagens();

    String mostraItem(int itemId);

    public String getDados(String username);

    boolean cancelarItem(int itemId);

    public ArrayList<String> getUtilizadoresPedidos();
}
