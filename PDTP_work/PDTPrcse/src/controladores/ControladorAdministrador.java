package controladores;

import menus.MenuAdminDenuncias;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import menus.MenuAdminContas;
import beans.ClientAdminRemote;
import static controladores.Controlador.sc;
import java.util.Iterator;
import java.util.List;
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
    public void consultarDenuncias() {
        menu = new MenuAdminDenuncias(ligacaoAdmin, (ControladorAdministrador) controlador);
    }

    /**
     *
     */
    public void consultarReativacoes() {
        ArrayList<String> pedidos = ligacaoAdmin.getUtilizadoresPedidoReAtivacao();
        System.out.print("Pedidos de reativacao de conta: ");
        for (String pedido : pedidos) {
            System.out.print(pedido.concat(" "));
        }
        System.out.print("\n");
    }

    /**
     *
     */
    public void cancelarItens() {
        System.out.println("Cancelar item");
        System.out.print("Item ID: ");
        int itemId = sc.nextInt();
        sc.skip("\n");
        if (ligacaoAdmin.cancelarItem(itemId))
            System.out.print("Item cancelado");
        else
            System.out.print("ERRO: Item nao cancelado");
        
    }

    /**
     *
     */
    public void suspenderContas() {
        System.out.print("Suspender username -> ");
        String username = sc.nextLine();
        if (ligacaoAdmin.suspendeUsername(username)) {
            System.out.println("Utilizador suspenso");
        } else {
            System.out.println("ERRO: Utilizador nao suspenso");
        }
    }

    /**
     *
     */
    public void reativarContas() {
        System.out.print("Reativar username: ");
        String username = sc.nextLine();
        if (ligacaoAdmin.ativaUtilizador(username)) {
            System.out.println("Utilizador reativado");
        } else {
            System.out.println("ERRO: Utilizador nao reativado");
        }
    }


    /**
     *
     */
    public void consultarUtilizador() {
        System.out.println("Dados do utilizador:");
        String username = "";
        System.out.print("Username: ");
        username = sc.nextLine();
        System.out.println(ligacaoAdmin.getDados(username));

    }

    /**
     *
     */
    public void consultarItem() {
        System.out.println("Consultar Item");
        System.out.print("ItemID: ");
        int itemId = sc.nextInt();
        sc.skip("\n");
        System.out.println(ligacaoAdmin.mostraItem(itemId));
        currentItemId = itemId;
        //menu = new MenuUtilizadorConsultarItem(ligacao, (ControladorUtilizador) controlador);

    }

    /**
     *
     */
    public void subMenuGerirCategorias() {
        menu = new MenuAdminCategorias(ligacaoAdmin, (ControladorAdministrador) controlador);
    }

    /**
     *
     */
    public void ativarConta() {
        System.out.print("Ativar username: ");
        String username = sc.nextLine();
        if (ligacaoAdmin.ativaUtilizador(username)) {
            System.out.println("Utilizador ativado");
        } else {
            System.out.println("ERRO: Utilizador nao ativado");
        }
    }

    /**
     *
     */
    public void consultarPedidosAtivacao() {
        ArrayList<String> pedidos = ligacaoAdmin.getUtilizadoresPedidoAtivacao();
        System.out.print("Pedidos de ativacao de conta: ");
        for (String pedido : pedidos) {
            System.out.print(pedido.concat(" "));
        }
        System.out.print("\n");
    }

    /**
     *
     */
    public void consultarPedidosSuspensao() {
        HashMap<String, String> pedidos = ligacaoAdmin.getPedidosSuspensao();
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
    }

    /**
     *
     */
    public void subMenuContas() {
        menu = new MenuAdminContas(ligacaoAdmin, (ControladorAdministrador) controlador);
    }

    /**
     *
     */
    public void consultarCategorias() {
        //Obter o servidor as categorias
        List<String> categorias = ligacaoAdmin.obtemCategorias();
        System.out.print("Categorias disponíveis: ");
        for (String categoria : categorias) {
            System.out.print(categoria.concat(" "));
        }
        System.out.print("\n");
        //Listar o resultados
    }

    /**
     *
     */
    public void novaCategoria() {
        System.out.print("Nome da categoria: ");
        String nomecat = sc.nextLine();
        if (ligacaoAdmin.adicionarCategoria(nomecat)) {
            System.out.println("Categoria Adicionada");
        } else {
            System.out.println("ERRO: Categoria não adicionada");
        }
    }

    /**
     *
     */
    public void eliminarCategoria() {
        System.out.print("Nome da categoria: ");
        String nomecat = sc.nextLine();
        if (ligacaoAdmin.eliminaCategoria(nomecat)) {
            System.out.println("Categoria Eliminada");
        } else {
            System.out.println("ERRO: Categoria não eliminada");
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
        if (ligacaoAdmin.modificaCategoria(nomecat, novoNomeCat)) {
            System.out.println("Categoria alterada");
        } else {
            System.out.println("ERRO: Categoria não alterada");
        }
    }

    /**
     *
     */
    public void consultarDenunciasVendedores() {
        System.out.print("Denuncias de Vendedores");
        List<String> denunciasvendedores = ligacaoAdmin.obtemDenunciasVendedores();

        for (String denuncia : denunciasvendedores) {
            System.out.println(denuncia);
        }
    }

    /**
     *
     */
    public void consultarDenunciasItens() {
        System.out.print("Denuncias de Itens");
        List<String> denunciasItens = ligacaoAdmin.obtemDenunciasItens();

        for (String denuncia : denunciasItens) {
            System.out.println(denuncia);
        }

    }

}
