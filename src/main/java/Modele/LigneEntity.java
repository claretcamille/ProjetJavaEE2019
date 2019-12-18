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
public class LigneEntity {
    private int commande ;
    private int product;
    private int quantite;
    
    public LigneEntity( int refProd, int qt){
        this.product = refProd;
        this.quantite = qt;
    }
    
    public int getProduct(){
        return this.product;
    }
    
    public int getQuantite(){
        return this.quantite;
    }
    
}
