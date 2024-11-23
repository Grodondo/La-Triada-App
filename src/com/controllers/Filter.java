package com.controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.models.Filtro;
import com.views.SearchView;

/**
 * Class to manage filters for the search view
 * 
 * 
 * @author Carlos Arroyo Caballero
 * @version 1.0
 * @see SearchView
 */
public class Filter {

	private Set<Filtro> activeFilters;
	private JPanel appliedFiltersPanel;
	
	/**
	 * Constructor - Requires the panel to add the filter tags
	 * 
	 * @param appliedFiltersPanel The panel to add the filter tags to
	 */
	public Filter(JPanel appliedFiltersPanel) {
		activeFilters = new HashSet<>();
		this.appliedFiltersPanel = appliedFiltersPanel;
	}
	
	/**
	 * Checks if a filter is already applied
	 * 
	 * @param filter The filter to check
	 * @return True if the filter is applied, false otherwise
	 */
	public boolean containsFilter(String filter) {
		for (Filtro f : activeFilters) {
			if (f.getName().equalsIgnoreCase(filter))
				return true;
		}
		return false;
	}
	
	/**
	 * Applies a filter to the active filters set and adds a tag to the applied
	 * filters panel
	 * 
	 * 
	 * @param filter The filter to apply
	 */
	public void applyFilter(Filtro filter) {
		if (!containsFilter(filter.getName())) {
			System.out.println("Applying filter: " + filter.getName());
			this.activeFilters.add(filter);
			this.appliedFiltersPanel.add(createFilterTag(filter));
	
			appliedFiltersPanel.revalidate();
			appliedFiltersPanel.repaint();
		}
		else System.out.println("Filter already exists: " + filter.getName());
	}

	/**
	 * Removes a filter from the active filters set and the applied filters panel
	 * 
	 * @param filter The filter to remove
	 */
	public void removeFilter(Filtro  filter) {
		System.out.println("Removing filter: " + filter.getName());
		activeFilters.remove(filter);

		for (Component comp : appliedFiltersPanel.getComponents()) {
			if (comp instanceof JButton && ((JButton) comp).getText().equalsIgnoreCase(filter.getName())) {
				appliedFiltersPanel.remove(comp);
				break;
			}
		}

		appliedFiltersPanel.revalidate();
		appliedFiltersPanel.repaint();
	}

	/**
	 * Creates a JButton with the text of the filter and the functionality to remove
	 * the filter when clicked
	 * 
	 * @param text The text of the filter
	 * @return The JButton with the text of the filter
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
