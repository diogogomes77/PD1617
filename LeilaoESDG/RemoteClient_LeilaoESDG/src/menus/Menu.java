package menus;

import java.util.Scanner;
import java.util.TreeMap;

public abstract class Menu {

    
    protected static TreeMap<Integer, String> menuMap;
    protected static StringBuilder menuText;
    //public abstract int getMenu();

    public static TreeMap<Integer, String> getMenuMap() {
        return menuMap;
    }

    public static StringBuilder getMenuText() {
        return menuText;
    }
   
}
