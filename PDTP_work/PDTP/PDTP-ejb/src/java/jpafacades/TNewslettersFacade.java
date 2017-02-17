/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import jpaentidades.DAOLocal;
import jpaentidades.TNewsletters;

/**
 *
 * @author eugenio
 */
@Stateless
public class TNewslettersFacade extends AbstractFacade<TNewsletters> {

    @EJB
    private DAOLocal DAO;

    @Override
    protected EntityManager getEntityManager() {
        return DAO.getEntityManager();
    }

    public TNewslettersFacade() {
        super(TNewsletters.class);
    }
    
}
