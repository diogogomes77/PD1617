
package entity;

import java.util.Date;
import java.util.TreeMap;


public class Leilao {
   
    private TreeMap<Double,Membro> licitacoes;
    private Date dataLimite;
    
    private Item item;
    private LeilaoEstados estado;
    private Venda venda;
    private Membro comprador;

    public Leilao(Item item,Date dataLimite, String descricao, Double comprarJa) {
       this.item = item;
        this.licitacoes = new TreeMap<>();
        this.dataLimite = dataLimite;

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
    public boolean comprarJa(Membro membro){
        if (!checkLicitador(membro))
            return false;
        this.comprador = membro;
        this.estado = LeilaoEstados.TERMINADA;
        return true;
    }
    
    public boolean cancelarVenda(Membro membro){
        if (checkLicitador(membro)){
            this.estado = LeilaoEstados.CANCELADA;
            return true;
        }
        return false;
    }
    private boolean checkLicitador(Membro licitador){
         return licitador != item.getVendedor();
    }
    private boolean checkValor(Double valor){
        return (valor<=licitacoes.lastKey());
    }
    
}
