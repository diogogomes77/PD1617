/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfclasses;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

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
    
    public void checkFile(String fullUrl){
           File yourFile = new File(fullUrl);
        try {
            yourFile.createNewFile();
//            try {
//                String content = new Scanner(new File("index.xhtml")).useDelimiter("\\Z").next();
//                Files.write(Paths.get(fullUrl), "the text".getBytes(), StandardOpenOption.APPEND);
//            } catch (IOException e) {
//                //exception handling left as an exercise for the reader
//            }
        } catch (IOException ex) {
            System.out.println("File " + fullUrl + " Exists");
        }
    }
     
}
