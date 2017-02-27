/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 *
 * @author diogo
 */
@Singleton
public class ClientWebSession {
     @EJB
    LeiloeiraLocal leiloeira;
     

     public boolean isLogged(String username){
        if (leiloeira.isLogged(username)) {
            leiloeira.setLastAction(username);
            return true;
        }
        return false;
        
     }
}
