package controladores;

import menus.MenuMembro;
import referencias.ReferenciasMembro;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import menus.Menu;
import session.UtilizadorStatefullRemote;

public class ControlMembro extends Controlador {
    private Menu menu;
    private String username;
    //private static ControlMembro instancia = new ControlMembro();
    
//    public ControlMembro getInstance(String username){
//        this.username=username;
//        nome=membro.getNome(username);
//        this.menu = MenuMembro.getInstance();
//        return instancia;
//    }
    private UtilizadorStatefullRemote membro;
    
    private static String nome;

    public ControlMembro(String username) {
        this.referencias = ReferenciasMembro.getInstance();
        this.membro = (UtilizadorStatefullRemote) referencias.getUtilizador();
        this.nome=membro.getNome(username);
        this.menu = MenuMembro.getInstance();
    }

    @Override
    public int getMenu() {

        int opcao;
        Menu.getMenuText().setLength(0);
        Menu.getMenuText().append("*****************************\n");
        Menu.getMenuText().append("\n     Benvindo aos Leilões\n");
        Menu.getMenuText().append("\n     ").append(nome).append("\n\n");
        Set set = Menu.getMenuMap().entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Menu.getMenuText().append("\t").append(mentry.getKey()).append(" - ").append(mentry.getValue()).append("\n");

        }

        Menu.getMenuText().append("\n*****************************\n");
        System.out.println(Menu.getMenuText().toString());
        do {

            System.out.println("Escolha uma opcao (" + Menu.getMenuMap().firstKey() + "-" + Menu.getMenuMap().lastKey() + "):");
            opcao = sc.nextInt();
            sc.skip("\n");
            if (opcao >= 0 && opcao <= Menu.getMenuMap().lastKey()) {
                break;
            }
            System.out.println("Opcao invalida! Escolha outra");
        } while (true);

        return(opcao);

    }
     @Override
    public Controlador escolheOpcao(int opcao) {
        switch (opcao) {
            case 0:
                System.out.println("Sair");
                System.exit(0);
                break;
            case 1:
                System.out.println("Inscricao");
                //System.out.println(inscreveMembro());
                break;
            case 2:
                System.out.println("Newsletter");
                break;
            case 3:
                System.out.println("Terminar sessao");
                return new ControlVisitante();
        }
        return null;
    }
}
