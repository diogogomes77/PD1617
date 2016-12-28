
package menus;


import controladores.ControladorAdministrador;
import pdtp.ClientAdminRemote;
;

public class MenuAdministrador extends MenuUtilizadorAdministrador{
    
    public MenuAdministrador(ClientAdminRemote ligacao,ControladorAdministrador controlador) {
         super(ligacao,controlador);
         titulo="Administrador";
        this.controlador=controlador;
        opcoes.add(new OpcaoMenu("Consultar Denuncias", () -> controlador.consultarDenuncias()));
        opcoes.add(new OpcaoMenu("Consultar Adesoes e Reativacoes", () -> controlador.consultarAdesoes()));
        opcoes.add(new OpcaoMenu("Cancelar Itens", () -> controlador.cancelarItens()));
        opcoes.add(new OpcaoMenu("Suspender Contas", () -> controlador.suspenderContas()));
        opcoes.add(new OpcaoMenu("Reactivar Contas", () -> controlador.reativarContas()));
        opcoes.add(new OpcaoMenu("Mudar Password", () -> controlador.mudarPassword()));
        opcoes.add(new OpcaoMenu("Enviar Mensagens", () -> controlador.enviarMensagens()));
        opcoes.add(new OpcaoMenu("Consultar Utilizador", () -> controlador.consultarUtilizador()));
        opcoes.add(new OpcaoMenu("Consultar Item", () -> controlador.consultarItem()));
        opcoes.add(new OpcaoMenu("Gerir Categorias", () -> controlador.gerirCategorias()));
    }
    
}
