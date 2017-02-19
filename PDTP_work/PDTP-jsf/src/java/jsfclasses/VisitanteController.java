/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;

import beans.ClientVisitanteRemote;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import jpaentidades.TUtilizadores;
import jpafacades.TUtilizadoresFacade;
import jsfclasses.util.JsfUtil;
import jsfclasses.util.PaginationHelper;

/**
 *
 * @author diogo
 */
@Named("VisitanteController")
@SessionScoped
public class VisitanteController extends TUtilizadoresController implements Serializable {

    @EJB
    private ClientVisitanteRemote clientVisitante;
    private UIComponent loginButton;
    private String username;
    private String password;
    private String nome;
    private String morada;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public VisitanteController() {
        super();
    }
 

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String login() {
        boolean ok = clientVisitante.loginUtilizador(username, password);
        System.out.println("-----" + ok);
        if (ok) {
            HttpSession session = SessionUtils.getSession();
            if ("admin".equals(username)) {
                return "/Admin/Inicio";
            }
            return "/Utilizador/Inicio";

        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login invalido", "Login invalido");
            ctx.addMessage(loginButton.getClientId(ctx), msg);
            return "";
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }

    public UIComponent getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(UIComponent loginButton) {
        this.loginButton = loginButton;
    }

    // retirado de TUtilizadoresController
//    @EJB
//    private jpafacades.TUtilizadoresFacade ejbFacade;
//    
//    private TUtilizadores current;
//    private DataModel items = null;
//    private PaginationHelper pagination;
//    private int selectedItemIndex;
//
//    public TUtilizadores getSelected() {
//        if (current == null) {
//            current = new TUtilizadores();
//            selectedItemIndex = -1;
//        }
//        return current;
//    }
//
//    private TUtilizadoresFacade getFacade() {
//        return ejbFacade;
//    }
//
//    public PaginationHelper getPagination() {
//        if (pagination == null) {
//            pagination = new PaginationHelper(10) {
//
//                @Override
//                public int getItemsCount() {
//                    return getFacade().count();
//                }
//
//                @Override
//                public DataModel createPageDataModel() {
//                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
//                }
//            };
//        }
//        return pagination;
//    }
//
//    public String prepareList() {
//        recreateModel();
//        return "/templates/Sim sList";
//    }
//
//    public String prepareView() {
//        current = (TUtilizadores) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//        return "/templates/View";
//    }
//
//    public String prepareCreate() {
//        current = new TUtilizadores();
//        selectedItemIndex = -1;
//        return "Registo";
//    }
//
//    public String create() {
//        boolean ok = clientVisitante.inscreveUtilizador(nome, morada, username, password);
//        System.out.println("-----" + ok);
//        if (ok) {
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TUtilizadoresCreated"));
//            return prepareCreate();
//         
//
//        } else {
//           JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//            return null;
//        }
//      
//    }
//
//    public String prepareEdit() {
//        current = (TUtilizadores) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//        return "/templates/Edit";
//    }
//
//    public String update() {
//        try {
//            getFacade().edit(current);
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TUtilizadoresUpdated"));
//            return "/templates/View";
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//            return null;
//        }
//    }
//
//    public String destroy() {
//        current = (TUtilizadores) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//        performDestroy();
//        recreatePagination();
//        recreateModel();
//        return "List";
//    }
//
//    public String destroyAndView() {
//        performDestroy();
//        recreateModel();
//        updateCurrentItem();
//        if (selectedItemIndex >= 0) {
//            return "/templates/View";
//        } else {
//            // all items were removed - go back to list
//            recreateModel();
//            return "/templates/List";
//        }
//    }
//
//    private void performDestroy() {
//        try {
//            getFacade().remove(current);
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TUtilizadoresDeleted"));
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//        }
//    }
//
//    private void updateCurrentItem() {
//        int count = getFacade().count();
//        if (selectedItemIndex >= count) {
//            // selected index cannot be bigger than number of items:
//            selectedItemIndex = count - 1;
//            // go to previous page if last page disappeared:
//            if (pagination.getPageFirstItem() >= count) {
//                pagination.previousPage();
//            }
//        }
//        if (selectedItemIndex >= 0) {
//            current = (TUtilizadores) getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
//        }
//    }
//
//    public DataModel getItems() {
//        if (items == null) {
//            items = getPagination().createPageDataModel();
//        }
//        return items;
//    }
//
//    private void recreateModel() {
//        items = null;
//    }
//
//    private void recreatePagination() {
//        pagination = null;
//    }
//
//    public String next() {
//        getPagination().nextPage();
//        recreateModel();
//        return "List";
//    }
//
//    public String previous() {
//        getPagination().previousPage();
//        recreateModel();
//        return "List";
//    }
//
//    public SelectItem[] getItemsAvailableSelectMany() {
//        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
//    }
//
//    public SelectItem[] getItemsAvailableSelectOne() {
//        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
//    }
//
//    public TUtilizadores getTUtilizadores(java.lang.String id) {
//        return ejbFacade.find(id);
//    }
//
//    @FacesConverter(forClass = TUtilizadores.class)
//    public static class VisitanteControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            VisitanteController controller = (VisitanteController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "visitanteController");
//            return controller.getTUtilizadores(getKey(value));
//        }
//
//        java.lang.String getKey(String value) {
//            java.lang.String key;
//            key = value;
//            return key;
//        }
//
//        String getStringKey(java.lang.String value) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        @Override
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null) {
//                return null;
//            }
//            if (object instanceof TUtilizadores) {
//                TUtilizadores o = (TUtilizadores) object;
//                return getStringKey(o.getUsername());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TUtilizadores.class.getName());
//            }
//        }
//
//    }
    
}
