/*
 *Fichier pour les commandes spécifique du client
 */
package Modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import java.sql.Date; 
import java.sql.Timestamp;
import java.util.LinkedList;
import Modele.ClientEntity;

/**
 *
 * @author camilleclaret
 */
public final class DAOClient {
    
    private final DataSource myDAOClient;
    private ClientEntity client;
    private String codeClient;
    
    /**
     * Constructeur de la classe
     * @param dao
     * @throws java.sql.SQLException
     */
    public DAOClient(DataSource dao, String Code) throws SQLException{
        this.myDAOClient =  dao;
        this.setCodeClient(Code);
        this.client = this.getClient();
        
    }
    
    /**
     * Récupération de la dao générale
     * @return 
     */
    public DataSource getDAO(){
        return this.myDAOClient;
    }
    
    /**
     * 
     * @param code
     * @return
     * @throws SQLException 
     */
    public void setCodeClient(String code){
        this.codeClient = code;
    }
    
    public ClientEntity getClient() throws SQLException {
        ClientEntity result = null;
        String sql = "SELECT * FROM CLIENT WHERE code = '"+this.codeClient+"'";
        try(
                Connection myConnection = this.myDAOClient.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ){
            if(rs.next()){
                List<String> donnees =new LinkedList<>();
                donnees.add(rs.getString("code"));
                donnees.add(rs.getString("societe"));
                donnees.add(rs.getString("contact"));
                donnees.add(rs.getString("fonction"));
                donnees.add(rs.getString("adresse"));
                donnees.add(rs.getString("ville"));
                donnees.add(rs.getString("region"));
                int codePost = rs.getInt("code_postal");
                donnees.add(rs.getString("pays"));
                donnees.add(rs.getString("telephone"));
                donnees.add(rs.getString("fax"));
                // Implémentation du produit
                result = new ClientEntity(
                        donnees.get(0), // code du Client
                        donnees.get(1), // societe du Client
                        donnees.get(2), // contact du Client
                        donnees.get(3), // fonction du Client
                        donnees.get(4), // adresse du Client
                        donnees.get(5), // ville du Client
                        donnees.get(6), // region du Client
                        codePost, // code_postal du Client
                        donnees.get(7), // pays du Client
                        donnees.get(8), // telephone du Client
                        donnees.get(9) // fax du Client
                 );
               
            }
            
        }catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
           // throw new DAOException(ex.getMessage());
        }
        return result;
    }
    
    /**
     * Fonction récupérent les lignes des commande
     * @param numCommand
     * @return
     * @throws SQLException 
     */
    public List<LigneEntity> getLigne(int numCommand) throws SQLException{
         List<LigneEntity> result = new LinkedList<>();
         String sql = "SELECT * FROM LIGNE WHERE commande = "+ numCommand;
          try(
                Connection myConnection = this.myDAOClient.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
         ){
              while(rs.next()){
                  LigneEntity ligne = new LigneEntity(rs.getInt("produit"), rs.getInt("quantite"));
                  result.add(ligne);
              }
          }
          return result;
    }
    
    /**
     * Retourne la liste des commande
     * @return
     * @throws SQLException 
     */
    public List<CommandeEntity> getCommande() throws SQLException{
        List<CommandeEntity> result = new LinkedList<>();
        String sql = "SELECT * FROM COMMANDE WHERE client = '"+this.client.getCodeClient()+"'";
        try(
                Connection myConnection = this.myDAOClient.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
         ){
            while(rs.next()){
                int num = rs.getInt("numero");
                int port = rs.getInt("port");
                String date = rs.getString("saisie_le");
                List<LigneEntity> ligne = this.getLigne(num);
                CommandeEntity commande = new CommandeEntity(num, port, date, ligne);
                result.add(commande);
                
            }
            
        }
        return result;
    }
    /**
     * Fonction ajoutant une nouvelle commande
     * @param DateEnvoie
     * @param remise
     * @throws java.sql.SQLException
     */
    public void addCommand(String DateEnvoie, int remise) throws SQLException{
        String sql = "INSERT INTO COMMANDE(client,saisie_le,envoyee_le, port,destinataire, adresse_livraison,ville_livraison,region_livraison,code_postal_livrais,pays_livraison,remise)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try(
                Connection myConnection = this.myDAOClient.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS)             
        ){
            // On démarre la transaction
            myConnection.setAutoCommit(false);
            try{
                
                // Récup de la date actuelle
                long millis=System.currentTimeMillis();  
                Date date=new Date(millis);   
                String sToday = date.toString();
                
                // Données
                stmt.setString(1,this.client.getCodeClient());
                stmt.setString(2,sToday);
                stmt.setString(3,DateEnvoie);
                stmt.setInt(4,0);
                stmt.setString(5,this.client.getSocieteClient());
                stmt.setString(6,this.client.getAdresseClient());
                stmt.setString(7,this.client.getVilleClient());
                stmt.setString(8, this.client.getRegionClient());
                stmt.setInt(9,this.client.getCodePostal());
                stmt.setString(10,this.client.getPaysClient());
                stmt.setInt(11,remise);
                
                // Création du produit
                stmt.executeUpdate();
                
                myConnection.commit(); // Validation de la transaction
            }catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
               // throw new DAOException(ex.getMessage());
               myConnection.rollback(); // On annule la transaction
            } finally{
                myConnection.setAutoCommit(true); // On revient au mode de fonctionnement sans transaction
            }
        }
        
    }
    
    /**
     * Fonction permettant la suppression de tout une commande
     * @param numCommand est le numéro de référance de la commande
     */
     public void supCommand(int numCommand) throws SQLException{
         String sql1="DELETE FROM LIGNE WHERE commande = "+ numCommand; // Suppression des ligne de la commande 
         String sql2="DELETE FROM COMMANDE WHERE numero = "+ numCommand; // Supression de la commande
        try(
                Connection myConnection = this.myDAOClient.getConnection();
                PreparedStatement stmt1 = myConnection.prepareStatement(sql1 );
                PreparedStatement stmt2 = myConnection.prepareStatement(sql2)   
        ){
            // On démarre la transaction
            myConnection.setAutoCommit(false);
            try{
                // suppression ligne de la commande 
                stmt1.executeUpdate();
                // suppression commande 
                stmt2.executeUpdate();
                
                myConnection.commit(); // Validation de la transaction
            }catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
               // throw new DAOException(ex.getMessage());
               myConnection.rollback(); // On annule la transaction
            } finally{
                myConnection.setAutoCommit(true); // On revient au mode de fonctionnement sans transaction
            }
        }
    }
    
     public  void calculPrix(int numCommand) throws SQLException{
         
         String sql1= "UPDATE COMMANDE SET port = ? WHERE numero = "+numCommand; // mise à jour de la commande
         
         List<CommandeEntity> commande = this.getCommande();
         int index = 0;
         while (numCommand != commande.get(index).getNum()){
             index++;
         }
         String sql2 = "SELECT * FROM PRODUIT WHERE reference = ?"; // calcul du cout de la ligne
         float port = 0F;
         try( 
                Connection myConnection = this.myDAOClient.getConnection();
                PreparedStatement stmt2 = myConnection.prepareStatement(sql2)
             ){
             
                for(int i = 0; i < commande.get(index).getLignes().size(); i++){
                    int ref = commande.get(index).getLignes().get(i).getProduct();
                    stmt2.setInt(1, ref);
                    try(ResultSet rs2 = stmt2.executeQuery();){
                        port += rs2.getFloat("prix_unitaire")*commande.get(index).getLignes().get(i).getQuantite();
                    }
                }
                
                myConnection.setAutoCommit(false);
                try(PreparedStatement stmt1 = myConnection.prepareStatement(sql1)){
                    stmt1.setFloat(1, port);
                    stmt1.executeUpdate();
                    myConnection.commit(); // Validation de la transaction
                }
                catch (SQLException ex) {
                    Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                    // throw new DAOException(ex.getMessage());
                    myConnection.rollback(); // On annule la transaction
                } finally{
                     myConnection.setAutoCommit(true); // On revient au mode de fonctionnement sans transaction
                }
                
         }
  
     }
     
     /**
      * Fonction ajoutant une ligne a la commande
      * @param numCommand
      * @param info 
      */
    public void addLineCommand(int numCommand, ProductEntity info, int qt) throws SQLException{
        String sql1 = "INSERT INTO LIGNE VALUES(?,?,?)"; // Insertion de la ligne de commande  
        String sql2= "SELECT * FROM COMMANDE WHERE numero = "+numCommand; // info sur la commande
        
        try(
                Connection myConnection = this.myDAOClient.getConnection();
                PreparedStatement stmt1 = myConnection.prepareStatement(sql1);
                PreparedStatement stmt2 = myConnection.prepareStatement(sql2)
            ){
            // On démarre la transaction
            myConnection.setAutoCommit(false);
            try{
                // Ajout de la ligne 
                stmt1.setInt(1, numCommand);
                int ref = Integer.parseInt(info.getRef());
                stmt1.setInt(2, ref);
                stmt1.setInt(3, qt);
                stmt1.executeUpdate();
                
                // calcul du cout
               this.calculPrix(numCommand);
                

                myConnection.commit(); // Validation de la transaction
            }catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
               // throw new DAOException(ex.getMessage());
               myConnection.rollback(); // On annule la transaction
            } finally{
                myConnection.setAutoCommit(true); // On revient au mode de fonctionnement sans transaction
            }
            
        }
    }
    
    /**
     * Fonction modifiant la ligne d'une commande ici la seul modif peut être la quantité
     * @param numCommand
     * @param qt  
     */
    public void modifLineCommand(int numCommand, int refProd,int qt) throws SQLException{
        String sql = "UPDATE LIGNE SET quantite = " + qt + "WHERE produit ="+ refProd;
         try(
                Connection myConnection = this.myDAOClient.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql)
         ){
             // On démarre la transaction
            myConnection.setAutoCommit(false);
            try{
                // Mise a jour
                stmt.executeUpdate();
                
                myConnection.commit(); // Validation de la transaction
            }catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
               // throw new DAOException(ex.getMessage());
               myConnection.rollback(); // On annule la transaction
            } finally{
                myConnection.setAutoCommit(true); // On revient au mode de fonctionnement sans transaction
            }
  
         }       
    }
    
    /**
     * Fonction supprimant la ligne de la commande
     * @param numCommand
     * @param refProduct 
     */
    public void supLineCommand(int numCommand, int refProduct ) throws SQLException{
        String sql = "DROP FROM LIGNE  WHERE commade = "+numCommand+" and produit = "+refProduct;
        try(
                Connection myConnection = this.myDAOClient.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql)
         ){
             // On démarre la transaction
            myConnection.setAutoCommit(false);
            try{
                // Mise a jour
                stmt.executeUpdate();
                
                myConnection.commit(); // Validation de la transaction
                this.client = this.getClient(); // Mise a jour du client 
            }catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
               // throw new DAOException(ex.getMessage());
               myConnection.rollback(); // On annule la transaction
            } finally{
                myConnection.setAutoCommit(true); // On revient au mode de fonctionnement sans transaction
            }
  
         } 
    }
    
    /**
     * Fonction permettant de modifier les informations du client 
     * @param choixModif
     * @param modif 
     */
    public void modifInfoClient(String choixModif, String modif) throws SQLException{
        
        String sql = "UPDATE CLIENT SET "+choixModif +" = ? WHERE code = "+this.client.getCodeClient();
        try(
                Connection myConnection = this.myDAOClient.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql)
         ){
             // On démarre la transaction
            myConnection.setAutoCommit(false);
            try{
                stmt.setString(1, "modif");
                // Mise a jour
                stmt.executeUpdate();
                
                myConnection.commit(); // Validation de la transaction
            }catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
               // throw new DAOException(ex.getMessage());
               myConnection.rollback(); // On annule la transaction
            } finally{
                myConnection.setAutoCommit(true); // On revient au mode de fonctionnement sans transaction
            }
  
         } 
        
    }
    
   
    
}
