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
    private String contact;
    private String fonction;
    private String adresse;
    private String ville;
    private String region ;
    private int codePostal ;
    private String pays;
    private String telephone;
    private String fax;
    
    /**
     * Constructeur
     * @param codeClient
     * @param societeClient
     * @param contactClient
     * @param fonctionClient
     * @param adresseClient
     * @param villeClient
     * @param regionClient
     * @param codePostalClient
     * @param paysClient 
     * @param telephoneClient 
     * @param faxClient 
     */
    public ClientEntity(String codeClient, String societeClient , String contactClient , String fonctionClient , String adresseClient, String villeClient, String regionClient, int codePostalClient , String paysClient , String telephoneClient , String faxClient){
        this.code = codeClient;
        this.societe = societeClient;
        this.contact = contactClient;
        this.fonction = fonctionClient;
        this.adresse = adresseClient;
        this.ville = villeClient;
        this.region = regionClient;
        this.codePostal = codePostalClient;
        this.pays = paysClient;
        this.telephone = telephoneClient;
        this.fax = faxClient;
    }
    
    public String getCodeClient(){
        return this.code;
    }
    
    public String getSocieteClient(){
        return this.societe;
    }
    
    public String getContactClient(){
        return this.contact;
    }
    
    public String getFonctionClient(){
        return this.fonction;
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
    
    public String getTelephoneClient(){
        return this.telephone;
    }
    
    public String getFaxClient(){
        return this.fax;
    }
}
