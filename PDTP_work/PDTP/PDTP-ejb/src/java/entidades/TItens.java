/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author diogo
 */
@MappedSuperclass
@Table(name = "t_itens")
@XmlRootElement
public class TItens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "dataInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    @Column(name = "dataFim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;
    @Size(max = 15)
    @Column(name = "vendedor")
    private String vendedor;
    @Size(max = 100)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precoInicial")
    private Double precoInicial;
    @Column(name = "comprarJa")
    private Double comprarJa;
    @Size(max = 15)
    @Column(name = "categoria")
    private String categoria;
    @Size(max = 15)
    @Column(name = "estado")
    private String estado;
    @Size(max = 15)
    @Column(name = "comprador")
    private String comprador;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemID")
    private Long itemID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<TDenunciasItens> tDenunciasItensCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<TLicitacoes> tLicitacoesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<TitemsAVenda> titemsAVendaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<TitemsSeguidos> titemsSeguidosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<TVendas> tVendasCollection;
    @JoinColumn(name = "venda", referencedColumnName = "vendaId")
    @ManyToOne
    private TVendas venda;

    public TItens() {
    }

    public TItens(Long itemID) {
        this.itemID = itemID;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoInicial() {
        return precoInicial;
    }

    public void setPrecoInicial(Double precoInicial) {
        this.precoInicial = precoInicial;
    }

    public Double getComprarJa() {
        return comprarJa;
    }

    public void setComprarJa(Double comprarJa) {
        this.comprarJa = comprarJa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    @XmlTransient
    public Collection<TDenunciasItens> getTDenunciasItensCollection() {
        return tDenunciasItensCollection;
    }

    public void setTDenunciasItensCollection(Collection<TDenunciasItens> tDenunciasItensCollection) {
        this.tDenunciasItensCollection = tDenunciasItensCollection;
    }

    @XmlTransient
    public Collection<TLicitacoes> getTLicitacoesCollection() {
        return tLicitacoesCollection;
    }

    public void setTLicitacoesCollection(Collection<TLicitacoes> tLicitacoesCollection) {
        this.tLicitacoesCollection = tLicitacoesCollection;
    }

    @XmlTransient
    public Collection<TitemsAVenda> getTitemsAVendaCollection() {
        return titemsAVendaCollection;
    }

    public void setTitemsAVendaCollection(Collection<TitemsAVenda> titemsAVendaCollection) {
        this.titemsAVendaCollection = titemsAVendaCollection;
    }

    @XmlTransient
    public Collection<TitemsSeguidos> getTitemsSeguidosCollection() {
        return titemsSeguidosCollection;
    }

    public void setTitemsSeguidosCollection(Collection<TitemsSeguidos> titemsSeguidosCollection) {
        this.titemsSeguidosCollection = titemsSeguidosCollection;
    }

    @XmlTransient
    public Collection<TVendas> getTVendasCollection() {
        return tVendasCollection;
    }

    public void setTVendasCollection(Collection<TVendas> tVendasCollection) {
        this.tVendasCollection = tVendasCollection;
    }

    public TVendas getVenda() {
        return venda;
    }

    public void setVenda(TVendas venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemID != null ? itemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TItens)) {
            return false;
        }
        TItens other = (TItens) object;
        if ((this.itemID == null && other.itemID != null) || (this.itemID != null && !this.itemID.equals(other.itemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TItens[ itemID=" + itemID + " ]";
    }
    
}
