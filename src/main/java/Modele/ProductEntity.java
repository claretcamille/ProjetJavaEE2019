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
public class ProductEntity {
    
    private String ref;
    private String nom;
    private String fournisseur;
    private int categorie;
    private String quantite;
    private String prix;
    
    public ProductEntity(String refProd,String nomProd, String fourniProd, int catProd, String qtProd, String prixProd){
        this.ref = refProd;
        this.nom = nomProd;
        this.fournisseur = fourniProd;
        this.categorie = catProd;
        this.quantite = qtProd;
        this.prix = prixProd;
    }
    
    /**
     * Fonction permettant la récupération de la référence du produit
     * @return 
     */
    public String getRef(){
        return this.ref;
    }
    
     /**
     * Fonction permettant la récupération de le nom du produit
     * @return 
     */
    public String getNom(){
        return this.nom;
    }
    
     /**
     * Fonction permettant la récupération de le fournisseur du produit
     * @return 
     */
    public String getFourni(){
        return this.fournisseur;
    }
    
     /**
     * Fonction permettant la récupération de la catégorie du produit
     * @return 
     */
    public int getCat(){
        return this.categorie;
    }
    
     /**
     * Fonction permettant la récupération de la quantité vendu du produit
     * @return 
     */
    public String getQt(){
        return this.quantite;
    }
    
     /**
     * Fonction permettant la récupération de le prix du produit
     * @return 
     */
    public String getPrix(){
        return this.prix;
    }
    
}
