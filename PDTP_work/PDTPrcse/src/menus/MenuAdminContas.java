
package menus;

import controladores.ControladorAdministrador;
import beans.ClientAdminRemote;

public class MenuAdminContas extends Menu {

    public MenuAdminContas (ClientAdminRemote ligacao,ControladorAdministrador controlador) {
        super();
        titulo="Administrador - Contas";
         this.controlador=controlador;
        opcoes.add(new OpcaoMenu("Consultar Adesoes e Reativacoes", () -> controlador.consultarAdesoes()));
        opcoes.add(new OpcaoMenu("Activar Contas", () -> controlador.ativarConta()));
        opcoes.add(new OpcaoMenu("Reactivar Contas", () -> controlador.reativarContas()));
        opcoes.add(new OpcaoMenu("Consultar Pedidos de Suspensao", () -> controlador.consultarPedidosSuspensao()));
        opcoes.add(new OpcaoMenu("Suspender Contas", () -> controlador.suspenderContas()));
        opcoes.add(new OpcaoMenu("Voltar", () -> controlador.mostrarMenu(new MenuAdmin(ligacao,controlador))));
        
    }
    
    
}
