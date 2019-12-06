/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
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
public class DAOClientTest {
    
   private DAO myDAO;
    private DAOClient myDAOClient; // L'objet à tester
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
        this.myDAOClient = this.myDAO.toConnectClient("Maria Anders", "ALFKI");
    }
    
    @After
    public void tearDown() throws SQLException {
        this.myConnection.close(); // Fermeture de la connection
    }

    /**
     * Test of getDAO method, of class DAOClient.
     */
    @Test
    public void testGetDAO() {
        System.out.println("getDAO");
        DataSource expResult =  this.myDAO.getDAO();
        DataSource result = this.myDAOClient.getDAO();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClient method, of class DAOClient.
     */
    @Test
    public void testGetClient() throws Exception {
        System.out.println("getClient");
        ClientEntity expResult =  new ClientEntity("ALFKI","Alfreds Futterkiste", "Maria Anders" , "Représentant(e)" , "Obere Str. 57","Berlin","", 12209,"Allemagne", "030-0074321" , "030-0076545");
        ClientEntity result = this.myDAOClient.getClient();
        assertEquals(expResult.getCodeClient(), result.getCodeClient());
        assertEquals(expResult.getSocieteClient(), result.getSocieteClient());
    }

    /**
     * Test of addCommand method, of class DAOClient.
     */
    @Test
    public void testAddCommand() throws Exception {
        System.out.println("addCommand");
        long millis=System.currentTimeMillis();  
        Date date=new Date(millis);   
        String DateEnvoie = date.toString();
        System.out.println();
        int remise = 0;
        DAOClient instance = this.myDAOClient;
        instance.addCommand(DateEnvoie, remise);
        List<CommandeEntity> result = instance.getCommande();
        // Nbr de commande du client
        assertEquals(5,result.size());
        //Vérif des numéro de commande
        CommandeEntity expResult = new CommandeEntity(10702,119,"1995-11-13",null);
        assertEquals(expResult.getNum(),result.get(0).getNum());
        assertEquals(expResult.getPort(),result.get(0).getPort());
        assertEquals(expResult.getSaisieLe(),result.get(0).getSaisieLe());
        
    }

    /**
     * Test of supCommand method, of class DAOClient.
     */
    @Test
    public void testSupCommand() throws Exception {
        System.out.println("supCommand");
        int numCommand = 10702;
        DAOClient instance = this.myDAOClient;
        instance.supCommand(numCommand);
        List<CommandeEntity> result = instance.getCommande();
        // Nbr de commande du client
        assertEquals(3,result.size());
    }
    
    /**
     * Test of calculPrix method, of class DAOClient.
     */
    @Test
    public void testCalculPrix() throws Exception {
        System.out.println("calculPrix");
        int numCommand = 10702;
        DAOClient instance = this.myDAOClient;
        float result = instance.calculPrix(numCommand);
        float expResult = 1650F;
        if (expResult == result){
            assertEquals(true, true);
        } else {
            assertEquals(false, true);
        }
    }
    
 
    /**
     * Test of addLineCommand method, of class DAOClient.
    */
    @Test
    public void testAddLineCommand() throws Exception {
        System.out.println("addLineCommand");
        int numCommand = 10248;
        ProductEntity info = new ProductEntity("1","Chai","1",1,"10 boîtes x 20 sacs","90.00");;
        int qt = 3;
        DAOClient instance = this.myDAOClient;
        instance.addLineCommand(numCommand, info, qt);
        CommandeEntity result = new CommandeEntity(numCommand,119,"1995-11-13",instance.getLigne(numCommand));
        LigneEntity expResult = new LigneEntity(1,3);
        // Vérif qu'une ligne est ajouté
        assertEquals(4, result.getLignes().size());
        // Vérif des infos 
       assertEquals(expResult.getProduct(), result.getLignes().get(0).getProduct());
       assertEquals(expResult.getQuantite(), result.getLignes().get(0).getQuantite());
    }
    
    /**
     * Test of modifLineCommand method, of class DAOClient.
     */
    @Test
    public void testModifLineCommand() throws Exception {
        System.out.println("modifLineCommand");
        int numCommand = 10248;
        int refProd = 11;
        int qt = 2;
        DAOClient instance = this.myDAOClient;
        instance.modifLineCommand(numCommand, refProd, qt);
        CommandeEntity result = new CommandeEntity(numCommand,119,"1995-11-13",instance.getLigne(numCommand));
        LigneEntity expResult = new LigneEntity(refProd,qt);
        // Vérif que la commande a toujour la même taille
        assertEquals(3, result.getLignes().size());
        // Vérif des infos 
       assertEquals(expResult.getProduct(), result.getLignes().get(0).getProduct());
       assertEquals(expResult.getQuantite(), result.getLignes().get(0).getQuantite());
    }
 
    /**
     * Test of supLineCommand method, of class DAOClient.
     * @throws java.lang.Exception
    */
    @Test
    public void testSupLineCommand() throws Exception {
        System.out.println("supLineCommand");
        int numCommand = 10248;
        int refProduct = 3;
        DAOClient instance = this.myDAOClient;
        instance.supLineCommand(numCommand, refProduct);
        CommandeEntity result = new CommandeEntity(numCommand,119,"1995-11-13",instance.getLigne(numCommand));
        assertEquals(1, result.getLignes().size());
    }

    /**
     * Test of modifInfoClient method, of class DAOClient.
     
    @Test
    public void testModifInfoClient() throws Exception {
        System.out.println("modifInfoClient");
        String choixModif = "";
        String modif = "";
        DAOClient instance = null;
        instance.modifInfoClient(choixModif, modif);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
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
