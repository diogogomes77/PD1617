/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TControllers;

import TControllers.TMensagensController;
import autenticacao.Util;
import beans.ClientAdminRemote;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import jsfclasses.util.JsfUtil;

@Named("administradortMensagensController")
@SessionScoped
public class administradorTMensagensController extends TMensagensController {
    
    @EJB
    private ClientAdminRemote client;
        public String create() {
            String destinatario = current.getDestinatario().getUsername();
            String texto = current.getTexto();
            String assunto = current.getAssunto();
//            HttpSession session = Util.getSession();
//            client.setMyName((String)session.getAttribute("username"));
        try {
            if (client.sendMensagem(destinatario, texto, assunto)) {
                System.out.println("Mensagem enviada");
            } 
           // getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleMensagens").getString("TMensagensCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleMensagens").getString("PersistenceErrorOccured"));
            return null;
        }
    }
}
