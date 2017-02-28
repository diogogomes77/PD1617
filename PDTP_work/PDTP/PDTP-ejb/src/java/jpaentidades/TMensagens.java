/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaentidades;

import beans.MensagemEstado;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@Entity
@Table(name = "t_mensagens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMensagens.findAll", query = "SELECT t FROM TMensagens t")
    , @NamedQuery(name = "TMensagens.findByIdMensagem", query = "SELECT t FROM TMensagens t WHERE t.idMensagem = :idMensagem")
    , @NamedQuery(name = "TMensagens.findByDestinatario", query = "SELECT t FROM TMensagens t WHERE t.destinatario = :username")
    , @NamedQuery(name = "TMensagens.countFindByDestinatario", query = "SELECT count(t.idMensagem) FROM TMensagens t WHERE t.destinatario = :username")
    , @NamedQuery(name = "TMensagens.findByAssunto", query = "SELECT t FROM TMensagens t WHERE t.assunto = :assunto")
    , @NamedQuery(name = "TMensagens.findByData", query = "SELECT t FROM TMensagens t WHERE t.data = :data")
    , @NamedQuery(name = "TMensagens.findByEstado", query = "SELECT t FROM TMensagens t WHERE t.estado = :estado")
    , @NamedQuery(name = "TMensagens.findByTexto", query = "SELECT t FROM TMensagens t WHERE t.texto = :texto")})
public class TMensagens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mensagem")
    private Integer idMensagem;
    @Size(max = 255)
    @Column(name = "assunto")
    private String assunto;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "estado")
    private MensagemEstado estado;
    @Size(max = 255)
    @Column(name = "texto")
    private String texto;
    @JoinColumn(name = "destinatario", referencedColumnName = "username")
    @ManyToOne
    private TUtilizadores destinatario;
    @JoinColumn(name = "remetente", referencedColumnName = "username")
    @ManyToOne
    private TUtilizadores remetente;

    public TMensagens() {
        Date in = new Date();
        this.data = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public TMensagens(Integer idMensagem) {
        this.idMensagem = idMensagem;
        this.data = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public Integer getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public MensagemEstado getEstado() {
        return estado;
    }

    public void setEstado(MensagemEstado estado) {
        this.estado = estado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public TUtilizadores getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(TUtilizadores destinatario) {
        this.destinatario = destinatario;
    }

    public TUtilizadores getRemetente() {
        return remetente;
    }

    public void setRemetente(TUtilizadores remetente) {
        this.remetente = remetente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMensagem != null ? idMensagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMensagens)) {
            return false;
        }
        TMensagens other = (TMensagens) object;
        if ((this.idMensagem == null && other.idMensagem != null) || (this.idMensagem != null && !this.idMensagem.equals(other.idMensagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaentidades.TMensagens[ idMensagem=" + idMensagem + " assunto = '"+assunto+"']";
    }
    
}
