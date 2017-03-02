/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpaentidades.TLicitacoes;

/**
 *
 * @author diogo
 */
@Stateless
public class TLicitacoesFacade extends AbstractFacade<TLicitacoes> {


    public TLicitacoesFacade() {
        super(TLicitacoes.class);
    }
    
}
