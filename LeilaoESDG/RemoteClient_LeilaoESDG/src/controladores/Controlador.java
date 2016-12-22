
package controladores;

import menus.Menu;
import referencias.Referencias;
import java.util.Scanner;

public abstract class Controlador {
    
    protected Referencias referencias;
    public abstract int getMenu();
    protected static Scanner sc = new Scanner(System.in);
    public abstract Controlador escolheOpcao(int opcao);
}
