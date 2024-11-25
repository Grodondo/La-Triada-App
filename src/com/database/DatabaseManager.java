package com.database;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;

import com.config.AppConfig;
import com.models.Vehiculo;

/**
 * La clase <code>DatabaseManager</code> se encarga de gestionar la conexión con
 * la base de datos, eliminacion de vehiculos de la tabla e introduccion de los
 * mismos.
 * 
 * @version 1.0
 * @autor [Carlos Arroyo Caballero]
 * @version 1.0
 */
public class DatabaseManager {

	public static Connection con = createDatabase.connect();

	/**
	 * Constructor de la clase <code>DatabaseManager</code>. 
	 */
	public DatabaseManager() {
	}

	/**
	 * Metodo para eliminar un vehiculo de la tabla especificada.
	 * 
	 * @param matricula Identificador del vehiculo a eliminar.
	 * @param nameTable Nombre de la tabla de la que se eliminará el vehiculo.
	 */
	public void eliminarVehiculo(String matricula, String nameTable) {

		if (!"vehiculo".equals(nameTable) && !"alquilado".equals(nameTable)) {
			throw new IllegalArgumentException("Nombre de tabla no válido: " + nameTable);
		}

		// Consulta SQL para eliminar el vehículo
		String sqlDelete = "DELETE FROM " + nameTable + " WHERE matricula = ?";

		try (PreparedStatement psDelete = con.prepareStatement(sqlDelete)) {
			psDelete.setString(1, matricula); // Establece la matrícula como parámetro
			int rowsAffected = psDelete.executeUpdate(); // Ejecuta la consulta

			if (rowsAffected > 0) {
				System.out.println("Vehículo con matrícula " + matricula + " eliminado de la tabla " + nameTable + ".");
			} else {
				System.out.println(
						"No se encontró un vehículo con matrícula " + matricula + " en la tabla " + nameTable + ".");
			}
		} catch (SQLException e) {
			System.err.println("Error al eliminar el vehículo: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para eliminar un vehiculo de la tabla 'alquilado'.
	 *
	 * @param vehicle Vehiculo a eliminar de la tabla 'alquilado'.
	 */
	public static void eliminarVehiculoAlquilado(String matricula) {
		//String sqlDeleteAlquilado = "DELETE FROM alquilado WHERE matricula = ?";
		String sqlUpdateVehiculo = "UPDATE vehiculo SET alquilado = 0 WHERE matricula = ?";

		try (PreparedStatement psUpdateVehiculo = con.prepareStatement(sqlUpdateVehiculo)) {

			// Actualizar el estado del vehiculo en la tabla 'vehiculo'
			psUpdateVehiculo.setString(1, matricula);
			psUpdateVehiculo.executeUpdate();

			System.out.println("Vehículo eliminado de la tabla 'alquilado' y marcado como no alquilado.");

		} catch (SQLException e) {
			System.err.println("Error al eliminar el vehículo de la base de datos: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void insertarVehiculo(Vehiculo vehicle) throws SQLException {
		// Directorio base para las imágenes
		String directorioImagenes = AppConfig.PROJECT_PATH + "\\src\\com\\database\\imagenes\\";

		// Generar la ruta de la imagen usando la matrícula
		String rutaImagen = Paths.get(directorioImagenes, vehicle.getMatricula() + ".jpg").toString();
			
		String sql = "INSERT INTO vehiculo(matricula, tipo, marca, modelo, carroceria, combustible, consumo, plazas, kilometros, precio_compra, precio_venta, precio_alquiler, alquilado, imagen) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = createDatabase.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, vehicle.getMatricula());
			pstmt.setString(2, vehicle.getTipo());
			pstmt.setString(3, vehicle.getMarca());
			pstmt.setString(4, vehicle.getModelo());
			pstmt.setString(5, vehicle.getCarroceria());
			pstmt.setString(6, vehicle.getCombustible());
			pstmt.setDouble(7, vehicle.getConsumo());
			if (vehicle.getPlazas() != null) {
				pstmt.setInt(8, vehicle.getPlazas());
			}else {
				pstmt.setNull(8, Types.INTEGER);
			}
			pstmt.setDouble(9, vehicle.getKilometros());
			pstmt.setDouble(10, vehicle.getPrecioCompra());
			pstmt.setDouble(11, vehicle.getPrecioCompra() * 1.33); // Ejemplo: 33% margen sobre el precio de compra
			pstmt.setDouble(12, vehicle.getPrecioAlquiler());
			pstmt.setBoolean(13, vehicle.isAlquilado());
			pstmt.setString(14, rutaImagen); // Ruta generada de la imagen
			
			pstmt.executeUpdate();
			System.out.println("Vehículo insertado correctamente: " + vehicle.getMatricula());
		} 
	}
	
	/**
	 * Metodo para introducir un vehiculo en la base de datos.
	 * 
	 * @param vehicle  Vehiculo a introducir.
	 * @param fechaFin Fecha en la que finaliza el alquiler (puede ser null si no se
	 *                 especifica).
	 */
	public static void introducirVehiculoAlquilado(Vehiculo vehicle, String fechaFin) {
		//String sqlInsertAlquilado = "INSERT INTO alquilado (matricula, fecha_inicio, fecha_fin) VALUES (?, ?, ?)";
		String sqlUpdateVehiculo = "UPDATE vehiculo SET alquilado = 1 WHERE matricula = ?";
		try (
			 PreparedStatement psUpdateVehiculo = con.prepareStatement(sqlUpdateVehiculo)) {

//			// Establecer valores para la tabla 'alquilado'
//			psInsertAlquilado.setString(1, vehicle.getMatricula());
//			psInsertAlquilado.setString(2, LocalDate.now().toString()); // Fecha actual como fecha_inicio
//			psInsertAlquilado.setString(3, fechaFin); // Puede ser null
//
//			// Ejecutar inserción en la tabla 'alquilado'
//			psInsertAlquilado.executeUpdate();

			// Actualizar el estado del vehiculo en la tabla 'vehiculo' (alquilado = 1)
			psUpdateVehiculo.setString(1, vehicle.getMatricula());
			psUpdateVehiculo.executeUpdate();

			System.out.println("Vehículo introducido en la tabla 'alquilado' y marcado como alquilado.");

		} catch (SQLException e) {
			System.err.println("Error al introducir el vehículo en la base de datos: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para imprimir todos los valores de la tabla 'vehiculo' en consola.
	 */
	public static void imprimirTablaVehiculo() {
	    String sqlSelect = "SELECT * FROM vehiculo";

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sqlSelect)) {

	        // Recorrer el resultado y mostrar los valores en consola
	        while (rs.next()) {
	            String matricula = rs.getString("matricula");
	            String tipo = rs.getString("tipo");
	            String marca = rs.getString("marca");
	            String modelo = rs.getString("modelo");
	            String carroceria = rs.getString("carroceria");
	            String combustible = rs.getString("combustible");
	            double consumo = rs.getDouble("consumo");
	            int plazas = rs.getInt("plazas");
	            double kilometros = rs.getDouble("kilometros");
	            double precioCompra = rs.getDouble("precio_compra");
	            double precioVenta = rs.getDouble("precio_venta");
	            double precioAlquiler = rs.getDouble("precio_alquiler");
	            boolean alquilado = rs.getBoolean("alquilado");
	            String imagen = rs.getString("imagen");

	            // Imprimir en consola
	            System.out.println("Matricula: " + matricula);
	            System.out.println("Tipo: " + tipo);
	            System.out.println("Marca: " + marca);
	            System.out.println("Modelo: " + modelo);
	            System.out.println("Carroceria: " + carroceria);
	            System.out.println("Combustible: " + combustible);
	            System.out.println("Consumo: " + consumo + " L/100km");
	            System.out.println("Plazas: " + plazas);
	            System.out.println("Kilometros: " + kilometros + " km");
	            System.out.println("Precio Compra: $" + precioCompra);
	            System.out.println("Precio Venta: $" + precioVenta);
	            System.out.println("Precio Alquiler: $" + precioAlquiler + " por día");
	            System.out.println("Alquilado: " + (alquilado ? "Sí" : "No"));
	            System.out.println("Imagen: " + (imagen != null ? imagen : "No disponible"));
	            System.out.println("-------------------------------------------------");
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al consultar la tabla 'vehiculo': " + e.getMessage());
	        e.printStackTrace();
	    }
	}


}
