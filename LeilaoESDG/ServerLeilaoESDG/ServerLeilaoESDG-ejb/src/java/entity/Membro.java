
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * sao os inscritos no sistema que podem criar leiloes e fazer licitacoes
 */
public class Membro {
    private String nome;
    private String morada;
    private String username;
    private String password;
    private Double saldo;
    private List<Item> items;
    private List<Leilao> leiloes;
    
    public Membro(String nome, String morada, String username, String password) {
        this.nome = nome;
        this.morada = morada;
        this.username = username;
        this.password = password;
        this.saldo = 0.0;
        this.items = new ArrayList<>();
        this.leiloes = new ArrayList<>();
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
    
    
    
}
