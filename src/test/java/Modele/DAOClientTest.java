/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author camilleclaret
 */
public class DAOClientTest {
    
    public DAOClientTest() {
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
     * Test of addCommand method, of class DAOClient.
     */
    @Test
    public void testAddCommand() {
        System.out.println("addCommand");
        DAOClient instance = new DAOClient();
        instance.addCommand();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supCommand method, of class DAOClient.
     */
    @Test
    public void testSupCommand() {
        System.out.println("supCommand");
        int numCommand = 0;
        DAOClient instance = new DAOClient();
        instance.supCommand(numCommand);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addLineCommand method, of class DAOClient.
     */
    @Test
    public void testAddLineCommand() {
        System.out.println("addLineCommand");
        int numCommand = 0;
        List<String> info = null;
        DAOClient instance = new DAOClient();
        instance.addLineCommand(numCommand, info);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifLineCommand method, of class DAOClient.
     */
    @Test
    public void testModifLineCommand() {
        System.out.println("modifLineCommand");
        int numCommand = 0;
        List<String> info = null;
        DAOClient instance = new DAOClient();
        instance.modifLineCommand(numCommand, info);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supLineCommand method, of class DAOClient.
     */
    @Test
    public void testSupLineCommand() {
        System.out.println("supLineCommand");
        int numCommand = 0;
        int refProduct = 0;
        DAOClient instance = new DAOClient();
        instance.supLineCommand(numCommand, refProduct);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifInfoClient method, of class DAOClient.
     */
    @Test
    public void testModifInfoClient() {
        System.out.println("modifInfoClient");
        String choixModif = "";
        String modif = "";
        DAOClient instance = new DAOClient();
        instance.modifInfoClient(choixModif, modif);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
