/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;

/**
 *
 * @author diogo
 */
public class MenuPage {
    String viewId;
    String title;

    public MenuPage(String viewId, String title) {
        this.viewId = viewId;
        this.title = title;
    }

    public String getViewId() {
        return viewId;
    }

    public String getTitle() {
        return title;
    }
    
}
