/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import jpafacades.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import jpaentidades.TMensagens;
import jpaentidades.TMensagens;

/**
 *
 * @author diogo
 */
@Stateless
public class TMensagensFacade extends AbstractFacade<TMensagens> {

   
//    @Override
//    protected EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }


    public TMensagensFacade() {
        super(TMensagens.class);
    }
    
}
