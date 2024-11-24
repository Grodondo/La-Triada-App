package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.models.Filtro;

/**
 * Clase para filtrar los resultados de la base de datos.
 * 
 * @author [Carlos Arroyo Caballero]
 * @version 1.01
 */
public class DatabaseFiltering {
	
	private final static String tablaVehiculos = "vehiculo";
	//private final static String tablaVehiculosAlquilados = "vehiculos_alquilados";
	
	/**
	 * Obtener los resultados filtrados de la base de datos
	 *
	 * @param conn la {@link Connection} a la base de datos
	 * @param filters Una lista con los {@link Filtro} que se aplicarán a la consulta
	 * 
	 * @return Un {@link ResultSet} con los resultados filtrados
	 * 
	 * @throws SQLException si ocurre un error al ejecutar la consulta
	 */
	public static ResultSet getFilteredResults(Connection conn,  Set<Filtro> filters) throws SQLException {
		
		System.out.println("Starting getFilteredResults");
		
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
        //System.out.println("Query: " + query);

        // Crear un PreparedStatement con la consulta
        PreparedStatement statement = conn.prepareStatement(query);

        // Asignar los valores de los filtros a los parámetros de la consulta
        int paramIndex = 1;
        for (Map.Entry<String, List<String>> entry : groupedFilters.entrySet()) {
            for (String value : entry.getValue()) {
            	//System.out.println("ParamIndex: " + paramIndex + " Value: " + value);
                statement.setString(paramIndex++, value); 
            }
        }
        
        System.out.println("Query: " + statement.toString());
        // Ejecutar la consulta
        return statement.executeQuery();
        
    }


}
