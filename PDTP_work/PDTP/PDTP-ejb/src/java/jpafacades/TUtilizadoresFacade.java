/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import javax.ejb.Singleton;
import jpaentidades.TUtilizadores;

/**
 *
 * @author diogo
 */
@Singleton
public class TUtilizadoresFacade extends AbstractFacade<TUtilizadores> {



    public TUtilizadoresFacade() {
        super(TUtilizadores.class);
    }
}
