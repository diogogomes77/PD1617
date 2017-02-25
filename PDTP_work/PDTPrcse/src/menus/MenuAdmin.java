package menus;

import controladores.ControladorAdministrador;
import beans.ClientAdminRemote;

;

/**
 *
 * @author diogo
 */
public class MenuAdmin extends MenuUtilizadorAdmin {

    /**
     *
     * @param ligacao
     * @param controlador
     */
    public MenuAdmin(ClientAdminRemote ligacao, ControladorAdministrador controlador) {
        super(ligacao, controlador);
        titulo = "Administrador";
        this.controlador = controlador;
        opcoes.add(new OpcaoMenu("> Contas", () -> controlador.subMenuContas()));
        opcoes.add(new OpcaoMenu("Consultar Denuncias", () -> controlador.consultarDenuncias()));

        opcoes.add(new OpcaoMenu("Cancelar Itens", () -> controlador.cancelarItens()));

        opcoes.add(new OpcaoMenu("Mudar Password", () -> controlador.alterarPassword()));
        opcoes.add(new OpcaoMenu("Enviar Mensagens", () -> controlador.enviarMensagem()));
        opcoes.add(new OpcaoMenu("Minhas mensagens", () -> controlador.consultarMensagensMinhas()));
        opcoes.add(new OpcaoMenu("Consultar Utilizador", () -> controlador.consultarUtilizador()));
        opcoes.add(new OpcaoMenu("Consultar Item", () -> controlador.consultarItem()));
        opcoes.add(new OpcaoMenu("> Gerir Categorias", () -> controlador.subMenuGerirCategorias()));
    }

}
