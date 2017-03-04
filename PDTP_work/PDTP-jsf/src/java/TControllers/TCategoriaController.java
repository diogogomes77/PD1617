package TControllers;

import jpaentidades.TCategoria;
import jsfclasses.util.JsfUtil;
import jsfclasses.util.PaginationHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import jpafacades.TCategoriaFacade;

@Named("tCategoriaController")
@SessionScoped
public class TCategoriaController implements Serializable {

    protected TCategoria current;
    protected String nomeAntigoCategoria = null;
    protected DataModel items = null;
    @EJB
    private TCategoriaFacade ejbFacade;
    protected PaginationHelper pagination;
    protected int selectedItemIndex;

    protected String novaCategoria = null;

    public String getNovaCategoria() {
        if (novaCategoria == null) {
            novaCategoria = "";
        }
        return novaCategoria;
    }

    public void setNovaCategoria(String novaCategoria) {
        this.novaCategoria = novaCategoria;
    }

    public TCategoriaController() {
    }

    public TCategoria getSelected() {
        if (current == null) {
            current = new TCategoria();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TCategoriaFacade getFacade() {
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

    public String create() {
        try {
            getFacade().create(current);
            novaCategoria = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleCat").getString("TCategoriaCreated"));
            recreateModel();
            return "Listarcategorias";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleCat").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleCat").getString("TCategoriaUpdated"));
            return "Listarcategorias";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleCat").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareList() {
        recreateModel();
        return "Listarcategorias";
    }

    public String prepareView() {
        current = (TCategoria) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "AlterarCategoria";
    }

    public String prepareCreate() {
        current = new TCategoria();
        selectedItemIndex = -1;
        return "Novacategoria";
    }

    public String prepareEdit() {
        current = (TCategoria) getItems().getRowData();
        nomeAntigoCategoria = current.getNome();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "AlterarCategoria";
    }

    public String destroy() {
        current = (TCategoria) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "Listarcategorias";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "AlterarCategoria";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "Listarcategorias";
        }
    }

    protected void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleCat").getString("TCategoriaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleCat").getString("PersistenceErrorOccured"));
        }
    }

    protected void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    protected void recreateModel() {
        items = null;
    }

    protected void recreatePagination() {
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

    public SelectItem[] getItemsAvailableSelectOne_() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public List<TCategoria> getItemsAvailableSelectOne() {
        return ejbFacade.findAll();
    }

    public List getItemsAvailableSelectOnee() {
        List<SelectItem> itens = Arrays.asList(JsfUtil.getSelectItems(ejbFacade.findAll(), true));
        List<String> result = new ArrayList();
        for (SelectItem item : itens) {
//            TCategoria t = (TCategoria)item.getValue();
//            result.add(t.getNome());

            result.add(item.getLabel());
        }
        return result;
    }

    public TCategoria getTCategoria(String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TCategoria.class)
    public static class TCategoriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TCategoriaController controller = (TCategoriaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tCategoriaController");
            return controller.getTCategoria(getKey(value));
        }

        String getKey(String value) {
            return value.trim();
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
            if (object instanceof TCategoria) {
                TCategoria o = (TCategoria) object;
                return getStringKey(o.getIdCategoria());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TCategoria.class.getName());
            }
        }

    }

}
