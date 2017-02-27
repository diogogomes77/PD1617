/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import beans.LeiloeiraLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import beans.DAOLocal;
import jpaentidades.TUtilizadores;

/**
 *
 * @author diogo
 */
@Singleton
public class TUtilizadoresFacade {//extends AbstractFacade<TUtilizadores> {

//    @EJB
//    private DAOLocal DAO;

//    @Override
//    public EntityManager getEntityManager() {
//        return DAO.getEntityManager();
//    }

//    public TUtilizadoresFacade() {
//        super(TUtilizadores.class);
//    }
    
    @EJB
    LeiloeiraLocal leiloeira;
     
    public void create(TUtilizadores entity) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Create Entity "+entity);
       leiloeira.registaUtilizador(entity.getNome(),entity.getMorada(), entity.getUsername(), entity.getPassword());
    }

    public void edit(TUtilizadores entity) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Edit Entity "+entity);
       // getEntityManager().merge(entity);
        leiloeira.getDAO().edit(entity);
    }

    public void remove(TUtilizadores entity) {
        Logger.getLogger(getClass().getName()).log(Level.FINE, "Remove Entity "+entity);
        //getEntityManager().remove(getEntityManager().merge(entity));
        leiloeira.getDAO().remove(entity);
    }

    public TUtilizadores find(Object id) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find Entity "+id);
        //return getEntityManager().find(entityClass, id);
        return (TUtilizadores)leiloeira.getDAO().find(TUtilizadores.class,id);
    }

    public List<Object> findAll() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find All Entity");
        //javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        //cq.select(cq.from(entityClass));
        //return getEntityManager().createQuery(cq).getResultList();
        return (List<Object>) leiloeira.getDAO().findAll(TUtilizadores.class);
    }

    public List<Object> findRange(int[] range) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find Range of Entity");
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        q.setMaxResults(range[1] - range[0] + 1);
//        q.setFirstResult(range[0]);
//        //return q.getResultList();
        
        return (List<Object>) leiloeira.getDAO().findRange(TUtilizadores.class,range);
    }

    public int count() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Count Entity");
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
//        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        return ((Long) q.getSingleResult()).intValue();
        return leiloeira.getDAO().count(TUtilizadores.class);
    }
}
