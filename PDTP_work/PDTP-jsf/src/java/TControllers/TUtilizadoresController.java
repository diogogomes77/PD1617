package TControllers;

import autenticacao.Util;
import beans.ClientAdminRemote;
import beans.ClientRemote;
import beans.ClientVisitanteRemote;
import beans.SessionException;
import jpaentidades.TUtilizadores;
import jsfclasses.util.JsfUtil;
import jsfclasses.util.PaginationHelper;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import beans.UtilizadorTipoLista;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import jsfclasses.AdminController;

@Named("tUtilizadoresController")
@SessionScoped
public class TUtilizadoresController implements Serializable {

    private ClientRemote remoteSession;

    protected TUtilizadores current;
    protected DataModel items = null;

    UtilizadorTipoLista tipoLista = UtilizadorTipoLista.LISTA_USER_ALL;

    public UtilizadorTipoLista getTipoLista() {
        return tipoLista;
    }

    @PostConstruct
    public void init() {
        HttpSession session = Util.getSession();
        remoteSession = (ClientRemote) session.getAttribute("sessaoUser");
    }

    private ClientRemote getUserSession() {
        return remoteSession;
    }

    public TUtilizadores getCurrent() {
        return current;
    }

    protected PaginationHelper pagination;
    protected PaginationHelper paginationNamedQuery;
    protected int selectedItemIndex;

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

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    try {
                        //return getFacade().count();
                        return getUserSession().obtemNumUtilizador(getTipoLista());
                    } catch (SessionException ex) {
                        Logger.getLogger(TUtilizadoresController.class.getName()).log(Level.SEVERE, null, ex);
                        return 0;
                    }
                }

                @Override
                public DataModel createPageDataModel() {
                    List<Object> list = null;
                    try {
                        //return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                        list = getUserSession().obtemUtilizadoresRange(getTipoLista(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
                    } catch (SessionException ex) {
                        Logger.getLogger(TMensagensController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return new ListDataModel(list);
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (TUtilizadores) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TUtilizadores();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create(TUtilizadores current) {
        this.current = current;
        boolean ok = false;
        if (remoteSession instanceof ClientVisitanteRemote) {
            ok = ((ClientVisitanteRemote) remoteSession).inscreveUtilizador(current.getNome(), current.getMorada(), current.getUsername(), current.getPassword());
        }
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
        return "Edit";
    }

    public String update() {
        try {
            //TODO: Saber que sessão pode fazer esta operação
//            getUtilizadoresFacade().edit(current);
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
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            //TODO: Saber que sessão pode fazer esta operação
//            getUtilizadoresFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TUtilizadoresDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        //TODO: Saber que sessão pode fazer esta operação
        try {
            int count = getUserSession().obtemNumUtilizador(getTipoLista());//getUtilizadoresFacade().count();
            if (selectedItemIndex >= count) {
                // selected index cannot be bigger than number of items:
                selectedItemIndex = count - 1;
                // go to previous page if last page disappeared:
                if (pagination.getPageFirstItem() >= count) {
                    pagination.previousPage();
                }
            }
            if (selectedItemIndex >= 0) {
                try {
                    //return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    current = (TUtilizadores) getUserSession().obtemUtilizadoresRange(getTipoLista(), new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
                } catch (SessionException ex) {
                    Logger.getLogger(TMensagensController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SessionException ex) {
            Logger.getLogger(TMensagensController.class.getName()).log(Level.SEVERE, null, ex);
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

    public String ativar(String user) {
        try {
            if (remoteSession instanceof ClientAdminRemote) {
                Boolean ativado = ((ClientAdminRemote) remoteSession).ativaUtilizador(user);
                JsfUtil.addSuccessMessage("Utilizador username " + user + " ativado!");
            }
        } catch (SessionException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addSuccessMessage("ERRO ao ativar " + user);
        }
        recreateModel();
        return null;
    }

    public String reativar(String user) {
        try {
            if (remoteSession instanceof ClientAdminRemote) {
                Boolean ativado = ((ClientAdminRemote) remoteSession).ativaUtilizador(user);
                JsfUtil.addSuccessMessage("Utilizador username " + user + " reativado!");
            }
        } catch (SessionException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addSuccessMessage("ERRO ao reativar " + user);
        }
        recreateModel();
        return null;
    }

    public String suspender(String user) {
        try {
            if (remoteSession instanceof ClientAdminRemote) {
                Boolean ativado = ((ClientAdminRemote) remoteSession).suspendeUsername(user);
            }
        } catch (SessionException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        recreateModel();
        return null;
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
        List<Object> list = null;
        try {
            list = getUserSession().obtemUtilizadores(tipoLista);
        } catch (SessionException ex) {
            Logger.getLogger(TUtilizadoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return JsfUtil.getSelectItems(list, false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        List<Object> list = null;
        try {
            list = getUserSession().obtemUtilizadores(tipoLista);
        } catch (SessionException ex) {
            Logger.getLogger(TUtilizadoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return JsfUtil.getSelectItems(list, true);
    }

    public TUtilizadores getTUtilizadores(java.lang.String id) {
        try {
            return (TUtilizadores) getUserSession().obtemUtilizadorById(id);//ejbFacade.find(id);
        } catch (SessionException ex) {
            Logger.getLogger(TUtilizadoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
                    getValue(facesContext.getELContext(), null, "tUtilizadoresAtivosController");
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
