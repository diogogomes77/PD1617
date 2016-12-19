
package entity;

import java.util.Date;
import java.util.TreeMap;


public class Leilao {
    private Vendedor vendedor;
    private TreeMap<Double,Membro> licitacoes;
    private Date dataLimite;
    private String descricao;
    private Double comprarJa;
    private LeilaoEstados estado;
    private Membro comprador;

    public Leilao(Vendedor vendedor, Date dataLimite, String descricao, Double comprarJa) {
        this.vendedor = vendedor;
        this.licitacoes = new TreeMap<>();
        this.dataLimite = dataLimite;
        this.descricao = descricao;
        this.comprarJa = comprarJa;
        this.estado = LeilaoEstados.INICIADA;
    }
    public boolean addLicitacao(Membro licitador,Double valor){
        if (!checkLicitador(licitador))
            return false;
        if (!checkValor(valor))
            return false;
        this.licitacoes.put(valor, licitador);
        return true;
    }
    public Venda comprarJa(Membro membro){
        if (!checkLicitador(membro))
            return false;
        this.comprador = membro;
        this.estado = LeilaoEstados.TERMINADA;
        return new Venda();
    }
    public boolean cancelarVenda(Membro membro){
        if (checkLicitador(membro)){
            this.estado = LeilaoEstados.CANCELADA;
            return true;
        }
        return false;
    }
    private boolean checkLicitador(Membro licitador){
         return licitador != vendedor;
    }
    private boolean checkValor(Double valor){
        return (valor<=licitacoes.lastKey());
    }
    
}
