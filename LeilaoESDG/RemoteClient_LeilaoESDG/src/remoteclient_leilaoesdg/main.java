package remoteclient_leilaoesdg;

import controladores.ControlVisitante;
import controladores.Controlador;
import menus.MenuVisitante;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.UtilizadorStatefullRemote;
import session.VisitanteStatelessRemote;

public class main {
    public static void main(String[] args) {
        //Controlador control = ControlVisitante.getInstance();
         Controlador control = new ControlVisitante();
        int opcao;
        do {
            opcao=control.getMenu();
            Object result = control.escolheOpcao(opcao);
            if (result !=null){
                control=(Controlador)result;
            }
        } while (true);
    }


}
