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

/**
 *
 * @author diogo
 */
public class ClientUtilizadorRemoteTest {
    
    public ClientUtilizadorRemoteTest() {
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
     * Test of logOff method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testLogOff() {
        System.out.println("logOff");
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        boolean expResult = false;
        boolean result = instance.logOff();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existeUsername method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testExisteUsername() {
        System.out.println("existeUsername");
        String username = "";
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        boolean expResult = false;
        boolean result = instance.existeUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSaldo method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testAddSaldo() {
        System.out.println("addSaldo");
        Double valor = null;
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        Double expResult = null;
        Double result = instance.addSaldo(valor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSaldo method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testGetSaldo() {
        System.out.println("getSaldo");
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        Double expResult = null;
        Double result = instance.getSaldo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsernameInscritos method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testGetUsernameInscritos() {
        System.out.println("getUsernameInscritos");
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        ArrayList expResult = null;
        ArrayList result = instance.getUsernameInscritos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsernamesOnline method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testGetUsernamesOnline() {
        System.out.println("getUsernamesOnline");
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        ArrayList expResult = null;
        ArrayList result = instance.getUsernamesOnline();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMyName method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testSetMyName() {
        System.out.println("setMyName");
        String username = "";
        String password = "";
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        boolean expResult = false;
        boolean result = instance.setMyName(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMyName method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testGetMyName() {
        System.out.println("getMyName");
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        String expResult = "";
        String result = instance.getMyName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDados method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testGetDados() {
        System.out.println("getDados");
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        String expResult = "";
        String result = instance.getDados();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atualizaDados method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testAtualizaDados() {
        System.out.println("atualizaDados");
        String nome = "";
        String morada = "";
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        boolean expResult = false;
        boolean result = instance.atualizaDados(nome, morada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pedirSuspensao method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testPedirSuspensao() {
        System.out.println("pedirSuspensao");
        String razao = "";
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        boolean expResult = false;
        boolean result = instance.pedirSuspensao(razao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMensagem method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testSendMensagem() {
        System.out.println("sendMensagem");
        String destinatario = "";
        String texto = "";
        String assunto = "";
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        boolean expResult = false;
        boolean result = instance.sendMensagem(destinatario, texto, assunto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarMensagens method, of class ClientUtilizadorRemote.
     */
    @Test
    public void testConsultarMensagens() {
        System.out.println("consultarMensagens");
        ClientUtilizadorRemote instance = new ClientUtilizadorRemoteImpl();
        ArrayList<Mensagem> expResult = null;
        ArrayList<Mensagem> result = instance.consultarMensagens();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ClientUtilizadorRemoteImpl implements ClientUtilizadorRemote {

        public boolean logOff() {
            return false;
        }

        public boolean existeUsername(String username) {
            return false;
        }

        public Double addSaldo(Double valor) {
            return null;
        }

        public Double getSaldo() {
            return null;
        }

        public ArrayList getUsernameInscritos() {
            return null;
        }

        public ArrayList getUsernamesOnline() {
            return null;
        }

        public boolean setMyName(String username, String password) {
            return false;
        }

        public String getMyName() {
            return "";
        }

        public String getDados() {
            return "";
        }

        public boolean atualizaDados(String nome, String morada) {
            return false;
        }

        public boolean pedirSuspensao(String razao) {
            return false;
        }

        public boolean sendMensagem(String destinatario, String texto, String assunto) {
            return false;
        }

        public ArrayList<Mensagem> consultarMensagens() {
            return null;
        }
    }
    
}
