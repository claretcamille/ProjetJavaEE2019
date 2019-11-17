/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author camilleclaret
 */
public class DataSourceFactory {
    

    public static DataSource getDataSource() throws SQLException {
        
        org.apache.derby.jdbc.ClientDataSource ds = new org.apache.derby.jdbc.ClientDataSource();
        ds.setDatabaseName("projetJavaEE");
        ds.setUser("javaEE");
        ds.setPassword("javaEE");
        // The host on which Network Server is running
        ds.setServerName("localhost");
        // port on which Network Server is listening
        ds.setPortNumber(1527);
        
        return ds;
    }

}
