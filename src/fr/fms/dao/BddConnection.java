package fr.fms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class BddConnection {
	
    private static Connection connection;

    private BddConnection() {}

    
    // Synchronized unique thread
    public synchronized static Connection getConnection() {
        if (connection == null) {
            try {
                Properties props = new Properties();
                try (FileInputStream fis = new FileInputStream("C:\\Users\\DelbosA\\Workspace\\JavaSE-workspace\\ExBdd\\src\\credentials.propreties")) {
                    props.load(fis);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Erreur de lecture du fichier de configuration.");
                }

                String driver = props.getProperty("db.driver.class");
                String url = props.getProperty("db.url");
                String username = props.getProperty("db.username");
                String password = props.getProperty("db.password");

                Class.forName(driver);

                connection = DriverManager.getConnection(url, username, password);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur de connexion à la base de données.");
            }
        }
        return connection;
    }
}
