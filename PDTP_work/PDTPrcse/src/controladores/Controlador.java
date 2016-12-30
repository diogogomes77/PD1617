package controladores;

import java.util.ArrayList;
import java.util.Scanner;
import menus.Menu;
import pdtprcse.PDTPrcse;
import beans.ClientRemote;

public abstract class Controlador extends PDTPrcse{
    
    protected ClientRemote ligacao;
    protected static Scanner sc = new Scanner(System.in);
    
    protected Controlador(ClientRemote ligacao) {
      this.ligacao=ligacao;
    }

    public void printInscritos() {
        ArrayList<String> insccritos = ligacao.getUsernameInscritos();
        int total = insccritos.size();
        System.out.print("Utilizadores inscritos("+total+"): ");
        insccritos.forEach((user) -> {
            System.out.print(user + " ");
        });
        System.out.print("\n");
    }
    public void printOnline() {
        ArrayList<String> logados = ligacao.getUsernamesOnline();
        int total = logados.size();
        System.out.print("Utilizadores online("+total+"): ");
        logados.forEach((user) -> {
            System.out.print(user + " ");
        });
        System.out.print("\n");
    }
    
    public void sair(){
        System.exit(1);
    }
    
    public void mostrarMenu(Menu newmenu) {
        menu=newmenu;
        controlador=newmenu.getControlador();
    }
}
