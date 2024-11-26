package com.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.database.createDatabase;
import com.models.Vehiculo;

/**
 * Clase encargada de inicializar la base de datos para la aplicación.
 * 
 * Contiene métodos para crear tablas, triggers y datos iniciales en la base de
 * datos SQLite. Esta clase utiliza los métodos definidos en la clase
 * {@link createDatabase} para establecer la estructura inicial y poblarla con
 * datos predeterminados.
 * 
 * <p>
 * El flujo principal incluye:
 * <ul>
 * <li>Creación de la tabla de vehículos
 * ({@link createDatabase#createTableVehiculos()})</li>
 * <li>Creación de la tabla de vehículos alquilados
 * ({@link createDatabase#createTableAlquilados()})</li>
 * <li>Creación de un trigger para manejar el retorno de vehículos
 * ({@link createDatabase#createReturnTrigger()})</li>
 * <li>Inserción de datos iniciales en la tabla de vehículos
 * ({@link #insertarDatosIniciales()})</li>
 * </ul>
 * </p>
 * 
 * @author [Sara MJ]
 * @version 1.0
 */

public class DatabaseInitialData {
	public static void main(String[] args) {
		// Usados solo para correcciones
		/*
		 * createDatabase.EliminarTabla("alquilado");
		 * createDatabase.EliminarTabla("vehiculo");
		 */

		createDatabase.createTableVehiculos();
		createDatabase.createTableAlquilados();
		createDatabase.createReturnTrigger();
		try (Connection conn = createDatabase.connect()) {
			conn.setAutoCommit(false);

			// Crear tablas
			createDatabase.createTableVehiculos();
			createDatabase.createTableAlquilados();

			// Insertar datos iniciales
			insertarDatosIniciales();

			conn.commit();
		} catch (SQLException e) {
			System.out.println("Error en la transacción: " + e.getMessage());
		}

	}

	public static void insertarDatosIniciales() {
		try {
			// Insertar coches
			Vehiculo.insertarVehiculoInicial("7654DCY", "coche", "toyota", "corolla sedan", "berlina", "hibrido", 5.00,
					5, 45678, 15000.00, 20.00, false);
			Vehiculo.insertarVehiculoInicial("9238JRC", "coche", "toyota", "c-hr", "suv", "hibrido", 4.70, 5, 98734,
					18900.00, 23.90, false);
			Vehiculo.insertarVehiculoInicial("6789LTR", "coche", "toyota", "yaris", "turismo", "hibrido", 4.60, 5,
					20345, 20000.00, 25.00, false);
			Vehiculo.insertarVehiculoInicial("0987KPT", "coche", "kia", "xceed", "crossover", "hibrido", 4.95, 5, 18928,
					19000.00, 24.00, false);
			Vehiculo.insertarVehiculoInicial("2348HTZ", "coche", "hyundai", "i20", "turismo", "hibrido", 5.15, 5, 38762,
					18000.00, 23.00, false);
			// Nuevos
			Vehiculo.insertarVehiculoInicial("1234ABC", "coche", "ford", "focus hatchback", "compacto", "gasolina",
					4.50, 5, 45679, 12000.00, 18.00, true);
			Vehiculo.insertarVehiculoInicial("2345BCD", "coche", "honda", "civic sedan", "berlina", "diesel", 4.80, 5,
					45680, 18000.00, 25.00, false);
			Vehiculo.insertarVehiculoInicial("3456CDE", "coche", "audi", "a3 sportback", "compacto", "hibrido", 5.20, 5,
					45681, 25000.00, 22.00, true);
			Vehiculo.insertarVehiculoInicial("4567DEF", "coche", "mercedes", "benz clase c", "berlina", "gasolina",
					5.30, 5, 45682, 30000.00, 28.00, false);
			Vehiculo.insertarVehiculoInicial("5678EFG", "coche", "bmw", "serie 3 sedan", "berlina", "diesel", 5.10, 5,
					45683, 35000.00, 30.00, true);
			Vehiculo.insertarVehiculoInicial("6789FGH", "coche", "renault", "megane hatchback", "compacto", "gasolina",
					4.60, 5, 45684, 10000.00, 17.00, false);
			// Insertar motos
			Vehiculo.insertarVehiculoInicial("7539JBR", "moto", "bmw", "r 1300 gs", "tracker", "gasolina", 5.00, null,
					14556, 15000.00, 20.00, false);
			Vehiculo.insertarVehiculoInicial("8743GVT", "moto", "kawasaki", "ninja 400", "sport", "gasolina", 4.90,
					null, 19384, 14000.00, 19.00, false);
			Vehiculo.insertarVehiculoInicial("9874HRC", "moto", "yamaha", "xsr125 legacy", "heritage", "gasolina", 4.57,
					null, 12345, 13900.00, 18.00, false);
			// Nuevas
			Vehiculo.insertarVehiculoInicial("8437KLC", "moto", "harley davidson", "iron 883", "chopper", "gasolina",
					4.80, null, 14557, 12000.00, 18.00, true);
			Vehiculo.insertarVehiculoInicial("9472LKM", "moto", "yamaha", "mt-09", "naked", "gasolina", 4.90, null,
					14558, 11000.00, 16.00, false);
			Vehiculo.insertarVehiculoInicial("8365MNP", "moto", "ducati", "monster 1200", "naked", "gasolina", 5.10,
					null, 14559, 16000.00, 22.00, true);
			Vehiculo.insertarVehiculoInicial("9203OPQ", "moto", "kawasaki", "ninja zx-6r", "deportiva", "gasolina",
					5.20, null, 14560, 14000.00, 19.00, false);
			// Insertar camiones
			Vehiculo.insertarVehiculoInicial("8742GTR", "camion", "mercedes", "atego 1418", "ligero", "diesel", 6.34, 3,
					23456, 19000.00, 24.00, false);
			Vehiculo.insertarVehiculoInicial("7934LTC", "camion", "iveco", "stralis ad", "pesado", "diesel", 5.79, 3,
					45678, 18000.00, 23.00, false);
			Vehiculo.insertarVehiculoInicial("9876KCT", "camion", "volvo", "fh16 750", "pesado", "diesel", 5.95, 3,
					34563, 15000.00, 20.00, false);
			// Nuevos
			Vehiculo.insertarVehiculoInicial("9234HTL", "camion", "volvo", "fh 12", "pesado", "diesel", 7.50, 4, 23457,
					25000.00, 30.00, true);
			Vehiculo.insertarVehiculoInicial("8321KRM", "camion", "man", "tgs 18.400", "ligero", "diesel", 6.80, 2,
					23458, 22000.00, 28.00, false);
			Vehiculo.insertarVehiculoInicial("7562LKT", "camion", "scania", "r 450", "pesado", "diesel", 7.20, 3, 23459,
					27000.00, 26.00, true);

			System.out.println("Datos iniciales insertados correctamente.");
		} catch (Exception e) {
			System.out.println("Error al insertar datos iniciales: " + e.getMessage());
		}
	}

}
