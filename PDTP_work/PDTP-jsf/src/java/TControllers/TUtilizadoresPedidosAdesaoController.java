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
@Named("tUtilizadoresPedidosAdesaoController")
@SessionScoped
public class TUtilizadoresPedidosAdesaoController extends TUtilizadoresController {

    public TUtilizadoresPedidosAdesaoController() {
        tipoLista = UtilizadorTipoLista.LISTA_USER_ADESOES;
    }

    public TUtilizadoresPedidosAdesaoController(TUtilizadores current) {
        super(current);
        tipoLista = UtilizadorTipoLista.LISTA_USER_ADESOES;
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }
    
    
}
