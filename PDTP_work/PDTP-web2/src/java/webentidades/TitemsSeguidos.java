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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@Entity
@Table(name = "t_itemsSeguidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TitemsSeguidos.findAll", query = "SELECT t FROM TitemsSeguidos t")
    , @NamedQuery(name = "TitemsSeguidos.findById", query = "SELECT t FROM TitemsSeguidos t WHERE t.id = :id")})
public class TitemsSeguidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "item", referencedColumnName = "itemID")
    @ManyToOne(optional = false)
    private TItens item;
    @JoinColumn(name = "utilizador", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private TUtilizadores utilizador;

    public TitemsSeguidos() {
    }

    public TitemsSeguidos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TItens getItem() {
        return item;
    }

    public void setItem(TItens item) {
        this.item = item;
    }

    public TUtilizadores getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(TUtilizadores utilizador) {
        this.utilizador = utilizador;
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
        if (!(object instanceof TitemsSeguidos)) {
            return false;
        }
        TitemsSeguidos other = (TitemsSeguidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TitemsSeguidos[ id=" + id + " ]";
    }
    
}
