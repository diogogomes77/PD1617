/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaentidades.TMensagens;

/**
 *
 * @author diogo
 */
@Stateless
public class TMensagensFacade extends AbstractFacade<TMensagens> {

    private EntityManagerFactory  emf = Persistence.createEntityManagerFactory("PDTP-ejbPU");

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public TMensagensFacade() {
        super(TMensagens.class);
    }
    
}
