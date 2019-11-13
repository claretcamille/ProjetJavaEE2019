/*
 * Fichier pour les commandes spécifique de l'administrateur
 */
package Modele;

import java.util.List;
import javax.activation.DataSource;

/**
 *
 * @author camilleclaret
 */
public class DAOAdmin {
    
    private DataSource myDAOAdmin;
    private DAO myDAO;
    
    
    /**
     * Constructeur de la classe
     */
    public DAOAdmin(){
        this.myDAOAdmin = this.myDAO.getDAO();// récupération de la base générale
    }
    
    /**
     * Fonction permettant l'ajout d'un produit a la base de donnée
     * @param Info liste des information nescessaire a l'ajout d'un produit
     */
    public void addProduct(List<String> Info ){
  
    }
    
    /**
     * Fonction supprimant un produit de la base de données
     * @param reference la référence du produit a supprimer
     */
    public void suppProduct(int reference){
        
    }
    
    /**
     * Fonction modifiant un produit 
     * @param reference la référence du produit a supprimer
     * @param choixModif est la ou porte la modification
     * @param modifProd est la modification a apporter
     */
    public void modifProduct(int reference, String choixModif, String modifProd){
        
    }
    /**
     * Fonction permettant d'avoir le chiffre d'affaire par categorie de produit en une période choisie
     * @param categori 
     * @param dateDebut
     * @param dateFin
     * @return la liste du chiffre d'affaire par catégorie
     */
    public List<String> chiffreAffCat(String categori, String dateDebut, String dateFin){
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
