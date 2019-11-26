/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.List;

/**
 *
 * @author camilleclaret
 */
public class CommandeEntity {
    
    private int numero;
    private int port;
    private String saisieLe;
    private List<LigneEntity> lignes;
    
    /**
     * Constructeur
     * @param numCom
     * @param portCom
     * @param saisieLeCom
     * @param products 
     */
    public CommandeEntity(int numCom, int portCom, String saisieLeCom, List<LigneEntity> products){
        this.numero = numCom;
        this.port =portCom;
        this.saisieLe = saisieLeCom;
        this.lignes = products;
    }
    
    /**
     * 
     * @return 
     */
    public int getNum(){
        return this.numero;
    }
    
    /**
     * 
     * @return 
     */
    public int getPort(){
        return this.port;
    }
    
    /**
     * 
     * @return 
     */
    public String getSaisieLe(){
        return this.saisieLe;
    }
    
    /**
     * 
     * @return 
     */
    public List<LigneEntity> getLignes(){
        return this.lignes;
    }
    
}
