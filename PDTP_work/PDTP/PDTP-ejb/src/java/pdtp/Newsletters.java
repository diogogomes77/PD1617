/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdtp;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import javax.ejb.EJB;
import beans.DAOLocal;
import jpaentidades.TNewsletters;
import jpafacades.TNewslettersFacade;

/**
 *
 * @author diogo
 */
public class Newsletters {//extends TNewsletters{
    
    private Integer idNewsletter;
    private String assunto;
    private Date data;
    private long timestamp;
    private String newsletter;
    
    @EJB
    private DAOLocal DAO;
//    @EJB
//    private TNewslettersFacade newslettersFacade;
    

    public Newsletters(String assunto, String newsletter) {
        //super(assunto,newsletter);
        this.assunto = assunto;
        this.newsletter = newsletter;
        this.timestamp = LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
        
        TNewsletters news = new TNewsletters(assunto,newsletter);
    }

    public String getAssunto() {
        return assunto;
    }

    public Date getData() {
        return data;
    }

    public String getNewsletter() {
        return newsletter;
    }
    
}
