/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdtp;

import java.io.Serializable;

/**
 *
 * venda é um leilao terminado com sucesso
 */
public class Venda implements Serializable {
    private Item item;
    private Utilizador comprador;
    private Double valor;
    private VendaEstados estado;
    private VendaTipo tipo;
    
    public Venda(Item item, Utilizador comprador, Double valor) {
        this.item = item;
        this.comprador = comprador;
        this.valor = valor;
        this.estado = VendaEstados.ESPERA;
         this.tipo = VendaTipo.COMPRAJA;
    }

    public Venda(Licitacao lic) {
        this.item = lic.getItem();
        this.comprador = lic.getLicitador();
        this.valor = lic.getValor();
        this.estado = VendaEstados.ESPERA;
        this.tipo = VendaTipo.LICITACAO;
    }

    public Item getItem() {
        return item;
    }

    public Double getValor() {
        return valor;
    }

    public VendaEstados getEstado() {
        return estado;
    }

    public VendaTipo getTipo() {
        return tipo;
    }
    
}
