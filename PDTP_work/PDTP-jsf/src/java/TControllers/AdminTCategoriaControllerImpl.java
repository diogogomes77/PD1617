/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TControllers;

import autenticacao.Util;
import beans.ClientAdminRemote;
import beans.ClientRemote;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
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

    public String create() {
        try {
            System.out.println("---CATEGORIA="+current.getNome());
           remoteSession.adicionarCategoria(current.getNome());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleCat").getString("TCategoriaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleCat").getString("PersistenceErrorOccured"));
            return null;
        }
    }
}
