/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur_Servelet;

import Modele.DAO;
import Modele.DataSourceFactory;
import Modele.ProductEntity;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.derby.tools.ij;

/**
 * Web application lifecycle listener.
 *
 * @author camilleclaret
 */
public class CreateDataBase implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
            if (!databaseExists()) {
                initializeDatabase();
            }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
    
    private boolean databaseExists() {
        boolean result = false;
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        try {
            List<ProductEntity> products = dao.allProduct();
            Logger.getLogger("Projets").log(Level.INFO, "Database already exists");
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger("Projets").log(Level.INFO, "Database does not exist");
        }
        return result;
    }
    
    private void initializeDatabase() {
        OutputStream nowhere = new OutputStream() {
            @Override
            public void write(int b) {}
        };
        Logger.getLogger("Projets").log(Level.INFO, "Creating databse from SQL script");
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            int result = ij.runScript(connection, this.getClass().getResourceAsStream("export.sql"), "UTF-8", System.out, "UTF-8");
            if (result == 0 ) {
                Logger.getLogger("Projets").log(Level.INFO, "Database succesfully created");
            } else {
                Logger.getLogger("Projets").log(Level.SEVERE, "Errors creating database");
            }
        } catch (Exception e) {
            String s = e.getMessage();
            Logger.getLogger("Projets").log(Level.SEVERE, null, e);
        }
    }
}
