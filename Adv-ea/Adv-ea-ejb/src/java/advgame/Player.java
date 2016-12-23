/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advgame;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 *
 * @author diogo
 */
public class Player implements Serializable{
    String name;
    int score;
    int attempts;
    boolean advised;
    boolean logged;
    long lastAction;
    
    public Player(String name){
        this.name=name;
        this.advised=false;
        lastAction=LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
        logged=true;
    }
    
    public String getname() {
        return name;
    }
    public int getScore(){
        return score;
    }
    public int getAttempts(){
        return attempts;
    }
    public void addScore(){
        score++;
    }
    public void addAttempts(){
        attempts++;
    }
    public boolean advised(){
        return advised;
    }
    public void resetAdvised(){
        advised = false;
    }
    public void setAdvised(){
        advised=true;
    }
    public void setLastAction(){
        lastAction=LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
    }
    public boolean lastActionMoreThan(long seconds){
        return LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond()
        - lastAction < seconds;
    }
    public long fromLastActionFromNoew(long now) {
        return now - lastAction;
    }
    public boolean isLogged() {
        return logged;
    }
    public void setLogged() {
        logged = true;
    }
    public void resetLogged() {
        logged = false;
    }
    @Override
    public String toString(){
        return name + ": Pontos="+score+
                " Tent="+attempts+
                " ("+(logged?"logado":"nao logado") + ")";
    }
    @Override
    public boolean equals(Object x){
        if(x==null)
            return false;
        if(getClass() != x.getClass())
            return false;
        Player j = (Player) x;
        return name.compareToIgnoreCase(j.name) == 0;
    }
    @Override
    public int hashCode(){
        return name.hashCode();
    }
}
