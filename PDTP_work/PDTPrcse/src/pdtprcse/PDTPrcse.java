package pdtprcse;

import controladores.*;

import javax.naming.InitialContext;
import menus.Menu;
import menus.MenuVisitante;
import pdtp.ClientRemote;
import pdtp.ClientVisitanteRemote;

public class PDTPrcse {
    public static Controlador controlador;
    public static Menu menu;
    static ClientRemote ligacao;
    static InitialContext ctx;

    static Referencias referencia;

    public static void main(String[] args) {
        ReferenciaVisitante refVisitante = new ReferenciaVisitante();
        referencia = refVisitante;
        ClientVisitanteRemote clientVisitante = refVisitante.getLigacao();
        ligacao = clientVisitante;
        ControladorVisitante controladorVisitante = new ControladorVisitante(clientVisitante);
        controlador = controladorVisitante;
        menu = new MenuVisitante(clientVisitante,controladorVisitante);
        
        boolean continuar = true;
        while (continuar) {
            continuar = menu.escolheOpcao();
        }
    }  
}
