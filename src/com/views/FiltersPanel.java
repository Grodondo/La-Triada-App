package com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.config.AppConfig;
import com.config.SharedMethods;
import com.controllers.Filter;
import com.models.Filtro;


/**
 * Vista de búsqueda para el catálogo de vehículos
 * 
 * Es importante que todos los elementos de la vista se hayan añadido con el tipo correspondiente al de la BD
 * 
 * @author Carlos Arroyo Caballero
 * @see CustomTitleBar
 * @version 1.0
 */
public class FiltersPanel extends JPanel{
	
	private Filter filter;
	
	private Color backgroundColor = new Color(42, 63, 90);;

	/**
	 * Constructor, creates the filter panel -  Requires the Filter to be created and stup beforehand
	 * 
	 * @param filter The filter to apply
	 * 
	 * @see createFilterSectionImages
	 * @see createFilterSection
	 * @see createTextFilterPanel
	 */
	public FiltersPanel(Filter filter) {
		// Filter controller
		this.filter = filter;
		
		 // Crea el panel principal que contendrá todos los demás paneles
	    this.setLayout(new BorderLayout());
	    this.setBackground(this.backgroundColor); 

	    // Panel para el título (non-scrollable)
	    JPanel titlePanel = new JPanel();
	    titlePanel.setBackground(this.backgroundColor);
	    titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
	    JLabel title = new JLabel("FILTROS");
	    title.setForeground(Color.WHITE);
	    title.setFont(new Font("Arial", Font.BOLD, 20));
	    title.setAlignmentX(Component.CENTER_ALIGNMENT);
	    titlePanel.add(title);

	    // Panel para los filtros (scrollable)
	    JPanel filtersPanel = new JPanel();
	    filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS));
	    filtersPanel.setBackground(this.backgroundColor); 

	    // Añadir los diferentes paneles de filtros
	    filtersPanel.add(createFilterSectionImages());
	    filtersPanel.add(createFilterSection("Marca", new String[] { "BMW", "Toyota", "Mercedes", "Renault" }));
	    filtersPanel.add(createFilterSection("Combustible", new String[] { "diesel", "gasolina", "hibrido", "electrico" }));
	    filtersPanel.add(createFilterSection("Marca", new String[] { "BMW", "Toyota", "Mercedes", "Renault" }));
	    filtersPanel.add(createTextFilterPanel("Plazas"));
	    filtersPanel.add(createFilterSection("Modelo", new String[] {}));
	    filtersPanel.add(createFilterSection("Carroceria", new String[] {}));
	    filtersPanel.add(createFilterSection("Combustible", new String[] {}));

	    // Scroll pane para los filtros
	    JScrollPane scrollPane = new JScrollPane(filtersPanel);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	    // Estilo del scroll bar
	    scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
	        @Override
	        protected void configureScrollBarColors() {
	            this.thumbColor = new Color(100, 100, 255); // Scrollbar thumb color
	            this.trackColor = new Color(200, 200, 200); // Scrollbar track color
	        }

	        @Override
	        protected JButton createDecreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        @Override
	        protected JButton createIncreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        private JButton createZeroButton() {
	            JButton button = new JButton();
	            button.setPreferredSize(new Dimension(0, 0));
	            return button;
	        }
	    });

	    scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(12, Integer.MAX_VALUE));
	    scrollPane.setBackground(Color.GRAY);
	    scrollPane.getViewport().setBackground(Color.DARK_GRAY);

	    // Añaadir los paneles al panel principal
	    this.add(titlePanel, BorderLayout.NORTH); // Titulo en la parte superior
	    this.add(scrollPane, BorderLayout.CENTER); // Contenido (scrollable)

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

		final JLabel textFilter = new JLabel(title.toUpperCase());
		;
		final JTextField textField = new JTextField();
		final JButton searchButton = new JButton(imageIconCar);
		;

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
			textField.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 1), new EmptyBorder(5, 10, 5, 5)));

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
				filter.applyFilter(new Filtro(textField.getText(), title.toLowerCase()));
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
	 * Creates a JPanel with the filter options for car, bike and truck as image
	 * checkBoxes
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
			SearchView.buttonIconCar = new JCheckBox(imageIconCar);
			SearchView.buttonIconCar.setContentAreaFilled(false);
			SearchView.buttonIconCar.setBorderPainted(false);

			SearchView.buttonIconCar.setIcon(imageIconCar);
			SearchView.buttonIconCar.setSelectedIcon(selectedImageIconCar);

			SearchView.buttonIconCar.addActionListener(e -> {
				if (SearchView.buttonIconCar.isSelected()) {
					filter.applyFilter(new Filtro("coche", "tipo"));
				} else {
					filter.removeFilter(new Filtro("coche", "tipo"));
				}
			});
		}

		// -------------- buttonIconBike --------------
		{
			SearchView.buttonIconBike = new JCheckBox(imageIconBike);
			SearchView.buttonIconBike.setContentAreaFilled(false);
			SearchView.buttonIconBike.setBorderPainted(false);

			SearchView.buttonIconBike.setIcon(imageIconBike);
			SearchView.buttonIconBike.setSelectedIcon(selectedImageIconBike);

			SearchView.buttonIconBike.addActionListener(e -> {
				if (SearchView.buttonIconBike.isSelected()) {
					filter.applyFilter(new Filtro("moto", "tipo"));
				} else {
					filter.removeFilter(new Filtro("moto", "tipo"));
				}
			});
		}

		// -------------- buttonIconTruck --------------
		{
			SearchView.buttonIconTruck = new JCheckBox(imageIconTruck);
			SearchView.buttonIconTruck.setContentAreaFilled(false);
			SearchView.buttonIconTruck.setBorderPainted(false);

			SearchView.buttonIconTruck.setIcon(imageIconTruck);
			SearchView.buttonIconTruck.setSelectedIcon(selectedImageIconTruck);

			SearchView.buttonIconTruck.addActionListener(e -> {
				if (SearchView.buttonIconTruck.isSelected()) {
					filter.applyFilter(new Filtro("camion", "tipo"));
				} else {
					filter.removeFilter(new Filtro("camion", "tipo"));
				}
			});
		}

		gbc.gridx = 0;
		gbc.gridy = 0;
		sectionPanel.add(SearchView.buttonIconCar, gbc);
		gbc.gridx = 1;
		sectionPanel.add(SearchView.buttonIconBike, gbc);
		gbc.gridx = 2;
		sectionPanel.add(SearchView.buttonIconTruck, gbc);

		return sectionPanel;
	}
	
	/**
	 * Creates a JPanel with a title and a list of options as checkboxes to apply
	 * filters
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
					filter.applyFilter(new Filtro(checkBox.getText(), title.toLowerCase()));
				} else {
					filter.removeFilter(new Filtro(checkBox.getText(), title.toLowerCase()));
				}
			});

			optionsPanel.add(checkBox);
		}

		sectionPanel.add(optionsPanel, BorderLayout.CENTER);
		return sectionPanel;
	}
	
}
