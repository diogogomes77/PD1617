
package views;

import controllers.IControl;
import controllers.MainControl;
import java.util.ArrayList;
import java.util.List;

public class  Menu {
    
    public static String getMenu() {
        MainControl control = new MainControl();
        String menuText="menu\n";
        Integer i = 1;
        ArrayList<IControl> controllers = MainControl.getControllers();
        controllers.forEach((controller) -> {
            menuText.concat(i.toString()+"\t");
            menuText.concat(controller.getTitulo());
            menuText.concat("\n");
        });
        return menuText;
    }
    
}
