/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaentidades;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eugenio
 */
@Entity
@Table(name = "t_newsletters")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TNewsletters.findAll", query = "SELECT t FROM TNewsletters t")
    , @NamedQuery(name = "TNewsletters.findByIdMensagem", query = "SELECT t FROM TNewsletters t WHERE t.idNewsletter = :idNewsletter")
    , @NamedQuery(name = "TNewsletters.findByAssunto", query = "SELECT t FROM TNewsletters t WHERE t.assunto = :assunto")
    , @NamedQuery(name = "TNewsletters.findByData", query = "SELECT t FROM TNewsletters t WHERE t.data = :data")
    , @NamedQuery(name = "TNewsletters.findByTexto", query = "SELECT t FROM TNewsletters t WHERE t.newsletter = :newsletter")})
public class TNewsletters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "addressGen",sequenceName = "t_newsletters_id_seq")
                    //   allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "addressGen")
    
   // @Basic(optional = false)
    @Column(name = "id_newsletter", updatable = false, nullable = false)
    private Integer idNewsletter;
    @Size(max = 255)
    @Column(name = "assunto")
    private String assunto;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Size(max = 255)
    @Column(name = "newsletter")
    private String newsletter;

    public TNewsletters() {
    }

    public TNewsletters(String assunto, String newsletter) {
        this.assunto = assunto;
        this.newsletter = newsletter;
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        this.data = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public TNewsletters(Integer idNewsletter) {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        this.data = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        this.idNewsletter = idNewsletter;
    }

    public Integer getIdMensagem() {
        return idNewsletter;
    }

    public void setIdMensagem(Integer idNewsletter) {
        this.idNewsletter = idNewsletter;
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


    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNewsletter != null ? idNewsletter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TNewsletters)) {
            return false;
        }
        TNewsletters other = (TNewsletters) object;
        if ((this.idNewsletter == null && other.idNewsletter != null) || (this.idNewsletter != null && !this.idNewsletter.equals(other.idNewsletter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaentidades.TNewsletters[ idNewsletter=" + idNewsletter + " assunto = '"+assunto+"']";
    }
    
}
