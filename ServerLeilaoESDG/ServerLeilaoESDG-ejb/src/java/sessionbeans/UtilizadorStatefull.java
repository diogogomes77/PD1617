
package sessionbeans;

import javax.ejb.Stateful;
import views.Menu;

@Stateful
public class UtilizadorStatefull implements UtilizadorStatefullRemote {


    @Override
    public int teste() {
        return 123;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String getMenu() {
        return Menu.getMenu();
    }
    
}
