/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advgame;

import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author diogo
 */
@Local
public interface AdvGameLocal {

    ArrayList<String> getHiScores();

    int getMyScore(String name);

    int getMyAttempts(String name);

    boolean acceptNewPlayer(String name);

    TryResult tryNumber (String name, int numero);

    boolean logOff(String name);
    
}
