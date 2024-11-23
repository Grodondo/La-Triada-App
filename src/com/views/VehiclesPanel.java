package com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.config.AppConfig;
import com.config.SharedMethods;
import com.controllers.Filter;
import com.models.Vehiculo;


/**
 * Panel que contiene la lista de vehículos
 * 
 * @author Carlos Arroyo Caballero
 * @version 1.0
 * @see SearchView
 */
public class VehiclesPanel extends JPanel{

	//private Filter filter;
	public static JPanel vehiclesPanel;
	
	/**
	 * Constructor - Requires the Filter to be created and stup beforehand
	 * 
	 * @param appliedFiltersPanel The panel with the applied filters
	 * @param filter              The filter to apply
	 */
	public VehiclesPanel(JPanel appliedFiltersPanel) {
		//this.filter = filter;

		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		vehiclesPanel = new JPanel();
		vehiclesPanel.setLayout(new BoxLayout(vehiclesPanel, BoxLayout.Y_AXIS));
		
//		List<Vehiculo> vehicles = filter.getVehiclesFiltered();
//		if (vehicles == null) {
//			System.out.println("Vehicles is null");
//		}else {
//			for (Vehiculo vehicle : vehicles) {
//				vehiclesPanel.add(createVehicleEntry(vehicle));
//			}
//		}
		
		//____________________________________________________________________________________________________
		
	    // Scroll pane para los filtros
	    JScrollPane scrollPane = new JScrollPane(vehiclesPanel);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	    // Estilo del scroll bar
	    scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
	        @Override
	        protected void configureScrollBarColors() {
	            this.thumbColor = new Color(100, 100, 100); 
	            this.trackColor = new Color(200, 200, 200); 
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
	
	
		this.add(appliedFiltersPanel, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
	    
	}
	
	
	public static void reloadVehicles() {
		vehiclesPanel.removeAll();

		vehiclesPanel.revalidate();
		vehiclesPanel.repaint();
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
	public static JPanel createVehicleEntry(Vehiculo vehicle) {
	        JPanel vehiclePanel = new JPanel();
	        vehiclePanel.setLayout(new BorderLayout());
	        vehiclePanel.setBackground(Color.WHITE);
	        vehiclePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	        vehiclePanel.setPreferredSize(new Dimension(600, 150));

	        JLabel titleLabel = new JLabel(vehicle.getMarca() + " " + vehicle.getModelo());
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

	        JLabel typeLabel = new JLabel(vehicle.getTipo());
	        typeLabel.setFont(new Font("Arial", Font.PLAIN, 16));

	        JLabel fuelLabel = new JLabel(vehicle.getCombustible());
	        fuelLabel.setFont(new Font("Arial", Font.PLAIN, 16));

	        JPanel textPanel = new RoundedPanel(20);
	        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
	        textPanel.setBackground(Color.WHITE);
	        textPanel.add(titleLabel);
	        textPanel.add(typeLabel);
	        textPanel.add(fuelLabel);
	        
	        
	        // Imagen del vehículo
	        JLabel imageLabel = new JLabel();
	        
	        ImageIcon vehicleIcon = null;
	        try {
	        	vehicleIcon = SharedMethods.resizeImage(new ImageIcon(vehicle.getRutaImagen()), 4);
	        	//vehicleIcon = SharedMethods.resizeImage(new ImageIcon(AppConfig.PROJECT_PATH + "\\src\\com\\database\\imagenes\\0987KPT.jpg"), 2);
	        }
			catch (Exception e) {
				vehicleIcon = SharedMethods.resizeImage(new ImageIcon( AppConfig.RESOURCES_URL + "images\\iconos\\CocheNegro.png"), 5);
			}
	        System.out.println("Ruta: " + vehicle.getRutaImagen());
	        System.out.println(AppConfig.PROJECT_PATH + "\\src\\com\\database\\imagenes\\0987KPT.jpg");
	        
	       
            imageLabel.setIcon(vehicleIcon);
	        imageLabel.setPreferredSize(new Dimension(200, 100));
	        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	        
	        
	        // Añade los elementos al panel
	        vehiclePanel.add(imageLabel, BorderLayout.WEST);
	        vehiclePanel.add(textPanel, BorderLayout.CENTER);

	        // click listener para abrir el panel de detalles
	        vehiclePanel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                //openDetailsPanel(vehicle);
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
	    private void openDetailsPanel(Vehiculo vehicle) {
	        JFrame detailFrame = new JFrame("Vehicle Details");
	        detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        detailFrame.setSize(400, 300);

	        JPanel detailsPanel = new JPanel();
	        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
	        detailsPanel.add(new JLabel("Title: " + vehicle.getMarca() + " " + vehicle.getModelo()));
	        detailsPanel.add(new JLabel("Type: " + vehicle.getTipo()));
	        detailsPanel.add(new JLabel("Fuel: " + vehicle.getCombustible()));
	        JLabel imageLabel = new JLabel(new ImageIcon(vehicle.getRutaImagen()));
	        detailsPanel.add(imageLabel);

	        detailFrame.add(detailsPanel);
	        detailFrame.setVisible(true);
	    }
	
}
