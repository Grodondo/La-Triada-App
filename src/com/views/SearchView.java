package com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.config.AppConfig;
import com.config.SharedMethods;

/**
 * Search view for the vehicle catalog
 * 
 * @author Carlos Arroyo Caballero
 * @version 1.0
 * @see CustomTitleBar
 */
public class SearchView extends JFrame {

	private static final long serialVersionUID = 1L;

	private Set<String> activeFilters;
	private JPanel appliedFiltersPanel;
	private JPanel carListPanel;
	private JPanel filtersPanel;

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
			applyFilter("car");
			break;
		case "bike":
			buttonIconBike.setSelected(true);
			applyFilter("bike");
			break;
		case "truck":
			buttonIconTruck.setSelected(true);
			applyFilter("truck");
			break;
		}

	}

	/**
	 * Sets up the JFrame and its components
	 */
	private void setup() {
		activeFilters = new HashSet<>();

		setTitle("Vehicle Catalog");
		setMinimumSize(AppConfig.MINIMUM_WINDOW_SIZE);
		setSize(AppConfig.sizeWindow);
		setIconImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\icon.png").getImage());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		getContentPane().setBackground(backgroundColor);

		// Top JMenuBar - Custom Title Bar
		JMenuBar customTitleBar = new CustomTitleBar(this, "Vehicle Catalog");

		// Left Panel - Filters
		filtersPanel = createFiltersPanel();
		filtersPanel.setPreferredSize(new Dimension(300, getHeight()));
		filtersPanel.setBackground(backgroundColor);

		// Center Panel - Car List
		carListPanel = createCarListPanel();
		carListPanel.setBackground(backgroundColor);

		// Add panels to JFrame
		add(customTitleBar, BorderLayout.NORTH);
		add(filtersPanel, BorderLayout.WEST);
		add(carListPanel, BorderLayout.CENTER);
	}

	
	/**
	 * Applies a filter to the active filters set and adds a tag to the applied filters panel
	 * 
	 * 
	 * @param filter The filter to apply
	 */
	private void applyFilter(String filter) {
		System.out.println("Applying filter: " + filter + " - " + activeFilters);
		this.activeFilters.add(filter);
		this.appliedFiltersPanel.add(createFilterTag(filter));

		appliedFiltersPanel.revalidate();
		appliedFiltersPanel.repaint();
	}

	/**
	 * Removes a filter from the active filters set and the applied filters panel
	 * 
	 * @param filter The filter to remove
	 */
	private void removeFilter(String filter) {
		activeFilters.remove(filter);
		
		for (Component comp : appliedFiltersPanel.getComponents()) {
			if (comp instanceof JButton && ((JButton) comp).getText().equalsIgnoreCase(filter)) {
				appliedFiltersPanel.remove(comp);
				break;
			}
		}

		appliedFiltersPanel.revalidate();
		appliedFiltersPanel.repaint();
	}

	/**
	 * Creates a JPanel with a text filter input and a button to add the filter
	 * 
	 * @param title The title of the text filter
	 * @return The JPanel with the text filter input and button
	 */
	private JPanel createTextFilterPanel(String title) {
	    JPanel textFilterPanel = new JPanel();
	    textFilterPanel.setBackground(this.backgroundColor);
	    textFilterPanel.setBorder(new LineBorder(Color.WHITE, 1));
	    textFilterPanel.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();

	    // Components
        final ImageIcon imageIconCar = SharedMethods
                .resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconos\\CocheBlanco.png"), 8);
        
	    
	    final JLabel textFilter = new JLabel(title.toUpperCase());;
	    final JTextField textField = new JTextField();
	    final JButton searchButton = new JButton(imageIconCar);;
	    
	    // Title Label
	    {
	        textFilter.setForeground(Color.WHITE);
	        textFilter.setFont(new Font("Arial", Font.BOLD, 14));

	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.gridwidth = 2; // Span across both columns
	        gbc.anchor = GridBagConstraints.WEST;
	        gbc.insets = new Insets(0, 10, 10, 0); // Padding
	        textFilterPanel.add(textFilter, gbc);
	    }

	    // Text Field
	    {
	        textField.setBackground(this.backgroundColor);
	        textField.setForeground(Color.WHITE);
	        textField.setFont(new Font("Arial", Font.PLAIN, 14));
	        textField.setBorder(new CompoundBorder(
	            new LineBorder(Color.WHITE, 1),
	            new EmptyBorder(5, 10, 5, 5) // Inner padding for text field
	        ));

	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        gbc.gridwidth = 1; // Return to single column
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        gbc.weightx = 1.0;
	        gbc.insets = new Insets(0, 10, 0, 0); // Left padding of 10 pixels
	        textFilterPanel.add(textField, gbc);
	    }

	    // Search Button
	    {
	        searchButton.setContentAreaFilled(false);
	        searchButton.setBorderPainted(false);
	        searchButton.setFocusPainted(false);
	        searchButton.addActionListener(e -> {
	            applyFilter(textField.getText());
	            textField.setText("");
	        });

	        gbc.gridx = 1;
	        gbc.gridy = 1;
	        gbc.fill = GridBagConstraints.NONE; // Avoid resizing button
	        gbc.weightx = 0.0;
	        gbc.insets = new Insets(0, 0, 0, 0); // No additional padding for button
	        textFilterPanel.add(searchButton, gbc);
	    }

	    return textFilterPanel;
	}

	/**
	 * Creates the JPanel that will contain all other JPanels with the filter options
	 * 
	 * @return The JPanel with the filter options
	 * @see createFilterSectionImages
	 * @see createFilterSection
	 * @see createTextFilterPanel
	 */
	private JPanel createFiltersPanel() {
		JPanel filtersPanel = new JPanel();
		filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS));

		// Title label
		JLabel title = new JLabel("FILTROS");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Filter sections
		filtersPanel.add(title);
		filtersPanel.add(createFilterSectionImages());
		filtersPanel.add(createFilterSection("Marca", new String[] { "BMW", "Toyota", "Mercedes", "Renault" }));
		filtersPanel.add(createTextFilterPanel("Modelo"));
		filtersPanel.add(createFilterSection("Modelo", new String[] {}));
		filtersPanel.add(createFilterSection("Carroceria", new String[] {}));
		filtersPanel.add(createFilterSection("Combustible", new String[] {}));

		return filtersPanel;
	}

	/**
	 * Creates a JPanel with the filter options for car, bike and truck as image checkBoxes
	 * 
	 * @return The JPanel with the filter options for car, bike and truck
	 */
	private JPanel createFilterSectionImages() {
		JPanel sectionPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		sectionPanel.setBackground(this.backgroundColor);
		sectionPanel.setBorder(new LineBorder(Color.WHITE, 1));

		// Icon images
		final ImageIcon imageIconCar = SharedMethods
				.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconos\\CocheBlanco.png"), 8);
		final ImageIcon imageIconBike = SharedMethods
				.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconos\\MotoBlanco.png"), 8);
		final ImageIcon imageIconTruck = SharedMethods
				.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconos\\CamionBlanco.png"), 8);

		// Selected icon images
		final ImageIcon selectedImageIconCar = SharedMethods
				.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconos\\CocheNegro.png"), 8);
		final ImageIcon selectedImageIconBike = SharedMethods
				.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconos\\MotoNegro.png"), 8);
		final ImageIcon selectedImageIconTruck = SharedMethods
				.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconos\\CamionNegro.png"), 8);

		// -------------- buttonIconCar --------------
		{
			buttonIconCar = new JCheckBox(imageIconCar);
			buttonIconCar.setContentAreaFilled(false);
			buttonIconCar.setBorderPainted(false);

			buttonIconCar.setIcon(imageIconCar);
			buttonIconCar.setSelectedIcon(selectedImageIconCar);

			buttonIconCar.addActionListener(e -> {
				if (buttonIconCar.isSelected()) {
					applyFilter("car");
				} else {
					removeFilter("car");
				}
			});
		}

		// -------------- buttonIconBike --------------
		{
			buttonIconBike = new JCheckBox(imageIconBike);
			buttonIconBike.setContentAreaFilled(false);
			buttonIconBike.setBorderPainted(false);

			buttonIconBike.setIcon(imageIconBike);
			buttonIconBike.setSelectedIcon(selectedImageIconBike);

			buttonIconBike.addActionListener(e -> {
				if (buttonIconBike.isSelected()) {
					applyFilter("bike");
				} else {
					removeFilter("bike");
				}
			});
		}

		// -------------- buttonIconTruck --------------
		{
			buttonIconTruck = new JCheckBox(imageIconTruck);
			buttonIconTruck.setContentAreaFilled(false);
			buttonIconTruck.setBorderPainted(false);

			buttonIconTruck.setIcon(imageIconTruck);
			buttonIconTruck.setSelectedIcon(selectedImageIconTruck);

			buttonIconTruck.addActionListener(e -> {
				if (buttonIconTruck.isSelected()) {
					applyFilter("truck");
				} else {
					removeFilter("truck");
				}
			});
		}

		gbc.gridx = 0;
		gbc.gridy = 0;
		sectionPanel.add(buttonIconCar, gbc);
		gbc.gridx = 1;
		sectionPanel.add(buttonIconBike, gbc);
		gbc.gridx = 2;
		sectionPanel.add(buttonIconTruck, gbc);

		return sectionPanel;
	}

	/**
	 * Creates a JPanel with a title and a list of options as checkboxes to apply filters
	 * 
	 * @param title   The title of the section
	 * @param options The list of options to display as checkboxes
	 * @return The JPanel with the title and checkboxes
	 */
	private JPanel createFilterSection(String title, String[] options) {
		JPanel sectionPanel = new JPanel();
		sectionPanel.setLayout(new BorderLayout());
		sectionPanel.setBackground(this.backgroundColor);
		sectionPanel.setBorder(new LineBorder(Color.WHITE, 1));

		JLabel sectionTitle = new JLabel(title);
		sectionTitle.setForeground(Color.WHITE);
		sectionTitle.setFont(new Font("Arial", Font.BOLD, 14));
		sectionPanel.add(sectionTitle, BorderLayout.NORTH);

		JPanel optionsPanel = new JPanel();
		optionsPanel.setBackground(this.backgroundColor);
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

		for (String option : options) {
			JCheckBox checkBox = new JCheckBox(option);
			checkBox.setForeground(Color.WHITE);
			checkBox.setBackground(new Color(42, 63, 90));
			checkBox.setFont(new Font("Arial", Font.PLAIN, 14));

			checkBox.addActionListener(e -> {
				if (checkBox.isSelected()) {
					applyFilter(checkBox.getText());
				} else {
					removeFilter(checkBox.getText());
				}
			});

			optionsPanel.add(checkBox);
		}

		sectionPanel.add(optionsPanel, BorderLayout.CENTER);
		return sectionPanel;
	}

	/**
	 * Creates a JPanel with the title "Filtros Aplicados" and the tags for all the active filters
	 *
	 * 
	 * @return The JPanel with the title and tags for the active filters
	 * @see createFilterTag
	 */
	private JPanel createAppliedFiltersPanel() {
		appliedFiltersPanel = new JPanel();
		appliedFiltersPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel appliedTitle = new JLabel("FILTROS APLICADOS");
		appliedTitle.setForeground(Color.WHITE);
		appliedTitle.setFont(new Font("Arial", Font.BOLD, 16));
		appliedFiltersPanel.add(appliedTitle);

		return appliedFiltersPanel;
	}

	/**
	 * Creates a JButton with the text of the filter and the functionality to remove
	 * the filter when clicked
	 * 
	 * @param text The text of the filter
	 * @return The JButton with the text of the filter
	 */
	private JButton createFilterTag(String text) {
		JButton filterButton = new JButton(text + "");
		filterButton.setFont(new Font("Arial", Font.BOLD, 14));
		filterButton.setBackground(Color.WHITE);
		filterButton.setForeground(new Color(42, 63, 90));
		filterButton.setBorder(new LineBorder(Color.WHITE, 1, true));

		filterButton.addActionListener(e -> {
			removeFilter(text);
			switch (text) {
			case "car":
				buttonIconCar.setSelected(false);
				break;
			case "bike":
				buttonIconBike.setSelected(false);
				break;
			case "truck":
				buttonIconTruck.setSelected(false);
				break;
			}
		});

		return filterButton;
	}

	/**
	 * Creates a JPanel with example car entries
	 * 
	 * @return The JPanel with the example car entries
	 * @see createCarEntry
	 */
	private JPanel createCarListPanel() {

		// Top Panel - Applied Filters
		JPanel appliedFiltersPanel = createAppliedFiltersPanel();
		appliedFiltersPanel.setPreferredSize(new Dimension(getWidth(), 50));
		appliedFiltersPanel.setBackground(this.backgroundColor);

		carListPanel = new JPanel();
		carListPanel.setLayout(new BoxLayout(carListPanel, BoxLayout.Y_AXIS));

		// Example car entries
		carListPanel.add(appliedFiltersPanel);
		carListPanel.add(createCarEntry("Toyota Corolla Sedan", "Berlina", "Híbrido", "car_image.png"));
		carListPanel.add(createCarEntry("Toyota C-HR", "SUV", "Híbrido", "car_image.png"));
		carListPanel.add(createCarEntry("Toyota Yaris", "Turismo", "Híbrido", "car_image.png"));

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
	private JPanel createCarEntry(String title, String type, String fuel, String imagePath) {
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

		// Placeholder for car image
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(new ImageIcon(imagePath)); // Load your image path here
		imageLabel.setPreferredSize(new Dimension(100, 100));

		carPanel.add(imageLabel, BorderLayout.WEST);
		carPanel.add(textPanel, BorderLayout.CENTER);

		return carPanel;
	}

	// -------------- COMPONENTS --------------
	private JCheckBox buttonIconCar;
	private JCheckBox buttonIconBike;
	private JCheckBox buttonIconTruck;

}