package menus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import static pdtprcse.PDTPrcse.logOff;
import static pdtprcse.PDTPrcse.loginUtilizador;
import static pdtprcse.PDTPrcse.registarUtilizador;

public abstract class Menu {

    
    protected static HashMap<Integer, String> menuMap;
    protected static StringBuilder menuText;
    //public abstract int getMenu();
    protected HashMap<Integer, OpcaoMenu> comandos;
         protected static Scanner sc = new Scanner(System.in);
    protected ArrayList<OpcaoMenu> opcoes;
    private  Integer numOpcoes;
    


    protected Menu() {
       opcoes =  new ArrayList();
        OpcaoMenu newsletter = new OpcaoMenu("Newsletter",() -> registarUtilizador());
        OpcaoMenu sair = new OpcaoMenu("Sair",() -> logOff());
        opcoes.add(sair);
        opcoes.add(newsletter);
        
        comandos = new HashMap<>();
        
    }
    private void geraMenu(){
        numOpcoes=0;
        for (OpcaoMenu opcao: opcoes){
            comandos.put(numOpcoes,opcao);
            numOpcoes++;
        }
        menuText = new StringBuilder();
        menuText.setLength(0);
        menuText.append("*****************************\n");
        menuText.append("\nBenvindo a Leiloeira PD1617\n\n");
        Iterator it = comandos.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry par = (Map.Entry)it.next();
            OpcaoMenu opcao = (OpcaoMenu) par.getValue();
            menuText.append(par.getKey()).append(" - ").append(opcao.getOpcao()).append("\n");
         
        }
        menuText.append("\n*****************************\n");
    }
    public boolean escolheMenu() {
        geraMenu();
        int opcao;
        System.out.println(menuText.toString());
        do {
            System.out.print("Escolha uma opcao (0-" + (numOpcoes-1) + "): ");
            opcao = sc.nextInt();
            sc.skip("\n");
            if (opcao == 0){
                return false;
            }
            if (opcao >= 1 && opcao <= numOpcoes-1) {
                break;
            }
            System.out.println("Opcao invalida! Escolha outra");
        } while (true);
        System.out.println(comandos);
        OpcaoMenu escolha = comandos.get(opcao);
       // System.out.println(escolha.getOpcao());
        escolha.getFuncao().run();
        return true;
    }  
}