package beans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;



import pdtp.Denuncia;
import pdtp.DenunciaItem;
import pdtp.DenunciaVendedor;
import pdtp.Item;

import pdtp.Licitacao;
import pdtp.Utilizador;
import pdtp.UtilizadorEstado;
import pdtp.Venda;

/**
 *
 * @author diogo
 */
@Singleton
public class Leiloeira implements LeiloeiraLocal {

    private HashMap<String, Utilizador> utilizadoresOk = new HashMap<>();
    private HashMap<String, Utilizador> utilizadoresNotOk = new HashMap<>();
    private HashMap<Integer, Item> itensAVenda = new HashMap<>();
    private HashMap<Integer, Item> itensTerminados = new HashMap<>();
    private List<DenunciaItem> denunciasItens = new ArrayList<>();
    private List<DenunciaVendedor> denunciasVendedores = new ArrayList<>();
    private List<String> categorias = new ArrayList<>();
    private List<Mensagem> mensagens = new ArrayList<>();
    private int itemCount;
    
   // @EJB
  //  private UsersFacade ufEjb;
   
    
    /**
     *
     */
    public Leiloeira() {
        if (!utilizadoresOk.containsKey("admin")) {
            utilizadoresOk.put("admin",
                    new Utilizador("Administrador", "Sistema", "admin", "admin", UtilizadorEstado.ATIVO));
        }
        itemCount = getIntenCount();
        
    }

    private int getIntenCount() {
        return itensAVenda.size();
    }

    /**
     *
     * @return Lista de utilizadores ativados
     */
    @Override
    public HashMap<String, Utilizador> getUtilizadores() {
        return utilizadoresOk;
    }

    /**
     *
     * @param username
     * @return true se o userame ja existe
     */
    @Override
    public boolean existeUtilizador(String username) {
        if (username == null) {
            return false;
        }
        Utilizador j = utilizadoresOk.get(username);
        if (j == null) // sera que nao fica null?
        {
            j = utilizadoresNotOk.get(username);
            if (j==null)
                return false;
        }
        return true;
    }

    /**
     *
     * @param nome
     * @param morada
     * @param username
     * @param password
     * @return true se o utilizador ficar registado
     */
    @Override
    public boolean registaUtilizador(String nome, String morada, String username, String password) {
        if (!existeUtilizador(username)) {
            utilizadoresNotOk.put(username,
                    new Utilizador(nome, morada, username, password, UtilizadorEstado.ATIVO_PEDIDO));
            
//            Users u = new Users();
//            u.setName(nome);
//            u.setId(1);
//            ufEjb.create(u);
//            
//            em.getTransaction().begin();
//            em.persist(u);
//             em.getTransaction().commit();
       
        
            return true;
        }
        return false;
    }

    /**
     *
     * @param username
     * @param password
     * @return true se o utilizador ficar logado
     */
    @Override
    public boolean loginUtilizador(String username, String password) {
        Utilizador j = utilizadoresOk.get(username);
        if (j != null) {
            // existe
            if (j.getPassword().equalsIgnoreCase(password)) {
                if (j.getEstado() == UtilizadorEstado.ATIVO || j.getEstado() == UtilizadorEstado.SUSPENDO_PEDIDO) {
                    if (j.isLogged()) // esta logado -Z nao deixa repetir user
                    {
                        return false;
                    } else {
                        j.setLogged();
                        j.setLastAction();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @param username
     * @return true se o utilizador fizer loggoff
     */
    @Override
    public boolean logOff(String username) {
        if (username == null) //quem?
        {
            return false;
        }
        Utilizador j = utilizadoresOk.get(username);
        if (j == null) {
            return false; //nao conheco
        }
        if (!j.isLogged()) {
            return false;
        }
        j.resetLogged(); //unloga
        return true;
    }

    private void terminaItem(Item it) {
        
        itensTerminados.put(it.getItemID(), it);
        itensAVenda.remove(it.getItemID());
        if (it.getLicitacoes().isEmpty()) {
            it.terminaItemSemLicitacoes();
            this.addMensagem("admin", "admin", it.toString(), "Item Terminado sem comprador");
        }
        else{
            if (it.addVendaLicitacao()) {
                this.addMensagem("admin", "admin", it.toString(), "Item Terminado com comprador");
            } else {
                this.addMensagem("admin", "admin", it.toString(), "ERRO: Item nao Terminado com sucesso");
            }
        }

    }

    /**
     * termina os itens nas respetivas horas de fim
     */
    @Schedule(second = "*", minute = "1", hour = "*")
    @Override
    public void checkItensDataFinal() {
        Timestamp now = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        List<Item> list = new ArrayList<Item>(itensAVenda.values());
        for (Item it : list) {
            if (it.getDataFimTimeStamp().after(now)) {
                terminaItem(it);
            }
        }

    }

    /**
     *
     * @throws InterruptedException
     * faz logout aos utilizadodres com 4 minutos de inatividade
     */
    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void checkInactivity() throws InterruptedException {
        long now = LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
        Collection<Utilizador> todos = utilizadoresOk.values();
        for (Utilizador j : todos) {
            if (j.isLogged()) {
                if (j.fromLastActionFromNoew(now) > 240) { // 4 minutos
                    j.resetLogged();
                }
            }
        }
    }

    /**
     * Le dados do disco quando inicia
     */
    @PostConstruct
    public void loadstate() {
        try (ObjectInputStream ois
                = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream("/tmp/LeiloeiraDados")))) {
            utilizadoresOk = (HashMap<String, Utilizador>) ois.readObject();
            mensagens = (ArrayList<Mensagem>) ois.readObject();
            categorias = (ArrayList<String>) ois.readObject();
            itensAVenda = (HashMap<Integer, Item>) ois.readObject();
            itensTerminados = (HashMap<Integer, Item>) ois.readObject();

        } catch (Exception e) {
            //Utilizadors = fica com o objecto vazio criado no construtor
        }
    }

    /**
     * Grava dados em disco antes de sair
     */
    @PreDestroy
    public void saveState() {
        try (ObjectOutputStream oos
                = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream("/tmp/LeiloeiraDados")))) {
            oos.writeObject(utilizadoresOk);
            oos.writeObject(mensagens);
            oos.writeObject(categorias);
            oos.writeObject(itensAVenda);
            oos.writeObject(itensTerminados);
        } catch (Exception e) {

        }
    }

    /**
     * 
     * @return Lista de utilizadores inscritos, ativos e nao ativos
     */
    @Override
    public ArrayList<String> getUsernameInscritos() {
        ArrayList<String> inscritos = new ArrayList<String>();
        Collection<Utilizador> ok = utilizadoresOk.values();
        for (Utilizador j : ok) {
            inscritos.add(j.getUsername());
        }
        Collection<Utilizador> notOk = utilizadoresNotOk.values();
        for (Utilizador j : notOk) {
            inscritos.add(j.getUsername());
        }
        return inscritos;
    }

    /**
     * 
     * @return lista de utilizadores online
     */
    @Override
    public ArrayList<String> getUsernamesOnline() {
        ArrayList<String> logados = new ArrayList<String>();
        Collection<Utilizador> todos = utilizadoresOk.values();
        for (Utilizador j : todos) {
            if (j.isLogged()) {
                logados.add(j.getUsername());
            }
        }
        return logados;
    }

    /**
     * Adiciona saldo a utilizador
     * @param valor
     * @param username
     * @return valor de saldo se o utilizador estiver logado
     */
    @Override
    public Double addSaldo(Double valor, String username) {
        if (utilizadoresOk.get(username).isLogged()) {
            return utilizadoresOk.get(username).addSaldo(valor);
        }
        return null;
    }

    /**
     *
     * @param username
     * @return valor de saldo do utilizador
     */
    @Override
    public Double getSaldo(String username) {
        if (utilizadoresOk.get(username)!=null) {
            return utilizadoresOk.get(username).getSaldo();
        }
        return null;

    }

    /**
     *
     * @param username
     * @return true se o utilizador ficar ativo
     */
    @Override
    public boolean ativaUtilizador(String username) {
        Utilizador u = utilizadoresNotOk.get(username);
        if (u.getEstado() != UtilizadorEstado.ATIVO) {
            u.setEstado(UtilizadorEstado.ATIVO);
            this.utilizadoresOk.put(username, u);
            this.utilizadoresNotOk.remove(username);
            return true;
        }
        return false;
    }

    /**
     * 
     * @param estado
     * @return lista de utilizadores em determinado estado
     */
    @Override
    public ArrayList<String> getUtilizadoresEstado(UtilizadorEstado estado) {
        ArrayList<String> users = new ArrayList<String>();
        Collection<Utilizador> ok = utilizadoresOk.values();
        for (Utilizador j : ok) {
            if (j.getEstado() == estado) {
                users.add(j.getUsername());//.concat("-").concat(j.getEstado().msg()));
            }
        }
        Collection<Utilizador> noOk = utilizadoresNotOk.values();
        for (Utilizador j : noOk) {
            if (j.getEstado() == estado) {
                users.add(j.getUsername());//.concat("-").concat(j.getEstado().msg()));
            }
        }
        return users;
    }

    /**
     *
     * @param username
     * @return dados pessoais de utilizador
     */
    @Override
    public String getDadosUtilizador(String username) {
        return utilizadoresOk.get(username).getDados();
    }

    /**
     *
     * @param username
     * @param nome
     * @param morada
     * @return true se os dados forem atualizados
     */
    @Override
    public boolean atualizaDadosUtilizador(String username, String nome, String morada) {
        return utilizadoresOk.get(username).aualizaDados(nome, morada);
    }

    /**
     * Pedido de suspensao
     * @param denunciador
     * @param denunciado
     * @param razao
     * @return true 
     */
    @Override
    public boolean pedirSuspensaoUtilizador(String denunciador,String denunciado, String razao) {
        utilizadoresOk.get(denunciado).setEstado(UtilizadorEstado.SUSPENDO_PEDIDO);
        utilizadoresOk.get(denunciado).setRazaoPedidoSuspensao(razao);
        Mensagem msg = new Mensagem(denunciador, "admin", razao, "pedido de suspensao", MensagemEstado.ENVIADA);
        mensagens.add(msg);
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public HashMap<String, String> getPedidosSuspensao() {
        HashMap<String, String> pedidos = new HashMap<>();
        // ArrayList users = new ArrayList<>();
        Collection<Utilizador> todos = utilizadoresOk.values();
        for (Utilizador j : todos) {
            if (j.getEstado() == UtilizadorEstado.SUSPENDO_PEDIDO) {
                pedidos.put(j.getUsername(), j.getRazaoPedidoSuspensao());
            }
        }
        return pedidos;

    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public boolean suspendeUtilizador(String username) {
        Utilizador u = utilizadoresOk.get(username);
        u.setEstado(UtilizadorEstado.SUSPENSO);
        utilizadoresOk.remove(username);
        utilizadoresNotOk.put(username, u);
        return true;
    }

    /**
     *
     * @param username
     */
    @Override
    public void setLastAction(String username) {
        utilizadoresOk.get(username).setLastAction();
    }

    /**
     *
     * @param remetente
     * @param destinatario
     * @param texto
     * @param assunto
     * @return
     */
    @Override
    public boolean addMensagem(String remetente, String destinatario, String texto, String assunto) {
        Mensagem msg = new Mensagem(remetente, destinatario, texto, assunto, MensagemEstado.ENVIADA);
        mensagens.add(msg);
        return true;
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public ArrayList<Mensagem> getMensagensUtilizador(String username) {
        ArrayList<Mensagem> myMsg = new ArrayList<>();
        for (Mensagem msg : mensagens) {
            if (msg.getDestinatario().equals(username)) {
                myMsg.add(msg);
            }
        }
        return myMsg;
    }

    /**
     *
     * @param nomeCategoria
     * @return
     */
    @Override
    public boolean adicionarCategoria(String nomeCategoria) {
        if (categorias.indexOf(nomeCategoria) >= 0) {
            return false;
        } else {
            categorias.add(nomeCategoria);
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> obterCategorias() {
        return categorias;
    }

    /**
     *
     * @param nomeCategoria
     * @return
     */
    @Override
    public boolean eliminaCategoria(String nomeCategoria) {
        if (categorias.indexOf(nomeCategoria) >= 0) {
            categorias.remove(nomeCategoria);
            return true;

        }
        return false;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean pedirReativacaoUsername(String username, String password) {
        if (existeUtilizador(username)) {
            Utilizador u = utilizadoresNotOk.get(username);
            if (u==null)
                return false;
            if (u.getPassword().equals(password)) {
                if (u.getEstado() == UtilizadorEstado.SUSPENSO) {
                    u.setEstado(UtilizadorEstado.REATIVACAO_PEDIDO);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param nomeCategoria
     * @param novoNomeCategoria
     * @return
     */
    @Override
    public boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria) {
        int index = categorias.indexOf(nomeCategoria);
        if (index >= 0) {
            categorias.set(index, novoNomeCategoria);
            return true;
        }
        return false;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verificaPassword(String username, String password) {
        return (utilizadoresOk.get(username).getPassword().equals(password));
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean alteraPassword(String username, String password) {
        utilizadoresOk.get(username).setPassword(password);
        return true;
    }

    /**
     *
     * @param username
     * @param descricao
     * @param precoInicial
     * @param precoComprarJa
     * @param dataLimite
     * @return
     */
    @Override
    public boolean addItem(String username, String descricao, Double precoInicial, Double precoComprarJa, Timestamp dataLimite) {
        Utilizador u = utilizadoresOk.get(username);
        if (u == null) {
            return false;
        }
        itensAVenda.put(itemCount, new Item(itemCount, u, precoInicial, precoComprarJa, dataLimite, descricao));
        itemCount++;
        return true;
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public List<String> getItensUtilizador(String username) {
        List<String> itensUtilizador = new ArrayList<>();
        List<Item> listItens = new ArrayList<Item>(itensAVenda.values());
        for (Item item : listItens) {
            if (item.getVendedor().getUsername().equals(username)) {
                itensUtilizador.add(item.toLineString());
            }
        }
        return itensUtilizador;
    }

    /**
     *
     * @return
     */
    @Override
    public int getTotalItens() {
        return itensAVenda.size();
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> getItens() {
        List<String> itensResult = new ArrayList<>();
        List<Item> listItens = new ArrayList<Item>(itensAVenda.values());
        for (Item item : listItens) {
            itensResult.add(item.toLineString());
        }
        return itensResult;
    }

    /**
     *
     * @param itemId
     * @return
     */
    @Override
    public String mostraItem(int itemId) {
        Item item =  itensAVenda.get(itemId);
        if (item==null){
            return "ERRO: Item invalido";
        }
        return item.toString();
    }

    /**
     *
     * @param itemId
     * @return
     */
    @Override
    public String getVendedorItem(int itemId) {
        Item item =  itensAVenda.get(itemId);
        if (item==null){
            return "ERRO: Item invalido";
        }
        return item.getVendedor().getUsername();
    }

    /**
     *
     * @param itemid
     * @return
     */
    @Override
    public String consultarLicitacoes(int itemid) {
         Item item =  itensAVenda.get(itemid);
        if (item==null){
            return "ERRO: Item invalido";
        }
        List<Licitacao> licitacoes = new ArrayList<Licitacao>(item.getLicitacoes().values());
        StringBuilder lista = new StringBuilder();
        for (Licitacao licitacao : licitacoes) {
            lista.append(licitacao.getTimestamp());
            lista.append("->");
            lista.append(Double.toString(licitacao.getValor()));
            lista.append("\n Licitador: ");
            lista.append(licitacao.getLicitador());
            lista.append("\n");
        }
        return lista.toString();
    }

    /**
     *
     * @param itemId
     * @param comprador
     * @return
     */
    @Override
    public boolean comprarJaItem(int itemId, String comprador) {
        Item item =  itensAVenda.get(itemId);
        if (item==null){
            return false;
        }  
        if (item == null)
            return false;
        Utilizador u = utilizadoresOk.get(comprador);
        if (item.addVendacomprarJa(u)) {
            itensTerminados.put(itemId, item);
            itensAVenda.remove(itemId);
            return true;
        }
        return false;
    }

    /**
     *
     * @param itemId
     * @param value
     * @param username
     * @return
     */
    @Override
    public boolean licitarItem(int itemId, Double value, String username) {
        Item item = itensAVenda.get(itemId);
        if (item != null) {
            Utilizador licitador = utilizadoresOk.get(username);
            return item.addLicitacao(licitador, value);
        }
        return false;
    }

    /**
     *
     * @param username
     * @param itemId
     * @return
     */
    @Override
    public boolean seguirItem(String username, int itemId) {
        Item item = itensAVenda.get(itemId);
         if (item==null){
            return false;
        }
        return utilizadoresOk.get(username).addItemSeguido(item);
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public List<String> getItensSeguidos(String username) {
        return utilizadoresOk.get(username).getItemsSeguidos();
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public List<String> getIensPorPagarUtilizador(String username) {
        Utilizador u = utilizadoresOk.get(username);
        
        return u.getItemsPorPagar();
    }

    /**
     *
     * @param username
     * @param itemId
     * @return
     */
    @Override
    public boolean concluirTransacao(String username, int itemId) {
        Item i = this.itensTerminados.get(itemId);
        if (i==null)
            return false;
        Venda v = i.getVenda();
        if (v==null)
            return false;
        return v.concluirVenda();
    }

    /**
     *
     * @param itemId
     * @param denunciador
     * @param razao
     * @return
     */
    @Override
    public boolean denunciarItem(int itemId,String denunciador,String razao) {
        Item i = this.itensAVenda.get(itemId);
        if (i==null)
            return false;
        Utilizador u = utilizadoresOk.get(denunciador);
        if (u==null)
            return false;
        DenunciaItem d = new DenunciaItem(i,u,razao);
        denunciasItens.add(d);
        addMensagem(denunciador, "admin", d.toString(), "denuncia item "+i.getItemID());
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> obtemDenunciasVendedores(){
        List<String> result = new ArrayList<>();
        for (Denuncia denuncia : denunciasVendedores){
            result.add(denuncia.toString());
        }
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> obtemDenunciasItens() {
        List<String> result = new ArrayList<>();
        for (Denuncia denuncia : denunciasItens){
            result.add(denuncia.toString());
        }
        return result;
    }

    /**
     *
     * @param denunciador
     * @param vendedor
     * @param razao
     * @return
     */
    @Override
    public boolean denunciarVendedor(String denunciador, String vendedor, String razao) {
        Utilizador d = utilizadoresOk.get(denunciador);
        if (d==null)
            return false;
        Utilizador v = utilizadoresOk.get(vendedor);
        if (v==null)
            return false;
        DenunciaVendedor den = new DenunciaVendedor(d,v,razao);
        denunciasVendedores.add(den);
        addMensagem(denunciador, "admin", d.toString(), "denuncia vendedor "+v.getUsername());
        return true;
    }

    /**
     *
     * @param itemId
     * @return
     */
    @Override
    public boolean cancelarItem(int itemId) {
        Item i= itensAVenda.get(itemId);
        if (i==null)
            return false;
        return i.cancelarItem(i.getVendedor());
    }

}
