/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import pdtprcse.ReferenciaAdmin;
import pdtprcse.ReferenciaUtilizador;
import pdtprcse.ReferenciaVisitante;

/**
 *
 * @author diogo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGlobal {
    
    private static String username ="";
    private static String password="";
    private static int contador = 2;
    private static ClientUtilizadorRemote utilizadorRemote;
    
    public TestGlobal() {
       
    }
    
    @BeforeClass
    public static void setUpClass() {
      
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         
        
    }
    
    @After
    public void tearDown() {
    }
    
    private ClientAdminRemote getInstanceAdmin(){
        ReferenciaAdmin refVisitante = new ReferenciaAdmin();
        ClientAdminRemote instance = refVisitante.getLigacao();
        return instance;
    }
    private ClientVisitanteRemote getInstanceVisitante(){
        ReferenciaVisitante refVisitante = new ReferenciaVisitante();
        ClientVisitanteRemote instance = refVisitante.getLigacao();
        return instance;
    }
    private ClientUtilizadorRemote getInstanceUtilizador(){
        ReferenciaUtilizador refUtilizador = new ReferenciaUtilizador();
        ClientUtilizadorRemote instance = refUtilizador.getLigacao();
        return instance;
    }
    
     /**
     * Test of inscreveUtilizador method, of class ClientVisitanteRemote.
     */
    @Test
    public void test1InscreveUtilizador() {
         contador++;
         username = "user".concat(Integer.toString(contador));
        password = username;
        System.out.println("inscreveUtilizador ".concat(username));
        String nome = username;
        String morada = "testes";
       
        boolean expResult = true;
        boolean result = getInstanceVisitante().inscreveUtilizador(nome, morada, username, password);
        assertEquals(expResult, result);

    }
    @Test
    public void test2LoginInscrito() {
        System.out.println("loginInscrito ".concat(username));
        String name = username;
        
        boolean expResult = false;
        boolean result = getInstanceVisitante().loginUtilizador(name, password);
        assertEquals(expResult, result);

    }
    /**
     * Test of loginUtilizador method, of class ClientVisitanteRemote.
     */
    @Test
    public void test3LoginAdmin() {
        System.out.println("loginAdmin");
        String name = "admin";
        String password = "admin";
        boolean expResult = true;
        boolean result = getInstanceVisitante().loginUtilizador(name, password);
        assertEquals(expResult, result);

    }
    /**
     * Test of ativaUtilizador method, of class ClientAdminRemote.
     */
    @Test
    public void test4AtivaUtilizador() {
        System.out.println("ativaUtilizador ".concat(username));
        //String username = username;
        boolean expResult = true;
        boolean result = getInstanceAdmin().ativaUtilizador(username);
        assertEquals(expResult, result);
       
    }
    /**
     * Test of logOff method, of class ClientAdminRemote.
     */
    @Test
    public void test5LogOffAdmin() {
        System.out.println("logOffAdmin");
        boolean expResult = true;
        boolean result = getInstanceAdmin().logOff();
        assertEquals(expResult, result);
     
    }
    @Test
    public void test6LoginUtilizador() {
        System.out.println("loginUtilizador ".concat(username));
        String name = username;
        System.out.println(username);
        boolean expResult = true;
        boolean result = getInstanceVisitante().loginUtilizador(name, password);
        assertEquals(expResult, result);
        utilizadorRemote = getInstanceUtilizador();

    }
    @Test
    public void test7LogOffUtilizador() {
        System.out.println("logOff ".concat(username));
        
        boolean expResult = true;
        boolean result = utilizadorRemote.logOff();
        assertEquals(expResult, result);
     
    }



}
