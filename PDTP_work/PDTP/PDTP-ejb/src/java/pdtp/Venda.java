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
    
    /**
     *
     * @param item
     * @param comprador
     * @param valor
     */
    public Venda(Item item, Utilizador comprador, Double valor) {
        this.item = item;
        this.comprador = comprador;
        this.valor = valor;
        this.estado = VendaEstados.ESPERA;
         this.tipo = VendaTipo.COMPRAJA;
    }

    /**
     *
     * @param lic
     */
    public Venda(Licitacao lic) {
        this.item = lic.getItem();
        this.comprador = lic.getLicitador();
        this.valor = lic.getValor();
        this.estado = VendaEstados.ESPERA;
        this.tipo = VendaTipo.LICITACAO;
    }

    /**
     *
     * @return
     */
    public Item getItem() {
        return item;
    }

    /**
     *
     * @return
     */
    public Double getValor() {
        return valor;
    }

    /**
     *
     * @return
     */
    public VendaEstados getEstado() {
        return estado;
    }

    /**
     *
     * @return
     */
    public VendaTipo getTipo() {
        return tipo;
    }

    /**
     *
     * @return
     */
    public boolean concluirVenda(){
        if (this.tipo==VendaTipo.COMPRAJA){
            this.comprador.pagarJaItem(item);
            return true;
        }
        if (this.tipo==VendaTipo.LICITACAO){
            this.comprador.pagarItemLicitacao(item);
            return true;
        } 
        return false;
    }
}
