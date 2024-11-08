package com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.config.AppConfig;
import com.config.SharedMethods;

public class SearchView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	private Set<String> activeFilters;
	private JPanel appliedFiltersPanel;
	private JPanel carListPanel;
	private JPanel filtersPanel;
	
	
	private final Color backgroundColor = new Color(42, 63, 90); // Dark blue background
	
	public SearchView() {
        setup();
    }
	
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
	
	private void setup() {
		activeFilters = new HashSet<>();
		
        setTitle("Vehicle Catalog");
        setSize(900, 600);
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
       
        getContentPane().setBackground(backgroundColor);

        // Top JMenuBar - Custom Title Bar
        JMenuBar customTitleBar = new CustomTitleBar(this);
        
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

    private JPanel createFiltersPanel() {    	
        JPanel filtersPanel = new JPanel();
        filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("FILTROS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        filtersPanel.add(title);
        filtersPanel.add(createFilterSectionImages());
        filtersPanel.add(createFilterSection("Marca", new String[]{"BMW", "Toyota", "Mercedes", "Renault"}));
        filtersPanel.add(createFilterSection("Modelo", new String[]{}));
        filtersPanel.add(createFilterSection("Carroceria", new String[]{}));
        filtersPanel.add(createFilterSection("Combustible", new String[]{}));
        
        return filtersPanel;
    }

    private JPanel createFilterSectionImages() {
    	JPanel sectionPanel = new JPanel(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
    	sectionPanel.setBackground(this.backgroundColor);
    	sectionPanel.setBorder(new LineBorder(Color.WHITE, 1));
    	
    	final ImageIcon imageIconCar = SharedMethods.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconCar.png"), 6);
    	final ImageIcon imageIconBike = SharedMethods.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconBike.png"), 6);
    	final ImageIcon imageIconTruck = SharedMethods.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconTruck.png"), 6);
		
    	// -------------- buttonIconCar --------------
    	{
	    	buttonIconCar = new JCheckBox(imageIconCar);
	    	buttonIconCar.setContentAreaFilled(false);
	    	buttonIconCar.setBorderPainted(false);
	    	
	    	buttonIconCar.setIcon(imageIconCar);          // Icono para no seleccionado
	    	buttonIconCar.setSelectedIcon(imageIconBike);    // Icono para seleccionado
	    	
			buttonIconCar.addActionListener(e -> {
				   if (buttonIconCar.isSelected()) {
		                applyFilter("car");  
		            } else {
		                removeFilter("car");
		            }
			});
    	}
		
    	
    	// -------------- buttonIconCar --------------
    	{
        	buttonIconBike = new JCheckBox(imageIconBike);
        	buttonIconBike.setContentAreaFilled(false);
        	buttonIconBike.setBorderPainted(false);
	    	
        	buttonIconBike.setIcon(imageIconCar);          // Icono para no seleccionado
        	buttonIconBike.setSelectedIcon(imageIconBike);    // Icono para seleccionado
	    	
        	buttonIconBike.addActionListener(e -> {
				   if (buttonIconBike.isSelected()) {
		                applyFilter("bike"); 
		            } else {
		                removeFilter("bike");
		            }
			});
    	}
		
		
		// -------------- buttonIconCar --------------
    	{
    		buttonIconTruck = new JCheckBox(imageIconTruck);
    		buttonIconTruck.setContentAreaFilled(false);
    		buttonIconTruck.setBorderPainted(false);
	    	
    		buttonIconTruck.setIcon(imageIconCar);          // Icono para no seleccionado
    		buttonIconTruck.setSelectedIcon(imageIconBike);    // Icono para seleccionado
	    	
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
            optionsPanel.add(checkBox);
        }

        sectionPanel.add(optionsPanel, BorderLayout.CENTER);
        return sectionPanel;
    }

    private void applyFilter(String filter) {
    	System.out.println("Applying filter: " + filter + " - " + activeFilters);
    	this.activeFilters.add(filter);
    	this.appliedFiltersPanel.add(createFilterTag(filter));
    	
        appliedFiltersPanel.revalidate();
        appliedFiltersPanel.repaint();
    }
    
	private void removeFilter(String filter) {
		activeFilters.remove(filter);

	    // Recorre los componentes del panel de filtros aplicados y elimina el que tenga el texto del filtro
	    for (Component comp : appliedFiltersPanel.getComponents()) {
	        if (comp instanceof JButton && ((JButton) comp).getText().equalsIgnoreCase(filter)) {
	            appliedFiltersPanel.remove(comp);
	            break;
	        }
	    }
	    
		appliedFiltersPanel.revalidate();
       	appliedFiltersPanel.repaint();
	}
    
    private JPanel createAppliedFiltersPanel() {
        appliedFiltersPanel = new JPanel();
        appliedFiltersPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel appliedTitle = new JLabel("FILTROS APLICADOS");
        appliedTitle.setForeground(Color.WHITE);
        appliedTitle.setFont(new Font("Arial", Font.BOLD, 16));
        appliedFiltersPanel.add(appliedTitle);
        
        return appliedFiltersPanel;
    }

    private JButton createFilterTag(String text) {
        JButton filterButton = new JButton(text + "");
        filterButton.setFont(new Font("Arial", Font.BOLD, 14));
        filterButton.setBackground(Color.WHITE);
        filterButton.setForeground(new Color(42, 63, 90));
        filterButton.setBorder(new LineBorder(Color.WHITE, 1, true));
        return filterButton;
    }

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