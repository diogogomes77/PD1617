
package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import pdtp.UtilizadorEstado;

@Singleton
public class ClientAdmin implements ClientAdminRemote {
 @EJB LeiloeiraLocal leiloeira;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean logOff() {

        if (leiloeira.logOff("admin")){ // Singleeton testa MyName == null
             return true;
        }
        setLastAction();
        return false;
    }
    @Override
        public ArrayList getUsernameInscritos() {
            setLastAction();
        return leiloeira.getUsernameInscritos();
    }
   
    @Override
    public ArrayList getUsernamesOnline() {
        setLastAction();
         return leiloeira.getUsernamesOnline();
    }

    @Override
    public ArrayList getUtilizadoresPedidoAtivacao() {
        setLastAction();
        return leiloeira.getUtilizadoresEstado(UtilizadorEstado.ATIVO_PEDIDO);
    }
    @Override
    public HashMap<String,String> getPedidosSuspensao() {
        setLastAction();
        //return leiloeira.getUtilizadoresEstado(UtilizadorEstado.SUSPENDO_PEDIDO);
        return leiloeira.getPedidosSuspensao();
    }
    @Override
    public ArrayList getUtilizadoresPedidoReAtivacao() {
        setLastAction();
        return leiloeira.getUtilizadoresEstado(UtilizadorEstado.REATIVACAO_PEDIDO);
    }
    
    @Override
    public boolean ativaUtilizador(String username) {
        setLastAction();
        return leiloeira.ativaUtilizador(username);
    }


    @Override
    public boolean suspendeUsername(String username) {
        setLastAction();

        return leiloeira.suspendeUtilizador(username);
    }
       private void setLastAction(){
        leiloeira.setLastAction("admin");
    }

    @Override
    public boolean adicionarCategoria(String nomeCategoria) {
        
        return leiloeira.adicionarCategoria(nomeCategoria);
    }

    @Override
    public List<String> obtemCategorias() {
        return leiloeira.obterCategorias();
    }

    @Override
    public boolean eliminaCategoria(String nomeCategoria) {
        return leiloeira.eliminaCategoria(nomeCategoria);
    }

    @Override
    public boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria) {
        return leiloeira.modificaCategoria(nomeCategoria, novoNomeCategoria);
    }
    
        @Override
    public boolean verificaPassword(String password) {
        return leiloeira.verificaPassword("admin",password);
    }

    @Override
    public boolean alteraPassword(String password) {
        return leiloeira.alteraPassword("admin",password);
    }

    @Override
    public int getTotalItens() {
        return leiloeira.getTotalItens();
    }

    @Override
    public List obtemDenunciasVendedores() {
        return leiloeira.obtemDenunciasVendedores();
    }

    @Override
    public List obtemDenunciasItens() {
        return leiloeira.obtemDenunciasItens();
    }
        @Override
    public boolean sendMensagem(String destinatario, String texto, String assunto) {
        setLastAction();
        if (leiloeira.existeUtilizador(destinatario)) {
            return leiloeira.addMensagem("admin", destinatario, texto, assunto);
        }
        return false;
    }
        @Override
    public ArrayList<Mensagem> consultarMensagens() {
        setLastAction();
        return leiloeira.getMensagensUtilizador("admin");
    }
}
