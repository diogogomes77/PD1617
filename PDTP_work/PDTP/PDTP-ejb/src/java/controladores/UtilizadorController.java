/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import beans.DAOLocal;
import entidades.TUtilizadores;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author diogo
 */
@Stateless
public class UtilizadorController implements UtilizadorControllerLocal {

        @EJB
    private DAOLocal dao;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public ArrayList<String> getAll() {
        EntityManager em = dao.getEntityManager();
        Query q = em.createNamedQuery("TUtilizadores.findAll");
        List<TUtilizadores> pessoas = q.getResultList();
        ArrayList<String> res = new ArrayList<>();
        for (TUtilizadores p : pessoas)
            res.add(p.toString());
        if (res.isEmpty())
            res.add("sem resultados");
        return res;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
