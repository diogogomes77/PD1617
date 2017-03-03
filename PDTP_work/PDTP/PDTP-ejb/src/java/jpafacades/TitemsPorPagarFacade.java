/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpaentidades.TitemsPorPagar;

/**
 *
 * @author diogo
 */
@Stateless
public class TitemsPorPagarFacade extends AbstractFacade<TitemsPorPagar> {


    public TitemsPorPagarFacade() {
        super(TitemsPorPagar.class);
    }
    
}
