/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdtp;

/**
 *
 * venda é um leilao terminado com sucesso
 */
public class Venda {
    private Item item;
    private Utilizador comprador;
    private Double valor;
    private VendaEstados estado;

    public Venda(Item item, Utilizador comprador, Double valor) {
        this.item = item;
        this.comprador = comprador;
        this.valor = valor;
        this.estado = VendaEstados.ESPERA;
    }
    
}
