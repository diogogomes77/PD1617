/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdtp;

/**
 *
 * Item para leilao
 */
public class Item {
    private Utilizador vendedor;
    private String descricao;
    private Double comprarJa;

    public Item(Utilizador vendedor, String descricao, Double comprarJa) {
        this.vendedor = vendedor;
        this.descricao = descricao;
        this.comprarJa = comprarJa;
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
