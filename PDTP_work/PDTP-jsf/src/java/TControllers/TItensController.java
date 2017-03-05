package TControllers;

import autenticacao.Util;
import beans.ClientRemote;
import beans.ClientUtilizadorRemote;
import beans.ItensTipoLista;
import beans.SessionException;
import jpaentidades.TItens;
import jsfclasses.util.JsfUtil;
import jsfclasses.util.PaginationHelper;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
import javax.servlet.http.HttpSession;
import jpafacades.TItensFacade;

@Named("tItensController")
@SessionScoped
public class TItensController implements Serializable {

    private ClientRemote remoteSession;

    protected ItensTipoLista tipoList = ItensTipoLista.LISTA_ITENS_ALL;
    private TItens current;
    private DataModel items = null;
    @EJB
    private TItensFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TItensController() {
    }

    @PostConstruct
    public void init() {
        HttpSession session = Util.getSession();
        remoteSession = (ClientRemote) session.getAttribute("sessaoUser");
    }

    public TItens getSelected() {
        if (current == null) {
            current = new TItens();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TItensFacade getFacade() {
        return ejbFacade;
    }
    public int getItensCount() {
        if( remoteSession != null ){
            try {
                return remoteSession.obtemNumItens(tipoList);
            } catch (SessionException ex) {
                Logger.getLogger(TItensController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public List findRange(int[] range ){
        if( remoteSession != null ){
            try {
                return remoteSession.obtemItensRange(tipoList, range);
            } catch (SessionException ex) {
                Logger.getLogger(TItensController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new ArrayList();
    }
    public List findAll( ){
        if( remoteSession != null ){
            try {
                return remoteSession.obtemItens(tipoList);
            } catch (SessionException ex) {
                Logger.getLogger(TItensController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new ArrayList();
    }

    public TItens findById( Long id ){
        return null;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getItensCount();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "ListadeItens";
    }

    public String prepareView() {
        current = (TItens) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "ConsultarItem";
    }

    public String seguir() {
        HttpSession session = Util.getSession();

        current = (TItens) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        System.out.println("---SEGUIR itemid " + current.getItemid());
        try {
            boolean ok = ((ClientUtilizadorRemote) remoteSession).seguirItem(current.getItemid());
            if (ok) {
                JsfUtil.addSuccessMessage("Item Seguido");
            } else {
                JsfUtil.addSuccessMessage("Erro");
            }
            return null;
        } catch (SessionException ex) {
            Logger.getLogger(TItensController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(TItensController.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public String prepareCreate() {
        current = new TItens();
        selectedItemIndex = -1;
        return "ColocarItemavenda";
    }

    public String create() {
        try {

            Timestamp limite = new java.sql.Timestamp(current.getDatafim().getTime());
            ((ClientUtilizadorRemote) remoteSession).addItem(current.getCategoria(), current.getDescricao(), current.getPrecoinicial(), current.getComprarja(), limite);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleItens").getString("TItensCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleItens").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TItens) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            //TODO: Atualizar os item
            getFacade().edit(current);
            //((ClientUtilizadorRemote) remoteSession).
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleItens").getString("TItensUpdated"));
            return "ConsultarItem";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleItens").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TItens) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "ListadeItens";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "ConsultarItem";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "ListadeItens";
        }
    }

    private void performDestroy() {
        try {
            //TODO:Fazer o remove
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleItens").getString("TItensDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleItens").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getItensCount();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = (TItens)findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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
        return null;
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return null;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(findAll(), true);
    }

    public TItens getTItens(java.lang.Long id) {
        return findById(id);
    }

    @FacesConverter(forClass = TItens.class)
    public static class TItensControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TItensController controller = (TItensController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tItensController");
            return controller.getTItens(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TItens) {
                TItens o = (TItens) object;
                return getStringKey(o.getItemid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TItens.class.getName());
            }
        }

    }

}
