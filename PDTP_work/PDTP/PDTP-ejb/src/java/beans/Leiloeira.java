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
import javax.annotation.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TimerService;
import javax.persistence.EntityTransaction;
import jpaentidades.TCategoria;
import jpaentidades.TDenunciasItens;
import jpaentidades.TDenunciasVendedores;
import jpaentidades.TItens;
import jpaentidades.TLicitacoes;
import jpaentidades.TMensagens;
import jpaentidades.TNewsletters;
import jpaentidades.TUtilizadores;
import jpaentidades.TVendas;
import jpaentidades.TitemsAVenda;
import jpaentidades.TitemsJaPagos;
import jpaentidades.TitemsPorPagar;
import jpaentidades.TitemsSeguidos;

import pdtp.ItemEstados;

import pdtp.UtilizadorEstado;
import pdtp.VendaEstados;


/*
--Tabela alteradas que tem de ser recriadas
drop table t_utilizadores cascade;
drop table t_mensagens cascade;

drop table t_itens cascade;
drop table t_vendas cascade;
drop table t_itemsAVenda cascade;
direct comparability between those and the annotations of a CDI bean: @RequestScoped --> @Stateless, @SessionScoped --> @Stateful , @ApplicationScoped --> @Singleton.

 */
/**
 *
 * @author diogo
 */
@Singleton
public class Leiloeira implements LeiloeiraLocal {

    @EJB
    private DAOLocal DAO;

    @Resource
    TimerService timerService;

    public Leiloeira() {
    }

    private void addAdmin() {
        //Registar o ADMIN se ainda não exist
        if (!existeUtilizador("admin")) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "A registar o administrador");
            TUtilizadores user = new TUtilizadores();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setMorada("Morada do Sistema");
            user.setNome("Administrador");
            user.setEstado(UtilizadorEstado.ATIVO);
            TNewsletters news = new TNewsletters("Registo do Administrador", "O Administrador foi inserido no sistema.");

            EntityTransaction trans = DAO.getEntityManager().getTransaction();
            trans.begin();
            DAO.create(user);
            DAO.create(news);
            trans.commit();

        }
    }

    /**
     * termina os itens nas respetivas horas de fim
     */
    @Schedule(second = "*/29", minute = "*", hour = "*")
    public void checkItensDataFinal() {
        Timestamp now = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        //Refacturing para usar os itens da base de dados
        for (Object it : DAO.findByNamedQuery(TUtilizadores.class, "TItens.findByEstado", "estado", ItemEstados.INICIADA)) {
            if (((TItens) it).getDatafim().after(now)) {
                terminaItem(((TItens) it));
            }
        }
    }

    /**
     *
     * @throws InterruptedException faz logout aos utilizadodres com 4 minutos
     * de inatividade
     */
    @Schedule(second = "*/30", minute = "*", hour = "*")
    public void checkInactivity() throws InterruptedException {
        long now = LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
        for (Object u : DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByLogged", "logged", true)) {
            if (((TUtilizadores) u).fromLastActionFromNow(now) > 240) {
                ((TUtilizadores) u).setLogged(false);
                DAO.editWithCommit(u);
                System.out.println("---Session Timeout user " + ((TUtilizadores) u).getUsername());
            }
        }
    }

    /**
     * Le dados do disco quando inicia
     */
    @PostConstruct
    public void loadstate() {
        this.addAdmin();
        //timerService.notifyAll();
        //DAO.getEntityManager();
        try (ObjectInputStream ois
                = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream("/tmp/LeiloeiraDados")))) {
//            categorias = (ArrayList<String>) ois.readObject();

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
//            oos.writeObject(categorias);
        } catch (Exception e) {

        }
    }

//    private int getIntenCount() {
//        return itensAVenda.size();
//    }
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

            Logger.getLogger(getClass().getName()).log(Level.INFO, "Criação do utilizador");
            EntityTransaction trans = DAO.getEntityManager().getTransaction();
            trans.begin();
            DAO.create(user);
            DAO.create(news);
            trans.commit();
            return true;
        } else {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "O Utilizador já existe");
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
        Logger.getLogger(getClass().getName()).log(Level.INFO, "A verificar o login " + username + "...");
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            if (util.getPassword().equals(password)) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, "A verificar o password " + username + "...");
                if ((util.getEstado() == UtilizadorEstado.ATIVO) || util.getEstado() == UtilizadorEstado.SUSPENDO_PEDIDO) {
                    if (util.isLogged()) { // esta logado -Z nao deixa repetir user
                        //TODO: Isto tem mesmo de ficar assim?
                        return true;//return false;
                    } else {
                        Logger.getLogger(getClass().getName()).log(Level.INFO, "O Utilizador " + username + " está logado...");
                        util.setLogged(true);
                        util.setLastActionNow();
                        DAO.editWithCommit(util);
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
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util == null) {
            return false; //nao conheco
        }
        if (!util.isLogged()) {
            return false;
        }
        //Registar o LogOut
        util.setLogged(false);
        DAO.editWithCommit(util);
        return true;
    }

    private void terminaItem(TItens it) {

        //Remover da lista dos itens à venda, determinar o comparador e o valor
        if (it.getTLicitacoesCollection().isEmpty()) {
            it.setEstado(ItemEstados.TERMINADA);
            DAO.editWithCommit(it);
            //Enviar mensagem ao vendedor
            this.addMensagem("admin", it.getVendedor().getUsername(), "Item Terminado sem comprador", it.getDescricao());
        } else {
            TLicitacoes maxLic
                    = (TLicitacoes) DAO.getEntityManager().createQuery("select t from TLicitacoes t where t.item = :item and t.valor = :valor ")
                            .setParameter("item", it)
                            .setParameter("valor", it.getLicitacaomaxima())
                            .getSingleResult();
            if (maxLic != null) {
                EntityTransaction trans = DAO.getEntityManager().getTransaction();
                trans.begin();
                it.setEstado(ItemEstados.VENDIDA);
                it.setComprador(maxLic.getLicitador());
                DAO.edit(it);
                DAO.create(new TitemsPorPagar(maxLic.getLicitador(), it));
                TNewsletters news = new TNewsletters("Item vendido", it.getDescricao());
                DAO.create(news);
                trans.commit();
                //Enviar mensagem ao vendedor
                this.addMensagem("admin", it.getVendedor().getUsername(), "Item Terminado com comprador", it.getDescricao());
                //Enviar mensagem ao comprador
                this.addMensagem("admin", maxLic.getLicitador().getUsername(), "Ganhou o item ", it.getDescricao());
                //Publicar a news
            } else {
                it.setEstado(ItemEstados.CANCELADA);
                DAO.editWithCommit(it);
                //Enviar mensagem ao comprador
                this.addMensagem("admin", it.getVendedor().getUsername(), "ERRO: Item nao Terminado com sucesso", it.getDescricao());
            }
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
        if (util.getEstado() != UtilizadorEstado.ATIVO) {
            util.setEstado(UtilizadorEstado.ATIVO);
            TMensagens msg = new TMensagens();
            msg.setRemetente((TUtilizadores) DAO.find(TUtilizadores.class, "admin"));
            msg.setDestinatario(util);
            msg.setAssunto("Conta ativada");
            msg.setTexto("Conta ativada");
            msg.setEstado(MensagemEstado.ENVIADA);
            TNewsletters news = new TNewsletters("Conta ativada", "O " + username + " foi ativado.");

            //Guardar a ativação do utilizador
            EntityTransaction trans = DAO.getEntityManager().getTransaction();
            trans.begin();
            DAO.edit(util);
            DAO.create(news);
            DAO.create(msg);
            trans.commit();
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
        for (Object u : DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByEstado", "estado", estado)) {
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
            if (!nome.isEmpty()) {
                util.setNome(nome);
            }
            if (!morada.isEmpty()) {
                util.setMorada(morada);
            }
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
            util.setEstado(UtilizadorEstado.SUSPENDO_PEDIDO);
            util.setRazaopedidosuspensao(razao);
            TMensagens msg = new TMensagens();
            msg.setRemetente((TUtilizadores) DAO.find(TUtilizadores.class, denunciador));
            msg.setDestinatario((TUtilizadores) DAO.find(TUtilizadores.class, "admin"));
            msg.setAssunto(razao);
            msg.setTexto("Pedido de suspensao o utilizador " + util.getUsername());
            msg.setEstado(MensagemEstado.ENVIADA);

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
        for (Object u : DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByEstado", "estado", UtilizadorEstado.SUSPENDO_PEDIDO)) {
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
            util.setEstado(UtilizadorEstado.SUSPENSO);
            TMensagens msg = new TMensagens();
            msg.setRemetente(util);
            msg.setDestinatario((TUtilizadores) DAO.find(TUtilizadores.class, "admin"));
            msg.setAssunto("Conta suspensa");
            msg.setTexto("Conta suspensa do utilizador " + username);
            msg.setEstado(MensagemEstado.ENVIADA);

            //Guardar a ativação do utilizador
            EntityTransaction trans = DAO.getEntityManager().getTransaction();
            trans.begin();
            DAO.create(msg);
            DAO.edit(util);
            trans.commit();

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
        //TODO: Refazer com utilizadores em vez de strings
        TMensagens msg = new TMensagens();
        msg.setRemetente((TUtilizadores) DAO.find(TUtilizadores.class, remetente));
        msg.setDestinatario((TUtilizadores) DAO.find(TUtilizadores.class, destinatario));
        msg.setAssunto(assunto);
        msg.setTexto(texto);
        msg.setEstado(MensagemEstado.ENVIADA);

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
        //Obter todas as mensagens enviadas para o utilizador
        for (Object msg : this.getTMensagensUtilizador(username)) {
            Mensagem msgRef = new Mensagem(((TMensagens) msg).getRemetente().getUsername(),
                    ((TMensagens) msg).getDestinatario().getUsername(),
                    ((TMensagens) msg).getTexto(),
                    ((TMensagens) msg).getAssunto(),
                    ((TMensagens) msg).getEstado(),
                    ((TMensagens) msg).getData());
            myMsg.add(msgRef);
        }

        return myMsg;
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public List<Object> getTMensagensUtilizador(String username) {
        //Obter todas as mensagens enviadas para o utilizador
        return DAO.findByNamedQuery(TMensagens.class, "TMensagens.findByDestinatario", "username", new TUtilizadores(username));
    }

    @Override
    public int obtemNumTMensagens(String username) {
        //Obter o numero de mensagens do utilizador
        return DAO.countByNamedQuery(TMensagens.class, "TMensagens.countFindByDestinatario", "username", new TUtilizadores(username));
    }

    @Override
    public Object obtemMensagemById(String username, Integer id) {
        //Obter um mensagem pelo ID e verificar se é para o utilizador
        TMensagens msg = (TMensagens) DAO.find(TMensagens.class, id);
        if (msg != null) {
            if (username.equals(msg.getDestinatario().getUsername())) {
                return msg;
            }
        }
        return null;
    }

    @Override
    public Object obtemMensagemByIdEnviada(String username, Integer id) {
        //Obter um mensagem pelo ID e verificar se é para do utilizador
        TMensagens msg = (TMensagens) DAO.find(TMensagens.class, id);
        if (msg != null) {
            if (username.equals(msg.getRemetente().getUsername())) {
                return msg;
            }
        }
        return null;
    }

    @Override
    public List<Object> obtemMensagensRange(String username, int[] range) {
        //Obter pelo intervalo passado de registos
        return DAO.findRangeByNamedQuery(TMensagens.class, range, "TMensagens.findByDestinatario", "username", new TUtilizadores(username));
    }

    @Override
    public boolean alteraMensagem(String username, Integer id, String destinatario, String texto, String assunto) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TMensagens msg = (TMensagens) this.obtemMensagemById(username, id);
        if (msg != null) {
            if (msg.getEstado() == MensagemEstado.ENVIADA) {
                msg.setAssunto(assunto);
                msg.setTexto(texto);
                msg.setDestinatario(new TUtilizadores(destinatario));
                DAO.editWithCommit(msg);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean alteraMensagemParaLida(String username, Integer id, Boolean lida) {
        //Marcar uma mensagem como lida
        TMensagens msg = (TMensagens) this.obtemMensagemById(username, id);
        if (msg != null) {
            if (lida) {
                msg.setEstado(MensagemEstado.LIDA);
            } else {
                msg.setEstado(MensagemEstado.ENTREGUE);
            }
            DAO.editWithCommit(msg);
        }
        return false;
    }

    /**
     *
     * @param nomeCategoria
     * @return
     */
    @Override
    public boolean adicionarCategoria(String nomeCategoria) {
        if (DAO.findByNamedQuery(TCategoria.class, "TCategoria.findByNome", "nome", nomeCategoria).isEmpty()) {
            //Se a categoria não existe, então adiciona-se
            DAO.createWithCommit(new TCategoria(nomeCategoria));
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> obterCategorias() {
        List<String> categorias = new ArrayList<>();
        for (Object cat : DAO.findAll(TCategoria.class)) {
            categorias.add(((TCategoria) cat).getNome());
        }
        return categorias;
    }

    /**
     *
     * @param nomeCategoria
     * @return
     */
    @Override
    public boolean eliminaCategoria(String nomeCategoria) {
        boolean ret = false;
        for (Object cat : DAO.findByNamedQuery(TCategoria.class, "TCategoria.findByNome", "nome", nomeCategoria)) {
            DAO.removeWithCommit(cat);
            ret = true;
        }
        return ret;
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
                    util.setEstado(UtilizadorEstado.REATIVACAO_PEDIDO);
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
        boolean ret = false;
        for (Object cat : DAO.findByNamedQuery(TCategoria.class, "TCategoria.findByNome", "nome", nomeCategoria)) {
            ((TCategoria) cat).setNome(novoNomeCategoria);
            DAO.editWithCommit(cat);
            ret = true;
        }
        return ret;
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
     * @param categoria
     * @param descricao
     * @param precoInicial
     * @param precoComprarJa
     * @param dataLimite
     * @return
     */
    @Override
    public boolean addItem(String username, String categoria, String descricao, Double precoInicial, Double precoComprarJa, Timestamp dataLimite) {
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            TItens item = new TItens();
            item.setDescricao(descricao);
            item.setPrecoinicial(precoInicial);
            item.setComprarja(precoComprarJa);
            item.setDatafim(dataLimite);
            item.setVendedor(util);
            item.setEstado(ItemEstados.INICIADA);
            item.setCategoria(categoria);
            TitemsAVenda iVenda = new TitemsAVenda(item);
            TNewsletters news = new TNewsletters("Item adicionado ", item.getDescricao());
            EntityTransaction trans = DAO.getEntityManager().getTransaction();

            trans.begin();
            DAO.create(item);
            DAO.create(iVenda);
            DAO.create(news);
            trans.commit();

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
        TUtilizadores util = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (util != null) {
            //Obter todosos itens do utilizador
            for (Object it : DAO.findByNamedQuery(TItens.class, "TItens.findByVendedor", "vendedor", util)) {
                itensUtilizador.add(((TItens) it).toLineString());
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
        //Saber o número de itens à venda
        return DAO.count(TitemsAVenda.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> getItens() {
        List<String> itensResult = new ArrayList<>();
        //Obter todos os itens
        for (Object d : DAO.findAll(TItens.class)) {
            itensResult.add(((TItens) d).toLineString());
        }
        return itensResult;
    }

    /**
     *
     * @param itemId
     * @return
     */
    @Override
    public String mostraItem(long itemId) {
        TItens item = (TItens) DAO.find(TItens.class, itemId);
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
    public String getVendedorItem(long itemId) {
        TItens item = (TItens) DAO.find(TItens.class, itemId);
        if (item == null) {
            return "ERRO: Item invalido";
        }
        return item.getVendedor().getUsername();
    }

    /**
     *
     * @param itemId
     * @return
     */
    @Override
    public String consultarLicitacoes(long itemId) {
        StringBuilder lista = new StringBuilder();
        TItens i = (TItens) DAO.find(TItens.class, itemId);
        if (i == null) {
            return "ERRO: Item invalido";
        }
        //Obter todas as licitações do Item
        for (TLicitacoes licitacao : i.getTLicitacoesCollection()) {
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
    public boolean comprarJaItem(long itemId, String comprador) {
        TItens i = (TItens) DAO.find(TItens.class, itemId);
        if (i != null) {
            TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, comprador);
            if (u != null) {
                i.setEstado(ItemEstados.VENDIDA);
                i.setComprador(u);
                TVendas venda = new TVendas();
                venda.setComprador(u);
                venda.setItem(i);
                venda.setValor(i.getLicitacaomaxima());
                venda.setEstado(VendaEstados.ESPERA);
                EntityTransaction trans = DAO.getEntityManager().getTransaction();
                trans.begin();
                DAO.edit(i);
                DAO.create(venda);
                DAO.create(new TitemsPorPagar(u, i));
                trans.commit();
                //Enviar mensagem ao vendedor
                this.addMensagem("admin", i.getVendedor().getUsername(), "Item Terminado com comprador", i.getDescricao());
                //Enviar mensagem ao comprador
                this.addMensagem("admin", u.getUsername(), "Ganhou o item ", i.getDescricao());
                //Publicar a news
                TNewsletters news = new TNewsletters("Item vendido", i.getDescricao());
                DAO.createWithCommit(news);

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
    public boolean licitarItem(long itemId, Double value, String username) {
        TItens i = (TItens) DAO.find(TItens.class, itemId);
        if (i != null) {
            TUtilizadores licitador = (TUtilizadores) DAO.find(TUtilizadores.class, username);
            if (licitador != null) {
                if (i.getPrecoinicial() == null || i.getPrecoinicial() >= value) {
                    //Refacturing para passar a usar e entidade dos utilizadores
                    if (i.getLicitacaomaxima() == null || i.getLicitacaomaxima() > value) {
                        //registar a licitação
                        EntityTransaction trans = DAO.getEntityManager().getTransaction();
                        trans.begin();
                        i.setLicitacaomaxima(value);
                        DAO.edit(i);
                        TLicitacoes lic = new TLicitacoes();
                        lic.setItem(i);
                        lic.setLicitador(licitador);
                        lic.setValor(value);
                        DAO.create(lic);
                        trans.commit();
                        if (value >= i.getComprarja()) {
                            this.comprarJaItem(itemId, username);
                        }
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
     * @param itemId
     * @return
     */
    @Override
    public boolean seguirItem(String username, long itemId) {
        TItens i = (TItens) DAO.find(TItens.class, itemId);
        if (i == null) {
            return false;
        }
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            //Refacturing para passar a user a entidade dos utilizadores
            if (DAO.findByNamedQuery(TitemsSeguidos.class, "TitemsSeguidos.findByItemUtilizador", "item", i, "utilizador", u).isEmpty()) {
                DAO.createWithCommit(new TitemsSeguidos(u, i));
                return true;
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
    public boolean seguirItemCancelar(String username, long itemId) {
        TItens i = (TItens) DAO.find(TItens.class, itemId);
        if (i == null) {
            return false;
        }
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            //Remover o item da lista dos seguidos
            if (!DAO.findByNamedQuery(TitemsSeguidos.class, "TitemsSeguidos.findByItemUtilizador", "item", i, "utilizador", u).isEmpty()) {
                DAO.removeWithCommit(new TitemsSeguidos(u, i));
                return true;
            }
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
        List<String> result = new ArrayList<>();
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            //Refacturing para passar a user a entidade dos utlizadores
            for (Object seg : DAO.findByNamedQuery(TitemsSeguidos.class, "TitemsSeguidos.findByUtilizador", "utilizador", u)) {
                result.add(((TitemsSeguidos) seg).toString());
            }
            return result;
        }
        return null;
    }
    @Override
    public List<Object> getItensSeguidosObj(String username) {
        List<Object> result = new ArrayList<>();
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            for (Object itemseg : DAO.findByNamedQuery(TitemsSeguidos.class, "TitemsSeguidos.findByUtilizador", "utilizador", u)) {
                System.out.println("---"+((TitemsSeguidos)itemseg).toString());
                result.add( ((TitemsSeguidos)itemseg).getItem());
                 System.out.println("ADD---"+((TitemsSeguidos)itemseg).getItem().getDescricao());
                //result.add(((TitemsSeguidos) seg).toString());
            }
            return result;
        }
        return null;
    }
    /**
     *
     * @param username
     * @return
     */
    @Override
    public List<String> getUltimosItensVendidos() {
        List<String> result = new ArrayList<>();
        //Refacturing para passar a user a entidade dos utlizadores
        for (Object venda : DAO.findByNamedQuery(TVendas.class, "TVendas.findLast", 10)) {
            result.add(((TVendas) venda).toString());
        }
        return result;
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public List<String> getIensPorPagarUtilizador(String username) {
        List<String> result = new ArrayList<>();
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            //Refacturing para passar a user a entidade dos utilizadores
            for (Object seg : DAO.findByNamedQuery(TitemsPorPagar.class, "TitemsPorPagar.findByUtilizador", "utilizador", u)) {
                result.add(((TitemsPorPagar) seg).toString());
            }
            return result;
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
    public boolean concluirTransacao(String username, long itemId) {
        //Concluir a transação/pagamento
        TItens i = (TItens) DAO.find(TItens.class, itemId);
        if (i == null) {
            return false;
        }
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            //Refacturing para passar a user a entidade dos utilizadores
            TitemsPorPagar porPag = (TitemsPorPagar) DAO.findByNamedQuery(TitemsPorPagar.class, "TitemsPorPagar.findByItemUtilizador", "item", i, "utilizador", u).get(0);
            if (porPag == null && i.getLicitacaomaxima() <= u.getSaldo()) {
                //Tirar o Saldo
                u.setSaldo(u.getSaldo() - i.getLicitacaomaxima());
                //Guardar ps dados
                EntityTransaction trans = DAO.getEntityManager().getTransaction();
                trans.begin();
                DAO.edit(u);
                DAO.remove(porPag);
                DAO.create(new TitemsJaPagos(u, i));
                trans.commit();
                return true;
            }
        }
        return false;

    }

    /**
     *
     * @param itemId
     * @param denunciador
     * @param razao
     * @return
     */
    @Override
    public boolean denunciarItem(long itemId, String denunciador, String razao) {
        TItens i = (TItens) DAO.find(TItens.class, itemId);
        if (i == null) {
            return false;
        }
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, denunciador);
        if (u == null) {
            return false;
        }
        //refacturing para usar a entidade utilizador
        TDenunciasItens den = new TDenunciasItens();
        den.setDenunciador(u);
        den.setItem(i);
        den.setRazao(razao);
        DAO.createWithCommit(den);
        addMensagem(denunciador, "admin", den.toString(), "denuncia item " + i.getItemid());
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> obtemDenunciasVendedores() {
        List<String> result = new ArrayList<>();
        //Obter os vendedores denunciados
        for (Object d : DAO.findAll(TDenunciasVendedores.class)) {
            result.add(((TDenunciasVendedores) d).toLineString());
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
        //Obter os itens denunciados
        for (Object d : DAO.findAll(TDenunciasItens.class)) {
            result.add(((TDenunciasItens) d).toLineString());
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
        //Refacturing para usar a entidade utilizador
        TDenunciasVendedores den = new TDenunciasVendedores();
        den.setDenunciador(d);
        den.setVendedor(v);
        den.setRazao(razao);
        DAO.createWithCommit(den);
        addMensagem(denunciador, "admin", d.toString(), "denuncia vendedor " + v.getUsername());
        return true;
    }

    /**
     *
     * @param itemId
     * @return
     */
    @Override
    public boolean cancelarItem(long itemId) {
        TItens i = (TItens) DAO.find(TItens.class, itemId);
        if (i != null) {
            i.setEstado(ItemEstados.CANCELADA);
            DAO.editWithCommit(i);
            return true;
        }
        return false;
    }

    @Override
    public boolean isLogged(String username) {
        TUtilizadores u = (TUtilizadores) DAO.find(TUtilizadores.class, username);
        if (u != null) {
            return u.isLogged();
        }
        return false;
    }

    @Override
    public List<String> obtemNewsletter() {
        List<String> news = new ArrayList<>();
        for (Object n : obtemNewsletter(0)) {
            news.add(((TNewsletters) n).getAssunto() + ":" + ((TNewsletters) n).getNewsletter());
        }
        return news;
    }

    @Override
    public List<Object> obtemNewsletter(int nlastNews) {
        List<Object> list;
        if (nlastNews > 0) {
            //Encontra todas
            list = DAO.findByNamedQuery(TNewsletters.class, "TNewsletters.findAll", "", null);
        } else {
            //Encontrar as ultimas 
            list = DAO.findByNamedQuery(TNewsletters.class, "TNewsletters.findAll", nlastNews);
        }
        return list;
    }

    @Override
    public Object obtemUserById(String username) {
        return DAO.find(TUtilizadores.class, username);
    }

    @Override
    public List<Object> obtemUtilizadores(UtilizadorTipoLista lista) throws SessionException {
        List<Object> list = null;
        if (null != lista) {
            switch (lista) {
                case LISTA_USER_ALL:
                    list = DAO.findAll(TUtilizadores.class);
                    break;
                case LISTA_USER_ATIVOS:
                    list = DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByEstado", "estado", UtilizadorEstado.ATIVO);
                    break;
                case LISTA_USER_ADESOES:
                    list = DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByEstado", "estado", UtilizadorEstado.ATIVO_PEDIDO);
                    break;
                case LISTA_USER_REARIVAR:
                    list = DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByEstado", "estado", UtilizadorEstado.REATIVACAO_PEDIDO);
                    break;
                case LISTA_USER_SUPENDER:
                    list = DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByEstado", "estado", UtilizadorEstado.SUSPENDO_PEDIDO);
                    break;
                case LISTA_USER_ONLINE:
                    list = DAO.findByNamedQuery(TUtilizadores.class, "TUtilizadores.findByLogged", "logged", true);
                    break;
                default:
                    break;
            }
        }
        return list;
    }

    @Override
    public int obtemNumUtilizador(UtilizadorTipoLista lista) throws SessionException {
        int numReg = 0;
        if (null != lista) {
            switch (lista) {
                case LISTA_USER_ALL:
                    numReg = DAO.count(TUtilizadores.class);
                    break;
                case LISTA_USER_ATIVOS:
                    numReg = DAO.countByNamedQuery(TUtilizadores.class, "TUtilizadores.findByEstado", "estado", UtilizadorEstado.ATIVO);
                    break;
                case LISTA_USER_ADESOES:
                    numReg = DAO.countByNamedQuery(TUtilizadores.class, "TUtilizadores.countFindByEstado", "estado", UtilizadorEstado.ATIVO_PEDIDO);
                    break;
                case LISTA_USER_REARIVAR:
                    numReg = DAO.countByNamedQuery(TUtilizadores.class, "TUtilizadores.countFindByEstado", "estado", UtilizadorEstado.REATIVACAO_PEDIDO);
                    break;
                case LISTA_USER_SUPENDER:
                    numReg = DAO.countByNamedQuery(TUtilizadores.class, "TUtilizadores.countFindByEstado", "estado", UtilizadorEstado.SUSPENDO_PEDIDO);
                    break;
                case LISTA_USER_ONLINE:
                    numReg = DAO.countByNamedQuery(TUtilizadores.class, "TUtilizadores.countFindByLogged", "logged", true);
                    break;
                default:
                    break;
            }
        }
        return numReg;
    }

    @Override
    public List<Object> obtemUtilizadoresRange(UtilizadorTipoLista lista, int[] range) throws SessionException {
        List<Object> list = null;
        if (null != lista) {
            switch (lista) {
                case LISTA_USER_ALL:
                    list = DAO.findRange(TUtilizadores.class, range);
                    break;
                case LISTA_USER_ATIVOS:
                    list = DAO.findRangeByNamedQuery(TUtilizadores.class, range, "TUtilizadores.findByEstado", "estado", UtilizadorEstado.ATIVO);
                    break;
                case LISTA_USER_ADESOES:
                    list = DAO.findRangeByNamedQuery(TUtilizadores.class, range, "TUtilizadores.findByEstado", "estado", UtilizadorEstado.ATIVO_PEDIDO);
                    break;
                case LISTA_USER_REARIVAR:
                    list = DAO.findRangeByNamedQuery(TUtilizadores.class, range, "TUtilizadores.findByEstado", "estado", UtilizadorEstado.REATIVACAO_PEDIDO);
                    break;
                case LISTA_USER_SUPENDER:
                    list = DAO.findRangeByNamedQuery(TUtilizadores.class, range, "TUtilizadores.findByEstado", "estado", UtilizadorEstado.SUSPENDO_PEDIDO);
                    break;
                case LISTA_USER_ONLINE:
                    list = DAO.findRangeByNamedQuery(TUtilizadores.class, range, "TUtilizadores.findByLogged", "logged", true);
                    break;
                default:
                    break;
            }
        }

        return list;
    }

    @Override
    public List<Object> obtemCategoriasEnt() {
        return DAO.findAll(TCategoria.class);
    }

    @Override
    public int obtemNumCategorias() {
        return DAO.count(TCategoria.class);
    }

    @Override
    public Object obtemCategoriasById(String id) {
        return DAO.find(TCategoria.class, id);
    }

    @Override
    public List<Object> obtemCategoriasRange(int[] range) {
        return DAO.findRange(TCategoria.class, range);
    }

    @Override
    public List<Object> obtemNewsletterEnt() {
        return DAO.findAll(TNewsletters.class);
    }

    @Override
    public int obtemNumNewsletter() {
        return DAO.count(TNewsletters.class);
    }

    @Override
    public Object obtemNewsletterById(Integer id) {
        return DAO.find(TNewsletters.class, id);
    }

    @Override
    public List<Object> obtemNewsletterRange(int[] range) {
        return DAO.findRange(TNewsletters.class, range);
    }
}
