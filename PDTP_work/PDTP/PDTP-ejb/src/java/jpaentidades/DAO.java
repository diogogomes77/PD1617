/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaentidades;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
//import javax.ejb.TransactionManagement;
//import static javax.ejb.TransactionManagementType.BEAN;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author eugenio
 */
//@TransactionManagement(BEAN)
@Singleton
public class DAO implements DAOLocal {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDTP-ejbPU");

    //@PersistenceContext(unitName = "PDTP-ejbPU")
    
    private EntityManager em = null;//emf.createEntityManager();

//    @Resource
//    private UserTransaction utx;
    @Override
    public EntityManager getEntityManager() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "getEntityManager from DAO");
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("PDTP-ejbPU");
        }
        if (em == null) {
            em = emf.createEntityManager();
        }
        return em;
    }
    
    @PostConstruct
     public void loadstate() {
         Logger.getLogger(getClass().getName()).log(Level.INFO, "A abrir as ligações");
         this.getEntityManager();
     }

    @PreDestroy
    public void destruct() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fechar as ligações");
        //em.getTransaction().commit();
        em.close();
        emf.close();
        em = null;
        emf = null;
        
    }

    @Override
    public void persist(Object object) {
        /* Add this to the deployment descriptor of this module (e.g. web.xml, ejb-jar.xml):
         * <persistence-context-ref>
         * <persistence-context-ref-name>persistence/LogicalName</persistence-context-ref-name>
         * <persistence-unit-name>WebApplication2PU</persistence-unit-name>
         * </persistence-context-ref>
         * <resource-ref>
         * <res-ref-name>UserTransaction</res-ref-name>
         * <res-type>javax.transaction.UserTransaction</res-type>
         * <res-auth>Container</res-auth>
         * </resource-ref> */
        try {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "A persistir um novo registo");
            Context ctx = new InitialContext();
            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            utx.begin();
            em.joinTransaction();
            //em.getTransaction().begin();
            em.persist(object);
            utx.commit();
            //em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
