
package pdtp;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;


public class Utilizador {
    private String nome;
    private String morada;
    private String username;
    private String password;
    private Double saldo;
    private List<Item> items;
    private List<Leilao> leiloes;
    
    public Utilizador(String nome, String morada, String username, String password) {
        this.nome = nome;
        this.morada = morada;
        this.username = username;
        this.password = password;
        this.saldo = 0.0;
        this.items = new ArrayList<>();
        this.leiloes = new ArrayList<>();
        logged=true;
    }

    public void addItem(Item item){
        this.items.add(item);
    }
    public void addLeilao(Leilao leilao){
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

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
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
        return items;
    }

    public List<Leilao> getLeiloes() {
        return leiloes;
    }
    
    String name;
    int score;
    int attempts;
    boolean advised;
    boolean logged;
    long lastAction;
    
    public Utilizador(String name){
        this.name=name;
        this.advised=false;
        lastAction=LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
        logged=true;
    }
    
    public String getname() {
        return name;
    }

    public int getAttempts(){
        return attempts;
    }
    public void addScore(){
        score++;
    }
    public void addAttempts(){
        attempts++;
    }
    public boolean advised(){
        return advised;
    }
    public void resetAdvised(){
        advised = false;
    }
    public void setAdvised(){
        advised=true;
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
    @Override
    public String toString(){
        return name + ": Pontos="+score+
                " Tent="+attempts+
                " ("+(logged?"logado":"nao logado") + ")";
    }
    @Override
    public boolean equals(Object x){
        if(x==null)
            return false;
        if(getClass() != x.getClass())
            return false;
        Utilizador j = (Utilizador) x;
        return name.compareToIgnoreCase(j.name) == 0;
    }
    @Override
    public int hashCode(){
        return name.hashCode();
    }


  
}
