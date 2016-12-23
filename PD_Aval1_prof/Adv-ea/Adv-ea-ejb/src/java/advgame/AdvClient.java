/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advgame;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author diogo
 */
@Stateful
public class AdvClient implements AdvClientRemote {

    String myName;
    
    @EJB AdvGameLocal advGame;
    @Override
    public boolean registerName(String name) {
        //if (MyName != null))
        //return false;
        if (advGame.acceptNewPlayer(name)){
            myName = name;
            return true;
        }
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public int getMyScore() {
        return advGame.getMyScore(myName);
    }

    @Override
    public ArrayList<String> getHiScores() {
        return null;
    }

    @Override
    public int getMyAttempts() {
        return advGame.getMyAttempts(myName);
    }

    @Override
    public TryResult tryNumber(int numero) {
        if (myName == null)
            return TryResult.NoName; // testado no singleton
        
        return advGame.tryNumber(myName,numero);
    }

    @Override
    public boolean logOff() {
        myName = null;
        return advGame.logOff(myName); // Singleeton testa MyName == null
    }
    
}
