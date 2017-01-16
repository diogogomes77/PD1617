/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author diogo
 */
@MappedSuperclass
@Table(name = "t_vendas")
@XmlRootElement
public class TVendas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "comprador")
    private String comprador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;
    @Size(max = 15)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "tipo")
    private String tipo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "vendaId")
    private Long vendaId;
    @JoinColumn(name = "item", referencedColumnName = "itemID")
    @ManyToOne(optional = false)
    private TItens item;
    @OneToMany(mappedBy = "venda")
    private Collection<TItens> tItensCollection;

    public TVendas() {
    }

    public TVendas(Long vendaId) {
        this.vendaId = vendaId;
    }

    public TVendas(Long vendaId, String comprador, double valor, String tipo) {
        this.vendaId = vendaId;
        this.comprador = comprador;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getVendaId() {
        return vendaId;
    }

    public void setVendaId(Long vendaId) {
        this.vendaId = vendaId;
    }

    public TItens getItem() {
        return item;
    }

    public void setItem(TItens item) {
        this.item = item;
    }

    @XmlTransient
    public Collection<TItens> getTItensCollection() {
        return tItensCollection;
    }

    public void setTItensCollection(Collection<TItens> tItensCollection) {
        this.tItensCollection = tItensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendaId != null ? vendaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TVendas)) {
            return false;
        }
        TVendas other = (TVendas) object;
        if ((this.vendaId == null && other.vendaId != null) || (this.vendaId != null && !this.vendaId.equals(other.vendaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TVendas[ vendaId=" + vendaId + " ]";
    }
    
}
