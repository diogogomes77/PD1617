/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;

import beans.ClientVisitanteRemote;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import jpaentidades.TUtilizadores;

/**
 *
 * @author diogo
 */

public abstract class AbstractController  implements Serializable { // extends TUtilizadoresController

    
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
