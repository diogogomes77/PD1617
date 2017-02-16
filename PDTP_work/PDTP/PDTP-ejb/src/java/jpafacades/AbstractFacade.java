/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author diogo
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Set Class Entity");
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Create Entity "+entity);
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Edit Entity "+entity);
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.FINE, "Remove Entity "+entity);
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find Entity "+id);
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find All Entity");
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find Range of Entity");
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Count Entity");
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
