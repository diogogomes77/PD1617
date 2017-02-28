/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author eugenio
 */
public class ClientBase implements ClientRemote {

    @EJB
    LeiloeiraLocal leiloeira;

    @Override
    public ArrayList<String> getUsernameInscritos() {
        return leiloeira.getUsernameInscritos();
    }

    @Override
    public List<String> getNewsletter() {
        return leiloeira.obtemNewsletter();
    }

    @Override
    public ArrayList<String> getUsernamesOnline() {
        return leiloeira.getUsernamesOnline();
    }

    @Override
    public List<String> getUltimosItensVendidos() {
        return leiloeira.getUltimosItensVendidos();
    }

    @Override
    public int getTotalItens() {
        return leiloeira.getTotalItens();
    }

}
