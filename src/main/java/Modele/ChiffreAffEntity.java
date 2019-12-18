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
public class ChiffreAffEntity{
    
    private String choix;
    private float chiffre;
    
    /**
     * Constructeur
     * @param info est la catégorie, le pays ou le client choisie
     * @param chifInfo est le chiffre d'affaire relier a celui-ci
     */
    public ChiffreAffEntity(String info, float chifInfo){
        this.choix = info;
        this.chiffre = chifInfo;
    }
    
    /**
     * fonction de récupération de l'information
     * @return le choix
     */
    public String getInfo(){
        return this.choix;
    }
    
    /**
     * fonction de récupération de chiffre 
     * @return chiffre associer au choix
     */
    public float getChiffre(){
        return this.chiffre;
    }
    
    /**
     * Fonction pour augmanter le chiffre
     * @param chif 
     */
    public void ajoutChiffre(float chif){
        this.chiffre += chif;
    }
            
    
}
