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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.persistence.EntityTransaction;
import jpaentidades.DAOLocal;
import jpaentidades.TMensagens;
import jpaentidades.TNewsletters;
import jpaentidades.TUtilizadores;

import pdtp.Denuncia;
import pdtp.DenunciaItem;
import pdtp.DenunciaVendedor;
import pdtp.Item;

import pdtp.Licitacao;
//import pdtp.Utilizador;
import pdtp.UtilizadorEstado;
import pdtp.Venda;
/*
--Tabela alteradas que tem de ser recriadas
drop table t_utilizadores cascade;
drop table t_mensagens cascade;
*/

/**
 *
 * @author diogo
 */
@Singleton
public class Leiloeira implements LeiloeiraLocal {

    @EJB
    private DAOLocal DAO;

    private HashMap<Integer, Item> itensAVenda = new HashMap<>();
    private HashMap<Integer, Item> itensTerminados = new HashMap<>();
    private List<DenunciaItem> denunciasItens = new ArrayList<>();
    private List<DenunciaVendedor> denunciasVendedores = new ArrayList<>();
    private List<String> categorias = new ArrayList<>();
//    private List<Mensagem> mensagens = new ArrayList<>();
    private int itemCount;

    public Leiloeira() {
        itemCount = getIntenCount();

    }
    private void addAdmin(){
        if (!utilizadoresOk.containsKey("admin")) {
            utilizadoresOk.put("admin",
                    new Utilizador("Administrador", "Sistema", "admin", "admin", UtilizadorEstado.ATIVO));
        }
    }
    
    private int getIntenCount() {
        return itensAVenda.size();
    }

    @Override
    public DAOLocal getDAO() {
        return DAO;
    }

    /**
     *
     * @return Lista de utilizadores ativados
     */
    @Override
    public HashMap<String, TUtilizadores> getUtilizadores() {
        HashMap<String, TUtilizadores> utilizadores = new HashMap<>();
        //Refacturing para passar a user a entidade dos utilizadores
        for (Object u : DAO.findAll(TUtilizadores.class)) {
            utilizadores.put(((TUtilizadores) u).getUsername(), ((TUtilizadores) u));
        }
        return utilizadores;
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
        return DAO.find(TUtilizadores.class, username) != null;
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

            TUtilizadores user = new TUtilizadores();
            user.setUsername(username);
            user.setPassword(password);
            user.setMorada(morada);
            user.setNome(nome);
            user.setEstado(UtilizadorEstado.ATIVO_PEDIDO);
            TNewsletters news = new TNewsletters("Novo utilizador", "O " + nome + " está inscrito.");

            Logger.getLogger(getClass().getName()).log(Level.INFO, "O Utilizador Já existe");
            EntityTransaction trans = DAO.getEntityManager().getTransaction();
            trans.begin();
            DAO.create(user);
            DAO.create(news);
            trans.commit();
            return true;
        } else {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "O Utilizador Já existe");
            return false;
        }
    }

    /**
     *
     * @param username
     * @param password
     * @return true se o utilizador ficar logado
     */
    @Override
    public boolean loginUtilizador(String username, String password) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "A verificar o login "+username+"...");
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            if (util.getPassword().equals(password)) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, "A verificar o password "+username+"...");
                if ((util.getEstado() == UtilizadorEstado.ATIVO ) || util.getEstado() == UtilizadorEstado.SUSPENDO_PEDIDO ) {
                    if (util.isLogged()) { // esta logado -Z nao deixa repetir user
                        return false;
                    } else {
                        Logger.getLogger(getClass().getName()).log(Level.INFO, "O Utilizador "+username+"logado...");
                        util.setLogged(true);
                        util.setLastActionNow();
                        DAO.editWithCommit(util);
                        return true;
                    }
                }
            }
        }
         System.out.println("---Nao Existe");
        return false;
    }

    /**
     *
     * @param username
     * @return true se o utilizador fizer loggoff
     */
    @Override
    public boolean logOff(String username) {
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util == null) {
            return false; //nao conheco
        }
        if (!util.isLogged()) {
            return false;
        }
        util.setLogged(false);
        DAO.editWithCommit(util);
        return true;
    }

    private void terminaItem(Item it) {

        itensTerminados.put(it.getItemID(), it);
        itensAVenda.remove(it.getItemID());
        if (it.getLicitacoes().isEmpty()) {
            it.terminaItemSemLicitacoes();
            this.addMensagem("admin", "admin", it.toString(), "Item Terminado sem comprador");
        } else {
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
     * @throws InterruptedException faz logout aos utilizadodres com 4 minutos
     * de inatividade
     */
    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void checkInactivity() throws InterruptedException {
        long now = LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
        for (Object u : DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByLogged", "logged", true)) {
            if (((TUtilizadores) u).fromLastActionFromNow(now) > 240) {
                ((TUtilizadores) u).setLogged(false);
                DAO.editWithCommit(u);
            }
        }
    }

    /**
     * Le dados do disco quando inicia
     */
    @PostConstruct
    public void loadstate() {

        //Registar o ADMIN se ainda não exist
        if (!existeUtilizador("admin")) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "A registar o administrador");
            TUtilizadores user = new TUtilizadores();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setMorada("Sistema");
            user.setNome("Administrador");
            user.setEstado(UtilizadorEstado.ATIVO);
            TNewsletters news = new TNewsletters("Registo do Administrador", "O Administrador foi inserido no sistema.");

            EntityTransaction trans = DAO.getEntityManager().getTransaction();
            trans.begin();
            DAO.create(user);
            DAO.create(news);
            trans.commit();

        }

        //DAO.getEntityManager();
        try (ObjectInputStream ois
                = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream("/tmp/LeiloeiraDados")))) {
            categorias = (ArrayList<String>) ois.readObject();
            itensAVenda = (HashMap<Integer, Item>) ois.readObject();
            itensTerminados = (HashMap<Integer, Item>) ois.readObject();

        } catch (Exception e) {
            //Utilizadors = fica com o objecto vazio criado no construtor
        }
        this.addAdmin();
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
        ArrayList<String> inscritos = new ArrayList<>();
        for (Object u : DAO.findAll(TUtilizadores.class)) {
            inscritos.add(((TUtilizadores) u).getUsername());
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
        for (Object u : DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByLogged", "logged", true)) {
            logados.add(((TUtilizadores) u).getUsername());
        }
        return logados;
    }

    /**
     * Adiciona saldo a utilizador
     *
     * @param valor
     * @param username
     * @return valor de saldo se o utilizador estiver logado
     */
    @Override
    public Double addSaldo(Double valor, String username) {
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null && util.isLogged() && valor > 0.0) {
            if (util.getSaldo() != null) {
                util.setSaldo(util.getSaldo() + valor);
            } else {
                util.setSaldo(valor);
            }
            DAO.editWithCommit(util);
            return util.getSaldo();
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
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            return util.getSaldo();
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
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util.getEstado() != UtilizadorEstado.ATIVO ) {
            util.setEstado(UtilizadorEstado.ATIVO );
            DAO.editWithCommit(util);
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
        ArrayList<String> users = new ArrayList<>();
        for (Object u : DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByEstado", "estado", estado.msg())) {
            users.add(((TUtilizadores) u).getUsername());
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
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            return util.getDados();
        }
        return null;
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
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            util.setNome(nome);
            util.setMorada(morada);
            DAO.editWithCommit(util);
            return true;
        }
        return false;
    }

    /**
     * Pedido de suspensao
     *
     * @param denunciador
     * @param denunciado
     * @param razao
     * @return true
     */
    @Override
    public boolean pedirSuspensaoUtilizador(String denunciador, String denunciado, String razao) {
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, denunciado);
        if (util != null) {
            util.setEstado(UtilizadorEstado.SUSPENDO_PEDIDO );
            util.setRazaopedidosuspensao(razao);
            TMensagens msg = new TMensagens();
            msg.setRemetente((TUtilizadores) DAO.find(TUtilizadores.class, denunciador));
            msg.setDestinatario((TUtilizadores) DAO.find(TUtilizadores.class, "admin"));
            msg.setAssunto(razao);
            msg.setTexto("Pedido de suspensao o utilizador "+util.getUsername());
            msg.setEstado(MensagemEstado.ENVIADA );
            
            //Guardar a mensagem e o utilizador
            EntityTransaction trans = DAO.getEntityManager().getTransaction();
            trans.begin();
            DAO.edit(util);
            DAO.create(msg);
            trans.commit();

            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public HashMap<String, String> getPedidosSuspensao() {
        HashMap<String, String> pedidos = new HashMap<>();
        for (Object u : DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByEstado", "estado", UtilizadorEstado.SUSPENDO_PEDIDO.msg())) {
            pedidos.put(((TUtilizadores) u).getUsername(), ((TUtilizadores) u).getRazaopedidosuspensao());
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
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            util.setEstado(UtilizadorEstado.SUSPENSO );
            DAO.editWithCommit(util);
            return true;
        }
        return false;
    }

    /**
     *
     * @param username
     */
    @Override
    public void setLastAction(String username) {
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            util.setLastActionNow();
            DAO.editWithCommit(util);
        }
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
        TMensagens msg = new TMensagens();
        msg.setRemetente((TUtilizadores) DAO.find(TUtilizadores.class, remetente));
        msg.setDestinatario((TUtilizadores) DAO.find(TUtilizadores.class, destinatario));
        msg.setAssunto(assunto);
        msg.setTexto(texto);
        msg.setEstado( MensagemEstado.ENVIADA );

        //Guardar a mensagem e o utilizador
        DAO.createWithCommit(msg);
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
        //TODO: Verificar se isto funciona
        for (Object msg : DAO.findByNamedQuery(TMensagens.class, "TMensagens.findByDestinatario", "username", username )) {
            Mensagem msgRef = new Mensagem(((TMensagens) msg).getRemetente().getUsername(),
                                           ((TMensagens) msg).getDestinatario().getUsername(),
                                           ((TMensagens) msg).getTexto(),
                                           ((TMensagens) msg).getAssunto(),
                                           ((TMensagens) msg).getEstado() );
            myMsg.add(msgRef);
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
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            if (util.getPassword().equals(password)) {
                if (UtilizadorEstado.SUSPENSO == util.getEstado()) {
                    util.setEstado(UtilizadorEstado.REATIVACAO_PEDIDO );
                    DAO.editWithCommit(util);
                    return true;
                } else {
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
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            return (password.equals(util.getPassword()));
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
    public boolean alteraPassword(String username, String password) {
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            util.setPassword(password);
            DAO.editWithCommit(util);
            return true;
        }
        return false;
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
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            //TODO: Refacturing para passar a usar a entidade dos utilizadores
            itensAVenda.put(itemCount, new Item(itemCount, /*u*/null, precoInicial, precoComprarJa, dataLimite, descricao));
            itemCount++;
            return true;
        }
        return false;
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
        Item item = itensAVenda.get(itemId);
        if (item == null) {
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
        Item item = itensAVenda.get(itemId);
        if (item == null) {
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
        Item item = itensAVenda.get(itemid);
        if (item == null) {
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
        Item item = itensAVenda.get(itemId);
        if (item != null) {
            TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, comprador);
            if (u != null) {
                //TODO: Refacturing para usar as entidades dos utilizadores
                if (item.addVendacomprarJa(/*u*/null)) {
                    itensTerminados.put(itemId, item);
                    itensAVenda.remove(itemId);
                    return true;
                }
            }
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
            TUtilizadores licitador = (TUtilizadores) DAO.find(TUtilizadores.class, username);
            if (licitador != null) {
                //TODO: Refacturing para passar a usar e entidade dos utilizadores
                return true;//item.addLicitacao(licitador, value);
            }
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
        if (item == null) {
            return false;
        }
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            //TODO: Refacturing para passar a user a entidade dos utlizadores
            return true;//u.addItemSeguido(item);
        }
        return false;
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public List<String> getItensSeguidos(String username) {
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            //TODO: Refacturing para passar a user a entidade dos utlizadores
            return null;//u.getItemsSeguidos();
        }
        return null;
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public List<String> getIensPorPagarUtilizador(String username) {
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            //TODO: Refacturing para passar a user a entidade dos utlizadores
            return null;//u.getItemsPorPagar();
        }
        return null;
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
        if (i == null) {
            return false;
        }
        Venda v = i.getVenda();
        if (v == null) {
            return false;
        }
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
    public boolean denunciarItem(int itemId, String denunciador, String razao) {
        Item i = this.itensAVenda.get(itemId);
        if (i == null) {
            return false;
        }
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, denunciador);
        if (u == null) {
            return false;
        }
        //TODO: refacturing para usar a entidade utilizador
        DenunciaItem d = new DenunciaItem(i, /*u*/ null, razao);
        denunciasItens.add(d);
        addMensagem(denunciador, "admin", d.toString(), "denuncia item " + i.getItemID());
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> obtemDenunciasVendedores() {
        List<String> result = new ArrayList<>();
        for (Denuncia denuncia : denunciasVendedores) {
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
        for (Denuncia denuncia : denunciasItens) {
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
        TUtilizadores d = (TUtilizadores) DAO.find(TUtilizadores.class, denunciador);
        if (d == null) {
            return false;
        }
        TUtilizadores v = (TUtilizadores) DAO.find(TUtilizadores.class, vendedor);
        if (v == null) {
            return false;
        }
        //TODO:Refacturing para usar a entidade utilizador
        DenunciaVendedor den = new DenunciaVendedor(/*d*/null, /*v*/ null, razao);
        denunciasVendedores.add(den);
        addMensagem(denunciador, "admin", d.toString(), "denuncia vendedor " + v.getUsername());
        return true;
    }

    /**
     *
     * @param itemId
     * @return
     */
    @Override
    public boolean cancelarItem(int itemId) {
        Item i = itensAVenda.get(itemId);
        if (i == null) {
            return false;
        }
        return i.cancelarItem(i.getVendedor());
    }

    @Override
    public boolean isLogged(String username) {
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            return u.isLogged();
        }
        return false;
    }

}
