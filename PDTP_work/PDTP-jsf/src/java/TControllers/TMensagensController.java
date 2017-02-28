package TControllers;

import autenticacao.Util;
import beans.ClientAuthRemote;
import beans.SessionException;
import jpaentidades.TMensagens;
import jsfclasses.util.JsfUtil;
import jsfclasses.util.PaginationHelper;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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


public class TMensagensController implements Serializable {

<<<<<<< 80e1432ead1e0734f4491421d21c5f489901b1cd:PDTP_work/PDTP-jsf/src/java/TControllers/TMensagensController.java
    @EJB
    protected TMensagensFacade ejbFacade;
=======
//    @EJB
//    private TMensagensFacade ejbFacade;
    private ClientAuthRemote remoteSession;
>>>>>>> f749b1a9a31bd826017fd920ad3a0289edcd8beb:PDTP_work/PDTP-jsf/src/java/jsfclasses/TMensagensController.java

    protected TMensagens current;
    protected DataModel items = null;
//    @EJB
//    private beans.TMensagensFacade ejbFacade;
    protected PaginationHelper pagination;
    protected int selectedItemIndex;

    public TMensagensController() {
    }

    @PostConstruct
    public void init() {
        //session = null;
        HttpSession session = Util.getSession();
        remoteSession = (ClientAuthRemote)session.getAttribute("sessaoUser");
    }
    
    public TMensagens getSelected() {
        if (current == null) {
            current = new TMensagens();
            selectedItemIndex = -1;
        }
        return current;
    }

<<<<<<< 80e1432ead1e0734f4491421d21c5f489901b1cd:PDTP_work/PDTP-jsf/src/java/TControllers/TMensagensController.java
    protected TMensagensFacade getFacade() {
        return ejbFacade;
=======
    private ClientAuthRemote getUserSession() {
        return remoteSession;
>>>>>>> f749b1a9a31bd826017fd920ad3a0289edcd8beb:PDTP_work/PDTP-jsf/src/java/jsfclasses/TMensagensController.java
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    try {
                        //return getFacade().count();
                        return getUserSession().obtemMensagens().size();
                    } catch (SessionException ex) {
                        Logger.getLogger(TMensagensController.class.getName()).log(Level.SEVERE, null, ex);
                        return 0;
                    }
                }

                @Override
                public DataModel createPageDataModel() {
                    List<Object> list = null;
                    try {
                        //return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                        list = getUserSession().obtemMensagensRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
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
        current = (TMensagens) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TMensagens();
        selectedItemIndex = -1;
        return "Create";
    }

<<<<<<< 80e1432ead1e0734f4491421d21c5f489901b1cd:PDTP_work/PDTP-jsf/src/java/TControllers/TMensagensController.java
=======
    public String create() {
        try {
            //getFacade().create(current);
            if (getUserSession().sendMensagem(current.getDestinatario().getUsername(), current.getTexto(), current.getAssunto())) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleMensagens").getString("TMensagensCreated"));
                return prepareCreate();
            }
        } catch (SessionException e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleMensagens").getString("PersistenceErrorOccured"));
        }
        return null;
    }
>>>>>>> f749b1a9a31bd826017fd920ad3a0289edcd8beb:PDTP_work/PDTP-jsf/src/java/jsfclasses/TMensagensController.java

    public String prepareEdit() {
        current = (TMensagens) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            //getFacade().edit(current);
            if (getUserSession().alteraMensagem(current.getIdMensagem(), current.getDestinatario().getUsername(), current.getTexto(), current.getAssunto())) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleMensagens").getString("TMensagensUpdated"));
                return "View";
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleMensagens").getString("PersistenceErrorOccured"));
        }
        return null;
    }

    public String destroy() {
        current = (TMensagens) getItems().getRowData();
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
            //getFacade().remove(current);
            if (getUserSession().alteraMensagemParLida(current.getIdMensagem())) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleMensagens").getString("TMensagensDeleted"));
            }
        } catch (SessionException e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleMensagens").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        try {
            int count = getUserSession().obtemNumMensagens();//.count();
            if (selectedItemIndex >= count) {
                // selected index cannot be bigger than number of items:
                selectedItemIndex = count - 1;
                // go to previous page if last page disappeared:
                if (pagination.getPageFirstItem() >= count) {
                    pagination.previousPage();
                }
            }
            if (selectedItemIndex >= 0) {
                //current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
                current = (TMensagens) getUserSession().obtemMensagensRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        List<Object> list = null;
        try {
            list = getUserSession().obtemMensagens();
        } catch (SessionException ex) {
            Logger.getLogger(TMensagensController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return JsfUtil.getSelectItems(list, false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        List<Object> list = null;
        try {
            list = getUserSession().obtemMensagens();
        } catch (SessionException ex) {
            Logger.getLogger(TMensagensController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return JsfUtil.getSelectItems(list, true);
    }

    public TMensagens getTMensagens(java.lang.Integer id) {
        try {
            return (TMensagens) getUserSession().obtemMensagemById(id);//ejbFacade.find(id);
        } catch (SessionException ex) {
            Logger.getLogger(TMensagensController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FacesConverter(forClass = TMensagens.class)
    public static class TMensagensControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TMensagensController controller = (TMensagensController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tMensagensController");
            return controller.getTMensagens(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TMensagens) {
                TMensagens o = (TMensagens) object;
                return getStringKey(o.getIdMensagem());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TMensagens.class.getName());
            }
        }

    }

}
