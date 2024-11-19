package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

// He cambiado el atributo carrocería de la tabla de vehiculo para que no sea un ENUM, aún tengo que probar a añadir el atributo para las imágenes
// Imagenes en una carpeta y con un método que las nombre con la matricula 1234ABC.jpg - todas misma extensión

public class createDatabase {
	/**
     * Establece una conexión a la base de datos SQLite.
     * 
     * @return Connection objeto que representa la conexión a la base de datos
     */
	public static Connection connect() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC"); // Cargar el driver JDBC para SQLite
			String url = "jdbc:sqlite:./test.db"; // Crea bdd "test.db"
			conn = DriverManager.getConnection(url);
			System.out.println("Conexión a SQLite establecida.");
		} catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
		}
		return conn;
	}

	/**
     * Crea la tabla 'vehiculo' en la base de datos si no existe
     * 
     */
	public static void createTableVehiculos() {
		// Definir la sentencia SQL
		String sql = "CREATE TABLE IF NOT EXISTS vehiculo (\n"
				+ " matricula TEXT PRIMARY KEY CHECK(length(matricula) = 7),\n" // Matrícula única de 7 caracteres
				+ " tipo TEXT NOT NULL CHECK(tipo IN ('coche', 'moto', 'camion')),\n" // Tipo de vehículo
				+ " marca TEXT NOT NULL,\n" // Marca del vehículo
				+ " modelo TEXT NOT NULL,\n" // Modelo del vehículo
//				+ " carroceria TEXT NOT NULL CHECK(carroceria IN ('suv', 'berlina', 'compacto')),\n" // Carrocería (ENUM) - hay que añadir tipos
				+ " carroceria TEXT NOT ,\n" // Carrocería
				+ " combustible TEXT NOT NULL CHECK(combustible IN ('diesel', 'gasolina', 'hibrido', 'electrico')),\n" // Combustible (ENUM)
				+ " consumo REAL NOT NULL,\n" // Consumo del vehículo (L/100 km)
				+ " plazas INTEGER,\n" // Las motos no tienen por qué tenerlo
				+ " kilometros REAL NOT NULL,\n" // Kilómetros recorridos
				+ " precio_compra REAL NOT NULL,\n" // Precio de compra
				+ " precio_venta REAL NOT NULL,\n" // Precio de compra
				+ " precio_alquiler REAL NOT NULL,\n" // Precio de alquiler - hay que automatizar que sea el de venta entre 1000 +/-
				+ " alquilado BOOLEAN NOT NULL CHECK(alquilado IN (0, 1))\n" // Alquilado (boolean)
				+ " imagen TEXT\n" // Ruta a un archivo JPG
				+ ");";

		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			// Ejecutar la sentencia SQL
			stmt.execute(sql);
			System.out.println("Tabla 'vehiculo' creada o ya existe.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
     * Crea la tabla 'alquilado' en la base de datos si no existe
     * 
     */
    public static void createTableAlquilados() {
        // Definir la sentencia SQL 
        String sql = "CREATE TABLE IF NOT EXISTS alquilado (\n"
                + " matricula TEXT PRIMARY KEY,\n"  // Matrícula única, foreign key desde Vehiculo
                + " fecha_inicio TEXT NOT NULL,\n"  // Fecha de inicio del alquiler
                + " fecha_fin TEXT\n,"  // Fecha de fin 
                + " FOREIGN KEY(matricula) REFERENCES vehiculo(matricula) ON DELETE CASCADE\n"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            // Ejecutar la sentencia SQL 
            stmt.execute(sql);
            System.out.println("Tabla 'alquilado' creada o ya existe.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Crea un trigger llamado 'return_trigger' que elimina un registro de
     * la tabla 'alquilado' cuando se devuelve un vehículo
     * 
     * Este trigger se activa después de actualizar el estado de alquiler
     * en la tabla 'vehiculo' a false
     */
    public static void createReturnTrigger() {
        // Definir el trigger para eliminar de la tabla 'alquilado' cuando se actualiza 'alquilado' en 'vehiculo' a false
        String sql = "CREATE TRIGGER IF NOT EXISTS return_trigger "
                + "AFTER UPDATE OF alquilado ON vehiculo "
                + "WHEN NEW.alquilado = 0 "  // Cuando el campo 'alquilado' se actualiza a 0 (false)
                + "BEGIN "
                + "DELETE FROM alquilado WHERE matricula = NEW.matricula; "
                + "END;";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            // Ejecutar la sentencia SQL 
            stmt.execute(sql);
            System.out.println("Trigger 'return_trigger' creado o ya existe.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	

}
