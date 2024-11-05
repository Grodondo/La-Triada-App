package com.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class sample1 {

	public static void createTableVehiculos() {
		// Definir la sentencia SQL
		String sql = "CREATE TABLE IF NOT EXISTS vehiculo (\n"
				+ " matricula TEXT PRIMARY KEY CHECK(length(matricula) = 8),\n" // Matrícula única de 8 caracteres
				+ " tipo TEXT NOT NULL CHECK(tipo IN ('coche', 'moto', 'camion')),\n" // Tipo de vehículo
				+ " marca TEXT NOT NULL,\n" // Marca del vehículo
				+ " modelo TEXT NOT NULL,\n" // Modelo del vehículo
				+ " carroceria TEXT NOT NULL CHECK(carroceria IN ('suv', 'berlina', 'compacto')),\n" // Carrocería (ENUM) - hay que añadir tipos
				+ " combustible TEXT NOT NULL CHECK(combustible IN ('diesel', 'gasolina', 'hibrido', 'electrico')),\n" // Combustible (ENUM)
				+ " consumo REAL NOT NULL,\n" // Consumo del vehículo (L/100 km)
				+ " kilometros REAL NOT NULL,\n" // Kilómetros recorridos
				+ " precio_compra REAL NOT NULL,\n" // Precio de compra
				+ " precio_alquiler REAL NOT NULL,\n" // Precio de alquiler
				+ " alquilado BOOLEAN NOT NULL CHECK(alquilado IN (0, 1))\n" // Alquilado (boolean)
				+ ");";

		try (Connection conn = DatabaseConnection.connect("vehiculos"); Statement stmt = conn.createStatement()) {
			// Ejecutar la sentencia SQL
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
    public static void createTableAlquilados() {
        // Definir la sentencia SQL 
        String sql = "CREATE TABLE IF NOT EXISTS alquilado (\n"
                + " matricula TEXT PRIMARY KEY,\n"  // Matrícula única, foreign key desde Vehiculo
                + " fecha_inicio TEXT NOT NULL,\n"  // Fecha de inicio del alquiler
                + " fecha_fin TEXT\n,"  // Fecha de fin 
                + " FOREIGN KEY(matricula) REFERENCES vehiculo(matricula) ON DELETE CASCADE\n"
                + ");";

        try (Connection conn = DatabaseConnection.connect("alquilado");
             Statement stmt = conn.createStatement()) {
            // Ejecutar la sentencia SQL 
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para crear el trigger que elimina el vehículo de la tabla 'alquilado' cuando se devuelve
    public static void createReturnTrigger() {
        // Definir el trigger para eliminar de la tabla 'alquilado' cuando se actualiza 'alquilado' en 'vehiculo' a false
        String sql = "CREATE TRIGGER IF NOT EXISTS return_trigger "
                + "AFTER UPDATE OF alquilado ON vehiculo "
                + "WHEN NEW.alquilado = 0 "  // Cuando el campo 'alquilado' se actualiza a 0 (false)
                + "BEGIN "
                + "DELETE FROM alquilado WHERE matricula = NEW.matricula; "
                + "END;";

        try (Connection conn = DatabaseConnection.connect("trigger");
             Statement stmt = conn.createStatement()) {
            // Ejecutar la sentencia SQL 
            stmt.execute(sql);
            System.out.println("Trigger 'return_trigger' creado o ya existe.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	

}
