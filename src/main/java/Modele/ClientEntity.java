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
    private String codePostal ;
    private String pays;
    private String tel;
    private String fax;
    
    public ClientEntity(List<String> infoClient){
        this.code = infoClient.get(0);
        this.societe = infoClient.get(1);
        this.contact = infoClient.get(2);
        this.fonction = infoClient.get(3);
        this.adresse = infoClient.get(4);
        this.ville = infoClient.get(5);
        this.region = infoClient.get(6);
        this.codePostal = infoClient.get(7);
        this.pays = infoClient.get(8);
        this.tel = infoClient.get(9);
        this.fax = infoClient.get(10);
    }
    
    public String getContact(){
        return this.contact;
    }
    
}
