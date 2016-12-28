package menus;

import controladores.Controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import pdtprcse.PDTPrcse;


public abstract class Menu extends PDTPrcse{

    
    protected static HashMap<Integer, String> menuMap;
    protected static StringBuilder menuText;
    //public abstract int getMenu();
    protected HashMap<Integer, OpcaoMenu> comandos;
         protected static Scanner sc = new Scanner(System.in);
    protected ArrayList<OpcaoMenu> opcoes;
    private  Integer numOpcoes;
    
    protected Controlador controlador;

    protected String titulo;
    
    protected Menu() {
       opcoes =  new ArrayList();
        OpcaoMenu newsletter = new OpcaoMenu("Newsletter",() -> controlador.printInscritos());
        OpcaoMenu sair = new OpcaoMenu("Sair",() -> controlador.sair());
        opcoes.add(sair);
        opcoes.add(newsletter);
        //this.controlador=controlador;
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
        menuText.append("Menu de ").append(titulo).append("\n");
        Iterator it = comandos.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry par = (Map.Entry)it.next();
            OpcaoMenu opcao = (OpcaoMenu) par.getValue();
            menuText.append(par.getKey()).append(" - ").append(opcao.getOpcao()).append("\n");
         
        }
        menuText.append("\n*****************************\n");
    }
    public boolean escolheOpcao() {
        geraMenu();
        controlador.printInscritos();
        controlador.printOnline();
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
   
        OpcaoMenu escolha = comandos.get(opcao);
   
        escolha.getFuncao().run();
        return true;
    }  

    public Controlador getControlador() {
        return controlador;
    }
    
}