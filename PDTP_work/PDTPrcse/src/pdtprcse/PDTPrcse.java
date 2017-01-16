package pdtprcse;

import controladores.*;

import javax.naming.InitialContext;
import menus.Menu;
import menus.MenuVisitante;
import beans.ClientRemote;
import beans.ClientVisitanteRemote;

/**
 *
 * @author diogo
 */
public class PDTPrcse {

    /**
     *
     */
    public static Controlador controlador;

    /**
     *
     */
    public static Menu menu;
    static ClientRemote ligacao;
    static InitialContext ctx;

    static Referencias referencia;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        ReferenciaVisitante refVisitante = new ReferenciaVisitante();
        referencia = refVisitante;
        ClientVisitanteRemote clientVisitante = refVisitante.getLigacao();
        ligacao = clientVisitante;
        ControladorVisitante controladorVisitante = new ControladorVisitante(clientVisitante);
        controlador = controladorVisitante;
        menu = new MenuVisitante(clientVisitante,controladorVisitante);
        
        try{
            boolean continuar = true;
            while (continuar) {
                continuar = menu.escolheOpcao();
            }
        }
        finally{
            System.out.println("Fim...");
            controlador.logOff();
        }
    }  
}
