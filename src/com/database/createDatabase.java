package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la creación y configuración de la base de datos
 * SQLite.
 * 
 * Proporciona métodos para establecer la conexión, crear tablas, y definir
 * triggers necesarios para la funcionalidad de la aplicación. Todos los métodos
 * de esta clase son estáticos, lo que permite su uso directo sin necesidad de
 * instanciar un objeto.
 * 
 * <p>
 * Funcionalidades principales:
 * <ul>
 * <li>Establecer la conexión a la base de datos SQLite
 * ({@link #connect()})</li>
 * <li>Crear la tabla de vehículos ({@link #createTableVehiculos()})</li>
 * <li>Crear la tabla de vehículos alquilados
 * ({@link #createTableAlquilados()})</li>
 * <li>Definir un trigger para manejar la devolución de vehículos
 * ({@link #createReturnTrigger()})</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Uso típico:
 * 
 * <pre>
 * createDatabase.createTableVehiculos();
 * createDatabase.createTableAlquilados();
 * createDatabase.createReturnTrigger();
 * </pre>
 * </p>
 * 
 * @author [Sara MJ]
 * @version 1.5
 */

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
			String url = "jdbc:sqlite:./database.db"; // Crea bdd "database.db"
			conn = DriverManager.getConnection(url);
			System.out.println("Conexión a SQLite establecida.");
			Statement stmt = conn.createStatement();
			stmt.execute("PRAGMA foreign_keys = ON;");
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e.getMessage());
		}
		return conn;
	}

	/**
	 * Crea la tabla 'vehiculo' en la base de datos SQLite si no existe.
	 * 
	 * La tabla `vehiculo` almacena información detallada de cada vehículo,
	 * incluyendo matrícula, tipo, marca, modelo, carrocería, combustible, consumo,
	 * plazas, kilómetros recorridos, precios (compra, venta, alquiler), estado de
	 * alquiler, y la ruta a una imagen asociada.
	 * 
	 * <p>
	 * SQL generado:
	 * 
	 * <pre>
	 * CREATE TABLE IF NOT EXISTS vehiculo (
	 *     matricula TEXT PRIMARY KEY CHECK(length(matricula) = 7),
	 *     tipo TEXT NOT NULL CHECK(tipo IN ('coche', 'moto', 'camion')),
	 *     marca TEXT NOT NULL,
	 *     modelo TEXT NOT NULL,
	 *     carroceria TEXT NOT NULL,
	 *     combustible TEXT NOT NULL CHECK(combustible IN ('diesel', 'gasolina', 'hibrido', 'electrico')),
	 *     consumo REAL NOT NULL,
	 *     plazas INTEGER,
	 *     kilometros REAL NOT NULL,
	 *     precio_compra REAL NOT NULL,
	 *     precio_venta REAL NOT NULL,
	 *     precio_alquiler REAL NOT NULL,
	 *     alquilado BOOLEAN NOT NULL CHECK(alquilado IN (0, 1)),
	 *     imagen TEXT
	 * );
	 * </pre>
	 * </p>
	 * 
	 * @throws SQLException si ocurre un error durante la ejecución del comando SQL.
	 */
	public static void createTableVehiculos() {
		// Definir la sentencia SQL
		String sql = "CREATE TABLE IF NOT EXISTS vehiculo (\n"
				+ " matricula TEXT PRIMARY KEY CHECK(length(matricula) = 7),\n" // Matrícula única de 7 caracteres
				+ " tipo TEXT NOT NULL CHECK(tipo IN ('coche', 'moto', 'camion')),\n" // Tipo de vehículo
				+ " marca TEXT NOT NULL,\n" // Marca del vehículo
				+ " modelo TEXT NOT NULL,\n" // Modelo del vehículo
				+ " carroceria TEXT NOT NULL,\n" // Carrocería
				+ " combustible TEXT NOT NULL CHECK(combustible IN ('diesel', 'gasolina', 'hibrido', 'electrico')),\n" // Combustible																						// (ENUM)
				+ " consumo REAL NOT NULL,\n" // Consumo del vehículo (L/100 km)
				+ " plazas INTEGER,\n" // Las motos no tienen por qué tenerlo
				+ " kilometros REAL NOT NULL,\n" // Kilómetros recorridos
				+ " precio_compra REAL NOT NULL,\n" // Precio de compra
				+ " precio_venta REAL NOT NULL,\n" // Precio de compra
				+ " precio_alquiler REAL NOT NULL,\n" // Precio de alquiler 
				+ " alquilado BOOLEAN NOT NULL CHECK(alquilado IN (0, 1)), \n" // Alquilado (boolean)
				+ " imagen TEXT \n" // Ruta a un archivo JPG
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
	 * Crea la tabla 'alquilado' en la base de datos SQLite si no existe.
	 * 
	 * La tabla `alquilado` almacena información sobre los vehículos alquilados,
	 * incluyendo la matrícula del vehículo, la fecha de inicio del alquiler y la
	 * fecha de fin (si aplica). La matrícula actúa como clave primaria y está
	 * vinculada como clave foránea a la tabla `vehiculo`.
	 * 
	 * <p>
	 * SQL generado:
	 * 
	 * <pre>
	 * CREATE TABLE IF NOT EXISTS alquilado (
	 *     matricula TEXT PRIMARY KEY,
	 *     fecha_inicio TEXT NOT NULL,
	 *     fecha_fin TEXT,
	 *     FOREIGN KEY(matricula) REFERENCES vehiculo(matricula) ON DELETE CASCADE
	 * );
	 * </pre>
	 * </p>
	 * 
	 * @throws SQLException si ocurre un error durante la ejecución del comando SQL.
	 */
	public static void createTableAlquilados() {
		// Definir la sentencia SQL
		String sql = "CREATE TABLE IF NOT EXISTS alquilado (\n" + " matricula TEXT PRIMARY KEY,\n" // Matrícula única, foreign key desde vehiculo
				+ " fecha_inicio TEXT NOT NULL,\n" // Fecha de inicio del alquiler
				+ " fecha_fin TEXT NOT NULL,\n" // Fecha de fin
				+ " FOREIGN KEY(matricula) REFERENCES vehiculo(matricula) ON DELETE CASCADE\n" + ");";

		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			// Ejecutar la sentencia SQL
			stmt.execute(sql);
			System.out.println("Tabla 'alquilado' creada o ya existe.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Crea un trigger llamado 'return_trigger' en la base de datos SQLite.
	 * 
	 * Este trigger se ejecuta automáticamente después de actualizar el campo
	 * `alquilado` en la tabla `vehiculo`. Si el valor del campo `alquilado` se
	 * establece en `false` (0), el trigger elimina el registro correspondiente en
	 * la tabla `alquilado`.
	 * 
	 * Este trigger asegura que los datos de la tabla `alquilado` se mantengan
	 * consistentes con el estado actual de los vehículos en la tabla `vehiculo`.
	 * 
	 * <p>
	 * SQL generado:
	 * 
	 * <pre>
	 * CREATE TRIGGER IF NOT EXISTS return_trigger
	 * AFTER UPDATE OF alquilado ON vehiculo
	 * WHEN NEW.alquilado = 0
	 * BEGIN
	 *     DELETE FROM alquilado WHERE matricula = NEW.matricula;
	 * END;
	 * </pre>
	 * </p>
	 * 
	 * @throws SQLException si ocurre un error durante la ejecución del comando SQL.
	 */
	public static void createReturnTrigger() {
		// Definir el trigger para eliminar de la tabla 'alquilado' cuando se actualiza 'alquilado' en 'vehiculo' a false
		String sql = "CREATE TRIGGER IF NOT EXISTS return_trigger " + "AFTER UPDATE OF alquilado ON vehiculo "
				+ "WHEN NEW.alquilado = 0 " // Cuando el campo 'alquilado' se actualiza a 0 (false)
				+ "BEGIN " + "DELETE FROM alquilado WHERE matricula = NEW.matricula; " + "END;";

		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			// Ejecutar la sentencia SQL
			stmt.execute(sql);
			System.out.println("Trigger 'return_trigger' creado o ya existe.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// Creada para DatabaseInitialData
	public static void EliminarTabla(String nombreTabla) {

		String sql = "DROP TABLE IF EXISTS " + nombreTabla;

		// Conexión a la base de datos y eliminación de la tabla
		try(
		Connection conn = connect();
		Statement stmt = conn.createStatement())
		{

			// Ejecutar la sentencia SQL para eliminar la tabla
			stmt.executeUpdate(sql);
			System.out.println("Tabla eliminada correctamente.");

		}catch(
		Exception e)
		{
			System.out.println("Error al eliminar la tabla: " + e.getMessage());
		}

	}

}
