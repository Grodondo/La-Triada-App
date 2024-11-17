package com.models;

import com.database.createDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// A revisar, creo que no es muy eficaz al tener checkbox pero tengo que investigarlo mejor

/**
 * Clase VehiculoDAO para manejar la búsqueda de vehículos con filtros en la base de datos.
 */
public class VehiculoDAO {

    /**
     * Lista los vehículos de la base de datos con posibilidad de aplicar filtros en todos los atributos.
     *
     * @param matricula  matrícula del vehículo (opcional)
     * @param tipo       tipo de vehículo: coche, moto, camion (opcional)
     * @param marca      marca del vehículo (opcional)
     * @param modelo     modelo del vehículo (opcional)
     * @param carroceria carrocería del vehículo (opcional)
     * @param combustible tipo de combustible: diesel, gasolina, hibrido, electrico (opcional)
     * @param alquilado  estado de alquiler del vehículo (opcional)
     * @return lista de vehículos que cumplen con los filtros aplicados
     */
    public List<Vehiculo> listarVehiculosConFiltro(String matricula, String tipo, String marca, String modelo, String carroceria,
                                                   String combustible, Boolean alquilado) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM vehiculo WHERE 1=1");

        // Construcción de filtros
        if (matricula != null) sql.append(" AND matricula = ?");
        if (tipo != null) sql.append(" AND tipo = ?");
        if (marca != null) sql.append(" AND marca = ?");
        if (modelo != null) sql.append(" AND modelo = ?");
        if (carroceria != null) sql.append(" AND carroceria = ?");
        if (combustible != null) sql.append(" AND combustible = ?");
        if (alquilado != null) sql.append(" AND alquilado = ?");

        try (Connection conn = createDatabase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            // Asignación de parámetros en función de los filtros aplicados
            int index = 1;
            if (matricula != null) pstmt.setString(index++, matricula);
            if (tipo != null) pstmt.setString(index++, tipo);
            if (marca != null) pstmt.setString(index++, marca);
            if (modelo != null) pstmt.setString(index++, modelo);
            if (carroceria != null) pstmt.setString(index++, carroceria);
            if (combustible != null) pstmt.setString(index++, combustible);
            if (alquilado != null) pstmt.setBoolean(index, alquilado);

            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();

            // Recoger los resultados y agregar a la lista
            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                    rs.getString("matricula"),
                    rs.getString("tipo"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("carroceria"),
                    rs.getString("combustible"),
                    rs.getDouble("consumo"),
                    rs.getDouble("plazas"),
                    rs.getDouble("kilometros"),
                    rs.getDouble("precio_compra"),
                    rs.getBoolean("alquilado")
                );
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar vehículos con filtros: " + e.getMessage());
        }
        return vehiculos;
    }
}

