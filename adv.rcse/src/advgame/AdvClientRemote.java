/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advgame;

import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author diogo
 */
@Remote
public interface AdvClientRemote {

    boolean registerName(String name);

    int getMyScore();

    ArrayList<String> getHiScores();

    int getMyAttempts();

     TryResult tryNumber(int numero);

    boolean logOff();
    
}
