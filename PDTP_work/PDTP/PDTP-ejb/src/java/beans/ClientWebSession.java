/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author diogo
 */
@Stateful
public class ClientWebSession {

    @EJB
    LeiloeiraLocal leiloeira;
    
    String userName;

    ClientRemote objSessao;//Para substituir as variaveis de sessao http

    public boolean isLogged(String username) {
        if (leiloeira.isLogged(username)) {
            leiloeira.setLastAction(username);
            return true;
        }
        return false;
    }

     public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setObjSessao(ClientRemote objSessao) {
        this.objSessao = objSessao;
    }

    public ClientRemote getObjSessao() {
        return objSessao;
    }
}
