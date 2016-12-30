
package beans;

import java.util.ArrayList;
import java.util.HashMap;
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
    public ArrayList getUtilizadoresPedidos() {
        setLastAction();
        return leiloeira.getUtilizadoresEstado(UtilizadorEstado.ATIVO_PEDIDO);
    }

    @Override
    public boolean ativaUtilizador(String username) {
        setLastAction();
        return leiloeira.ativaUtilizador(username);
    }

    @Override
    public HashMap<String,String> getPedidosSuspensao() {
        setLastAction();
        return leiloeira.getPedidosSuspensao();
    }

    @Override
    public boolean suspendeUsername(String username) {
        setLastAction();

        return leiloeira.suspendeUtilizador(username);
    }
       private void setLastAction(){
        leiloeira.setLastAction("admin");
    }
}
