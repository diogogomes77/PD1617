
package pdtp;

//import entidades.TUtilizadores;
import beans.LeiloeiraLocal;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.Entity;
import javax.persistence.EntityTransaction;
import javax.persistence.Table;
import jpaentidades.DAOLocal;
import jpaentidades.TUtilizadores;

/**
 *
 * @author diogo
 */
public class Utilizador implements Serializable {
    private String nome;
    private String morada;
    private String username;
    private String password;
    private Double saldo;
    private List<Item> itemsAVenda;
    private List<Item> itemsSeguidos;
    private List<Item> itemsPorPagar;
    private List<Item> itemsJaPagos;
    //private List<Item> leiloes;
    private UtilizadorEstado estado;
    private String razaoPedidoSuspensao;
    private boolean logged;
    long lastAction;
    
    @EJB
    private DAOLocal DAO;


    /**
     *
     * @param nome
     * @param morada
     * @param username
     * @param password
     * @param estado
     */
    public Utilizador(String nome, String morada, String username, String password,UtilizadorEstado estado) {
        this.nome = nome;
        this.morada = morada;
        this.username = username;
        this.password = password;
        this.saldo = 0.0;
       // this.items = new ArrayList<>();
        //this.leiloes = new ArrayList<>();
        this.itemsAVenda = new ArrayList<>();
        this.itemsSeguidos = new ArrayList<>();
        this.itemsPorPagar = new ArrayList<>();
        this.itemsJaPagos = new ArrayList<>();
        
        this.logged=false;
        this.estado=estado;
    }

    /**
     *
     * @return
     */
    public List<Item> getItemsAVenda() {
        return itemsAVenda;
    }

    /**
     *
     * @param item
     * @return
     */
    public boolean addItemAVenda(Item item) {
        if (itemsAVenda.contains(item))
            return false;
        itemsAVenda.add(item);
        return true;
    }

    /**
     *
     * @return
     */
    public List<String> getItemsPorPagar() {
        List<String> result = new ArrayList<>();
        for (Item i : itemsPorPagar){
            result.add(i.toLineString());
        }
        return result;
    }

    /**
     *
     * @param item
     * @return
     */
    public boolean addItemPorPagar(Item item) {
        if (this.itemsPorPagar.contains(item))
            return false;
        this.itemsPorPagar.add(item);
        return true;
    }

    /**
     *
     * @return
     */
    public List<Item> getItemsJaPagos() {
        return itemsJaPagos;
    }

    /**
     *
     * @param item
     * @return
     */
    public boolean pagarItemLicitacao(Item item) {
        if (!this.itemsPorPagar.contains(item))
            return false;
        Double custo =item.getLicitacoes().pollLastEntry().getKey();
        if (this.saldo<custo){
            this.saldo-=custo;
            this.itemsPorPagar.remove(item);
            this.itemsJaPagos.add(item);
        }
        return false;
    }

    /**
     *
     * @param item
     * @return
     */
    public boolean pagarJaItem(Item item) {
        if (!this.itemsPorPagar.contains(item))
            return false;
        Double custo = item.getComprarJa();
        return pagarItem(item,custo);
        
    }
    private boolean pagarItem(Item item,Double custo){
        if (this.saldo < custo){
            this.saldo -= custo;
            this.itemsPorPagar.remove(item);
            this.itemsJaPagos.add(item);
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public UtilizadorEstado getEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(UtilizadorEstado estado) {
        this.estado = estado;
    }

    /**
     *
     * @param item
     * @return
     */
    public boolean addItem(Item item){
        if (itemsAVenda.contains(item))
            return false;
        this.itemsAVenda.add(item);
        return true;
    }

    /**
     *
     * @return
     */
    public List<String> getItemsSeguidos() {
        List<String> result =  new ArrayList<>();
         for (Item i : itemsSeguidos){
             result.add(i.toLineString());
         }

        return result;
    }

    /**
     *
     * @param itemsSeguidos
     */
    public void setItemsSeguidos(List<Item> itemsSeguidos) {
        this.itemsSeguidos = itemsSeguidos;
    }
    
    /**
     *
     * @param item
     * @return
     */
    public boolean addItemSeguido(Item item){
        if (itemsSeguidos.contains(item))
            return false;
        this.itemsSeguidos.add(item);
        return true;
    }


    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @param morada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param valor
     * @return
     */
    public Double addSaldo(Double valor) {
        this.saldo += valor;
        return this.saldo;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @return
     */
    public String getMorada() {
        return morada;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     *
     * @return
     */
    public List<Item> getItems() {
        return itemsAVenda;
    }

 

    /**
     *
     */
    public void setLastAction(){
        lastAction=LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
    }

    /**
     *
     * @param seconds
     * @return
     */
    public boolean lastActionMoreThan(long seconds){
        return LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond()
        - lastAction < seconds;
    }

    /**
     *
     * @param now
     * @return
     */
    public long fromLastActionFromNoew(long now) {
        return now - lastAction;
    }

    /**
     *
     * @return
     */
    public boolean isLogged() {
        return logged;
    }

    /**
     *
     */
    public void setLogged() {
        logged = true;
    }

    /**
     *
     */
    public void resetLogged() {
        logged = false;
    }
    
    /**
     *
     * @return
     */
    public String getDados(){
        StringBuilder dados = new StringBuilder();
        dados.append("\n");
        dados.append("Nome: ").append(nome).append("\n");
        dados.append("Morada: ").append(morada).append("\n");
        dados.append("Username: ").append(username).append("\n");
        dados.append("\n");
        return dados.toString();
    }
    @Override
    public boolean equals(Object x){
        if(x==null)
            return false;
        if(getClass() != x.getClass())
            return false;
        Utilizador j = (Utilizador) x;
        return username.compareToIgnoreCase(j.username) == 0;
    }
    @Override
    public int hashCode(){
        return username.hashCode();
    }

    /**
     *
     * @param nome
     * @param morada
     * @return
     */
    public boolean aualizaDados(String nome, String morada) {
         this.nome = nome;
        this.morada = morada;
        return true;
    }

    /**
     *
     * @return
     */
    public String getRazaoPedidoSuspensao() {
        return razaoPedidoSuspensao;
    }

    /**
     *
     * @param razaoPedidoSuspensao
     */
    public void setRazaoPedidoSuspensao(String razaoPedidoSuspensao) {
        this.razaoPedidoSuspensao = razaoPedidoSuspensao;
    }
}
