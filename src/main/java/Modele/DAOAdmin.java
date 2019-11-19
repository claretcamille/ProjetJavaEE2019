/*
 * Fichier pour les commandes spécifique de l'administrateur
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

/**
 *
 * @author camilleclaret
 */
public class DAOAdmin {
    
    private final DataSource myDAOAdmin;
    
    /**
     * Constructeur de la classe
     * @param myDAO est la dao générale créer
     */
    public DAOAdmin(DAO myDAO){
        this.myDAOAdmin = myDAO.getDAO();// récupération de la base générale
    }
    
    /**
     * Fonction permettant l'ajout d'un produit a la base de donnée
     * @param info liste des information nescessaire a l'ajout d'un produit
     * @param infoAdmin lisde des iformation des colone n'apparaissant pas dans ProductEntity
     * @throws java.sql.SQLException
     */
    public void addProduct(ProductEntity info, List<Integer> infoAdmin ) throws SQLException{
        
        String sql = "INSERT INTO  JAVAEE.PRODUIT  VALUES(?,?,?,?,?,?,?,?,?,?)"; 
        
        try(
                Connection myConnection = this.myDAOAdmin.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS)             
        ){
            // On démarre la transaction
            myConnection.setAutoCommit(false);
            try{
                
                // Création du produit
                stmt.executeQuery();
                
                //Ajout des infos;
                stmt.setString(2, info.getNom());
                stmt.setString(3, info.getFourni());
                stmt.setInt(4, info.getCat());
                stmt.setString(5, info.getQt());
                stmt.setString(6, info.getPrix());
                stmt.setInt(7,infoAdmin.get(0) );
                stmt.setInt(8, infoAdmin.get(1));
                stmt.setInt(9, infoAdmin.get(2));
                stmt.setInt(10, infoAdmin.get(3));
       
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
     * Fonction supprimant un produit de la base de données
     * @param reference la référence du produit a supprimer
     */
    public void suppProduct(int reference) throws SQLException {
        
        String sql = "DELETE FROM  JAVAEE.PRODUIT  WHERE reference = ? ";
        try(
                Connection myConnection = this.myDAOAdmin.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql )             
        ){
           try{
                // On démarre la transaction
                myConnection.setAutoCommit(false);
                stmt.setInt(1, reference);
                // Execution de la commande 
                stmt.executeQuery();     
           
            }catch (SQLException ex) {
                    Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                     myConnection.rollback(); // On annule la transaction
            }finally{
                    myConnection.setAutoCommit(true); // On revient au mode de fonctionnement sans transaction
            }
            myConnection.commit();
        }
        
    }
    
    /**
     * Fonction modifiant un produit 
     * @param reference la référence du produit a supprimer
     * @param choixModif est la ou porte la modification
     * @param modifProd est la modification a apporter
     */
    public void modifProduct(int reference, String choixModif, String modifProd) throws SQLException{
        
        String sql = "UPDATE  JAVAEE.PRODUIT SET "+choixModif +" = "+modifProd+" WHERE reference = ?";
        try(
                Connection myConnection = this.myDAOAdmin.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql )             
        ){
            
            try{
                // On démarre la transaction
                myConnection.setAutoCommit(false);
                stmt.setInt(1, reference);
                // Execution de la commande 
                stmt.executeQuery();     
           
            }catch (SQLException ex) {
                    Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                     myConnection.rollback(); // On annule la transaction
            }finally{
                    myConnection.setAutoCommit(true); // On revient au mode de fonctionnement sans transaction
            }
             myConnection.commit();     
        } 
        
    }
    /**
     * Fonction permettant d'avoir le chiffre d'affaire par categorie de produit en une période choisie
     * @param dateDebut
     * @param dateFin
     * @return la liste du chiffre d'affaire par catégorie
     */
    public List<String> chiffreAffCat(String dateDebut, String dateFin){
        return null;
    }
    
    /**
     * Fonction permettant d'avoir le chiffre d'affaire par pays  en une période choisie
     * @param pays
     * @param dateDebut
     * @param dateFin
     * @return a liste du chiffre d'affaire par pays
     */
    public List<String> chiffreAffPays(String pays, String dateDebut, String dateFin){
        return null;
    }
    
    /**
     * Fonction permettant d'avoir le chiffre d'affaire par client en une période choisie
     * @param categori
     * @param dateDebut
     * @param dateFin
     * @return a liste du chiffre d'affaire par client
     */
    public List<String> chiffreAffClient (String categori, String dateDebut, String dateFin){
        return null;
    }
    
}
