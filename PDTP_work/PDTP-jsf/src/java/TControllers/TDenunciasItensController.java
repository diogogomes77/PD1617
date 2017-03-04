package TControllers;

import jpafacades.TDenunciasItensFacade;
import jpaentidades.TDenunciasItens;
import beans.util.JsfUtil;
import beans.util.PaginationHelper;

import java.io.Serializable;
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

@Named("tDenunciasItensController")
@SessionScoped
public class TDenunciasItensController implements Serializable {

    private TDenunciasItens current;
    private DataModel items = null;
    @EJB
    private jpafacades.TDenunciasItensFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TDenunciasItensController() {
    }

    public TDenunciasItens getSelected() {
        if (current == null) {
            current = new TDenunciasItens();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TDenunciasItensFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

//    public String prepareList() {
//        recreateModel();
//        return "List";
//    }
//
//    public String prepareView() {
//        current = (TDenunciasItens) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//        return "View";
//    }
//
//    public String prepareCreate() {
//        current = new TDenunciasItens();
//        selectedItemIndex = -1;
//        return "Create";
//    }
//
//    public String create() {
//        try {
//            getFacade().create(current);
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleItenstudo").getString("TDenunciasItensCreated"));
//            return prepareCreate();
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleItenstudo").getString("PersistenceErrorOccured"));
//            return null;
//        }
//    }
//
//    public String prepareEdit() {
//        current = (TDenunciasItens) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//        return "Edit";
//    }
//
//    public String update() {
//        try {
//            getFacade().edit(current);
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleItenstudo").getString("TDenunciasItensUpdated"));
//            return "View";
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleItenstudo").getString("PersistenceErrorOccured"));
//            return null;
//        }
//    }
//
//    public String destroy() {
//        current = (TDenunciasItens) getItems().getRowData();
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
//            return "View";
//        } else {
//            // all items were removed - go back to list
//            recreateModel();
//            return "List";
//        }
//    }
//
//    private void performDestroy() {
//        try {
//            getFacade().remove(current);
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleItenstudo").getString("TDenunciasItensDeleted"));
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleItenstudo").getString("PersistenceErrorOccured"));
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
//            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
//        }
//    }

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
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public TDenunciasItens getTDenunciasItens(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TDenunciasItens.class)
    public static class TDenunciasItensControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TDenunciasItensController controller = (TDenunciasItensController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tDenunciasItensController");
            return controller.getTDenunciasItens(getKey(value));
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
            if (object instanceof TDenunciasItens) {
                TDenunciasItens o = (TDenunciasItens) object;
                return getStringKey(o.getIdDenunciaItem());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TDenunciasItens.class.getName());
            }
        }

    }

}
