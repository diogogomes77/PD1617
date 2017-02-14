/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaentidades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author diogo
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    private EntityManagerFactory emf ;
    private EntityManager em;

    public UsersFacade(Class<Users> entityClass) {
        super(entityClass);
        emf = Persistence.createEntityManagerFactory("PDTP-ejbPU");
        em =  emf.createEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
    
}
