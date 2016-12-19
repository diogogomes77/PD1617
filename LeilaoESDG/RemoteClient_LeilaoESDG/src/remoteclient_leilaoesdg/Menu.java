package remoteclient_leilaoesdg;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Menu {

    private static Scanner sc = new Scanner(System.in);
    private static TreeMap<Integer, String> menuMap;
    private static StringBuilder menuText;

    public Menu() {
        Menu.menuMap = new TreeMap<>();
        Menu.menuText = new StringBuilder("");
        // adicionar aqui as opçoes do menu
        menuMap.put(1, "Incricao");
        menuMap.put(2, "Newsletter");
        menuMap.put(0, "Sair");

    }

    public static int getMenuVisitante() {

        int opcao;

        menuText.append("*****************************\n");
        menuText.append("\n     Benvindo aos Leilões\n\n");

        Set set = menuMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            menuText.append("\t").append(mentry.getKey()).append(" - ").append(mentry.getValue()).append("\n");

        }

        menuText.append("\n*****************************\n");
        System.out.println(menuText.toString());
        do {
            
            System.out.println("Escolha uma opcao ("+menuMap.firstKey()+"-"+menuMap.lastKey()+"):");
            opcao = sc.nextInt();
            sc.skip("\n");
            if (opcao >= 0 && opcao <= menuMap.lastKey()) {
                break;
            }
            System.out.println("Opcao invalida! Escolha outra");
        } while (true);

        return opcao;

    }
}
