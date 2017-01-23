/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webentidades;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@Entity
@Table(name = "t_licitacoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TLicitacoes.findAll", query = "SELECT t FROM TLicitacoes t")
    , @NamedQuery(name = "TLicitacoes.findByTimestamp", query = "SELECT t FROM TLicitacoes t WHERE t.timestamp = :timestamp")
    , @NamedQuery(name = "TLicitacoes.findByValor", query = "SELECT t FROM TLicitacoes t WHERE t.valor = :valor")
    , @NamedQuery(name = "TLicitacoes.findByIdLicitacao", query = "SELECT t FROM TLicitacoes t WHERE t.idLicitacao = :idLicitacao")})
public class TLicitacoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_licitacao")
    private Integer idLicitacao;
    @JoinColumn(name = "item", referencedColumnName = "itemID")
    @ManyToOne(optional = false)
    private TItens item;
    @JoinColumn(name = "licitador", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private TUtilizadores licitador;

    public TLicitacoes() {
    }

    public TLicitacoes(Integer idLicitacao) {
        this.idLicitacao = idLicitacao;
    }

    public TLicitacoes(Integer idLicitacao, Date timestamp, double valor) {
        this.idLicitacao = idLicitacao;
        this.timestamp = timestamp;
        this.valor = valor;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Integer getIdLicitacao() {
        return idLicitacao;
    }

    public void setIdLicitacao(Integer idLicitacao) {
        this.idLicitacao = idLicitacao;
    }

    public TItens getItem() {
        return item;
    }

    public void setItem(TItens item) {
        this.item = item;
    }

    public TUtilizadores getLicitador() {
        return licitador;
    }

    public void setLicitador(TUtilizadores licitador) {
        this.licitador = licitador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLicitacao != null ? idLicitacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TLicitacoes)) {
            return false;
        }
        TLicitacoes other = (TLicitacoes) object;
        if ((this.idLicitacao == null && other.idLicitacao != null) || (this.idLicitacao != null && !this.idLicitacao.equals(other.idLicitacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TLicitacoes[ idLicitacao=" + idLicitacao + " ]";
    }
    
}
