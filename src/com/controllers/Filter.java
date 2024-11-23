package com.controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.database.DatabaseFiltering;
import com.database.createDatabase;
import com.models.Filtro;
import com.models.Vehiculo;
import com.views.SearchView;
import com.views.VehiclesPanel;

/**
 * Clase que se encarga de gestionar los filtros aplicados en la vista de búsqueda
 * 
 * 
 * @author [Carlos Arroyo Caballero]
 * @version 1.0
 * @see SearchView
 */
public class Filter {

	private Set<Filtro> activeFilters;
	private JPanel appliedFiltersPanel;
	
	/**
	 * Constructor - Requiere el panel al que se añadirán las etiquetas
	 * 
	 * @param appliedFiltersPanel El panel donde se añadirán los {@link JButton} de los {@link Filtro} aplicados
	 */
	public Filter(JPanel appliedFiltersPanel) {
		activeFilters = new HashSet<>();
		this.appliedFiltersPanel = appliedFiltersPanel;
		
	}

	/**
	 * Añade los paneles de los vehículos filtrados al panel de vehículos
	 * ({@link VehiclesPanel})
	 */
	public void reloadPanelsVehiclesByFilter() {
		List<Vehiculo> vehicles = this.getVehiclesFiltered();
		if (vehicles == null) {
			System.out.println("Vehicles is null");
		}else {
			VehiclesPanel.reloadVehicles();
			for (Vehiculo vehicle : vehicles) {
				VehiclesPanel.vehiclesPanel.add(VehiclesPanel.createVehicleEntry(vehicle));
			}
		}
	}
	
	
	/**
	 * Obtiene los vehículos filtrados según los filtros aplicados
	 * 
	 * @return Una lista de {@link Vehiculo} con los vehículos filtrados
	 */
	public List<Vehiculo> getVehiclesFiltered() {
		ResultSet rs = null;
		List<Vehiculo> vehicles = new ArrayList<Vehiculo>();
		
		try {
			rs = DatabaseFiltering.getFilteredResults(createDatabase.connect(), activeFilters);
			
			while(rs.next()) {
				String matricula = rs.getString("matricula");
				String tipo = rs.getString("tipo");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String carroceria = rs.getString("carroceria");
				String combustible = rs.getString("combustible");
				double consumo = rs.getDouble("consumo");
				Integer plazas = rs.getInt("plazas");
				double kilometros = rs.getDouble("kilometros");
				double precioCompra = rs.getDouble("precio_compra");
				
				Vehiculo vehicle = new Vehiculo(matricula, tipo, marca, modelo, carroceria, combustible, consumo, plazas, kilometros, precioCompra);
				
				//vehicle.debugMostrarDatos();
				
				vehicles.add(vehicle);
			}
				
			System.out.println("Vehicles filtered: " + vehicles.size());
		} catch (SQLException e) {
			System.out.println("Error al obtener los resultados filtrados: " + e.getMessage());
		}
		
		return vehicles;
	}
	
	/**
	 * Comprueba si un filtro está aplicado
	 * 
	 * @param filter El nombre del {@link Filtro} a comprobar
	 * @return True si el filtro está aplicado, false en caso contrario
	 */
	public boolean containsFilter(String filter) {
		for (Filtro f : activeFilters) {
			if (f.getName().equalsIgnoreCase(filter))
				return true;
		}
		return false;
	}
	
	/**
	 * Añade un {@link Filtro} a la lista de filtros activos y al panel de filtros aplicados ({@link appliedFiltersPanel})
	 * No se añadirá si ya existe
	 * 
	 * @param filter El filtro a aplicar
	 */
	public void applyFilter(Filtro filter) {
		if (!containsFilter(filter.getName())) {
			System.out.println("Applying filter: " + filter.getName());
			this.activeFilters.add(filter);
			this.appliedFiltersPanel.add(createFilterTag(filter));
	
			appliedFiltersPanel.revalidate();
			appliedFiltersPanel.repaint();

			reloadPanelsVehiclesByFilter();
		}
		else System.out.println("Filter already exists: " + filter.getName());
	}

	/**
	 * Elimina un {@link Filtro} de la lista de filtros activos y su {@link JButton} correspondiente
	 * 
	 * @param filter El {@link Filtro} a eliminar
	 */
	public void removeFilter(Filtro  filter) {
		System.out.println("Removing filter: " + filter.getName() + ", hashCode: " + filter.hashCode());
	    boolean removed = activeFilters.remove(filter);
	    System.out.println("Filter removed: " + removed);

	    for (Component comp : appliedFiltersPanel.getComponents()) {
	        if (comp instanceof JButton && ((JButton) comp).getText().equalsIgnoreCase(filter.getName())) {
	            appliedFiltersPanel.remove(comp);
	            break;
	        }
	    }
	    appliedFiltersPanel.revalidate();
	    appliedFiltersPanel.repaint();
	    
	    reloadPanelsVehiclesByFilter();
	}

	/**
	 * Crea el {@link JButton} con el texto del filtro, este será añadido al panel de filtros aplicados ({@link appliedFiltersPanel})
	 *
	 * @param filter El filtro que se usará para crear el {@link JButton}
	 * @return El {@link JButton} creado
	 */
	private JButton createFilterTag(Filtro filter) {
		JButton filterButton = new JButton(filter.getName() + "");
		filterButton.setFont(new Font("Arial", Font.BOLD, 14));
		filterButton.setBackground(Color.WHITE);
		filterButton.setForeground(new Color(42, 63, 90));
		filterButton.setBorder(new LineBorder(Color.WHITE, 1, true));

		filterButton.addActionListener(e -> {
			removeFilter(filter);
			switch (filter.getName()) {
			case "car":
				SearchView.buttonIconCar.setSelected(false);
				break;
			case "bike":
				SearchView.buttonIconBike.setSelected(false);
				break;
			case "truck":
				SearchView.buttonIconTruck.setSelected(false);
				break;
			}
		});

		return filterButton;
	}
	
}
