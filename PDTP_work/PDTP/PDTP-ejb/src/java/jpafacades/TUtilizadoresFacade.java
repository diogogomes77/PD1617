/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaentidades.DAOLocal;
import jpaentidades.TUtilizadores;

/**
 *
 * @author diogo
 */
@Stateless
public class TUtilizadoresFacade extends AbstractFacade<TUtilizadores> {

    @EJB
    private DAOLocal DAO;

    @Override
    protected EntityManager getEntityManager() {
        return DAO.getEntityManager();
    }

    public TUtilizadoresFacade() {
        super(TUtilizadores.class);
    }
    
}
