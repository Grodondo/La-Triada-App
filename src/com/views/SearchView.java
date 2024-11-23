package com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.config.AppConfig;
import com.controllers.Filter;
import com.models.Filtro;

/**
 * Search view for the vehicle catalog
 * 
 * @author Carlos Arroyo Caballero
 * @version 1.0
 * @see CustomTitleBar
 */
public class SearchView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel appliedFiltersPanel;
	private JPanel carListPanel;
	private JPanel filtersPanel;
	
	private Filter filter;

	private final Color backgroundColor = new Color(42, 63, 90); // Dark blue background

	/**
	 * Default constructor
	 */
	public SearchView() {
		setup();
	}

	/**
	 * Constructor with a type of vehicle to filter
	 * 
	 * @param type The type of vehicle to filter
	 */
	public SearchView(String type) {
		setup();
		switch (type) {
		case "car":
			buttonIconCar.setSelected(true);
			filter.applyFilter(new Filtro("coche", "tipo"));
			break;
		case "bike":
			buttonIconBike.setSelected(true);
			filter.applyFilter(new Filtro("moto", "tipo"));
			break;
		case "truck":
			buttonIconTruck.setSelected(true);
			filter.applyFilter(new Filtro("camion", "tipo"));
			break;
		}

	}

	/**
	 * Sets up the JFrame and its components
	 */
	private void setup() {

		setTitle("Vehicle Catalog");
		setMinimumSize(AppConfig.MINIMUM_WINDOW_SIZE);
		setSize(AppConfig.sizeWindow);
		setIconImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\icon.png").getImage());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		getContentPane().setBackground(backgroundColor);
		
		// Applied Filters Panel - Important to setup before Filters
		appliedFiltersPanel = createAppliedFiltersPanel();
		appliedFiltersPanel.setPreferredSize(new Dimension(getWidth(), 50));
		appliedFiltersPanel.setSize(new Dimension(getWidth(), 50));
		appliedFiltersPanel.setBackground(this.backgroundColor);
		
		// Filters Controller
		filter = new Filter(appliedFiltersPanel);

		// Top JMenuBar - Custom Title Bar
		JMenuBar customTitleBar = new CustomTitleBar(this, "Vehicle Catalog");

		// Left Panel - Filters
		filtersPanel = new FiltersPanel(filter);
		filtersPanel.setPreferredSize(new Dimension(300, getHeight()));
		filtersPanel.setBackground(backgroundColor);
		
		JPanel vehicleListPanel = new VehiclesPanel(appliedFiltersPanel);

		// Add panels to JFrame
		add(customTitleBar, BorderLayout.NORTH);
		add(filtersPanel, BorderLayout.WEST);
		add(vehicleListPanel, BorderLayout.CENTER);
		
	}


	/**
	 * Creates a JPanel with the title "Filtros Aplicados" and the tags for all the
	 * active filters
	 *
	 * 
	 * @return The JPanel with the title and tags for the active filters
	 * @see createFilterTag
	 */
	private JPanel createAppliedFiltersPanel() {
		
		JPanel appliedFiltersPanel = new JPanel();
		appliedFiltersPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel appliedTitle = new JLabel("FILTROS APLICADOS");
		appliedTitle.setForeground(Color.WHITE);
		appliedTitle.setFont(new Font("Arial", Font.BOLD, 16));
		appliedFiltersPanel.add(appliedTitle);

		return appliedFiltersPanel;
	}


	// -------------- COMPONENTS --------------
	public static JCheckBox buttonIconCar;
	public static JCheckBox buttonIconBike;
	public static JCheckBox buttonIconTruck;

}