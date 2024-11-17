package com.models;

import com.database.createDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase padre que representa un Vehiculo y contiene métodos para
 * interactuar con la base de datos, como insertar y actualizar el estado de alquiler.
 */
public class Vehiculo {
    protected String matricula;
    protected String tipo;
    protected String marca;
    protected String modelo;
    protected String carroceria;
    protected String combustible;
    protected double consumo;
    protected double plazas;
    protected double kilometros;
    protected double precioCompra;
    protected double precioAlquiler;
    protected boolean alquilado;

    /**
     * Constructor de la clase Vehiculo.
     */
    public Vehiculo(String matricula, String tipo, String marca, String modelo, String carroceria, String combustible, 
                    double consumo, double plazas, double kilometros, double precioCompra, boolean alquilado) {
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
        this.precioAlquiler = precioCompra / 1000; // Precio de alquiler calculado
        this.alquilado = alquilado;
    }

    /**
     * Inserta el vehículo en la base de datos.
     */
    public void insertarEnDB() {
        String sql = "INSERT INTO vehiculo (matricula, tipo, marca, modelo, carroceria, combustible, consumo, plazas, kilometros, precio_compra, precio_alquiler, alquilado) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = createDatabase.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, matricula);
            pstmt.setString(2, tipo);
            pstmt.setString(3, marca);
            pstmt.setString(4, modelo);
            pstmt.setString(5, carroceria);
            pstmt.setString(6, combustible);
            pstmt.setDouble(7, consumo);
            pstmt.setDouble(8, plazas);
            pstmt.setDouble(9, kilometros);
            pstmt.setDouble(10, precioCompra);
            pstmt.setDouble(11, precioAlquiler);
            pstmt.setBoolean(12, alquilado);
            pstmt.executeUpdate();
            System.out.println("Vehículo insertado en la base de datos.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
}

