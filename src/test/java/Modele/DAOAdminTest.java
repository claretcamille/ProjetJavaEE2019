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
public class DAOAdminTest {
    
    public DAOAdminTest() {
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
     * Test of addProduct method, of class DAOAdmin.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        List<String> Info = null;
        DAOAdmin instance = new DAOAdmin();
        instance.addProduct(Info);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of suppProduct method, of class DAOAdmin.
     */
    @Test
    public void testSuppProduct() {
        System.out.println("suppProduct");
        int reference = 0;
        DAOAdmin instance = new DAOAdmin();
        instance.suppProduct(reference);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifProduct method, of class DAOAdmin.
     */
    @Test
    public void testModifProduct() {
        System.out.println("modifProduct");
        int reference = 0;
        String choixModif = "";
        String modifProd = "";
        DAOAdmin instance = new DAOAdmin();
        instance.modifProduct(reference, choixModif, modifProd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chiffreAffCat method, of class DAOAdmin.
     */
    @Test
    public void testChiffreAffCat() {
        System.out.println("chiffreAffCat");
        String categori = "";
        String dateDebut = "";
        String dateFin = "";
        DAOAdmin instance = new DAOAdmin();
        List<String> expResult = null;
        List<String> result = instance.chiffreAffCat(categori, dateDebut, dateFin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chiffreAffPays method, of class DAOAdmin.
     */
    @Test
    public void testChiffreAffPays() {
        System.out.println("chiffreAffPays");
        String pays = "";
        String dateDebut = "";
        String dateFin = "";
        DAOAdmin instance = new DAOAdmin();
        List<String> expResult = null;
        List<String> result = instance.chiffreAffPays(pays, dateDebut, dateFin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chiffreAffClient method, of class DAOAdmin.
     */
    @Test
    public void testChiffreAffClient() {
        System.out.println("chiffreAffClient");
        String categori = "";
        String dateDebut = "";
        String dateFin = "";
        DAOAdmin instance = new DAOAdmin();
        List<String> expResult = null;
        List<String> result = instance.chiffreAffClient(categori, dateDebut, dateFin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
