/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        //this.checkFile(viewId);
    }

    public String getViewId() {
        return viewId;
    }

    public String getTitle() {
        return title;
    }

    public void createFile(String path, String fullUrl) {
        path = "/tmp/PDTP" + fullUrl;
        File targetFile = new File(path);
        File parent = targetFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }
        try {
            targetFile.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(MenuPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
