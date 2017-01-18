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
@Table(name = "t_mensagens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMensagens.findAll", query = "SELECT t FROM TMensagens t")
    , @NamedQuery(name = "TMensagens.findByEstado", query = "SELECT t FROM TMensagens t WHERE t.estado = :estado")
    , @NamedQuery(name = "TMensagens.findByTexto", query = "SELECT t FROM TMensagens t WHERE t.texto = :texto")
    , @NamedQuery(name = "TMensagens.findByAssunto", query = "SELECT t FROM TMensagens t WHERE t.assunto = :assunto")
    , @NamedQuery(name = "TMensagens.findByData", query = "SELECT t FROM TMensagens t WHERE t.data = :data")
    , @NamedQuery(name = "TMensagens.findByIdMensagem", query = "SELECT t FROM TMensagens t WHERE t.idMensagem = :idMensagem")})
public class TMensagens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 15)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "texto")
    private String texto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "assunto")
    private String assunto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mensagem")
    private Integer idMensagem;
    @JoinColumn(name = "destinatario", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private TUtilizadores destinatario;
    @JoinColumn(name = "remetente", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private TUtilizadores remetente;

    public TMensagens() {
    }

    public TMensagens(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public TMensagens(Integer idMensagem, String texto, String assunto, Date data) {
        this.idMensagem = idMensagem;
        this.texto = texto;
        this.assunto = assunto;
        this.data = data;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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

    public Integer getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
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
        return "entidades2.TMensagens[ idMensagem=" + idMensagem + " ]";
    }
    
}
