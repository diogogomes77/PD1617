/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webentidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@Entity
@Table(name = "t_itemsPorPagar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TitemsPorPagar.findAll", query = "SELECT t FROM TitemsPorPagar t")
    , @NamedQuery(name = "TitemsPorPagar.findByUtilizador", query = "SELECT t FROM TitemsPorPagar t WHERE t.utilizador = :utilizador")
    , @NamedQuery(name = "TitemsPorPagar.findByItem", query = "SELECT t FROM TitemsPorPagar t WHERE t.item = :item")
    , @NamedQuery(name = "TitemsPorPagar.findById", query = "SELECT t FROM TitemsPorPagar t WHERE t.id = :id")})
public class TitemsPorPagar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "utilizador")
    private String utilizador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "item")
    private long item;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public TitemsPorPagar() {
    }

    public TitemsPorPagar(Integer id) {
        this.id = id;
    }

    public TitemsPorPagar(Integer id, String utilizador, long item) {
        this.id = id;
        this.utilizador = utilizador;
        this.item = item;
    }

    public String getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    public long getItem() {
        return item;
    }

    public void setItem(long item) {
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TitemsPorPagar)) {
            return false;
        }
        TitemsPorPagar other = (TitemsPorPagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TitemsPorPagar[ id=" + id + " ]";
    }
    
}
