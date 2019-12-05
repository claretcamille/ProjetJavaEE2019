/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import static Modele.DAOTest.getTestDataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author camilleclaret
 */
public class DAOAdminTest {
    
    private DAO myDAO;
    private DAOAdmin myDAOAdmin; // L'objet à tester
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
        
        this.myDAO= new DAO(this.myDataSource);
        this.myDAOAdmin = new DAOAdmin(this.myDAO.getDAO());
    }
    
    @After
    public void tearDown() throws SQLException {
        this.myConnection.close(); // Fermeture de la connection
    }

    // Blocage de test : Pas de fichier compatible avec hdbsq
    
    /**
     * Test of addProduct method, of class DAOAdmin.
     */
    @Test
    public void testAddProduct() throws Exception {
        System.out.println("addProduct");
        ProductEntity info = new ProductEntity("78","coca","1",1,"6 cannettes","3.99");;
        List<Integer> infoAdmin = new LinkedList<>();
        infoAdmin.add(20);
        infoAdmin.add(1);
        infoAdmin.add(2);
        infoAdmin.add(0);
        DAOAdmin instance = this.myDAOAdmin;
        instance.addProduct(info, infoAdmin);
        List<ProductEntity> result = instance.getDAO().allProduct();
        // Premier test de la taille, + 1 produit
        assertEquals(70, result.size());
        // Deuxieme test on vérifie les données du premier produit
        assertEquals(info.getRef(), result.get(result.size()-1).getRef());
        assertEquals(info.getNom(), result.get(result.size()-1).getNom());
        assertEquals(info.getFourni(), result.get(result.size()-1).getFourni());
        assertEquals(info.getQt(), result.get(result.size()-1).getQt());
        assertEquals(info.getRef(), result.get(result.size()-1).getRef());
    }
   
    /**
     * Test of suppProduct method, of class DAOAdmin.
     */
    @Test
    public void testSuppProduct() throws Exception {
        System.out.println("suppProduct");
        int reference = 1;
        DAOAdmin instance = this.myDAOAdmin;
        instance.suppProduct(reference);
        List<ProductEntity> result = instance.getDAO().allProduct();
       assertEquals(68, result.size()); // vérif qu'il manque un produit
       
    }
   
    /**
     * Test of modifProduct method, of class DAOAdmin.
     */
    @Test
    public void testModifProduct() throws Exception {
        System.out.println("modifProduct");
        int reference = 1;
        String choixModif = "quantite_par_unite";
        String modifProd = "coca";
        DAOAdmin instance = this.myDAOAdmin;
        instance.modifProduct(reference, choixModif, modifProd);
        ProductEntity expResult = new ProductEntity("1","Chai","1",1,"coca","90.00");
        List<ProductEntity> result = instance.getDAO().allProduct();
        assertEquals(expResult.getQt(), result.get(0).getQt());
        
    }
    
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
     */
    @Test
    public void testChiffreAffPays() throws Exception {
        System.out.println("chiffreAffPays");
        String dateDebut = "1994-08-04";
        String dateFin = "1994-10-04";
        ChiffreAffEntity expResult = new ChiffreAffEntity("Allemagne", 4424.0F);
        List<ChiffreAffEntity> result = this.myDAOAdmin.chiffreAffPays(dateDebut, dateFin);   
        if(expResult.getChiffre() ==  result.get(0).getChiffre()){
            assertEquals(expResult.getInfo(), result.get(0).getInfo()); // Vérif info
        } 
        assertEquals(21,result.size()); // Férification de la taille de la liste
    }
    /**
     * Test of chiffreAffClient method, of class DAOAdmin.
      */
    @Test
    public void testChiffreAffClient() throws Exception {
        System.out.println("chiffreAffClient");
        String dateDebut = "1994-08-04";
        String dateFin = "1994-10-04";
        ChiffreAffEntity expResult = new ChiffreAffEntity("ALFKI", 0F);
        List<ChiffreAffEntity> result = this.myDAOAdmin.chiffreAffClient(dateDebut, dateFin);
        System.out.println(result.get(0).getChiffre());
        if(expResult.getChiffre() ==  result.get(0).getChiffre()){
            assertEquals(expResult.getInfo(), result.get(0).getInfo());
        } 
        assertEquals(91, result.size());
    }
     

    /**
     * Test of dateSelection method, of class DAOAdmin.
     */
    @Test
    public void testDateSelection() throws Exception {
        System.out.println("dateSelection");
        String expResult = "1994-08-04";
        List<String> result = this.myDAOAdmin.dateSelection();
        assertEquals(expResult, result.get(0));
        assertEquals(480, result.size());
        
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
