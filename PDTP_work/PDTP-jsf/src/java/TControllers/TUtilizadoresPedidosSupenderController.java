/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TControllers;

import beans.UtilizadorTipoLista;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import jpaentidades.TUtilizadores;

/**
 *
 * @author eugenio
 */
@Named("tUtilizadoresPedidosSupenderController")
@SessionScoped
public class TUtilizadoresPedidosSupenderController extends TUtilizadoresController {

    public TUtilizadoresPedidosSupenderController() {
        tipoLista = UtilizadorTipoLista.LISTA_USER_ALL;
    }

    public TUtilizadoresPedidosSupenderController(TUtilizadores current) {
        super(current);
        tipoLista = UtilizadorTipoLista.LISTA_USER_ALL;
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }
    
    
}
