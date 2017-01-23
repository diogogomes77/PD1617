/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import beans.ClientVisitanteRemote;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "controladorVisitante")
@RequestScoped
public class controladorVisitante {
 
    @EJB
    private ClientVisitanteRemote ligacaoVisitante;

}
