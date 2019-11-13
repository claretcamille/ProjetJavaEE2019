/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.List;
import javax.activation.DataSource;
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
public class DAOTest {
    
    public DAOTest() {
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
     * Test of getDAO method, of class DAO.
     */
    @Test
    public void testGetDAO() {
        System.out.println("getDAO");
        DAO instance = null;
        DataSource expResult = null;
        DataSource result = instance.getDAO();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allProduct method, of class DAO.
     */
    @Test
    public void testAllProduct() {
        System.out.println("allProduct");
        DAO instance = null;
        List<String> expResult = null;
        List<String> result = instance.allProduct();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of categoryProduct method, of class DAO.
     */
    @Test
    public void testCategoryProduct() {
        System.out.println("categoryProduct");
        String category = "";
        DAO instance = null;
        List<String> expResult = null;
        List<String> result = instance.categoryProduct(category);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toConnect method, of class DAO.
     */
    @Test
    public void testToConnect() {
        System.out.println("toConnect");
        DAO instance = null;
        instance.toConnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
