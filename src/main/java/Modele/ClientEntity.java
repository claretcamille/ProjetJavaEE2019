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
public class ClientEntity {
    
    private String code;
    private String societe;
    private String adresse;
    private String ville;
    private String region ;
    private int codePostal ;
    private String pays;
    
    /**
     * Constructeur
     * @param codeClient
     * @param societeClient
     * @param adresseClient
     * @param villeClient
     * @param regionClient
     * @param codePostalClient
     * @param paysClient 
     */
    public ClientEntity(String codeClient, String societeClient, String adresseClient, String villeClient, String regionClient, int codePostalClient,String paysClient){
        this.code = codeClient;
        this.societe = societeClient;
        this.adresse = adresseClient;
        this.ville = villeClient;
        this.region = regionClient;
        this.codePostal = codePostalClient;
        this.pays = paysClient;
    }
    
    public String getCodeClient(){
        return this.code;
    }
    
    public String getSocieteClient(){
        return this.societe;
    }
    
    public String getAdresseClient(){
        return this.adresse;
    }
    
    public String getVilleClient(){
        return this.ville;
    }
    
    public String getRegionClient(){
        return this.region;
    }
    
    public int getCodePostal(){
        return this.codePostal;
    }
    
    public String getPaysClient(){
        return this.pays;
    }
    
    
    
}
