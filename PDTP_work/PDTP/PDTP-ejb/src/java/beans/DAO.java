/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author diogo
 */
@Singleton
public class DAO implements DAOLocal {

    @PersistenceContext(unitName = "PDTP-ejbPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
