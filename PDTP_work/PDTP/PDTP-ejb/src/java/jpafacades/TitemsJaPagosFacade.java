/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpaentidades.TitemsJaPagos;

/**
 *
 * @author diogo
 */
@Stateless
public class TitemsJaPagosFacade extends AbstractFacade<TitemsJaPagos> {



    public TitemsJaPagosFacade() {
        super(TitemsJaPagos.class);
    }
    
}
