package TControllers;

import beans.ClientVisitanteRemote;
import jpaentidades.TUtilizadores;
import jsfclasses.util.JsfUtil;
import jsfclasses.util.PaginationHelper;
import jpafacades.TUtilizadoresFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityTransaction;
import beans.DAOLocal;
import jpafacades.TNewslettersFacade;

@Named("tUtilizadoresController")
@SessionScoped
public class TUtilizadoresController implements Serializable {

//    private static TUtilizadoresController instance = null;
    @EJB
    private TNewslettersFacade tNewslettersFacade;

    protected TUtilizadores current;
    protected DataModel items = null;
    @EJB
    private jpafacades.TUtilizadoresFacade utilizadoresFacade;
    @EJB
    private ClientVisitanteRemote clientVisitante;

    public TUtilizadores getCurrent() {
        return current;
    }
    
    protected PaginationHelper pagination;
    protected  int selectedItemIndex;

    public TUtilizadoresController() {
    }
    public TUtilizadoresController(TUtilizadores current) {
        this.current = current;
    }

    
    public TUtilizadores getSelected() {
        if (current == null) {
            current = new TUtilizadores();
            selectedItemIndex = -1;
        }
        return current;
    }

    public TNewslettersFacade getNewslettersFacade() {
        return tNewslettersFacade;
    }

    protected TUtilizadoresFacade getUtilizadoresFacade() {
        return utilizadoresFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getUtilizadoresFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getUtilizadoresFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "/tUtilizadores/List";
    }

    public String prepareView() {
        current = (TUtilizadores) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/tUtilizadores/View";
    }

    public String prepareCreate() {
        current = new TUtilizadores();
        selectedItemIndex = -1;
        return "/Visitante/Registo";
    }

    public String create(TUtilizadores current) {
        this.current = current;
        boolean ok = clientVisitante.inscreveUtilizador(current.getNome(), current.getMorada(),current.getUsername(), current.getPassword());
        if (ok) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TUtilizadoresCreated"));
            return prepareCreate();
         
        } else {
           JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TUtilizadores) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/tUtilizadores/Edit";
    }

    public String update() {
        try {
            getUtilizadoresFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TUtilizadoresUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TUtilizadores) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/tUtilizadores/List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "/tUtilizadores/View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "/tUtilizadores/List";
        }
    }

    private void performDestroy() {
        try {
            getUtilizadoresFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TUtilizadoresDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getUtilizadoresFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = (TUtilizadores) getUtilizadoresFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "/tUtilizadores/List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "/tUtilizadores/List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(utilizadoresFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(utilizadoresFacade.findAll(), true);
    }

    public TUtilizadores getTUtilizadores(java.lang.String id) {
        return utilizadoresFacade.find(id);
    }



    @FacesConverter(forClass = TUtilizadores.class)
    public static class TUtilizadoresControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
             System.out.println("----- getAsObject");
            if (value == null || value.length() == 0) {
                return null;
            }
            TUtilizadoresController controller = (TUtilizadoresController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tUtilizadoresController");
            return controller.getTUtilizadores(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TUtilizadores) {
                TUtilizadores o = (TUtilizadores) object;
                return getStringKey(o.getUsername());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TUtilizadores.class.getName());
            }
        }

    }

    
}
