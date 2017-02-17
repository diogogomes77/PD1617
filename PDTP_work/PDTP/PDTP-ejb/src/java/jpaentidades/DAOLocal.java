/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaentidades;

import javax.ejb.Local;
import javax.persistence.EntityManager;

/**
 *
 * @author eugenio
 */
@Local
public interface DAOLocal {
    
     EntityManager getEntityManager();
     
     public void persist(Object object);
    
}
