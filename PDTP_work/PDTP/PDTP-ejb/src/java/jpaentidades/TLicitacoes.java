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
    , @NamedQuery(name = "TLicitacoes.findByIdLicitacao", query = "SELECT t FROM TLicitacoes t WHERE t.idLicitacao = :idLicitacao")
    , @NamedQuery(name = "TLicitacoes.findByTimestamp", query = "SELECT t FROM TLicitacoes t WHERE t.timestamp = :timestamp")
    , @NamedQuery(name = "TLicitacoes.findByValor", query = "SELECT t FROM TLicitacoes t WHERE t.valor = :valor")})
public class TLicitacoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_licitacao")
    private Integer idLicitacao;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @JoinColumn(name = "item", referencedColumnName = "itemid")
    @ManyToOne
    private TItens item;
    @JoinColumn(name = "licitador", referencedColumnName = "username")
    @ManyToOne
    private TUtilizadores licitador;

    public TLicitacoes() {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        this.timestamp = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());        
    }

    public Integer getIdLicitacao() {
        return idLicitacao;
    }

    public void setIdLicitacao(Integer idLicitacao) {
        this.idLicitacao = idLicitacao;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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
        return "jpaentidades.TLicitacoes[ idLicitacao=" + idLicitacao + " ]";
    }
    
}
