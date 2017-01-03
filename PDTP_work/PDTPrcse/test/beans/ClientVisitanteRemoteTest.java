/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdtprcse.ReferenciaVisitante;
/**
 *
 * @author diogo
 */
public class ClientVisitanteRemoteTest {
    
    public ClientVisitanteRemoteTest() {
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

    private ClientVisitanteRemote getInstance(){
        ReferenciaVisitante refVisitante = new ReferenciaVisitante();
        ClientVisitanteRemote instance = refVisitante.getLigacao();
        return instance;
    }
    
    /**
     * Test of inscreveUtilizador method, of class ClientVisitanteRemote.
     */
    @Test
    public void testInscreveUtilizador() {
        System.out.println("inscreveUtilizador");
        String nome = "user1";
        String morada = "testes";
        String username = "user1";
        String password = "user1";
       
        //ClientVisitanteRemote instance = new ClientVisitanteRemote();
        boolean expResult = true;
        boolean result = getInstance().inscreveUtilizador(nome, morada, username, password);
        assertEquals(expResult, result);

    }

    /**
     * Test of loginUtilizador method, of class ClientVisitanteRemote.
     */
    @Test
    public void testLoginUtilizador() {
        System.out.println("loginUtilizador");
        String name = "user1";
        String password = "user1";
        
        boolean expResult = false;
        boolean result = getInstance().loginUtilizador(name, password);
        assertEquals(expResult, result);

    }

    /**
     * Test of existeUsername method, of class ClientVisitanteRemote.
     */
    @Test
    public void testExisteUsername() {
        System.out.println("existeUsername");
        String username = "user1";
       
        boolean expResult = true;
        boolean result = getInstance().existeUsername(username);
        assertEquals(expResult, result);

    }

    /**
     * Test of getUsernameInscritos method, of class ClientVisitanteRemote.
     */
    @Test
    public void testGetUsernameInscritos() {
        System.out.println("getUsernameInscritos");
      
        ArrayList expResult = null;
        ArrayList result = getInstance().getUsernameInscritos();
        assertEquals(expResult, result);

    }

    /**
     * Test of getUsernamesOnline method, of class ClientVisitanteRemote.
     */
    @Test
    public void testGetUsernamesOnline() {
        System.out.println("getUsernamesOnline");
       
        ArrayList expResult = null;
        ArrayList result = getInstance().getUsernamesOnline();
        assertEquals(expResult, result);

    }

   
}
