package com.models;

import com.config.AppConfig;
import com.database.createDatabase;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 * Clase que representa un vehículo en el sistema de gestión de alquileres.
 * 
 * Proporciona métodos para insertar nuevos vehículos en la base de datos,
 * actualizar su estado de alquiler y gestionar información detallada como
 * matrícula, tipo, marca, modelo, carrocería, consumo, precios, y más.
 * 
 * La clase también calcula automáticamente valores como el precio de venta y
 * genera dinámicamente la ruta para almacenar una imagen del vehículo.
 * 
 * <p>
 * Funcionalidades principales:
 * </p>
 * <ul>
 * <li>Inserción de vehículos en la base de datos.</li>
 * <li>Actualización del estado de alquiler de un vehículo.</li>
 * <li>Cálculo automático de precios (venta y alquiler) basado en el precio de
 * compra.</li>
 * </ul>
 * 
 * <p>
 * Relación con la base de datos:
 * </p>
 * <ul>
 * <li>Almacena los vehículos en la tabla `vehiculo` de la base de datos
 * SQLite.</li>
 * <li>Asocia una imagen a cada vehículo mediante una ruta generada a partir de
 * la matrícula.</li>
 * </ul>
 * 
 * @see com.database.createDatabase
 * @author [Sara MJ]
 * @version 1.3
 */
public class Vehiculo {
	protected String matricula;
	protected String tipo;
	protected String marca;
	protected String modelo;
	protected String carroceria;
	protected String combustible;
	protected double consumo;
	protected Integer plazas;
	protected double kilometros;
	protected double precioCompra;
	protected double precioVenta;
	protected double precioAlquiler;
	protected boolean alquilado;
	protected String imagen; // Ruta de la imagen

	// Constructor de la clase Vehiculo.
	public Vehiculo(String matricula, String tipo, String marca, String modelo, String carroceria, String combustible,
			double consumo, Integer plazas, double kilometros, double precioCompra, boolean alquilado) {
		this.matricula = matricula;
		this.tipo = tipo;
		this.marca = marca;
		this.modelo = modelo;
		this.carroceria = carroceria;
		this.combustible = combustible;
		this.consumo = consumo;
		this.plazas = plazas;
		this.kilometros = kilometros;
		this.precioCompra = precioCompra;
		this.precioVenta = precioCompra + 5000.00;
		this.precioAlquiler = precioCompra / 1000; // Precio de alquiler calculado
		this.alquilado = alquilado;

		// Generar la ruta de la imagen usando la matrícula
		String directorioImagenes = AppConfig.PROJECT_PATH + "\\src\\com\\database\\imagenes\\";
		this.imagen = Paths.get(directorioImagenes, matricula + ".jpg").toString();
	}

	// Metodo para insertar los primeros datos
	public static void insertarVehiculoInicial(String matricula, String tipo, String marca, String modelo,
			String carroceria, String combustible, double consumo, Integer plazas, double kilometros,
			double precioCompra, double precioAlquiler, boolean alquilado) {
		// Directorio base para las imágenes
		String directorioImagenes = AppConfig.PROJECT_PATH + "\\src\\com\\database\\imagenes\\";

		// Generar la ruta de la imagen usando la matrícula
		String rutaImagen = Paths.get(directorioImagenes, matricula + ".jpg").toString();

		String sql = "INSERT INTO vehiculo(matricula, tipo, marca, modelo, carroceria, combustible, consumo, plazas, kilometros, precio_compra, precio_venta, precio_alquiler, alquilado, imagen) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = createDatabase.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, matricula);
			pstmt.setString(2, tipo);
			pstmt.setString(3, marca);
			pstmt.setString(4, modelo);
			pstmt.setString(5, carroceria);
			pstmt.setString(6, combustible);
			pstmt.setDouble(7, consumo);
			if (plazas != null) {
				pstmt.setInt(8, plazas);
			} else {
				pstmt.setNull(8, Types.INTEGER);
			}
			pstmt.setDouble(9, kilometros);
			pstmt.setDouble(10, precioCompra);
			pstmt.setDouble(11, precioCompra * 1.33); // Ejemplo: 33% margen sobre el precio de compra
			pstmt.setDouble(12, precioAlquiler);
			pstmt.setBoolean(13, alquilado);
			pstmt.setString(14, rutaImagen); // Ruta generada de la imagen

			System.out.printf(
					"Inserting: matricula=%s, tipo=%s, marca=%s, modelo=%s, carroceria=%s, combustible=%s, consumo=%.2f, plazas=%s, kilometros=%.2f, precioCompra=%.2f, precioVenta=%.2f, precioAlquiler=%.2f, alquilado=%b, imagen=%s%n",
					matricula, tipo, marca, modelo, carroceria, combustible, consumo, plazas, kilometros, precioCompra,
					precioCompra * 1.33, precioAlquiler, alquilado, rutaImagen);

			pstmt.executeUpdate();
			System.out.println("Vehículo insertado correctamente: " + matricula);
		} catch (SQLException e) {
			System.out.println("Error al insertar el vehículo: " + e.getMessage());
		}
	}

	public static int disponibilidadVehiculos(String tipo) {
		String sql = "SELECT COUNT(tipo) FROM vehiculo WHERE tipo = ?";
		int cantidad = -1; // Valor predeterminado en caso de error
		try (Connection conn = createDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			// Establecer el valor del parámetro tipo de manera segura
			stmt.setString(1, tipo);

			// Ejecutar la consulta y obtener el resultado
			ResultSet rs = stmt.executeQuery();

			// Si hay resultados, obtener el conteo
			if (rs.next()) {
				cantidad = rs.getInt(1); // El primer (y único) valor de la consulta es el conteo
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()); // Mostrar el error en caso de excepciones
		}
		return cantidad; // Retornar la cantidad obtenida

	}

	/**
	 * Inserta un nuevo vehículo en la base de datos con los datos proporcionados.
	 * Además, genera la ruta de la imagen asociada al vehículo utilizando la
	 * matrícula y la guarda en el campo correspondiente de la base de datos.
	 * 
	 * @param matricula      La matrícula del vehículo.
	 * @param tipo           El tipo de vehículo (por ejemplo, coche, moto, etc.).
	 * @param marca          La marca del vehículo.
	 * @param modelo         El modelo del vehículo.
	 * @param carroceria     El tipo de carrocería del vehículo (por ejemplo,
	 *                       berlina, SUV, etc.).
	 * @param combustible    El tipo de combustible utilizado por el vehículo (por
	 *                       ejemplo, gasolina, diésel, eléctrico, etc.).
	 * @param consumo        El consumo de combustible del vehículo (en litros por
	 *                       100 km).
	 * @param plazas         El número de plazas disponibles en el vehículo.
	 * @param kilometros     Los kilómetros recorridos por el vehículo.
	 * @param precioCompra   El precio de compra del vehículo.
	 * @param precioAlquiler El precio de alquiler del vehículo por unidad de
	 *                       tiempo.
	 * @param alquilado      Indica si el vehículo está alquilado (true si está
	 *                       alquilado, false si no lo está).
	 * 
	 * @throws SQLException Si ocurre algún error al interactuar con la base de
	 *                      datos.
	 * @throws Exception    Si ocurre algún error general durante la inserción.
	 */
	public static void insertarVehiculo(String matricula, String tipo, String marca, String modelo, String carroceria,
			String combustible, double consumo, Integer plazas, double kilometros, double precioCompra) {
		// Directorio base para las imágenes
		String directorioImagenes = "./La-Triada-App/src/com/database/imagenes/";

		// Generar la ruta de la imagen usando la matrícula
		String rutaImagen = Paths.get(directorioImagenes, matricula + ".jpg").toString();

		String sql = "INSERT INTO vehiculo(matricula, tipo, marca, modelo, carroceria, combustible, consumo, plazas, kilometros, precio_compra, precio_venta, precio_alquiler, alquilado, imagen) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = createDatabase.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, matricula);
			pstmt.setString(2, tipo);
			pstmt.setString(3, marca);
			pstmt.setString(4, modelo);
			pstmt.setString(5, carroceria);
			pstmt.setString(6, combustible);
			pstmt.setDouble(7, consumo);
			if (plazas != null) {
				pstmt.setInt(8, plazas);
			} else {
				pstmt.setNull(8, Types.INTEGER);
			}
			pstmt.setDouble(9, kilometros);
			pstmt.setDouble(10, precioCompra);
			pstmt.setDouble(11, precioCompra * 1.33); // Ejemplo: 33% margen sobre el precio de compra
			pstmt.setDouble(12, (precioCompra * 1.33) / 1000.00);
			pstmt.setBoolean(13, false);
			pstmt.setString(14, rutaImagen); // Ruta generada de la imagen

			pstmt.executeUpdate();
			System.out.println("Vehículo insertado correctamente: " + matricula);
		} catch (SQLException e) {
			System.out.println("Error al insertar el vehículo: " + e.getMessage());
		}
	}

	// Actualiza el estado de alquiler en la base de datos.

	public void actualizarEstadoAlquiler(boolean alquilado) {
		String sql = "UPDATE vehiculo SET alquilado = ? WHERE matricula = ?";
		try (Connection conn = createDatabase.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setBoolean(1, alquilado);
			pstmt.setString(2, matricula);
			pstmt.executeUpdate();
			System.out.println("Estado de alquiler actualizado.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getMatricula() {
		return matricula;
	}

	public String getTipo() {
		return tipo;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getCarroceria() {
		return carroceria;
	}

	public String getCombustible() {
		return combustible;
	}

	public double getConsumo() {
		return consumo;
	}

	public Integer getPlazas() {
		return plazas;
	}

	public double getKilometros() {
		return kilometros;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public double getPrecioAlquiler() {
		return precioAlquiler;
	}

	public boolean isAlquilado() {
		return alquilado;
	}

	public String getRutaImagen() {
		return imagen;
	}

	/**
	 * Muestra los datos del vehículo en la consola.
	 */
	public void debugMostrarDatos() {
		System.out.println("Matrícula: " + matricula);
		System.out.println("Tipo: " + tipo);
		System.out.println("Marca: " + marca);
		System.out.println("Modelo: " + modelo);
		System.out.println("Carrocería: " + carroceria);
		System.out.println("Combustible: " + combustible);
		System.out.println("Consumo: " + consumo);
		System.out.println("Plazas: " + plazas);
		System.out.println("Kilómetros: " + kilometros);
		System.out.println("Precio de compra: " + precioCompra);
		System.out.println("Precio de venta: " + precioVenta);
		System.out.println("Precio de alquiler: " + precioAlquiler);
		System.out.println("Alquilado: " + alquilado);
		System.out.println("Ruta de la imagen: " + imagen);
		System.out.println("-------------------------------------------------");
	}

}
