/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TControllers;

import beans.ItensTipoLista;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author eugenio
 */
@Named("tItensVisitanteController")
@SessionScoped
public class TItensVisitanteController extends TItensController {

    public TItensVisitanteController() {
        tipoList = ItensTipoLista.LISTA_ITENS_ULTIMOS_VENDIDOS;
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }
    
    
}
