package com.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.config.AppConfig;

/**
 *  @author Carlos
 *  @version 1.01
 */
public class DatabaseConnection {
	
	/**
     * Establece una conexión a una base de datos SQLite almacenada en la carpeta "resources/databases" del proyecto. 
     * 
     * @param databaseName nombre de la base de datos a la que se desea conectar (No se necesita la extensión .db)
     * @return la conexión establecida con la base de datos
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public static Connection connect(String databaseName) {
        Connection conn = null;
        try {
        	
            // Obtiene el path de la carpeta "resources/databases" del proyecto
            String dbFolder = AppConfig.PROJECT_PATH + "/src/resources/databases"; 

           System.out.println("Attempting connection to SQLite database: " + dbFolder + "/" + databaseName + ".db");
            
            // Se asegura que la carpeta exista
            File dbDir = new File(dbFolder);
            if (!dbDir.exists()) {
                System.out.println("Folder " + AppConfig.PROJECT_PATH + " does not exist.");  
            }
            
            // Crea la conexión a la base de datos
            String url = "jdbc:sqlite:" + dbFolder + "/" + databaseName + ".db";

            // Establece la conexión
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established at: " + dbFolder + "/" + databaseName + ".db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Error: 'databases' folder not found in resources.");
        }
        return conn;
    }
    
}
