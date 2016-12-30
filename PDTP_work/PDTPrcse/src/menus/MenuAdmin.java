
package menus;


import controladores.ControladorAdministrador;
import beans.ClientAdminRemote;
;

public class MenuAdmin extends MenuUtilizadorAdmin{
    
    public MenuAdmin(ClientAdminRemote ligacao,ControladorAdministrador controlador) {
         super(ligacao,controlador);
         titulo="Administrador";
        this.controlador=controlador;
        opcoes.add(new OpcaoMenu("Contas", () -> controlador.contas()));
        opcoes.add(new OpcaoMenu("Consultar Denuncias", () -> controlador.consultarDenuncias()));

        opcoes.add(new OpcaoMenu("Cancelar Itens", () -> controlador.cancelarItens()));

        opcoes.add(new OpcaoMenu("Mudar Password", () -> controlador.mudarPassword()));
        opcoes.add(new OpcaoMenu("Enviar Mensagens", () -> controlador.enviarMensagens()));
        opcoes.add(new OpcaoMenu("Consultar Utilizador", () -> controlador.consultarUtilizador()));
        opcoes.add(new OpcaoMenu("Consultar Item", () -> controlador.consultarItem()));
        opcoes.add(new OpcaoMenu("Gerir Categorias", () -> controlador.gerirCategorias()));
    }
    
}
