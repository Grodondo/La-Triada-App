package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.models.Filtro;

/**
 * Clase para filtrar los resultados de la base de datos.
 * 
 * @author Carlos Arroyo Caballero
 * @version 1.01
 */
public class DatabaseFiltering {
	
	private final static String tablaVehiculos = "vehiculos";
	private final static String tablaVehiculosAlquilados = "vehiculos_alquilados";
	
	/**
	 * Obtener los resultados filtrados de la base de datos
	 *
	 * @param conn la conexión a la base de datos
	 * @param filters los filtros que se aplicarán a la consulta
	 * @return la query que devuelve los resultados filtr
	 * @throws SQLException si ocurre un error al ejecutar la consulta
	 */
	public static ResultSet getFilteredResults(Connection conn, List<Filtro> filters) throws SQLException {
		
		// Agrupa los filtros por tipo "column1": ["value1", "value2"].
        Map<String, List<String>> groupedFilters = filters.stream().collect(Collectors.groupingBy(
                    Filtro::getType, 
                    Collectors.mapping(Filtro::getName, Collectors.toList())
                ));
		
		// Crea un StringBuilder para construir la consulta
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM " + tablaVehiculos + " WHERE ");

        // Crear una lista de cláusulas WHERE para cada tipo de filtro
        List<String> whereClauses = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : groupedFilters.entrySet()) {
            String type = entry.getKey();
            List<String> names = entry.getValue();

            // Crear una cláusula OR para cada valor del filtro: matricula = ? OR matricula = ?
            String columnClause = names.stream()
                .map(value -> type + " = ?")
                .collect(Collectors.joining(" OR "));
            
            // Encierra las cláusulas OR entre paréntesis
            whereClauses.add("(" + columnClause + ")");
        }
        
        // Une las cláusulas WHERE con un AND
        queryBuilder.append(String.join(" AND ", whereClauses));

        // Convertir la consulta a un String
        String query = queryBuilder.toString();

        // Crear un PreparedStatement con la consulta
        PreparedStatement statement = conn.prepareStatement(query);

        // Asignar los valores de los filtros a los parámetros de la consulta
        int paramIndex = 1;
        for (Map.Entry<String, List<String>> entry : groupedFilters.entrySet()) {
            for (String value : entry.getValue()) {
                statement.setString(paramIndex++, value); 
            }
        }

        // Ejecutar la consulta
        return statement.executeQuery();
        
    }


}
