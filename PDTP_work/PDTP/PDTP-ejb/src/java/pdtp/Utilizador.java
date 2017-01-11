
package pdtp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;


public class Utilizador implements Serializable {
    private String nome;
    private String morada;
    private String username;
    private String password;
    private Double saldo;
    private List<Item> itemsAVenda;
    private List<Item> itemsSeguidos;
    private List<Item> leiloes;
    private UtilizadorEstado estado;
    private String razaoPedidoSuspensao;
    private boolean logged;
    long lastAction;
    
    public Utilizador(String nome, String morada, String username, String password,UtilizadorEstado estado) {
        this.nome = nome;
        this.morada = morada;
        this.username = username;
        this.password = password;
        this.saldo = 0.0;
       // this.items = new ArrayList<>();
        this.leiloes = new ArrayList<>();
        this.logged=false;
        this.estado=estado;
    }

    public UtilizadorEstado getEstado() {
        return estado;
    }

    public void setEstado(UtilizadorEstado estado) {
        this.estado = estado;
    }

    public boolean addItem(Item item){
        if (itemsAVenda.contains(item))
            return false;
        this.itemsAVenda.add(item);
        return true;
    }

    public List<Item> getItemsSeguidos() {
        return itemsSeguidos;
    }

    public void setItemsSeguidos(List<Item> itemsSeguidos) {
        this.itemsSeguidos = itemsSeguidos;
    }
    
    public boolean addItemSeguido(Item item){
        if (itemsSeguidos.contains(item))
            return false;
        this.itemsSeguidos.add(item);
        return true;
    }
    public void addLeilao(Item leilao){
        this.leiloes.add(leilao);
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Double addSaldo(Double valor) {
        this.saldo += valor;
        return this.saldo;
    }
    public Double decSaldo(Double valor) {
        this.saldo -= valor;
        return this.saldo;
    }
    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Double getSaldo() {
        return saldo;
    }

    public List<Item> getItems() {
        return itemsAVenda;
    }

    public List<Item> getLeiloes() {
        return leiloes;
    }

    public void setLastAction(){
        lastAction=LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
    }
    public boolean lastActionMoreThan(long seconds){
        return LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond()
        - lastAction < seconds;
    }
    public long fromLastActionFromNoew(long now) {
        return now - lastAction;
    }
    public boolean isLogged() {
        return logged;
    }
    public void setLogged() {
        logged = true;
    }
    public void resetLogged() {
        logged = false;
    }
    
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

    public boolean aualizaDados(String nome, String morada) {
         this.nome = nome;
        this.morada = morada;
        return true;
    }

    public String getRazaoPedidoSuspensao() {
        return razaoPedidoSuspensao;
    }

    public void setRazaoPedidoSuspensao(String razaoPedidoSuspensao) {
        this.razaoPedidoSuspensao = razaoPedidoSuspensao;
    }
}
