package TControllers;

import autenticacao.Util;
import beans.ClientRemote;
import jpaentidades.TNewsletters;
import jsfclasses.util.JsfUtil;
import jsfclasses.util.PaginationHelper;

import java.io.Serializable;
import java.util.List;
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

@Named("tNewslettersController")
@SessionScoped
public class TNewslettersController implements Serializable {

    private ClientRemote remoteSession;

//    @EJB
//    private TNewslettersFacade ejbFacade;

    private TNewsletters current;
    private DataModel items = null;

    
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TNewslettersController() {
    }

    @PostConstruct
    public void init() {
        //session = null;
        HttpSession session = Util.getSession();
        remoteSession = (ClientRemote) session.getAttribute("sessaoUser");
    }

    private ClientRemote getUserSession() {
        return remoteSession;
    }

    public TNewsletters getSelected() {
        if (current == null) {
            current = new TNewsletters();
            selectedItemIndex = -1;
        }
        return current;
    }

//    private TNewslettersFacade getFacade() {
//        return ejbFacade;
//    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getUserSession().obtemNumNewsletter();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getUserSession().obtemNewsletterRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    private void updateCurrentItem() {
        int count = getUserSession().obtemNumNewsletter();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = (TNewsletters)getUserSession().obtemNewsletterRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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
         List<Object> list = getUserSession().obtemNewsletter();
        return JsfUtil.getSelectItems(list, false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        List<Object> list = getUserSession().obtemNewsletter();
        return JsfUtil.getSelectItems(list, true);
    }

    public TNewsletters getTNewsletters(java.lang.Integer id) {
        return (TNewsletters)getUserSession().obtemNewsletterById(id);
    }

    @FacesConverter(forClass = TNewsletters.class)
    public static class TNewslettersControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TNewslettersController controller = (TNewslettersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tNewslettersController");
            return controller.getTNewsletters(getKey(value));
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
            if (object instanceof TNewsletters) {
                TNewsletters o = (TNewsletters) object;
                return getStringKey(o.getIdNewsletter());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TNewsletters.class.getName());
            }
        }

    }

}
