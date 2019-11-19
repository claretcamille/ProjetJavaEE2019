/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author camilleclaret
 */
public class DAOAdminTest {
    private  DAO myDAO;
    private  DAOAdmin myDAOAdmin;
    
    @Before
    public void setUp() throws SQLException {
        this.myDAO = new DAO();
        this.myDAOAdmin = new DAOAdmin(this.myDAO);
    }
    
    @After
    public void tearDown() {
    }

    // Blocage de test : Pas de fichier compatible avec hdbsq
    
    /**
     * Test of addProduct method, of class DAOAdmin.
     
    @Test
    public void testAddProduct() throws Exception {
        System.out.println("addProduct");
        ProductEntity info = null;
        List<Integer> infoAdmin = null;
        DAOAdmin instance = null;
        instance.addProduct(info, infoAdmin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of suppProduct method, of class DAOAdmin.
     
    @Test
    public void testSuppProduct() throws Exception {
        System.out.println("suppProduct");
        int reference = 0;
        DAOAdmin instance = null;
        instance.suppProduct(reference);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of modifProduct method, of class DAOAdmin.
     
    @Test
    public void testModifProduct() throws Exception {
        System.out.println("modifProduct");
        int reference = 0;
        String choixModif = "";
        String modifProd = "";
        DAOAdmin instance = null;
        instance.modifProduct(reference, choixModif, modifProd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of chiffreAffCat method, of class DAOAdmin.
     */
    @Test
    public void testChiffreAffCat() throws Exception {
        System.out.println("chiffreAffCat");
        String dateDebut = "1994-08-04";
        String dateFin = "1994-10-04";
        ChiffreAffEntity expResult = new ChiffreAffEntity("1", (float) 52892.0);
        List<ChiffreAffEntity> result = this.myDAOAdmin.chiffreAffCat(dateDebut, dateFin);
        System.out.println(result.get(0).getInfo());
        System.out.println(result.get(0).getChiffre());
    }

    /**
     * Test of chiffreAffPays method, of class DAOAdmin.
     
    @Test
    public void testChiffreAffPays() throws Exception {
        System.out.println("chiffreAffPays");
        String dateDebut = "1994-08-04";
        String dateFin = "1994-10-04";
        ChiffreAffEntity expResult = new ChiffreAffEntity("1", 52892.0F);
        List<ChiffreAffEntity> result = this.myDAOAdmin.chiffreAffClient(dateDebut, dateFin);        
        assertEquals(expResult.getInfo(), result.get(0).getInfo());
        assertEquals(expResult.getChiffre(), result.get(0).getChiffre());
    }
*/
    /**
     * Test of chiffreAffClient method, of class DAOAdmin.
     
    @Test
    public void testChiffreAffClient() throws Exception {
        System.out.println("chiffreAffClient");
        String dateDebut = "1994-08-04";
        String dateFin = "1994-10-04";
        ChiffreAffEntity expResult = new ChiffreAffEntity("1", (float) 52892.0);
        List<ChiffreAffEntity> result = this.myDAOAdmin.chiffreAffClient(dateDebut, dateFin);
        assertEquals(expResult.getChiffre(), result.get(0).getChiffre());
        assertEquals(expResult.getInfo(), result.get(0).getInfo());
       
    }
      */

    /**
     * Test of dateSelection method, of class DAOAdmin.
     */
    @Test
    public void testDateSelection() throws Exception {
        System.out.println("dateSelection");
        String expResult = "1994-11-10";
        List<String> result = this.myDAOAdmin.dateSelection();
        assertEquals(expResult, result.get(0));
        
    }
    
}
