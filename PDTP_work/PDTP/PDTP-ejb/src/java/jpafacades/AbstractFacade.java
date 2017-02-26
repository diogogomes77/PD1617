/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import beans.Leiloeira;
import beans.LeiloeiraLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import beans.DAOLocal;

/**
 *
 * @author diogo
 */
public abstract class AbstractFacade<T> {

   // EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDTP-ejbPU");

    @EJB
    private LeiloeiraLocal leiloeira;
    
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Set Class Entity");
        this.entityClass = entityClass;
    }

   // protected abstract EntityManager getEntityManager();
//    public EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }
    
    public void create(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Create Entity "+entity);
        leiloeira.getDAO().getEntityManager().getTransaction().begin();
        leiloeira.getDAO().create(entity);
        leiloeira.getDAO().getEntityManager().getTransaction().commit();
    }

    public void edit(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Edit Entity "+entity);
       // getEntityManager().merge(entity);
        leiloeira.getDAO().edit(entity);
    }

    public void remove(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.FINE, "Remove Entity "+entity);
        //getEntityManager().remove(getEntityManager().merge(entity));
        leiloeira.getDAO().remove(entity);
    }

    public T find(Object id) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find Entity "+id);
        //return getEntityManager().find(entityClass, id);
        return (T)leiloeira.getDAO().find(entityClass,id);
    }

    public List<T> findAll() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find All Entity");
        //javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        //cq.select(cq.from(entityClass));
        //return getEntityManager().createQuery(cq).getResultList();
        return (List<T>)leiloeira.getDAO().findAll(entityClass);
    }

    public List<T> findRange(int[] range) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find Range of Entity");
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        q.setMaxResults(range[1] - range[0] + 1);
//        q.setFirstResult(range[0]);
//        //return q.getResultList();
        
        return (List<T>)leiloeira.getDAO().findRange(entityClass,range);
    }

    public int count() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Count Entity");
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
//        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        return ((Long) q.getSingleResult()).intValue();
        return leiloeira.getDAO().count(entityClass);
    }
    
}
