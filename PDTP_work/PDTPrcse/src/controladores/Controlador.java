package controladores;

import java.util.ArrayList;
import java.util.Scanner;
import pdtp.ClientRemote;
import pdtprcse.PDTPrcse;

public abstract class Controlador extends PDTPrcse{
    
    protected ClientRemote ligacao;
    protected static Scanner sc = new Scanner(System.in);
    
    protected Controlador() {
      
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
}
