
package menus;

import controladores.ControladorAdministrador;
import beans.ClientAdminRemote;

/**
 *
 * @author diogo
 */
public class MenuAdminDenuncias extends MenuUtilizadorAdmin {

    /**
     *
     * @param ligacao
     * @param controlador
     */
    public MenuAdminDenuncias (ClientAdminRemote ligacao,ControladorAdministrador controlador, Menu anterior) {
        super(ligacao,controlador, anterior);
        titulo= anterior.titulo + " - Denuncias";
         this.controlador=controlador;
         
        opcoes.add(new OpcaoMenu("Consultar denuncias de itens", () -> controlador.consultarDenunciasItens()));
        opcoes.add(new OpcaoMenu("Consultar denuncias de vendedores", () -> controlador.consultarDenunciasVendedores()));        
    }
    
    
}
