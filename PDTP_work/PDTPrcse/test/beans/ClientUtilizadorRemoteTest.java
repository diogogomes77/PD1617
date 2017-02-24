/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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

        @Override
        public boolean logOff() {
            return false;
        }

        @Override
        public boolean existeUsername(String username) {
            return false;
        }

        @Override
        public Double addSaldo(Double valor) {
            return null;
        }

        @Override
        public Double getSaldo() {
            return null;
        }

        @Override
        public ArrayList getUsernameInscritos() {
            return null;
        }

        @Override
        public ArrayList getUsernamesOnline() {
            return null;
        }

        @Override
        public boolean setMyName(String username, String password) {
            return false;
        }

        @Override
        public String getMyName() {
            return "";
        }

        @Override
        public String getDados() {
            return "";
        }

        @Override
        public boolean atualizaDados(String nome, String morada) {
            return false;
        }

        @Override
        public boolean pedirSuspensao(String razao) {
            return false;
        }

        @Override
        public boolean sendMensagem(String destinatario, String texto, String assunto) {
            return false;
        }

        @Override
        public ArrayList<Mensagem> consultarMensagens() {
            return null;
        }

        @Override
        public boolean verificaPassword(String password) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean alteraPassword(String password) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean addItem(String descricao, Double precoInicial, Double precoComprarJa, Timestamp dataLimite) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<String> getCategorias() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<String> getMeusItens() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getTotalItens() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<String> getItens() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String mostraItem(int itemId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getVendedorItem(int itemId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String consultarLicitacoes(int itemId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean comprarJaItem(int itemid) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean licitarItem(int itemid, Double valor) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean seguirItem(int itemId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List getItensSeguidos() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List getMeusItensPorPagar() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean concluirTransacao(int itemId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean denunciarItem(int itemId, String razao) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean denunciarVendedor(String username, String razao) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<String> getNewsletter() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
