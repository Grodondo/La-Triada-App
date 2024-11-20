package com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.controllers.Filter;


/**
 * Panel to display a list of vehicles
 * 
 * @author Carlos Arroyo Caballero
 * @version 1.0
 * @see SearchView
 */
public class VehiclesPanel extends JPanel{

	private Filter filter;
	
	
	/**
	 * Constructor - Requires the Filter to be created and stup beforehand
	 * 
	 * @param appliedFiltersPanel The panel with the applied filters
	 * @param filter              The filter to apply
	 */
	public VehiclesPanel(JPanel appliedFiltersPanel, Filter filter) {
		this.filter = filter;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel vehiclesPanel = new JPanel();
		vehiclesPanel.setLayout(new BoxLayout(vehiclesPanel, BoxLayout.Y_AXIS));
		
		//____________________________________________________________________________________________________
		vehiclesPanel.add(createVehicleEntry("Toyota Corolla Sedan", "Berlina", "Híbrido", "car_image.png"));
		vehiclesPanel.add(createVehicleEntry("Toyota C-HR", "SUV", "Híbrido", "car_image.png"));
		vehiclesPanel.add(createVehicleEntry("Toyota Yaris", "Turismo", "Híbrido", "car_image.png"));
		//____________________________________________________________________________________________________
		
	    // Scroll pane para los filtros
	    JScrollPane scrollPane = new JScrollPane(vehiclesPanel);
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
	
	
		this.add(appliedFiltersPanel);
		this.add(scrollPane);
	    
	}
	
	private void addVehiclesByFilter(Filter filter) {
		
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
	        JPanel vehiclePanel = new JPanel();
	        vehiclePanel.setLayout(new BorderLayout());
	        vehiclePanel.setBackground(Color.WHITE);
	        vehiclePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	        vehiclePanel.setPreferredSize(new Dimension(600, 150));

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

	        vehiclePanel.add(imageLabel, BorderLayout.WEST);
	        vehiclePanel.add(textPanel, BorderLayout.CENTER);

	        // Add a click listener to open another panel
	        vehiclePanel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                // Logic to open another JPanel
	                openDetailsPanel(title, type, fuel, imagePath);
	            }

	            @Override
	            public void mouseEntered(MouseEvent e) {
	                vehiclePanel.setBackground(new Color(230, 230, 250)); // Highlight effect
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                vehiclePanel.setBackground(Color.WHITE); // Revert to original color
	            }
	        });

	        return vehiclePanel;
	    }

	   // Serves as an example, will be deleted once the proper one is made
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
	
}
