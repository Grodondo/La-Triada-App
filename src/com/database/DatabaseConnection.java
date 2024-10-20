package com.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  @author Carlos
 *  @version 1.0
 */
public class DatabaseConnection {
	
	// Obtiene la ruta del proyecto
	private static String projectPath = System.getProperty("user.dir");
	
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
            String dbFolder = projectPath + "/resources/databases"; 

            // Se asegura que la carpeta exista, y si no, la crea
            File dbDir = new File(dbFolder);
            if (!dbDir.exists()) {
                dbDir.mkdirs();  
            }
            
            // Crea la conexión a la base de datos
            String url = "jdbc:sqlite:" + dbFolder + "/" + databaseName + ".db";

            // Establece la conexión
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established at: " + dbFolder + "/sample.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Error: 'databases' folder not found in resources.");
        }
        return conn;
    }
}
