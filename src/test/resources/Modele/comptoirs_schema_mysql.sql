DROP TABLE IF EXISTS Ligne;
DROP TABLE IF EXISTS Commande;
DROP TABLE IF EXISTS Produit;
DROP TABLE IF EXISTS Categorie;
DROP TABLE IF EXISTS Client;


CREATE TABLE IF NOT EXISTS Categorie (
  Code int(11) NOT NULL auto_increment,
  Libelle varchar(25) NOT NULL default '',
  Description longtext,
  PRIMARY KEY  (Code),
  UNIQUE KEY Libelle (Libelle)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

CREATE TABLE IF NOT EXISTS Client (
  Code char(5) NOT NULL default '',
  Societe char(40) NOT NULL default '',
  Contact char(30) default NULL,
  Fonction char(30) default NULL,
  Adresse char(60) default NULL,
  Ville char(15) default NULL,
  Region char(15) default NULL,
  Code_postal char(10) default NULL,
  Pays char(15) default NULL,
  Telephone char(24) default NULL,
  Fax char(24) default NULL,
  PRIMARY KEY  (Code),
  UNIQUE KEY Societe (Societe)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Produit (
  Reference int(11) NOT NULL auto_increment,
  Nom char(40) NOT NULL default '',
  Fournisseur int(11) NOT NULL default '0',
  Categorie int(11) NOT NULL default '0',
  Quantite_par_unite char(30) default NULL,
  Prix_unitaire decimal(18,2) NOT NULL default '0.0000',
  Unites_en_stock smallint(6) NOT NULL default '0',
  Unites_commandees smallint(6) NOT NULL default '0',
  Niveau_de_reapprovi smallint(6) NOT NULL default '0',
  Indisponible tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (Reference),
  FOREIGN KEY (Categorie) REFERENCES Categorie (Code) ON UPDATE CASCADE,
  KEY Fournisseur (Fournisseur),
  UNIQUE KEY Nom (Nom)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=78 ;

CREATE TABLE IF NOT EXISTS Commande (
  Numero int(11) NOT NULL auto_increment,
  Client char(5) NOT NULL default '',
  SaisieLe datetime default CURRENT_TIMESTAMP,
  EnvoyeeLe datetime default NULL,
  Port decimal(18,2) default NULL,
  Destinataire char(40) default NULL,
  Adresse_livraison char(60) default NULL,
  Ville_livraison char(15) default NULL,
  Region_livraison char(15) default NULL,
  Code_postal_livrais char(10) default NULL,
  Pays_livraison char(15) default NULL,
  Remise float(10,2) NOT NULL default '0.00',
  PRIMARY KEY  (Numero),
  FOREIGN KEY (Client) REFERENCES Client (Code) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=11078 ;

CREATE TABLE IF NOT EXISTS Ligne (
  Commande int(11) NOT NULL default '0',
  Produit int(11) NOT NULL default '0',
  Quantite smallint(6) NOT NULL default '1',
  PRIMARY KEY  (Commande,Produit),
  FOREIGN KEY (Produit) REFERENCES Produit (Reference) ON UPDATE CASCADE,
  FOREIGN KEY (Commande) REFERENCES Commande (Numero) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

