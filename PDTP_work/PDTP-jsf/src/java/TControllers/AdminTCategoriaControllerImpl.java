/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TControllers;

import autenticacao.Util;
import beans.ClientAdminRemote;
import beans.SessionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import jpaentidades.TCategoria;
import jsfclasses.util.JsfUtil;

@Named("admintCategoriaController")
@SessionScoped
public class AdminTCategoriaControllerImpl extends TCategoriaController {

    private ClientAdminRemote remoteSession;

    public AdminTCategoriaControllerImpl() {
        super();
    }

    @PostConstruct
    public void init() {
        HttpSession session = Util.getSession();
        remoteSession = (ClientAdminRemote) session.getAttribute("sessaoUser");
    }

    @Override
    public String create() {
        try {
            remoteSession.adicionarCategoria(novaCategoria);
            novaCategoria = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleCat").getString("TCategoriaCreated"));
            recreateModel();
            return "Listarcategorias";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleCat").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    @Override
    public String update() {
        try {
            remoteSession.modificaCategoria(nomeAntigoCategoria, current.getNome());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleCat").getString("TCategoriaUpdated"));
            return "Listarcategorias";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleCat").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    @Override
    protected void performDestroy() {
        try {
            remoteSession.eliminaCategoria(current.getNome());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleCat").getString("TCategoriaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleCat").getString("PersistenceErrorOccured"));
        }
    }

    @Override
    public SelectItem[] getItemsAvailableSelectMany() {
        List<Object> list = null;
        try {
            list = remoteSession.obtemCategoriasEnt();
        } catch (SessionException ex) {
            Logger.getLogger(AdminTCategoriaControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return JsfUtil.getSelectItems(list, false);
    }

    @Override
    public SelectItem[] getItemsAvailableSelectOne_() {
        List<Object> list = null;
        try {
            list = remoteSession.obtemCategoriasEnt();
        } catch (SessionException ex) {
            Logger.getLogger(AdminTCategoriaControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return JsfUtil.getSelectItems(list, true);
    }

    @Override
    public List getItemsAvailableSelectOnee() {
        List<SelectItem> itens;
        List<String> result = new ArrayList();
        try {
            itens = Arrays.asList(JsfUtil.getSelectItems(remoteSession.obtemCategoriasEnt(), true));
            for (SelectItem item : itens) {
                result.add(item.getLabel());
            }
        } catch (SessionException ex) {
            Logger.getLogger(AdminTCategoriaControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public TCategoria getTCategoria(String id) {
        try {
            return (TCategoria) remoteSession.obtemCategoriasById(id);
        } catch (SessionException ex) {
            Logger.getLogger(AdminTCategoriaControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    List findRange(int[] range) {
        try {
            return remoteSession.obtemCategoriasRange(range);
        } catch (SessionException ex) {
            Logger.getLogger(AdminTCategoriaControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int getCount() {
        try {
            return remoteSession.obtemNumCategorias();
        } catch (SessionException ex) {
            Logger.getLogger(AdminTCategoriaControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
