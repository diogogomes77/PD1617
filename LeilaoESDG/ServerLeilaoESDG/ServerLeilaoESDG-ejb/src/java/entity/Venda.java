
package entity;

/**
 *
 * venda é um leilao terminado com sucesso
 */
public class Venda {
    private Item item;
    private Membro comprador;
    private Double valor;
    private VendaEstados estado;

    public Venda(Item item, Membro comprador, Double valor) {
        this.item = item;
        this.comprador = comprador;
        this.valor = valor;
        this.estado = VendaEstados.ESPERA;
    }
    
}
