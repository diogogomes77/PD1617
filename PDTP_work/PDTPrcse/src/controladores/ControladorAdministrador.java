package controladores;

import menus.MenuAdminDenuncias;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import menus.MenuAdminContas;
import beans.ClientAdminRemote;
import beans.SessionException;
import static controladores.Controlador.sc;
import java.util.Iterator;
import java.util.List;
import menus.Menu;
import menus.MenuAdminCategorias;
import static pdtprcse.PDTPrcse.controlador;
import static pdtprcse.PDTPrcse.menu;

/**
 *
 * @author diogo
 */
public class ControladorAdministrador extends ControladorUserAdmin {

    private final ClientAdminRemote ligacaoAdmin;

    /**
     *
     * @param ligacao
     */
    public ControladorAdministrador(ClientAdminRemote ligacao) {
        super(ligacao);
        this.ligacaoAdmin = ligacao;
    }

    /**
     *
     */
    public void consultarDenuncias(Menu anterior) {
        menu = new MenuAdminDenuncias(ligacaoAdmin, (ControladorAdministrador) controlador, anterior);
    }

    /**
     *
     */
    public void consultarReativacoes() {
        ArrayList<String> pedidos;
        try {
            pedidos = ligacaoAdmin.getUtilizadoresPedidoReAtivacao();
            System.out.print("Pedidos de reativacao de conta: ");
            for (String pedido : pedidos) {
                System.out.print(pedido.concat(" "));
            }
            System.out.print("\n");
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void cancelarItens() {
        System.out.println("Cancelar item");
        System.out.print("Item ID: ");
        int itemId = sc.nextInt();
        sc.skip("\n");
        try {
            if (ligacaoAdmin.cancelarItem(itemId)) {
                System.out.print("Item cancelado");
            } else {
                System.out.print("ERRO: Item nao cancelado");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }

    }

    /**
     *
     */
    public void suspenderContas() {
        System.out.print("Suspender username -> ");
        String username = sc.nextLine();
        try {
            if (ligacaoAdmin.suspendeUsername(username)) {
                System.out.println("Utilizador suspenso");
            } else {
                System.out.println("ERRO: Utilizador nao suspenso");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void reativarContas() {
        System.out.print("Reativar username: ");
        String username = sc.nextLine();
        try {
            if (ligacaoAdmin.ativaUtilizador(username)) {
                System.out.println("Utilizador reativado");
            } else {
                System.out.println("ERRO: Utilizador nao reativado");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void consultarUtilizador() {
        System.out.println("Dados do utilizador:");
        String username;
        System.out.print("Username: ");
        username = sc.nextLine();
        try {
            System.out.println(ligacaoAdmin.getDados(username));
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void subMenuGerirCategorias(Menu anterior) {
        menu = new MenuAdminCategorias(ligacaoAdmin, (ControladorAdministrador) controlador, anterior);
    }

    /**
     *
     */
    public void ativarConta() {
        System.out.print("Ativar username: ");
        String username = sc.nextLine();
        try {
            if (ligacaoAdmin.ativaUtilizador(username)) {
                System.out.println("Utilizador ativado");
            } else {
                System.out.println("ERRO: Utilizador nao ativado");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void consultarPedidosAtivacao() {
        ArrayList<String> pedidos;
        try {
            pedidos = ligacaoAdmin.getUtilizadoresPedidoAtivacao();
            System.out.print("Pedidos de ativacao de conta: ");
            for (String pedido : pedidos) {
                System.out.print(pedido.concat(" "));
            }
            System.out.print("\n");
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void consultarPedidosSuspensao() {
        HashMap<String, String> pedidos;
        try {
            pedidos = ligacaoAdmin.getPedidosSuspensao();
            System.out.println("Pedidos de suspensao de conta:");
            Iterator entries = pedidos.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                System.out.println("Username: " + key + ", Razao: " + value);
                //System.out.print("\n");
            }
            System.out.print("\n");
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void subMenuContas(Menu anterior) {
        menu = new MenuAdminContas(ligacaoAdmin, (ControladorAdministrador) controlador, anterior);
    }

    /**
     *
     */
    public void consultarCategorias() {
        //Obter o servidor as categorias
        List<String> categorias;
        try {
            categorias = ligacaoAdmin.obtemCategorias();
            System.out.print("Categorias disponíveis: ");
            for (String categoria : categorias) {
                System.out.print(categoria.concat(" "));
            }
            System.out.print("\n");
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
        //Listar o resultados
        //Listar o resultados
    }

    /**
     *
     */
    public void novaCategoria() {
        System.out.print("Nome da categoria: ");
        String nomecat = sc.nextLine();
        try {
            if (ligacaoAdmin.adicionarCategoria(nomecat)) {
                System.out.println("Categoria Adicionada");
            } else {
                System.out.println("ERRO: Categoria não adicionada");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void eliminarCategoria() {
        System.out.print("Nome da categoria: ");
        String nomecat = sc.nextLine();
        try {
            if (ligacaoAdmin.eliminaCategoria(nomecat)) {
                System.out.println("Categoria Eliminada");
            } else {
                System.out.println("ERRO: Categoria não eliminada");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void modificaCategoria() {
        System.out.print("Nome da categoria: ");
        String nomecat = sc.nextLine();
        System.out.print("novo nome da categoria: ");
        String novoNomeCat = sc.nextLine();
        try {
            if (ligacaoAdmin.modificaCategoria(nomecat, novoNomeCat)) {
                System.out.println("Categoria alterada");
            } else {
                System.out.println("ERRO: Categoria não alterada");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void consultarDenunciasVendedores() {
        System.out.print("Denuncias de Vendedores");
        List<String> denunciasvendedores;
        try {
            denunciasvendedores = ligacaoAdmin.obtemDenunciasVendedores();
            for (String denuncia : denunciasvendedores) {
                System.out.println(denuncia);
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }

    }

    /**
     *
     */
    public void consultarDenunciasItens() {
        System.out.print("Denuncias de Itens");
        List<String> denunciasItens;
        try {
            denunciasItens = ligacaoAdmin.obtemDenunciasItens();
            for (String denuncia : denunciasItens) {
                System.out.println(denuncia);
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }

    }

}
