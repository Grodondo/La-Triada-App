package com.models;

import com.database.createDatabase;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase padre que representa un Vehiculo y contiene métodos para interactuar
 * con la base de datos, como insertar y actualizar el estado de alquiler.
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

	/**
	 * Constructor de la clase Vehiculo.
	 */
	public Vehiculo(String matricula, String tipo, String marca, String modelo, String carroceria, String combustible,
			double consumo, Integer plazas, double kilometros, double precioCompra) {
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
		this.alquilado = false;
		
		 // Generar la ruta de la imagen usando la matrícula
        String directorioImagenes = "./La-Triada-App/src/com/database/imagenes/";
        this.imagen = Paths.get(directorioImagenes, matricula + ".jpg").toString();
	}

	/**
	 * Inserta el vehículo en la base de datos.
	 */
	public static void insertarVehiculo(String matricula, String tipo, String marca, String modelo, String carroceria,
			String combustible, double consumo, Integer plazas, double kilometros, double precioCompra,
			double precioAlquiler, boolean alquilado) {

		// Directorio base para las imágenes
		String directorioImagenes = "./La-Triada-App/src/com/database/imagenes/";

		// Generar la ruta de la imagen usando la matrícula
		String rutaImagen = Paths.get(directorioImagenes, matricula + ".jpg").toString();

		// Sentencia SQL para insertar un vehículo
		String sql = "INSERT INTO vehiculo (matricula, tipo, marca, modelo, carroceria, combustible, "
				+ "consumo, plazas, kilometros, precio_compra, precio_alquiler, alquilado, imagen) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:mi_base_de_datos.db");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// Establecer los parámetros
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
				pstmt.setNull(8, java.sql.Types.NULL);
			}
			pstmt.setDouble(9, kilometros);
			pstmt.setDouble(10, precioCompra);
			pstmt.setDouble(11, precioAlquiler);
			pstmt.setBoolean(12, alquilado);
			pstmt.setString(13, rutaImagen); // Ruta generada de la imagen

			// Ejecutar la sentencia SQL
			pstmt.executeUpdate();

			System.out.println("Vehículo insertado correctamente con la imagen en: " + rutaImagen);
		} catch (Exception e) {
			System.out.println("Error al insertar el vehículo: " + e.getMessage());
		}

	}

	/**
	 * Actualiza el estado de alquiler en la base de datos.
	 */
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
	
	
}
