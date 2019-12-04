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
        this.executeSQLScript(this.myConnection, "comptoirs_schema_derby.sql");
        // On y met des données
        this.executeSQLScript(this.myConnection, "comptoirs_data.sql");
        
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
    public void testGetDAO() throws SQLException {
        System.out.println("getDAO");
        assertEquals(this.myDataSource, this.myDAO.getDAO());
    }

    /**
     * Test of allProduct method, of class DAO.
     */
    @Test
    public void testAllProduct() throws SQLException {
        System.out.println("allProduct");
        List<ProductEntity> result = this.myDAO.allProduct();
        ProductEntity expResult = new ProductEntity("1","Chai","1",1,"10 boîtes x 20 sacs","90.00");
        // Premier test, on regarde que tout les produits sont présent
        assertEquals(result.size(), 69);
        // Deuxieme test on vérifie les données du premier produit
        assertEquals(expResult.getRef(), result.get(0).getRef());
        assertEquals(expResult.getNom(), result.get(0).getNom());
        assertEquals(expResult.getFourni(), result.get(0).getFourni());
        assertEquals(expResult.getQt(), result.get(0).getQt());
        assertEquals(expResult.getRef(), result.get(0).getRef());
        
    }
    
     /**
     * Test of categoryProduct method, of class DAO.
     */
    @Test
    public void testgetCatedorie() throws SQLException {
        System.out.println("allProduct");
        List<CategorieEntity> result = this.myDAO.getCategorie();
        CategorieEntity expResult = new CategorieEntity(1, "Boissons");
        // Premier test, on regarde que tout les produits sont présent
       assertEquals(result.size(), 8);
        // Deuxieme test on vérifie les données du premier produit
       assertEquals(expResult.getLibelle(), result.get(0).getLibelle());
       assertEquals(expResult.getCode(), result.get(0).getCode());
    
    }

    /**
     * Test of categoryProduct method, of class DAO.
     */
    @Test
    public void testCategoryProduct() throws SQLException {
        
        String category = "Boissons";
        ProductEntity expResult = new ProductEntity("1","Chai","1",1,"10 boîtes x 20 sacs","90.00");
        List<ProductEntity> result = this.myDAO.categoryProduct(category);
        System.out.println(result.size());
        // Vérification que la liste est de taille 12
        assertEquals(result.size(), 12);
        // Deuxieme test on vérifie les données du premier produit
        assertEquals(expResult.getRef(), result.get(0).getRef());
        assertEquals(expResult.getNom(), result.get(0).getNom());
        assertEquals(expResult.getFourni(), result.get(0).getFourni());
        assertEquals(expResult.getQt(), result.get(0).getQt());
        assertEquals(expResult.getRef(), result.get(0).getRef());
    }

    /**
     * Test of getConnection() method, of class DAO.
     */
    @Test
    public void testgetConnection() {
        System.out.println("toConnect");
        // test connection admin
        this.myDAO.toConnect("admin", "admin");
        assertEquals(this.myDAO.getConnection(), true);
         // test connection admin
        this.myDAO.toDisconnect();
        assertEquals(this.myDAO.getConnection(), false);
         // test connection admin
        this.myDAO.toConnect("Maria Anders", "ALFKI");
        assertEquals(this.myDAO.getConnection(), true);
         // test connection admin
        this.myDAO.toDisconnect();
        assertEquals(this.myDAO.getConnection(), false);
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
