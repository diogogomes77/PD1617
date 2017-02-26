
package menus;

import controladores.ControladorAdministrador;
import beans.ClientAdminRemote;

/**
 *
 * @author diogo
 */
public class MenuAdminCategorias extends MenuUtilizadorAdmin {

    /**
     *
     * @param ligacao
     * @param controlador
     */
    public MenuAdminCategorias (ClientAdminRemote ligacao,ControladorAdministrador controlador, Menu anterior) {
        super(ligacao,controlador, anterior);
        titulo=anterior.titulo + " - Categorias";
         this.controlador=controlador;
        opcoes.add(new OpcaoMenu("Listar categorias", () -> controlador.consultarCategorias()));
        opcoes.add(new OpcaoMenu("Nova categoria", () -> controlador.novaCategoria()));
        opcoes.add(new OpcaoMenu("Alterar Categoria", () -> controlador.modificaCategoria()));
        opcoes.add(new OpcaoMenu("Eliminar Categoria", () -> controlador.eliminarCategoria()));
        
    }
    
    
}
