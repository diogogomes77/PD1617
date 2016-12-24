/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdtp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author diogo
 */
@Singleton
public class Leiloeira implements LeiloeiraLocal {

   HashMap<String,Utilizador> utilizadores = new HashMap<>();
    Random rnd = new Random();
    int secretNum;
    public Leiloeira(){
        newNumber();
    }
    @Override
    public ArrayList<String> getHiScores() {
       Collection<Utilizador> todos = utilizadores.values();
       ArrayList<String> hisc = new ArrayList<>(); //pouco eficiente
       todos.forEach((j)-> {
           hisc.add(j.toString()); // ou so j
       });
       return hisc;
       // nao atualiza timetampo porque pode nem estar logado;
    }

    @Override
    public int getMyScore(String name) {
        if (name == null)
            return 0;
        Utilizador j = utilizadores.get(name);
        if(j== null)
            return 0;
        j.setLastAction();
        return j.getScore();
    }

    @Override
    public int getMyAttempts(String name) {
        if (name ==null)
            return 0;
        Utilizador j = utilizadores.get(name);
        if (j==null)
            return 0;
        j.setLastAction();
        Object x; // ???
        return j.getScore();
    }

    @Override
    public boolean acceptNewUtilizador(String name) {
        if(name==null)
            return false;
        Utilizador j= utilizadores.get(name);
        if (j!= null) //ja existe
            if(j.isLogged()) // esta logado -Z nao deixa repetir user
                return false;
            else{
                j.setLogged();
                j.setLastAction();
                return true;
            }
        j = new Utilizador(name);
        j.setLastAction();
        utilizadores.put(name,j);
        return true;
    }

    @Override
    public  TryResult tryNumber(String name, int numero) {
        if (name==null)
            return TryResult.Error;
        Utilizador j = utilizadores.get(name);
        if(j==null)
            return TryResult.Error;
        if(!j.isLogged())
            return TryResult.NotLogged;
        j.setLastAction();
        if(!j.advised()){
            j.setAdvised();
            return TryResult.NewNumber;
        }
        j.addAttempts();
        if (numero==secretNum){
            j.addScore();
             newNumber();
            return TryResult.Right;
        }
        if (numero > secretNum)
            return TryResult.TooBig;
        else
            return TryResult.TooSmall;
    }

    private void newNumber(){
        secretNum = rnd.nextInt(100)+1;
        //avisa todos que o numero mudou
        Collection<Utilizador> todos = utilizadores.values();
        ArrayList<String> hisc = new ArrayList<>(); // pouco eficiente
        todos.forEach((j)->{
            j.resetAdvised();
        });
    }
    @Override
    public boolean logOff(String name) {
        if (name == null) //quem?
            return false;
        Utilizador j = utilizadores.get(name);
        if (j==null)
            return false; //nao conheco
        if (!j.isLogged())
            return false;
        j.resetLogged(); //unloga
        return true;
    }
    @Schedule(second= "*/5",minute= "*",hour= "*")
    public void checkInactivity() throws InterruptedException {
        long now=LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
        Collection<Utilizador> todos = utilizadores.values();
        for (Utilizador j :todos)
            if(j.isLogged())
                if(j.fromLastActionFromNoew(now)>60)
                    j.resetLogged();
    }
    @PostConstruct
    public void loadstate(){
        try (ObjectInputStream ois =
                new ObjectInputStream(
                        new BufferedInputStream(
                        new FileInputStream("/tmp/advUtilizadors") ))) {
            utilizadores = (HashMap<String,Utilizador>) ois.readObject() ;
        }
        catch (Exception e){
            //Utilizadors = fica com o objecto vazio criado no construtor
        }
    }
    @PreDestroy
    public void saveState(){
        try (ObjectOutputStream oos =
                new ObjectOutputStream(
                new BufferedOutputStream(
                new FileOutputStream("/tmp/advUtilizadors") ))) {
            oos.writeObject(utilizadores);
        }
        catch (Exception e){
            
        }
    }
}
