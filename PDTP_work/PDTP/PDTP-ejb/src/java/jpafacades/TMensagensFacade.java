/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import javax.ejb.Singleton;
import jpaentidades.TMensagens;

/**
 *
 * @author diogo
 */
@Singleton
public class TMensagensFacade extends AbstractFacade<TMensagens> {


    public TMensagensFacade() {
        super(TMensagens.class);
    }
    
}
