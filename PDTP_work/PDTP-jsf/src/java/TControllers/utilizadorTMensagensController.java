/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TControllers;

import TControllers.TMensagensController;
import beans.ClientUtilizadorRemote;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import jsfclasses.util.JsfUtil;

@Named("utilizadortMensagensController")
@SessionScoped
public class utilizadorTMensagensController extends TMensagensController {
    
    @EJB
    ClientUtilizadorRemote client;
    public String create() {
       String destinatario = current.getDestinatario().getUsername();
            String texto = current.getTexto();
            String assunto = current.getAssunto();
            
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
