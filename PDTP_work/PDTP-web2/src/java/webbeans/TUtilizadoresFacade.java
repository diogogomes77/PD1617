/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import webentidades.TUtilizadores;

/**
 *
 * @author diogo
 */
@Stateless
public class TUtilizadoresFacade extends AbstractFacade<TUtilizadores> {

    @PersistenceContext(unitName = "PDTP-web2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TUtilizadoresFacade() {
        super(TUtilizadores.class);
    }
    
}
