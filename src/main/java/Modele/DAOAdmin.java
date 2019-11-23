/*
 * Fichier pour les commandes spécifique de l'administrateur
 */
package Modele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
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
    private DAO dao;
    
    /**
     * Constructeur de la classe
     * @param myDAO est la dao générale créer
     */
    public DAOAdmin(DAO myDAO){
        this.myDAOAdmin = myDAO.getDAO();// récupération de la base générale
        dao = myDAO;
    }
    
    public DAO getDAO(){
        return this.dao;
    }
    
    /**
     * Fonction permettant l'ajout d'un produit a la base de donnée
     * @param info liste des information nescessaire a l'ajout d'un produit
     * @param infoAdmin lisde des iformation des colone n'apparaissant pas dans ProductEntity
     * @throws java.sql.SQLException
     */
    public void addProduct(ProductEntity info, List<Integer> infoAdmin ) throws SQLException{
        
        String sql = "INSERT INTO  PRODUIT(nom,fournisseur,categorie,quantite_par_unite,prix_unitaire,unites_en_stock, unites_commandees,niveau_de_reappro,indisponible)  "
                + "VALUES(?,?,?,?,?,?,?,?,?)"; 
        
        try(
                Connection myConnection = this.myDAOAdmin.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS)             
        ){
            // On démarre la transaction
            myConnection.setAutoCommit(false);
            try{

                //Ajout des infos;
                
                stmt.setString(1, info.getNom());
                stmt.setString(2, info.getFourni());
                stmt.setInt(3, info.getCat());
                stmt.setString(4, info.getQt());
                stmt.setString(5, info.getPrix());
                stmt.setInt(6,infoAdmin.get(0) );
                stmt.setInt(7, infoAdmin.get(1));
                stmt.setInt(8, infoAdmin.get(2));
                stmt.setInt(9, infoAdmin.get(3));
                
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
     * Fonction supprimant un produit de la base de données
     * @param reference la référence du produit a supprimer
     */
    public void suppProduct(int reference) throws SQLException {
        // ici on ne peut pas supprimer directement le produit de la table
        // car sinon on doit supprimer les commandes ou le produit apparaît, on le passe donc en indisponible
        String sql = "UPDATE PRODUIT SET indisponible = 1  WHERE reference = " + reference;
        try(
                Connection myConnection = this.myDAOAdmin.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql )             
        ){
            myConnection.setAutoCommit(false);
            stmt.executeUpdate();
            myConnection.setAutoCommit(true);
        }
        
    }
    
    /**
     * Fonction modifiant un produit 
     * @param reference la référence du produit a supprimer
     * @param choixModif est la ou porte la modification
     * @param modifProd est la modification a apporter
     */
    public void modifProduct(int reference, String choixModif, String modifProd) throws SQLException{
        
        String sql = "UPDATE PRODUIT SET "+choixModif+" = '"+modifProd+"' WHERE reference = "+reference;
        
        try(
                Connection myConnection = this.myDAOAdmin.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql)
                ){ 
            myConnection.setAutoCommit(false);
            stmt.executeUpdate();
            myConnection.setAutoCommit(true);
            
            // Ici pas de rollBack() car une requête donc si elle marche pas aucun impact
            
        }
    }
    /**
     * Fonction permettant d'avoir le chiffre d'affaire par categorie de produit en une période choisie
     * @param dateDebut
     * @param dateFin
     * @return la liste du chiffre d'affaire par catégorie
     */
    public List<ChiffreAffEntity> chiffreAffCat(String dateDebut, String dateFin) throws SQLException{
       String sql1 ="SELECT   PRODUIT.CATEGORIE, PRODUIT.REFERENCE, LIGNE.QUANTITE , PRODUIT.PRIX_UNITAIRE  FROM LIGNE\n" +
                            "INNER JOIN  COMMANDE  ON LIGNE.COMMANDE = COMMANDE.NUMERO\n" +
                            "INNER JOIN PRODUIT ON LIGNE.PRODUIT = PRODUIT.REFERENCE\n" +
                            "WHERE COMMANDE.SAISIE_LE > ? AND  COMMANDE.SAISIE_LE < ?  AND  PRODUIT.CATEGORIE = ?";
       
       String sql2 = "SELECT COUNT(DISTINCT code) FROM  CATEGORIE";
       
       List<ChiffreAffEntity> result = new LinkedList<>();
       
       try(
                 Connection myConnection = this.myDAOAdmin.getConnection();
                 PreparedStatement stmt1 = myConnection.prepareStatement(sql2 )  ;
                 PreparedStatement stmt2 = myConnection.prepareStatement(sql1)
               ){
           ResultSet rs1 = stmt1.executeQuery();
           // Initialisation de la liste avec toute les catégorie
          for(int i = 1; i < rs1.getFetchDirection()+1; i++){
              String cat = Integer.toString(i);
              result.add(new ChiffreAffEntity(cat,0));
              // Mise à jour du chiffre d'affaire
             stmt2.setString(1, dateDebut);
             stmt2.setString(2, dateFin);
             stmt2.setInt(3, i);
             ResultSet rs2 = stmt2.executeQuery();
             while(rs2.next()){
                  // mise à jour du chiffre d'affaire
                  float qt = rs2.getFloat("quantite");
                  float prix = rs2.getFloat("prix_unitaire");
                  result.get(i-1).ajoutChiffre(qt*prix);
            }
          }
          
       }
        return result;
    }
    
    /**
     * Fonction permettant d'avoir le chiffre d'affaire par pays  en une période choisie
     * @param dateDebut
     * @param dateFin
     * @return a liste du chiffre d'affaire par pays
     */
    public List<ChiffreAffEntity> chiffreAffPays( String dateDebut, String dateFin) throws SQLException{
        String sql1 ="SELECT  port  FROM COMMANDE WHERE saisie_le > ? AND  saisie_le < ?  AND pays_livraison= ?";
        
        String sql2 = "SELECT DISTINCT pays FROM CLIENT";
        
        List<ChiffreAffEntity> result = new LinkedList<>();
        List<String>  pays = new LinkedList<>();
        
        try(
              Connection myConnection = this.myDAOAdmin.getConnection();
              PreparedStatement stmt1 = myConnection.prepareStatement(sql2 )  ;
              PreparedStatement stmt2 = myConnection.prepareStatement(sql1)  
         ){
            ResultSet rs1 = stmt1.executeQuery();
            int index = 0;
           // Initialisation de la liste avec toute les catégorie
          while(rs1.next()){
              String info = rs1.getNString("pays");
              pays.add(info);
              result.add(new ChiffreAffEntity(info,0));
             // Mise à jour du chiffre d'affaire
             stmt2.setString(1, dateDebut);
             stmt2.setString(2, dateFin);
             stmt2.setString(3, info);
             ResultSet rs2 = stmt2.executeQuery(); 
             while(rs2.next()){
                 result.get(index).ajoutChiffre(rs2.getFloat("port"));
             }
             index++;
          }
         
            
        }
        return result;
    }
    
    /**
     * Fonction permettant d'avoir le chiffre d'affaire par client en une période choisie
     * @param dateDebut
     * @param dateFin
     * @return a liste du chiffre d'affaire par client
     */
    public List<ChiffreAffEntity> chiffreAffClient ( String dateDebut, String dateFin) throws SQLException{
        String sql1 =" SELECT port FROM COMMANDE WHERE COMMANDE.SAISIE_LE > ? AND  COMMANDE.SAISIE_LE < ? AND client = ?";
        
        String sql2 = "SELECT code FROM CLIENT";
        
        List<ChiffreAffEntity> result = new LinkedList<>();
        List<String>  client = new LinkedList<>();
        
        try(
              Connection myConnection = this.myDAOAdmin.getConnection();
              PreparedStatement stmt1 = myConnection.prepareStatement(sql2 )  ;
              PreparedStatement stmt2 = myConnection.prepareStatement(sql1)  
         ){
            ResultSet rs1 = stmt1.executeQuery();
            String info = null;
            int index = 0;
           // Initialisation de la liste avec toute les catégorie
          while(rs1.next()){
              info = rs1.getNString("code");
              client.add(info);
              result.add(new ChiffreAffEntity(info,0));
          
              // Mise à jour du chiffre d'affaire
              stmt2.setString(1, dateDebut);
              stmt2.setString(2, dateFin);
              stmt2.setString(3, info);
              ResultSet rs2 = stmt2.executeQuery();
              while(rs2.next()){
                     result.get(index).ajoutChiffre(rs2.getFloat("port"));
              }
              index++;
           } 
        }
        return result;
    }
    
    /**
     * Fonction donnant la liste des dates possible
     * @return liste de date
     * @throws SQLException 
     */
    public List<String> dateSelection() throws SQLException{
        List<String> result = new LinkedList<>();
        String sql = "SELECT DISTINCT SAISIE_LE FROM COMMANDE";
        try(
              Connection myConnection = this.myDAOAdmin.getConnection();
              PreparedStatement stmt = myConnection.prepareStatement(sql);
              ResultSet rs = stmt.executeQuery();
        ){
            while(rs.next()){
                result.add( rs.getDate(1).toString());
            }
        }
        return result;
    }
    
}
