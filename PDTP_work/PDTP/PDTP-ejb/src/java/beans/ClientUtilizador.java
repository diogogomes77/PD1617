package beans;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;

/**
 *
 * @author diogo
 */
//@Named
@Stateful
public class ClientUtilizador extends ClientAuth implements ClientUtilizadorRemote {

    @Override
    public Double addSaldo(Double valor) throws SessionException {
        setLastAction();
        return leiloeira.addSaldo(valor, myName);
    }

    @Override
    public Double getSaldo() throws SessionException {
        setLastAction();
        return leiloeira.getSaldo(myName);
    }

//    @Override
//    public String toString() {
//        return leiloeira.getDadosUtilizador(myName);
//    }
    @Override
    public boolean pedirSuspensao(String razao) throws SessionException {
        setLastAction();
        return leiloeira.pedirSuspensaoUtilizador(myName, myName, razao);
    }

    @Override
    public boolean addItem(String categoria, String descricao, Double precoInicial, Double precoComprarJa, Timestamp dataLimite) throws SessionException {
        setLastAction();
        return leiloeira.addItem(myName, categoria, descricao, precoInicial, precoComprarJa, dataLimite);
    }

    @Override
    public List<String> getCategorias() throws SessionException {
        setLastAction();
        return leiloeira.obterCategorias();
    }

    @Override
    public List<String> getMeusItens() throws SessionException {
        setLastAction();
        return leiloeira.getItensUtilizador(myName);
    }

    @Override
    public int getTotalItens() {
        try {
            setLastAction();
        } catch (SessionException ex) {
            Logger.getLogger(ClientUtilizador.class.getName()).log(Level.INFO, null, ex);
        }
        return leiloeira.getTotalItens();
    }

    @Override
    public List<String> getItens() throws SessionException {
        setLastAction();
        return leiloeira.getItens();
    }

    @Override
    public String mostraItem(long itemId) throws SessionException {
        setLastAction();
        return leiloeira.mostraItem(itemId);
    }

    @Override
    public String getVendedorItem(long itemId) throws SessionException {
        setLastAction();
        return leiloeira.getVendedorItem(itemId);
    }

    @Override
    public String consultarLicitacoes(long itemId) throws SessionException {
        setLastAction();
        return leiloeira.consultarLicitacoes(itemId);
    }

    @Override
    public boolean comprarJaItem(long itemid) throws SessionException {
        setLastAction();
        return leiloeira.comprarJaItem(itemid, myName);
    }

    @Override
    public boolean licitarItem(long itemid, Double valor) throws SessionException {
        setLastAction();
        return leiloeira.licitarItem(itemid, valor, myName);
    }

    @Override
    public boolean seguirItem(long itemId) throws SessionException {
        System.out.println("-----SEGUIR ITEM= "+itemId);
        setLastAction();
        return leiloeira.seguirItem(myName, itemId);
    }

    @Override
    public List<String> getItensSeguidos() throws SessionException {
        setLastAction();
        return leiloeira.getItensSeguidos(myName);
    }
    @Override
    public List<Object> getItensSeguidosObj() throws SessionException {
        setLastAction();
        return leiloeira.getItensSeguidosObj(myName);
    }
    @Override
    public List<String> getMeusItensPorPagar() throws SessionException {
        setLastAction();
        return leiloeira.getIensPorPagarUtilizador(myName);
    }

    @Override
    public boolean concluirTransacao(long itemId) throws SessionException {
        setLastAction();
        return leiloeira.concluirTransacao(myName, itemId);
    }

    @Override
    public boolean denunciarItem(long itemId, String razao) throws SessionException {
        setLastAction();
        return leiloeira.denunciarItem(itemId, myName, razao);
    }

    @Override
    public boolean denunciarVendedor(String vendedor, String razao) throws SessionException {
        setLastAction();
        return leiloeira.denunciarVendedor(myName, vendedor, razao);
    }

    @Override
    public boolean seguirItemCancelar(long itemId) throws SessionException {
        setLastAction();
        return leiloeira.seguirItemCancelar(myName, itemId);
    }

    @Override
    public List<Object> obtemUtilizadores(UtilizadorTipoLista lista) throws SessionException {
        setLastAction();
        if ((lista == UtilizadorTipoLista.LISTA_USER_ALL) || (lista == UtilizadorTipoLista.LISTA_USER_ATIVOS)) {
            return super.obtemUtilizadores(lista);
        }
        return null;
    }

    @Override
    public int obtemNumUtilizador(UtilizadorTipoLista lista) throws SessionException {
        setLastAction();
        if ((lista == UtilizadorTipoLista.LISTA_USER_ALL) || (lista == UtilizadorTipoLista.LISTA_USER_ATIVOS)) {
            return super.obtemNumUtilizador(lista);
        }
        return 0;
    }

    @Override
    public Object obtemUtilizadorById(String id) throws SessionException {
        setLastAction();
        return super.obtemUtilizadorById(id);
    }

    @Override
    public List<Object> obtemUtilizadoresRange(UtilizadorTipoLista lista, int[] range) throws SessionException {
        setLastAction();
        if ((lista == UtilizadorTipoLista.LISTA_USER_ALL) || (lista == UtilizadorTipoLista.LISTA_USER_ATIVOS)){
            return super.obtemUtilizadoresRange(lista, range);
        }
        return null;
    }
}
