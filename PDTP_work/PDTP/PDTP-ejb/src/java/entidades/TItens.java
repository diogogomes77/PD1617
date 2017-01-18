/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 *
 * @author diogo
 */
@Entity
@Table(name = "t_itens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TItens.findAll", query = "SELECT t FROM TItens t")
    , @NamedQuery(name = "TItens.findByDataInicio", query = "SELECT t FROM TItens t WHERE t.dataInicio = :dataInicio")
    , @NamedQuery(name = "TItens.findByDataFim", query = "SELECT t FROM TItens t WHERE t.dataFim = :dataFim")
    , @NamedQuery(name = "TItens.findByVendedor", query = "SELECT t FROM TItens t WHERE t.vendedor = :vendedor")
    , @NamedQuery(name = "TItens.findByDescricao", query = "SELECT t FROM TItens t WHERE t.descricao = :descricao")
    , @NamedQuery(name = "TItens.findByPrecoInicial", query = "SELECT t FROM TItens t WHERE t.precoInicial = :precoInicial")
    , @NamedQuery(name = "TItens.findByComprarJa", query = "SELECT t FROM TItens t WHERE t.comprarJa = :comprarJa")
    , @NamedQuery(name = "TItens.findByCategoria", query = "SELECT t FROM TItens t WHERE t.categoria = :categoria")
    , @NamedQuery(name = "TItens.findByEstado", query = "SELECT t FROM TItens t WHERE t.estado = :estado")
    , @NamedQuery(name = "TItens.findByComprador", query = "SELECT t FROM TItens t WHERE t.comprador = :comprador")
    , @NamedQuery(name = "TItens.findByItemID", query = "SELECT t FROM TItens t WHERE t.itemID = :itemID")})
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
    private List<TDenunciasItens> tDenunciasItensList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<TLicitacoes> tLicitacoesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<TitemsAVenda> titemsAVendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<TitemsSeguidos> titemsSeguidosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<TVendas> tVendasList;
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
    public List<TDenunciasItens> getTDenunciasItensList() {
        return tDenunciasItensList;
    }

    public void setTDenunciasItensList(List<TDenunciasItens> tDenunciasItensList) {
        this.tDenunciasItensList = tDenunciasItensList;
    }

    @XmlTransient
    public List<TLicitacoes> getTLicitacoesList() {
        return tLicitacoesList;
    }

    public void setTLicitacoesList(List<TLicitacoes> tLicitacoesList) {
        this.tLicitacoesList = tLicitacoesList;
    }

    @XmlTransient
    public List<TitemsAVenda> getTitemsAVendaList() {
        return titemsAVendaList;
    }

    public void setTitemsAVendaList(List<TitemsAVenda> titemsAVendaList) {
        this.titemsAVendaList = titemsAVendaList;
    }

    @XmlTransient
    public List<TitemsSeguidos> getTitemsSeguidosList() {
        return titemsSeguidosList;
    }

    public void setTitemsSeguidosList(List<TitemsSeguidos> titemsSeguidosList) {
        this.titemsSeguidosList = titemsSeguidosList;
    }

    @XmlTransient
    public List<TVendas> getTVendasList() {
        return tVendasList;
    }

    public void setTVendasList(List<TVendas> tVendasList) {
        this.tVendasList = tVendasList;
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
        return "entidades2.TItens[ itemID=" + itemID + " ]";
    }
    
}
