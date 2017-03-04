/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;
import gui.Menu;
import TControllers.TUtilizadoresController;
import beans.ClientWebSession;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import jpaentidades.TUtilizadores;

/**
 *
 * @author diogo
 */

public abstract class AbstractController extends TUtilizadoresController implements Serializable { // extends TUtilizadoresController

    @EJB
    ClientWebSession webSession;
    
    protected TUtilizadoresController tUtilizadorController ;
    
    protected TUtilizadores current;
    
    protected ArrayList<Menu> menus;
    
    protected  String seccao;
    

    public TUtilizadores getSelected() {
        if (current == null) {
            current = new TUtilizadores();
            //selectedItemIndex = -1;
        }
        return current;
    }

    abstract ArrayList<Menu> getMenus() ;
}
