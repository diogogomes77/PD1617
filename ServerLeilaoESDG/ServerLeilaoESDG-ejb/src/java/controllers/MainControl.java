
package controllers;

import java.util.ArrayList;
import java.util.List;

public class MainControl {
    static ArrayList<IControl> controllers;

    public MainControl() {
        controllers = new ArrayList<>();
        controllers.add(new NewsLetterController());
        controllers.add(new InscricaoController());
    }

    public static ArrayList<IControl> getControllers() {
        return controllers;
    }
    
}
