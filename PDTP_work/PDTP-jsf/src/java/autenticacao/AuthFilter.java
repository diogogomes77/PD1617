/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacao;

import java.io.IOException;
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

    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

//    @Override
//    public void doFilter(ServletRequest req, ServletResponse response, FilterChain next) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
// System.out.println("-----DO FILTER");
//        //Manually check that the current user can access pages
//        //I did that by storing stuff in the session which you can access by 
//        //request.getSession().getAttribute(someKey);
//        if(1==1) {
//            HttpServletResponse r = (HttpServletResponse) response;
//            r.sendRedirect(request.getContextPath() + "/Inicio.xhtml");
//            return;
//        }
//
//        next.doFilter(req, response);
//    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("-----DO FILTER");
        chain.doFilter(request, response);
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

                if ((ses != null && ses.getAttribute("username") != null)) {
                    String user = ses.getAttribute("username").toString();
                    System.out.println("-----USER= "+user);
                    if ( "admin".equals(user)) {

                        if (!reqURI.contains("/Administrador")) {
                            res.sendRedirect(req.getContextPath() + "/faces/Administrador/Inicio.xhtml");
                        }
                    } else { // utilizador normal
                        if (!reqURI.contains("/Utilizador")) {
                            res.sendRedirect(req.getContextPath() + "/faces/Utilizador/Inicio.xhtml");
                        }
                    }
                }
                if ((ses == null) || ses.getAttribute("username").toString() == null) {
                    System.out.println("-----SES NULL");
//                if (reqURI.contains("javax.faces.resource")){
                    if (!reqURI.contains("/Visitante")) {
                        res.sendRedirect(req.getContextPath() + "/faces/Visitante/Inicio.xhtml");
                    }

                }
            }
//            if (reqURI.indexOf("/faces/Inicio.xhtml") >= 0
//                    || reqURI.indexOf("/Visitante/") >= 0
//                    || ) {
//                System.out.println("-----NO FILTER " + reqURI);
//                chain.doFilter(request, response);
//            } else {
//                System.out.println("-----FILTER!! " + reqURI);
//                chain.doFilter(request, response);
//                res.sendRedirect(req.getContextPath() + "/faces/Visitante/Inicio.xhtml");  // Anonymous user. Redirect to login page
//            }
//            else // user didn't log in but asking for a page that is not allowed so take user to login page
//            {
//                System.out.println("-----FILTER");
//                res.sendRedirect(req.getContextPath() + "/Inicio.xhtml");  // Anonymous user. Redirect to login page
//            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    } //doFilter

    @Override
    public void destroy() {

    }
}
