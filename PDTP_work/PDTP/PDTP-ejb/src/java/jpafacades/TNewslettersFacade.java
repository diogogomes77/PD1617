/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpafacades;

import javax.ejb.Singleton;
import jpaentidades.TNewsletters;

/**
 *
 * @author eugenio
 */
@Singleton
public class TNewslettersFacade extends AbstractFacade<TNewsletters> {


    public TNewslettersFacade() {
        super(TNewsletters.class);
    }

    @Override
    public void removeWithCommit(TNewsletters entity) {
        //Não permite a remoção das news
    }

    @Override
    public void remove(TNewsletters entity) {
        //Não permite a remoção das news
    }
    
    
}
