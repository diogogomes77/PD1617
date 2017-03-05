/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import javax.ejb.Stateless;
import jpaentidades.TItens;

/**
 *
 * @author diogo
 */
@Stateless
public class TItensFacade extends AbstractFacade<TItens> {

  

    public TItensFacade() {
        super(TItens.class);
    }
    
}
