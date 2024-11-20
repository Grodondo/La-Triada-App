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
			filter.applyFilter(new Filtro("car", "Vehicle"));
			break;
		case "bike":
			buttonIconBike.setSelected(true);
			filter.applyFilter(new Filtro("bike", "Vehicle"));
			break;
		case "truck":
			buttonIconTruck.setSelected(true);
			filter.applyFilter(new Filtro("truck", "Vehicle"));
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
		appliedFiltersPanel.setBackground(this.backgroundColor);
		
		// Filters Controller
		filter = new Filter(appliedFiltersPanel);

		// Top JMenuBar - Custom Title Bar
		JMenuBar customTitleBar = new CustomTitleBar(this, "Vehicle Catalog");

		// Left Panel - Filters
		filtersPanel = new FiltersPanel(filter);
		filtersPanel.setPreferredSize(new Dimension(300, getHeight()));
		filtersPanel.setBackground(backgroundColor);

//		// Center Panel - Car List
//		carListPanel = createCarListPanel();
//		carListPanel.setBackground(backgroundColor);
		
		JPanel vehicleListPanel = new VehiclesPanel(appliedFiltersPanel, filter);

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

	/**
	 * Creates a JPanel with example car entries
	 * 
	 * @return The JPanel with the example car entries
	 * @see createCarEntry
	 */
	private JPanel createCarListPanel() {

		carListPanel = new JPanel();
		carListPanel.setLayout(new BoxLayout(carListPanel, BoxLayout.Y_AXIS));

		// Example car entries
		carListPanel.add(appliedFiltersPanel);
		carListPanel.add(createVehicleEntry("Toyota Corolla Sedan", "Berlina", "Híbrido", "car_image.png"));
		carListPanel.add(createVehicleEntry("Toyota C-HR", "SUV", "Híbrido", "car_image.png"));
		carListPanel.add(createVehicleEntry("Toyota Yaris", "Turismo", "Híbrido", "car_image.png"));

		return carListPanel;
	}

	/**
	 * Creates a JPanel with the information of a car entry
	 * 
	 * @param title     The title of the car
	 * @param type      The type of the car
	 * @param fuel      The fuel type of the car
	 * @param imagePath The path to the image of the car
	 * @return The JPanel with the car information
	 */
	   private JPanel createVehicleEntry(String title, String type, String fuel, String imagePath) {
	        JPanel carPanel = new JPanel();
	        carPanel.setLayout(new BorderLayout());
	        carPanel.setBackground(Color.WHITE);
	        carPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	        carPanel.setPreferredSize(new Dimension(600, 150));

	        JLabel titleLabel = new JLabel(title);
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

	        JLabel typeLabel = new JLabel(type);
	        typeLabel.setFont(new Font("Arial", Font.PLAIN, 16));

	        JLabel fuelLabel = new JLabel(fuel);
	        fuelLabel.setFont(new Font("Arial", Font.PLAIN, 16));

	        JPanel textPanel = new JPanel();
	        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
	        textPanel.setBackground(Color.WHITE);
	        textPanel.add(titleLabel);
	        textPanel.add(typeLabel);
	        textPanel.add(fuelLabel);

	        JLabel imageLabel = new JLabel();
	        imageLabel.setIcon(new ImageIcon(imagePath));
	        imageLabel.setPreferredSize(new Dimension(100, 100));

	        carPanel.add(imageLabel, BorderLayout.WEST);
	        carPanel.add(textPanel, BorderLayout.CENTER);

	        // Add a click listener to open another panel
	        carPanel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                // Logic to open another JPanel
	                openDetailsPanel(title, type, fuel, imagePath);
	            }

	            @Override
	            public void mouseEntered(MouseEvent e) {
	                carPanel.setBackground(new Color(230, 230, 250)); // Highlight effect
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                carPanel.setBackground(Color.WHITE); // Revert to original color
	            }
	        });

	        return carPanel;
	    }

	    private void openDetailsPanel(String title, String type, String fuel, String imagePath) {
	        JFrame detailFrame = new JFrame("Vehicle Details");
	        detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        detailFrame.setSize(400, 300);

	        JPanel detailsPanel = new JPanel();
	        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
	        detailsPanel.add(new JLabel("Title: " + title));
	        detailsPanel.add(new JLabel("Type: " + type));
	        detailsPanel.add(new JLabel("Fuel: " + fuel));
	        JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
	        detailsPanel.add(imageLabel);

	        detailFrame.add(detailsPanel);
	        detailFrame.setVisible(true);
	    }

	// -------------- COMPONENTS --------------
	public static JCheckBox buttonIconCar;
	public static JCheckBox buttonIconBike;
	public static JCheckBox buttonIconTruck;

}