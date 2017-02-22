/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaentidades;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eugenio
 */
@Entity
@Table(name = "t_categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCategoria.findAll", query = "SELECT t FROM TCategoria t")
    , @NamedQuery(name = "TCategoria.findById", query = "SELECT t FROM TCategoria t WHERE t.idCategoria = :idCategoria")
    , @NamedQuery(name = "TCategoria.findByNome", query = "SELECT t FROM TCategoria t WHERE t.nome = :nome")})
public class TCategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria")
    private Integer idCategoria;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;

    public TCategoria() {
    }

    public TCategoria(String nome) {
        this.nome = nome;
    }


    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCategoria)) {
            return false;
        }
        TCategoria other = (TCategoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaentidades.TNewsletters[ idCategoria=" + idCategoria + " nome = '"+nome+"']";
    }
    
}
