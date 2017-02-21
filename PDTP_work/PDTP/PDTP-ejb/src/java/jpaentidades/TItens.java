/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaentidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import pdtp.ItemEstados;

/**
 *
 * @author diogo
 */
@Entity
@Table(name = "t_itens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TItens.findAll", query = "SELECT t FROM TItens t")
    , @NamedQuery(name = "TItens.findByItemid", query = "SELECT t FROM TItens t WHERE t.itemid = :itemid")
    , @NamedQuery(name = "TItens.findByCategoria", query = "SELECT t FROM TItens t WHERE t.categoria = :categoria")
    , @NamedQuery(name = "TItens.findByComprador", query = "SELECT t FROM TItens t WHERE t.comprador = :comprador")
    , @NamedQuery(name = "TItens.findByComprarja", query = "SELECT t FROM TItens t WHERE t.comprarja = :comprarja")
    , @NamedQuery(name = "TItens.findByDatafim", query = "SELECT t FROM TItens t WHERE t.datafim = :datafim")
    , @NamedQuery(name = "TItens.findByDatainicio", query = "SELECT t FROM TItens t WHERE t.datainicio = :datainicio")
    , @NamedQuery(name = "TItens.findByDescricao", query = "SELECT t FROM TItens t WHERE t.descricao = :descricao")
    , @NamedQuery(name = "TItens.findByEstado", query = "SELECT t FROM TItens t WHERE t.estado = :estado")
    , @NamedQuery(name = "TItens.findByPrecoinicial", query = "SELECT t FROM TItens t WHERE t.precoinicial = :precoinicial")})
public class TItens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemid")
    private Long itemid;
    @Size(max = 255)
    @Column(name = "categoria")
    private String categoria;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "comprarja")
    private Double comprarja;
    @Column(name = "licitacaomaxima")
    private Double licitacaomaxima;
    @Column(name = "datafim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datafim;
    @Column(name = "datainicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datainicio;
    @Size(max = 255)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 255)
    @Column(name = "estado")
    private ItemEstados estado;
    @Column(name = "precoinicial")
    private Double precoinicial;
    @OneToMany(mappedBy = "item")
    private Collection<TDenunciasItens> tDenunciasItensCollection;
    @OneToMany(mappedBy = "item")
    private Collection<TLicitacoes> tLicitacoesCollection;
    @OneToMany(mappedBy = "item")
    private Collection<TVendas> tVendasCollection;
    @JoinColumn(name = "vendedor", referencedColumnName = "username")
    @ManyToOne
    private TUtilizadores vendedor;
    @JoinColumn(name = "comprador", referencedColumnName = "username")
    @ManyToOne
    private TUtilizadores comprador;
    @JoinColumn(name = "venda", referencedColumnName = "vendaid")
    @ManyToOne
    private TVendas venda;

    public TItens() {
    }

    public TItens(Long itemid) {
        this.itemid = itemid;
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public TUtilizadores getComprador() {
        return comprador;
    }

    public void setComprador(TUtilizadores comprador) {
        this.comprador = comprador;
    }

    public Double getComprarja() {
        return comprarja;
    }

    public void setComprarja(Double comprarja) {
        this.comprarja = comprarja;
    }

    public Double getLicitacaomaxima() {
        return licitacaomaxima;
    }

    public void setLicitacaomaxima(Double licitacaomaxima) {
        this.licitacaomaxima = licitacaomaxima;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ItemEstados getEstado() {
        return estado;
    }

    public void setEstado(ItemEstados estado) {
        this.estado = estado;
    }

    public Double getPrecoinicial() {
        return precoinicial;
    }

    public void setPrecoinicial(Double precoinicial) {
        this.precoinicial = precoinicial;
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
    public Collection<TVendas> getTVendasCollection() {
        return tVendasCollection;
    }

    public void setTVendasCollection(Collection<TVendas> tVendasCollection) {
        this.tVendasCollection = tVendasCollection;
    }

    public TUtilizadores getVendedor() {
        return vendedor;
    }

    public void setVendedor(TUtilizadores vendedor) {
        this.vendedor = vendedor;
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
        hash += (itemid != null ? itemid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TItens)) {
            return false;
        }
        TItens other = (TItens) object;
        if ((this.itemid == null && other.itemid != null) || (this.itemid != null && !this.itemid.equals(other.itemid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaentidades.TItens[ itemid=" + itemid + " ]";
    }

}
