/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaentidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NotNull
    @JoinColumn(name = "utilizador", referencedColumnName = "username")
    @ManyToOne
    private TUtilizadores utilizador;
    @JoinColumn(name = "item", referencedColumnName = "itemid")
    @ManyToOne
    private TItens item;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    public TitemsPorPagar() {
        this.data = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public TitemsPorPagar(Integer id) {
        this.id = id;
        this.data = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public TitemsPorPagar(TUtilizadores utilizador, TItens item) {
        this.utilizador = utilizador;
        this.item = item;
    }

    public TUtilizadores getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(TUtilizadores utilizador) {
        this.utilizador = utilizador;
    }

    public TItens getItem() {
        return item;
    }

    public void setItem(TItens item) {
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
        return "jpaentidades.TitemsPorPagar[ id=" + id + " ]";
    }

}
