/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.activation.DataSource;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author camilleclaret
 */
public class DAOTest {
    
    private DAO myDAO; // L'objet à tester
    private javax.sql.DataSource myDataSource; // La source de données à utiliser
    private  Connection myConnection ; // La connection à la BD de test
    
    

    @Before
    public void setUp() throws SQLException, IOException, SqlToolError {
        // On utilise la base de données de test
        this.myDataSource = getTestDataSource();
        this.myConnection = this.myDataSource.getConnection();
        
        // On crée le schema de la base de test
        this.executeSQLScript(this.myConnection, "ComptoirInnoDB_Schema.sql");
        // On y met des données
        this.executeSQLScript(this.myConnection, "ComptoirInnoDB_Data.sql");
        
        this.myDAO = new DAO(this.myDataSource);

    }
    
    @After
    public void tearDown() throws SQLException {
        
        this.myConnection.close(); // Fermeture de la connection
        
    }

    /**
     * Test of getDAO method, of class DAO.
     */
    @Test
    public void testGetDAO() {
        System.out.println("getDAO");
        assertEquals(this.myDataSource, this.myDAO.getDAO());  
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
    
    public static javax.sql.DataSource getTestDataSource() {
        org.hsqldb.jdbc.JDBCDataSource ds = new org.hsqldb.jdbc.JDBCDataSource();
        ds.setDatabase("jdbc:hsqldb:mem:testcase;shutdown=true");
        ds.setUser("sa");
        ds.setPassword("sa");
        return ds;
    }
    
    private void executeSQLScript(Connection connexion, String filename)  throws IOException, SqlToolError, SQLException {
        
        String sqlFilePath = DAOTest.class.getResource(filename).getFile();
        
        SqlFile sqlFile = new SqlFile(new File(sqlFilePath));
        
        sqlFile.setConnection(connexion);
        sqlFile.execute();
        sqlFile.closeReader();
    
    }
    
}
