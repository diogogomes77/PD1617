
package entity;

/**
 *
 * Item para leilao
 */
public class Item {
    private Membro vendedor;
    private String descricao;
    private Double comprarJa;

    public Item(Membro vendedor, String descricao, Double comprarJa) {
        this.vendedor = vendedor;
        this.descricao = descricao;
        this.comprarJa = comprarJa;
    }

    public Membro getVendedor() {
        return vendedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getComprarJa() {
        return comprarJa;
    }
    
    
}
