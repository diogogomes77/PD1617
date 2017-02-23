package controladores;

import java.util.ArrayList;
import java.util.Scanner;
import menus.Menu;
import pdtprcse.PDTPrcse;
import beans.ClientRemote;
import java.util.List;

/**
 *
 * @author diogo
 */
public abstract class Controlador extends PDTPrcse{
    
    /**
     *
     */
    protected ClientRemote ligacao;

    /**
     *
     */
    protected static Scanner sc = new Scanner(System.in);
    
    /**
     *
     * @param ligacao
     */
    protected Controlador(ClientRemote ligacao) {
      this.ligacao=ligacao;
    }

    /**
     *
     */
    public void printInscritos() {
        ArrayList<String> insccritos = ligacao.getUsernameInscritos( );
        int total = insccritos.size();
        System.out.print("Utilizadores inscritos("+total+"): ");
        insccritos.forEach((user) -> {
            System.out.print(user + " ");
        });
        System.out.print("\n");
    }

    /**
     *
     */
    public void printNews() {
        List<String> allNews = ligacao.getNewsletter();
        int total = allNews.size();
        System.out.println("Total de newsletter("+total+"): ");
        allNews.forEach((news) -> {
            System.out.println(news);
        });
        System.out.print("\n");
    }

    /**
     *
     */
    public void printOnline() {
        ArrayList<String> logados = ligacao.getUsernamesOnline();
        int total = logados.size();
        System.out.print("Utilizadores online("+total+"): ");
        logados.forEach((user) -> {
            System.out.print(user + " ");
        });
        System.out.print("\n");
    }

    /**
     *
     */
    public void getTotalItens() {
       
        int total = ligacao.getTotalItens();
        System.out.print("Total de itens: "+total);
        System.out.print("\n");
    }

    /**
     *
     */
    public void sair(){
        System.exit(1);
    }
    
    /**
     *
     * @param newmenu
     */
    public void mostrarMenu(Menu newmenu) {
        menu=newmenu;
        controlador=newmenu.getControlador();
    }
    
    /**
     *
     */
    public abstract void logOff();

}
