/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdtprcse.ReferenciaAdmin;
import pdtprcse.ReferenciaVisitante;

/**
 *
 * @author diogo
 */
public class ClientAdminRemoteTest {
    
    public ClientAdminRemoteTest() {
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

    /**
     * Test of logOff method, of class ClientAdminRemote.
     */
    @Test
    public void testLogOff() {
        System.out.println("logOff");

        boolean expResult = false;
        boolean result = getInstanceAdmin().logOff();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of getUtilizadoresPedidos method, of class ClientAdminRemote.
     */
    @Test
    public void testGetUtilizadoresPedidos() {
        System.out.println("getUtilizadoresPedidos");

        ArrayList expResult = null;
        ArrayList result = getInstanceAdmin().getUtilizadoresPedidos();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of ativaUtilizador method, of class ClientAdminRemote.
     */
    @Test
    public void testAtivaUtilizador() {
        System.out.println("ativaUtilizador");
        String username = "user1";

        boolean expResult = true;
        boolean result = getInstanceAdmin().ativaUtilizador(username);
        assertEquals(expResult, result);
       
    }

     private ClientAdminRemote getInstanceAdmin(){
        ReferenciaAdmin refVisitante = new ReferenciaAdmin();
        ClientAdminRemote instance = refVisitante.getLigacao();
        return instance;
    }
    
}
