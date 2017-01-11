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
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import pdtp.Item;
import pdtp.ItemEstados;
import pdtp.Licitacao;
import pdtp.Utilizador;
import pdtp.UtilizadorEstado;

@Singleton
public class Leiloeira implements LeiloeiraLocal {

    private HashMap<String, Utilizador> utilizadores = new HashMap<>();
    private HashMap<Integer,Item> itensAVenda = new HashMap<>();
    private HashMap<Integer,Item> itensTerminados = new HashMap<>();
    private List<String> categorias = new ArrayList<>();
    private List<Mensagem> mensagens = new ArrayList<>();
    private int itemCount;
    
    public Leiloeira() {
        if( !utilizadores.containsKey("admin") )
            utilizadores.put("admin",
                    new Utilizador("Administrador", "Sistema", "admin", "admin",UtilizadorEstado.ATIVO));
        itemCount = getIntenCount();
    }

    private int getIntenCount(){
        return itensAVenda.size();
    }
    
    @Override
    public HashMap<String, Utilizador> getUtilizadores() {
        return utilizadores;
    }

    @Override
    public boolean existeUtilizador(String username) {
        if (username == null) {
            return false;
        }
        Utilizador j = utilizadores.get(username);
        if (j==null) // sera que nao fica null?
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean registaUtilizador(String nome, String morada, String username, String password) {
        if (!existeUtilizador(username)) {
            utilizadores.put(username, 
                    new Utilizador(nome, morada, username, password,UtilizadorEstado.ATIVO_PEDIDO));
            return true;
        }
        return false;
    }

    @Override
    public boolean loginUtilizador(String username, String password) {
        Utilizador j = utilizadores.get(username);
        if (j != null) {
            // existe
            if (j.getPassword().equalsIgnoreCase(password)) {
                if (j.getEstado()==UtilizadorEstado.ATIVO || j.getEstado()==UtilizadorEstado.SUSPENDO_PEDIDO){
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

    @Override
    public boolean logOff(String username) {
        if (username == null) //quem?
        {
            return false;
        }
        Utilizador j = utilizadores.get(username);
        if (j == null) {
            return false; //nao conheco
        }
        if (!j.isLogged()) {
            return false;
        }
        j.resetLogged(); //unloga
        return true;
    }

            
    @Schedule(second = "*", minute = "*", hour = "*")
    public void checkItensDataFinal(){
        Timestamp now = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        List<Item> list = new ArrayList<Item>(itensAVenda.values());
        for (Item it:list){
            if(it.getDataFimTimeStamp().after(now)){
                    it.setEstado(ItemEstados.TERMINADA);
                 itensTerminados.put(it.getItemID(), it);
                itensAVenda.remove(it.getItemID());
            }
        }
         
    }        
    
    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void checkInactivity() throws InterruptedException {
        long now = LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
        Collection<Utilizador> todos = utilizadores.values();
        for (Utilizador j : todos) {
            if (j.isLogged()) {
                if (j.fromLastActionFromNoew(now) > 240) { // 4 minutos
                    j.resetLogged();
                }
            }
        }
    }

    @PostConstruct
    public void loadstate() {
        try (ObjectInputStream ois
                = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream("/tmp/LeiloeiraDados")))) {
            utilizadores = (HashMap<String, Utilizador>) ois.readObject();
            mensagens = (ArrayList<Mensagem>) ois.readObject();
            categorias = (ArrayList<String>) ois.readObject();
            itensAVenda = (HashMap<Integer,Item>) ois.readObject();
        } catch (Exception e) {
            //Utilizadors = fica com o objecto vazio criado no construtor
        }
    }

    @PreDestroy
    public void saveState() {
        try (ObjectOutputStream oos
                = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream("/tmp/LeiloeiraDados")))) {
            oos.writeObject(utilizadores);
             oos.writeObject(mensagens);
            oos.writeObject(categorias);
             oos.writeObject(itensAVenda);
        } catch (Exception e) {

        }
    }

    @Override
    public ArrayList getUsernameInscritos() {
        ArrayList inscritos = new ArrayList<>();
        Collection<Utilizador> todos = utilizadores.values();
        for (Utilizador j : todos) {
            inscritos.add(j.getUsername());
        }
        return inscritos;
    }

    @Override
    public ArrayList getUsernamesOnline() {
        ArrayList logados = new ArrayList<>();
        Collection<Utilizador> todos = utilizadores.values();
        for (Utilizador j : todos) {
            if (j.isLogged()) {
                logados.add(j.getUsername());
            }
        }
        return logados;
    }

    @Override
    public Double addSaldo(Double valor,String username) {
        if (utilizadores.get(username).isLogged())
            return utilizadores.get(username).addSaldo(valor);
        return null;
    }

    @Override
    public Double getSaldo(String username) {
        if (utilizadores.get(username).isLogged())
            return utilizadores.get(username).getSaldo();
        return null;
      
    }

    @Override
    public boolean ativaUtilizador(String username) {
        Utilizador u = utilizadores.get(username);
        if (u.getEstado()!=UtilizadorEstado.ATIVO){
            u.setEstado(UtilizadorEstado.ATIVO);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList getUtilizadoresEstado(UtilizadorEstado estado) {
        ArrayList users = new ArrayList<>();
        Collection<Utilizador> todos = utilizadores.values();
        for (Utilizador j : todos) {
            if (j.getEstado()==estado) {
                users.add(j.getUsername());//.concat("-").concat(j.getEstado().msg()));
            }
        }
        return users;
    }

    @Override
    public String getDadosUtilizador(String username) {
        return utilizadores.get(username).getDados();
    }

    @Override
    public boolean atualizaDadosUtilizador(String username, String nome, String morada) {
        return utilizadores.get(username).aualizaDados(nome,morada);
    }

    @Override
    public boolean pedirSuspensaoUtilizador(String username,String razao) {
        utilizadores.get(username).setEstado(UtilizadorEstado.SUSPENDO_PEDIDO);
        utilizadores.get(username).setRazaoPedidoSuspensao(razao);
        return true;
    }

    @Override
    public HashMap<String,String> getPedidosSuspensao() {
        HashMap<String,String> pedidos = new HashMap<>();
       // ArrayList users = new ArrayList<>();
        Collection<Utilizador> todos = utilizadores.values();
        for (Utilizador j : todos) {
            if (j.getEstado()==UtilizadorEstado.SUSPENDO_PEDIDO) {
                pedidos.put(j.getUsername(), j.getRazaoPedidoSuspensao());
            }
        }
        return pedidos;
 
    }

    @Override
    public boolean suspendeUtilizador(String username) {
        utilizadores.get(username).setEstado(UtilizadorEstado.SUSPENSO);
        return true;
    }

    @Override
    public void setLastAction(String username) {
        utilizadores.get(username).setLastAction();
    }

    @Override
    public boolean addMensagem(String remetente, String destinatario, String texto,String assunto) {
        Mensagem msg = new Mensagem(remetente,destinatario,texto,assunto,MensagemEstado.ENVIADA);
        mensagens.add(msg);
        return true;
    }

    @Override
    public ArrayList<Mensagem> getMensagensUtilizador(String username) {
        ArrayList<Mensagem> myMsg = new ArrayList<>();
        for (Mensagem msg : mensagens){
            if (msg.getDestinatario().equals(username)){
                myMsg.add(msg);
            }
        }
        return myMsg;
    }

    @Override
    public boolean adicionarCategoria(String nomeCategoria) {
        if( categorias.indexOf(nomeCategoria) >= 0 )
            return false;
        else
            categorias.add(nomeCategoria);
        return true;
    }

    @Override
    public List<String> obterCategorias() {
        return categorias;
    }

    @Override
    public boolean eliminaCategoria(String nomeCategoria) {
        if( categorias.indexOf(nomeCategoria) >= 0 ){
            categorias.remove(nomeCategoria);
            return true;
            
        }
        return false;
    }
    

    @Override
    public boolean pedirReativacaoUsername(String username,String password) {
        if (existeUtilizador(username)){
            Utilizador u = utilizadores.get(username);
            if (u.getPassword().equals(password)){
                if (u.getEstado()==UtilizadorEstado.SUSPENSO){
                    u.setEstado(UtilizadorEstado.REATIVACAO_PEDIDO);
                    return true;
                }
            }
        }           
        return false;
    }

    @Override
    public boolean modificaCategoria(String nomeCategoria, String novoNomeCategoria) {
        int index = categorias.indexOf(nomeCategoria);
        if( index >= 0 ){
            categorias.set(index, novoNomeCategoria);
            return true;
        }
        return false;
    }

    @Override
    public boolean verificaPassword(String username, String password) {
        return (utilizadores.get(username).getPassword().equals(password));
    }

    @Override
    public boolean alteraPassword(String username, String password) {
        utilizadores.get(username).setPassword(password);
        return true;
    }

    @Override
     public boolean addItem(String username,String descricao, Double precoInicial, Double precoComprarJa,Timestamp dataLimite) {
        Utilizador u = utilizadores.get(username);
        if (u==null)
            return false;
        itensAVenda.put(itemCount,new Item(itemCount,u,precoInicial,precoComprarJa,dataLimite,descricao));
        itemCount++;
        return true;
    }
    
    @Override
    public List<String> getItensUtilizador(String username) {
       List<String> itensUtilizador = new ArrayList<>();
       List<Item> listItens = new ArrayList<Item>(itensAVenda.values());
       for (Item item:listItens){
           if (item.getVendedor().getUsername().equals(username)){
               itensUtilizador.add(item.toLineString());
           }
       }
        return itensUtilizador;
    }
    
    @Override
    public  int getTotalItens(){
        return itensAVenda.size();
    }
    @Override
    public List<String> getItens(){
        List<String> itensResult = new ArrayList<>();
         List<Item> listItens = new ArrayList<Item>(itensAVenda.values());
       for (Item item:listItens){
            itensResult.add(item.toLineString());
        }
        return itensResult;
    }

    @Override
    public String mostraItem(int itemId) {
        return itensAVenda.get(itemId).toString();
    }

    @Override
    public String getVendedorItem(int itemId) {
        return itensAVenda.get(itemId).getVendedor().getUsername();
    }

    @Override
    public String consultarLicitacoes(int itemid) {
        List<Licitacao> licitacoes = new ArrayList<Licitacao>(itensAVenda.get(itemid).getLicitacoes().values());
        StringBuilder lista = new StringBuilder();
        for(Licitacao licitacao:licitacoes){
            lista.append(licitacao.getTimestamp());
            lista.append("->");
            lista.append(Double.toString(licitacao.getValor()));
            lista.append("\n Licitador: ");
            lista.append(licitacao.getLicitador());
            lista.append("\n");
        }
        return lista.toString();
    }

    @Override
    public boolean comprarJaItem(int itemId, String comprador) {
        Double comprarJa= itensAVenda.get(itemId).getComprarJa();
        Double saldo = utilizadores.get(comprador).getSaldo();
        if (saldo < comprarJa){
            return false;
        }
        if (itensAVenda.get(itemId).comprarJa(utilizadores.get(comprador))){
             itensAVenda.get(itemId).setEstado(ItemEstados.TERMINADA);
             itensTerminados.put(itemId, itensAVenda.get(itemId));
             itensAVenda.remove(itemId);
           
             return true;
        }
        return false;
    }

    @Override
    public boolean licitarItem(int itemId, Double value) {
        Item item = itensAVenda.get(itemId);
        
        if (item!=null){
            Timestamp now = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            if(item.getDataFimTimeStamp().after(now)){
                
            }
        }
        return false;
    }

    @Override
    public boolean seguirItem(String username,int itemId) {
        Item item = itensAVenda.get(itemId);      
        return utilizadores.get(username).addItemSeguido(item);
    }

    @Override
    public List getItensSeguidos(String username) {
        return utilizadores.get(username).getItemsSeguidos();
    }
    
}
