/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import beans.DAOLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 *
 * @author diogo
 */
public abstract class AbstractFacade<T> {

    // EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDTP-ejbPU");
    @EJB
    private DAOLocal DAO;

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
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Create Entity " + entity);
        DAO.create(entity);
    }

    public void edit(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Edit Entity " + entity);
        DAO.edit(entity);
    }

    public void remove(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.FINE, "Remove Entity " + entity);
        DAO.remove(entity);
    }

    public T find(Object id) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find Entity " + id);
        return (T) DAO.find(entityClass, id);
    }

    public List<T> findAll() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find All Entity " + entityClass.getName());
        return (List<T>) DAO.findAll(entityClass);
    }

    public List<T> findByNamedQuery(String nameQuery, int maxResult) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find All Entity by namedQuery 1 arg" + entityClass.getName() + " -> " + nameQuery);
        return (List<T>) DAO.findByNamedQuery(entityClass, nameQuery, maxResult);
    }

    public List<T> findByNamedQuery(String nameQuery, String nameParam, Object valeu) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find All Entity by namedQuery 1 arg" + entityClass.getName() + " -> " + nameQuery);
        return (List<T>) DAO.findByNamedQuery(entityClass, nameQuery, nameParam, valeu);
    }

    public List<T> findByNamedQuery(String nameQuery, String nameParam1, Object valeu1, String nameParam2, Object valeu2) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find All Entity by namedQuery 2 args" + entityClass.getName() + " -> " + nameQuery);
        return (List<T>) DAO.findByNamedQuery(entityClass, nameQuery, nameParam1, valeu1, nameParam2, valeu2);
    }

    public List<T> findRange(int[] range) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find Range of Entity");
        return (List<T>) DAO.findRange(entityClass, range);
    }

    public List<T> findRangeByNamedQuery(int[] range, String nameQuery, String nameParam, Object valeu) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Find Range of Entity by named query " + entityClass.getName() + " -> " + nameQuery);
        return (List<T>) DAO.findRangeByNamedQuery(entityClass, range, nameQuery, nameParam, valeu);
    }

    public int count() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Count Entity " + entityClass.getName());
        return DAO.count(entityClass);
    }

    public int countByNamedQuery(String nameQuery, String nameParam, Object valeu) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Count Entity by named query " + entityClass.getName() + " -> " + nameQuery);
        return DAO.countByNamedQuery(entityClass, nameQuery, nameParam, valeu);
    }

    public void editWithCommit(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Alterar e faz commmit ao registo " + entity);
        DAO.editWithCommit(entity);
    }

    public void createWithCommit(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Cria e faz commmit ao registo " + entity);
        DAO.createWithCommit(entity);
    }

    public void removeWithCommit(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Elimina e faz commmit ao registo " + entity);
        DAO.removeWithCommit(entity);
    }

}
