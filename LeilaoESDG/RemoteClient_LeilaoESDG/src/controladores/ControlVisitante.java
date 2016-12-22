package controladores;

import menus.MenuVisitante;
import referencias.ReferenciasVisitante;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import menus.Menu;
import remoteclient_leilaoesdg.main;
import session.VisitanteStatelessRemote;

public class ControlVisitante extends Controlador {
private Menu menu;
//    private static final ControlVisitante instancia = new ControlVisitante();
//    
//    public  ControlVisitante getInstance(){
//        menu = MenuVisitante.getInstance();
//        return instancia;
//    }
    private static VisitanteStatelessRemote visitante;

    public ControlVisitante() {
        this.referencias = ReferenciasVisitante.getInstance();
        ControlVisitante.visitante = (VisitanteStatelessRemote) this.referencias.getUtilizador();
        menu = MenuVisitante.getInstance();
    }

    @Override
    public int getMenu() {

        int opcao;
        Menu.getMenuText().setLength(0);
        Menu.getMenuText().append("*****************************\n");
        Menu.getMenuText().append("\n     Benvindo aos Leilões\n\n");

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
        return opcao;
       // this.escolheOpcao(opcao);

    }

    private static String inscreveMembro() {
        String nome;
        String morada;
        String username;
        String password;
        System.out.println("Nome: ");
        nome = sc.next();
        sc.skip("\n");
        System.out.println("Morada: ");
        morada = sc.next();
        sc.skip("\n");
        System.out.println("Username: ");
        username = sc.next();
        sc.skip("\n");
        boolean ok = false;
        password = "";
        while (!ok) {
            System.out.println("Password: ");
            password = sc.next();
            sc.skip("\n");
            System.out.println("Repita password: ");

            if (password.compareTo(sc.next()) == 0) {
                ok = true;
            } else {
                System.out.println("ERRO: Password nao coincide! Tente novamente... ");
            }
            sc.skip("\n");
        }
        return visitante.inscreverMembro(nome, morada, username, password);
    }

    private Controlador loginMembro() {
        String username;
        String password;
        System.out.println("username: ");
        username = sc.next();
        sc.skip("\n");
        System.out.println("password: ");
        password = sc.next();
        sc.skip("\n");
        if (visitante.loginMembro(username, password)) {
            return new ControlMembro(username);
        }
        System.out.println("ERRO: username ou password desconhecidos");
        return null;
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
                System.out.println(inscreveMembro());
                break;
            case 2:
                System.out.println("Newsletter");
                break;
            case 3:
                System.out.println("Entrar");
                return loginMembro();
               //break;
        }
        return null;
    }
}
