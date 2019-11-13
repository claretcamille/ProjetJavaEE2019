/*
 *Fichier pour les commandes spécifique du client
 */
package Modele;

import java.util.List;
import javax.activation.DataSource;

/**
 *
 * @author camilleclaret
 */
public class DAOClient {
    
    private DataSource myDAOClient;
    private DAO myDAO;
    
    /**
     * Constructeur de la classe
     */
    public DAOClient(){
        this.myDAOClient = this.myDAO.getDAO();// récupération de la base générale
    }
    
    /**
     * Fonction ajoutant une nouvelle commande
     */
    public void addCommand(){
        
    }
    
    /**
     * Fonction permettant la suppression de tout une commande
     * @param numCommand est le numéro de référance de la commande
     */
     public void supCommand(int numCommand){
        
    }
    
     /**
      * Fonction ajoutant une ligne a la commande
      * @param numCommand
      * @param info 
      */
    public void addLineCommand(int numCommand, List<String> info){
        
    }
    
    /**
     * Fonction modifiant la ligne d'une commande
     * @param numCommand
     * @param info 
     */
    public void modifLineCommand(int numCommand, List<String> info){
        
    }
    
    /**
     * Fonction supprimant la ligne de la commande
     * @param numCommand
     * @param refProduct 
     */
    public void supLineCommand(int numCommand, int refProduct ){
        
    }
    
    /**
     * Fonction permettant de modifier les informations du client 
     * @param choixModif
     * @param modif 
     */
    public void modifInfoClient(String choixModif, String modif){
        
    }
    
   
    
}
