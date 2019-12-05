/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author camilleclaret
 */
public class DAO {
    
    private final DataSource myDao;
    private boolean connectionSite;
    private String client ;
    private  DAOClient DAOClient;
    
    
    public DAO(DataSource myDataSource){
        this.myDao = myDataSource;
    }
    
   /**
     * Permet de récupérer la base de donnée
     * @return la base de données utilisé.
     */
    public DataSource getDAO(){
        return this.myDao;
    }
    
    /**
     * Permet de savoir si une connection est en cour
     * @return 
     */
    public boolean getConnection(){
        return this.connectionSite;
    }
    
    public String getCode(){
        return this.client;
    }
    
    /**
     * Fonction qui retourne la liste de tous les produit
     * @return la liste de tous les produits
     */
    public List<ProductEntity> allProduct() throws SQLException {
        List<ProductEntity> result = new LinkedList<>();
        String sql = "SELECT * FROM PRODUIT WHERE indisponible = 0 ";
        try(
                Connection myConnection = this.myDao.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()){
                // Création d'une liste contenant les données pour les implémenter dans la classe produit
                List<String> donnees =new LinkedList<>();
                // Ajout des données
                donnees.add(rs.getString("reference"));
                donnees.add(rs.getString("nom"));
                donnees.add(rs.getString("fournisseur"));
                donnees.add(rs.getString("quantite_par_unite"));
                donnees.add(rs.getString("prix_unitaire"));
                // Récupération de la catégorie
                int catProd = rs.getInt("categorie");
                // Implémentation du produit
                ProductEntity prod = new ProductEntity(
                        donnees.get(0),// ref du produit
                        donnees.get(1), // nom du produit
                        donnees.get(2),// fournisseur du produit
                        catProd, // Num de catégorie du produit
                        donnees.get(3), // quantité vendu du produit
                        donnees.get(4) // prix du produit
                 );
                result.add(prod); // Incrémentation du résultat.
            }
            
        }
        return result;
    }
    /**
     * Fonction permettant la récupération des catégories
     * @return la liste des code et libelle de la table categorie
     * @throws java.sql.SQLException
     */
    public List<CategorieEntity> getCategorie() throws SQLException{
        List<CategorieEntity> result = new LinkedList<>();
        String sql ="SELECT * FROM CATEGORIE";
         try(
                Connection myConnection = this.myDao.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
         ){
             while(rs.next()){
                 int code = rs.getInt("code");
                 String libelle = rs.getString("libelle");
                 result.add(new CategorieEntity(code, libelle));
             }
         }        
        return result;
    }
    
    /**
     * Fonction cherchant les produits correspondant a une catégorie selectionner
     * @param category est la catégorie d'article
     * @return la liste des articles appartenant a la categorie selectionner
     */
    public List<ProductEntity> categoryProduct(String category) throws SQLException{
        
        List<ProductEntity> result = new LinkedList<>();
        // Commande sql : la première récupére le code de la category la seconde donne les produits correspondant
        String sql1 = "SELECT * FROM CATEGORIE WHERE libelle =?";
        String sql2  = "SELECT * FROM PRODUIT  WHERE  categorie =  ?";
         try(   
                 Connection myConnection = this.myDao.getConnection();
                 PreparedStatement stmt1 = myConnection.prepareStatement(sql1);
                 PreparedStatement stmt2 =  myConnection.prepareStatement(sql2);
         ){
             // Récupération du code 
             stmt1.setString(1, category); // donne que vaut le libelle  pour sql1
             try( ResultSet rs1 = stmt1.executeQuery() ){
                 if(rs1.next()){
                 int code = rs1.getInt("code"); // récupére le code de categorie 
                 stmt2.setInt(1, code);// donne que vaut le code pour sql2 
                 }
                 // Récupération des produits
                 try( ResultSet rs2 = stmt2.executeQuery()){
                    while(rs2.next()){
                        // Création d'une liste contenant les données pour les implémenter dans la classe produit
                        List<String> donnees =new LinkedList<>();
                        // Ajout des données
                        donnees.add(rs2.getString("reference"));
                        donnees.add(rs2.getString("nom"));
                        donnees.add(rs2.getString("fournisseur"));
                        donnees.add(rs2.getString("quantite_par_unite"));
                        donnees.add(rs2.getString("prix_unitaire"));
                        // Récupération de la catégorie
                        int catProd = rs2.getInt("categorie");
                        // Implémentation du produit
                        ProductEntity prod = new ProductEntity(
                                donnees.get(0),// ref du produit
                                donnees.get(1), // nom du produit
                                donnees.get(2),// fournisseur du produit
                                catProd, // Num de catégorie du produit
                                donnees.get(3), // quantité vendu du produit
                                donnees.get(4) // prix du produit
                         );
                        result.add(prod); // Incrémentation du résultat.
                    }
                 }    
             }
             
         }      
        return result;
    }
    
    public List<ProductEntity> categoryProduct(int category) throws SQLException{
        
        List<ProductEntity> result = new LinkedList<>();
        // Commande sql : la première récupére le code de la category la seconde donne les produits correspondant

        String sql2  = "SELECT * FROM PRODUIT  WHERE  categorie =  ?";
         try(   
                 Connection myConnection = this.myDao.getConnection();
                 PreparedStatement stmt2 =  myConnection.prepareStatement(sql2);
         ){      stmt2.setInt(1, category);
                 try( ResultSet rs2 = stmt2.executeQuery()){
                    while(rs2.next()){
                        // Création d'une liste contenant les données pour les implémenter dans la classe produit
                        List<String> donnees =new LinkedList<>();
                        // Ajout des données
                        donnees.add(rs2.getString("reference"));
                        donnees.add(rs2.getString("nom"));
                        donnees.add(rs2.getString("fournisseur"));
                        donnees.add(rs2.getString("quantite_par_unite"));
                        donnees.add(rs2.getString("prix_unitaire"));
                        // Récupération de la catégorie
                        int catProd = rs2.getInt("categorie");
                        // Implémentation du produit
                        ProductEntity prod = new ProductEntity(
                                donnees.get(0),// ref du produit
                                donnees.get(1), // nom du produit
                                donnees.get(2),// fournisseur du produit
                                catProd, // Num de catégorie du produit
                                donnees.get(3), // quantité vendu du produit
                                donnees.get(4) // prix du produit
                         );
                        result.add(prod); // Incrémentation du résultat.
                    }
                 }    
             
             
         }         
        return result;
    }
   
    /**
     * fonction récupérent les infos sur le client
     * @return
     * @throws SQLException 
     */
    public List<ClientEntity> getClient() throws SQLException{
        List<ClientEntity> result = new LinkedList<>();
        String sql = "SELECT * FROM CLIENT ";
        try(
                Connection myConnection = this.myDao.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ){
            if(rs.next()){
                 
                String code = rs.getNString("code");
                String societe = rs.getNString("societe");;
                 String contact = rs.getNString("contact");;
                String fonction = rs.getNString("fonction");;
                String adresse = rs.getNString("adresse");;
                String ville = rs.getNString("ville");;
                String region = rs.getNString("region");;
                int codePostal  = rs.getInt("code_postal");
                String pays = rs.getNString("pays");;
                String telephone = rs.getNString("telephone");;
                String fax = rs.getNString("fax");;
                
                result.add(new ClientEntity(code, societe, contact , fonction , adresse, ville, region, codePostal, pays , telephone , fax));
            }
        }
       return result;
    }
    
    
    /**
     * Fonction permettant la connection en administateur ou non.
     * @param user
     * @param pw
     */
    public  DAOClient toConnectClient(String user, String pw) throws SQLException{
        DataSource myDataSource = this.getDAO();
        DAOClient result = null ;
        String sql = "SELECT * FROM CLIENT Where contact = ?";
        try(
                Connection myConnection = this.myDao.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql);
         ){
           stmt.setString(1, user);
           try( ResultSet rs = stmt.executeQuery()){
               if(rs.next()){
                   String mdp = rs.getString("code");
                   this.client = mdp;
                    if(pw.equals(mdp)){
                        result = new DAOClient(myDataSource, pw);
                        result.setCodeClient(mdp);
                        this.DAOClient = result;
                        this.client = pw;
                    }
               }
           }
            
        }catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
           // throw new DAOException(ex.getMessage());
        }  
        return result;
    }
    public DAOAdmin toConnectAdmin(String user, String pw) throws SQLException{
        DAOAdmin result = null;
        if(user.equals("admin") && pw.equals("admin")){
            result = new DAOAdmin(this.getDAO());
            this.client = pw;
            
        }
        return result;
    }
    
    
    
     public DAOClient toDisconnectClient(){
         return null;
     }
     
     public DAOAdmin toDisconnectAdmin(){
         return null;
     }
    
}
