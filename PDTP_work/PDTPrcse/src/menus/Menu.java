package menus;

import controladores.Controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import pdtprcse.PDTPrcse;

/**
 *
 * @author diogo
 */
public abstract class Menu extends PDTPrcse {

    /**
     *
     */
    protected static HashMap<Integer, String> menuMap;

    /**
     *
     */
    protected static StringBuilder menuText;
    //public abstract int getMenu();

    /**
     *
     */
    protected HashMap<Integer, OpcaoMenu> comandos;

    /**
     *
     */
    protected static Scanner sc = new Scanner(System.in);

    /**
     *
     */
    protected ArrayList<OpcaoMenu> opcoes;
    private Integer numOpcoes;

    /**
     *
     */
    protected Controlador controlador;

    /**
     *
     */
    protected String titulo;

    /**
     *
     */
    protected Menu() {
        opcoes = new ArrayList<OpcaoMenu>();
        OpcaoMenu newsletter = new OpcaoMenu("Newsletter", () -> controlador.printNews());
        OpcaoMenu sair = new OpcaoMenu("Sair", () -> controlador.sair());
        opcoes.add(sair);
        opcoes.add(newsletter);
        //this.controlador=controlador;
        comandos = new HashMap<>();

    }

    private void geraMenu() {
        numOpcoes = 0;
        for (OpcaoMenu opcao : opcoes) {
            comandos.put(numOpcoes, opcao);
            numOpcoes++;
        }
        menuText = new StringBuilder();
        menuText.setLength(0);
        menuText.append("_______________________________\n");
        menuText.append("\n Benvindo a Leiloeira PD1617\n\n");
        menuText.append(" Menu de ").append(titulo).append("\n");
        Iterator it = comandos.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry par = (Map.Entry) it.next();
            OpcaoMenu opcao = (OpcaoMenu) par.getValue();
            menuText.append(" ");
            menuText.append(par.getKey()).append(" - ").append(opcao.getOpcao()).append("\n");

        }
        //menuText.append("\n Diogo Gomes & Eugenio Santos ");
        //menuText.append("\n_______________________________\n");
    }

    private void mostraMenu() {
        geraMenu();
        System.out.println("_______________________________");
        controlador.printInscritos();
        controlador.printOnline();
        controlador.getTotalItens();
        //System.out.println("_______________________________");
        System.out.println(menuText.toString());
    }

    /**
     *
     * @return
     */
    public boolean escolheOpcao() {
        mostraMenu();
        int opcao;

        do {
            System.out.print("Escolha uma opcao (0-" + (numOpcoes - 1) + "): ");
            String input = sc.nextLine();
            boolean escolheInt = false;
            if (input.length() < 1) {
                mostraMenu();
            } else {
                try {
                    opcao = Integer.parseInt(input);
                    if (opcao == 0) {
                        return false;
                    }
                    if (opcao >= 1 && opcao <= numOpcoes - 1) {
                        break;
                    }
                    System.out.println("Opcao invalida! Escolha outra");
                } catch (NumberFormatException nfe) {
                    mostraMenu();
                }
            }
        }while (true);

            OpcaoMenu escolha = comandos.get(opcao);

            escolha.getFuncao().run();
            return true;
        }  

    /**
     *
     * @return
     */
    public Controlador getControlador() {
        return controlador;
    }

}
