/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacao;

import beans.ClientWebSession;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author diogo
 */
@WebFilter(filterName = "AuthFilter") //, urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    @EJB
    ClientWebSession client;

    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("-----DO FILTER");

        try {

            // check whether session variable is set
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            //  allow user to proccede if url is login.xhtml or user logged in or user is accessing any page in //public folder

            String reqURI = req.getRequestURI();
            //System.out.println("-----TRY FILTER--");

            // chain.doFilter(request, response);
            if (!reqURI.contains("javax.faces.resource")) {
                if (ses != null) {
//                    System.out.println("-----SES --");
                    if (ses.getAttribute("username") != null) {
//                        System.out.println("-----SES USERNAME --");
                        if (client.isLogged(ses.getAttribute("username").toString())) {
                            String user = ses.getAttribute("username").toString();
//                            System.out.println("-----LOGED USERNAME= " + user);
                            if ("admin".equals(user)) {

                                if (!reqURI.contains("/Administrador")) {
//                                    System.out.println("-----URI no ADMINISTRADOR --");
                                    res.sendRedirect(req.getContextPath() + "/faces/Administrador/Inicio.xhtml");
//                                    return;
                                }
                            } else { // utilizador normal
                                if (!reqURI.contains("/Utilizador")) {
//                                    System.out.println("-----UIR no Utilizador --");
                                    res.sendRedirect(req.getContextPath() + "/faces/Utilizador/Inicio.xhtml");
//                                    return;
                                }
                            }
                        } else {
//                            System.out.println("-----IS NOT LOGGED --");
                            ses.invalidate();
                            res.sendRedirect(req.getContextPath() + "/faces/Visitante/Inicio.xhtml");
                            //return;
                        }
                    }
                }
                if ((ses == null) || ses.getAttribute("username") == null) {
//                    System.out.println("-----SES NULL or NO uSERNAME --");
                    if (!reqURI.contains("/Visitante")) {
                        System.out.println("-----URI no VISITANTE --");
                        res.sendRedirect(req.getContextPath() + "/faces/Visitante/Inicio.xhtml");
                        //return;
                    }
//                    else {
//                        chain.doFilter(request, response);
//                        return;
//                    }
                }
            }
//            System.out.println("-----CHAIN --");
            if (chain != null) {
                chain.doFilter(request, response);
            }
        } catch (IOException | ServletException t) {
            System.out.println(t.getMessage());
        }
    } //doFilter

    @Override
    public void destroy() {

    }
}
