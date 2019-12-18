/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author camilleclaret
 */
public class CategorieEntity {
    
    private int code;
    private String libelle;
   
    /**
     * Constructeur
     * @param codeLibelle corespond au code de la table catégorie
     * @param description corespond au libelle de la table catégorie
     */
    public CategorieEntity(int codeLibelle, String description){
        this.code = codeLibelle;
        this.libelle = description;
    }
    
    /**
     * Fonction de récupération du libelle
     * @return libelle de la table catégorie
     */
    public String getLibelle(){
        return this.libelle;
    }
    
    /**
     * Fonction de récupération du code
     * @return code de la table catégorie
     */
    public int getCode(){
        return this.code;
    }
    
}
