
package pdtp;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;

public class Item implements Serializable {
    private TreeMap<Double,Utilizador> licitacoes;
    private Date dataLimite;
     private Utilizador vendedor;
    private String descricao;
    private Double comprarJa;
    
    //private Item item;
    private ItemEstados estado;
    private Venda venda;
    private Utilizador comprador;

    public Item(Utilizador vendedor, Double comprarJa,Date dataLimite, String descricao) {
       this.vendedor = vendedor;
        this.descricao = descricao;
        this.comprarJa = comprarJa;
        this.licitacoes = new TreeMap<>();
        this.dataLimite = dataLimite;

        this.estado = ItemEstados.INICIADA;
    }
    public boolean addLicitacao(Utilizador licitador,Double valor){
        if (!checkLicitador(licitador))
            return false;
        if (!checkValor(valor))
            return false;
        this.licitacoes.put(valor, licitador);
        return true;
    }
    public boolean comprarJa(Utilizador membro){
        if (!checkLicitador(membro))
            return false;
        this.comprador = membro;
        this.estado = ItemEstados.TERMINADA;
        return true;
    }
    
    public boolean cancelarVenda(Utilizador membro){
        if (checkLicitador(membro)){
            this.estado = ItemEstados.CANCELADA;
            return true;
        }
        return false;
    }
    private boolean checkLicitador(Utilizador licitador){
         return licitador != vendedor;
    }
    private boolean checkValor(Double valor){
        return (valor<=licitacoes.lastKey());
    }
    public Utilizador getVendedor() {
        return vendedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getComprarJa() {
        return comprarJa;
    }
}
