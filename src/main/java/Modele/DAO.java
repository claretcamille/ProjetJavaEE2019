/*
 * Fichier pour les fonctions générale de la DAO
 */
package Modele;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

/**
 * @author camilleclaret
 */
public class DAO {
    
    private DataSource myDao;
    
    
      /**
     * Constructeur de la classe
     * @param DataSource est la base de données mise 
    */
    public DAO() throws SQLException{
        this.myDao =  DataSourceFactory.getDataSource();
    }
    
    /**
     * Constructeur de la classe
     * @param DataSource est la base de données mise 
    */
    public DAO(DataSource DataSource){
        this.myDao =  DataSource;
    }

    /**
     * Permet de récupérer la base de donnée
     * @return la base de données utilisé.
     */
    public DataSource getDAO(){
        return this.myDao;
    }
    
    /**
     * Fonction qui retourne la liste de tous les produit
     * @return la liste de tous les produits
     */
    public List<String> allProduct(){
        return null;
    }
    
    /**
     * Fonction cherchant les produits correspondant a une catégorie selectionner
     * @param category est la catégorie d'article
     * @return la liste des articles appartenant a la categorie selectionner
     */
    public List<String> categoryProduct(String category){
        return null;
    }
    
    /**
     * Fonction permettant la connection en administateur ou non.
     */
    public void toConnect(){
        
    }
}
