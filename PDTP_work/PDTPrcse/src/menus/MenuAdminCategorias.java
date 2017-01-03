
package menus;

import controladores.ControladorAdministrador;
import beans.ClientAdminRemote;

public class MenuAdminCategorias extends Menu {

    public MenuAdminCategorias (ClientAdminRemote ligacao,ControladorAdministrador controlador) {
        super();
        titulo="Administrador - Categorias";
         this.controlador=controlador;
        opcoes.add(new OpcaoMenu("Listar categorias", () -> controlador.consultarCategorias()));
        opcoes.add(new OpcaoMenu("Nova categoria", () -> controlador.novaCategoria()));
        opcoes.add(new OpcaoMenu("Eliminar Categoria", () -> controlador.eliminarCategoria()));
        opcoes.add(new OpcaoMenu("Voltar", () -> controlador.mostrarMenu(new MenuAdmin(ligacao,controlador))));
        
    }
    
    
}
