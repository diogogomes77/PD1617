/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
@Table(name = "t_denuncias_itens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDenunciasItens.findAll", query = "SELECT t FROM TDenunciasItens t")
    , @NamedQuery(name = "TDenunciasItens.findByData", query = "SELECT t FROM TDenunciasItens t WHERE t.data = :data")
    , @NamedQuery(name = "TDenunciasItens.findByRazao", query = "SELECT t FROM TDenunciasItens t WHERE t.razao = :razao")
    , @NamedQuery(name = "TDenunciasItens.findByIdDenunciaItem", query = "SELECT t FROM TDenunciasItens t WHERE t.idDenunciaItem = :idDenunciaItem")})
public class TDenunciasItens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "razao")
    private String razao;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_denuncia_item")
    private Integer idDenunciaItem;
    @JoinColumn(name = "item", referencedColumnName = "itemID")
    @ManyToOne(optional = false)
    private TItens item;
    @JoinColumn(name = "denunciador", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private TUtilizadores denunciador;

    public TDenunciasItens() {
    }

    public TDenunciasItens(Integer idDenunciaItem) {
        this.idDenunciaItem = idDenunciaItem;
    }

    public TDenunciasItens(Integer idDenunciaItem, Date data, String razao) {
        this.idDenunciaItem = idDenunciaItem;
        this.data = data;
        this.razao = razao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public Integer getIdDenunciaItem() {
        return idDenunciaItem;
    }

    public void setIdDenunciaItem(Integer idDenunciaItem) {
        this.idDenunciaItem = idDenunciaItem;
    }

    public TItens getItem() {
        return item;
    }

    public void setItem(TItens item) {
        this.item = item;
    }

    public TUtilizadores getDenunciador() {
        return denunciador;
    }

    public void setDenunciador(TUtilizadores denunciador) {
        this.denunciador = denunciador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDenunciaItem != null ? idDenunciaItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDenunciasItens)) {
            return false;
        }
        TDenunciasItens other = (TDenunciasItens) object;
        if ((this.idDenunciaItem == null && other.idDenunciaItem != null) || (this.idDenunciaItem != null && !this.idDenunciaItem.equals(other.idDenunciaItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades2.TDenunciasItens[ idDenunciaItem=" + idDenunciaItem + " ]";
    }
    
}
