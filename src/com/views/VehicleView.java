package com.views;

import javax.swing.*;
import com.config.AppConfig;
import com.database.DatabaseManager;
import com.models.Vehiculo;

import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.io.File;

/**
 * La clase <code>VehicleView</code> es una aplicación gráfica en Java que muestra información de un vehículo
 * y permite visualizar los detalles relacionados con su compra y alquiler.
 * La información se recupera desde una base de datos SQLite y se presenta en una interfaz gráfica utilizando Swing.
 * @author [Ismael Martin Boudiab] 
 * @version 1.0
 * 
 * Revisado por [Carlos Arroyo Caballero]
 */
public class VehicleView extends JFrame {

	
    /**
     * Método principal que inicia la aplicación.
     * Crea la ventana principal, configura los paneles y muestra los detalles del vehículo
     * obtenidos desde la base de datos.
     * 
     */
	public VehicleView(Vehiculo vehicle) {
		System.out.println("Mostrando vehiculo: " + vehicle.getMatricula());
		
        setTitle("La Triada - Compra/Alquiler");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\icon.png").getImage());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Crear un panel principal para contener todo
        JPanel mainPanel = new JPanel(null); // Usamos diseño absoluto para posicionar elementos manualmente
        //mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(29, 57, 96)); // Fondo azul oscuro
        add(mainPanel, BorderLayout.CENTER);

        // Crear el cuadro blanco como un panel con una imagen de fondo
        JPanel backgroundPanel = new JPanel(null) { 
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen del cuadro blanco
                ImageIcon backgroundImage = new ImageIcon(AppConfig.RESOURCES_URL + "images/blanco.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        //backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBounds(150, 55, 1250, 700); // Ajusta posición y tamaño del cuadro blanco
        backgroundPanel.setOpaque(false); 
        mainPanel.add(backgroundPanel);

        // Crear el panel derecho para la imagen de las flechas
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen de las flechas
                ImageIcon rightImage = new ImageIcon(AppConfig.RESOURCES_URL + "images/flechasSinFondo.png");
                g.drawImage(rightImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        rightPanel.setOpaque(false); 
        rightPanel.setBounds(1420, 80, 250, 600); 
        mainPanel.add(rightPanel);

        
        // Modificado por [Carlos Arroyo] -> Ahora se pasa el objeto Vehiculo como parametro
        String marcaModelo = vehicle.getMarca() + " - " + vehicle.getModelo();
        String precioCompra = vehicle.getPrecioCompra() + "";
        String precioAlquiler = vehicle.getPrecioAlquiler() + "";
        String imagenRuta = vehicle.getRutaImagen();
        String carroceria = vehicle.getCarroceria();
        String kilometros = vehicle.getKilometros() + "";
        String combustible = vehicle.getCombustible();
        String matricula = vehicle.getMatricula();
        String consumo = vehicle.getConsumo() + "";
        
        
        // Mostrar el nombre de la marca y modelo en el título
        JLabel titleLabel = new JLabel(marcaModelo);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(250, 150, 1300, 100);
        backgroundPanel.add(titleLabel);

        // Crear botones de "Comprar" y "Alquilar" y etiquetas de precio
        JButton buyButton = new JButton("Comprar");
        buyButton.setBackground(new Color(29, 57, 96));
        buyButton.setForeground(Color.WHITE);
        buyButton.setFont(new Font("Arial", Font.BOLD, 18));
        buyButton.setBounds(900, 270, 200, 50);
		buyButton.addActionListener(e -> {
			// Acción al hacer clic en el botón de comprar
			System.out.println("Comprar vehículo: " + marcaModelo);
			
		});
        
        backgroundPanel.add(buyButton);

        JLabel buyPriceLabel = new JLabel(precioCompra);
        buyPriceLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        buyPriceLabel.setForeground(Color.GRAY);
        buyPriceLabel.setBounds(750, 270, 200, 50);
        backgroundPanel.add(buyPriceLabel);

        JButton rentButton = new JButton("Alquilar");
        rentButton.setBackground(new Color(29, 57, 96));
        rentButton.setForeground(Color.WHITE);
        rentButton.setFont(new Font("Arial", Font.BOLD, 18));
        rentButton.setBounds(900, 340, 200, 50);
		rentButton.addActionListener(e -> {
			// Acción al hacer clic en el botón de alquilar
			System.out.println("Alquilar vehículo: " + marcaModelo);
		    LocalDate fechaActual = LocalDate.now();
		    String fechaFin = fechaActual.plusDays(7).toString();
			DatabaseManager.introducirVehiculoAlquilado(vehicle, fechaFin);
			
		});
        
        backgroundPanel.add(rentButton);

        JLabel rentPriceLabel = new JLabel(precioAlquiler);
        rentPriceLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        rentPriceLabel.setForeground(Color.GRAY);
        rentPriceLabel.setBounds(800, 340, 200, 50);
        backgroundPanel.add(rentPriceLabel);

        // Mostrar las características del vehículo
        JLabel carroceriaLabel = new JLabel(carroceria);
        carroceriaLabel.setFont(new Font("Arial", Font.BOLD, 30));
        carroceriaLabel.setForeground(Color.GRAY);
        carroceriaLabel.setBounds(200, 500, 400, 30);
        backgroundPanel.add(carroceriaLabel);

        JLabel kilometrosLabel = new JLabel(kilometros);
        kilometrosLabel.setFont(new Font("Arial", Font.BOLD, 30));
        kilometrosLabel.setForeground(Color.GRAY);
        kilometrosLabel.setBounds(200, 540, 400, 30);
        backgroundPanel.add(kilometrosLabel);

        JLabel combustibleLabel = new JLabel(combustible);
        combustibleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        combustibleLabel.setForeground(Color.GRAY);
        combustibleLabel.setBounds(200, 580, 400, 30);
        backgroundPanel.add(combustibleLabel);

        JLabel matriculaLabel = new JLabel(matricula);
        matriculaLabel.setFont(new Font("Arial", Font.BOLD, 30));
        matriculaLabel.setForeground(Color.GRAY);
        matriculaLabel.setBounds(450, 500, 400, 30);
        backgroundPanel.add(matriculaLabel);

        JLabel consumoLabel = new JLabel(consumo);
        consumoLabel.setFont(new Font("Arial", Font.BOLD, 30));
        consumoLabel.setForeground(Color.GRAY);
        consumoLabel.setBounds(450, 540, 400, 30);
        backgroundPanel.add(consumoLabel);

        // Mostrar la imagen del vehículo
        JLabel vehicleImageLabel = new JLabel();
        String nombreImagen = new File(imagenRuta).getName();
        String baseRutaImagenes = "src/com/database/imagenes/";
        String rutaImagenFinal = baseRutaImagenes + nombreImagen;
        ImageIcon vehicleImage = new ImageIcon(rutaImagenFinal);
        vehicleImageLabel.setIcon(new ImageIcon(vehicleImage.getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH)));
        vehicleImageLabel.setBounds(100, 130, 600, 375);
        backgroundPanel.add(vehicleImageLabel);

        // Forzar la actualización del panel
        backgroundPanel.revalidate();
        backgroundPanel.repaint();

	}
    
    
	// Revision de Carlos -> Todo esto sobra, a la clase ya se le va a pasar como parametro un Objeto de la clase Vehiculo, no lo borro por pena.
    /**
     * Recupera los datos del vehículo desde una base de datos SQLite.
     * 
     * @return Un array de <code>String</code> que contiene los siguientes datos:
     *         [marca y modelo, precio de compra, precio de alquiler, carrocería,
     *         kilometraje, combustible, matrícula, consumo, ruta de la imagen].
     */
    private static String[] getDatosDesdeBaseDeDatos() {
        // Valores por defecto
        String marcaModelo = "Cargando...";
        String precioCompra = "Precio no disponible";
        String precioAlquiler = "Precio no disponible";
        String carroceria = "Carrocería no disponible";
        String kilometros = "Kilometraje no disponible";
        String combustible = "Gasolina no disponible";
        String matricula = "Matrícula no disponible";
        String consumo = "Consumo no disponible";
        String imagenRuta = "uppss"; // Ruta por defecto

        // Conexión a la base de datos SQLite
        String url = "jdbc:sqlite:database.db";
        String query = "SELECT marca, modelo, precio_compra, precio_alquiler, carroceria, kilometros, combustible, matricula, consumo, imagen FROM vehiculo LIMIT 1";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                // Recuperar datos de la base de datos
                marcaModelo = rs.getString("marca") + " " + rs.getString("modelo");
                precioCompra = "$" + rs.getString("precio_compra");
                precioAlquiler = "$" + rs.getString("precio_alquiler");
                carroceria = rs.getString("carroceria");
                kilometros = rs.getString("kilometros") + " Km";
                combustible = rs.getString("combustible");
                matricula = rs.getString("matricula");
                consumo = rs.getString("consumo") + " L/100km";
                imagenRuta = rs.getString("imagen");
            } else {
                System.out.println("No se encontraron resultados en la consulta.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar o consultar la base de datos: " + e.getMessage());
        }

        return new String[]{marcaModelo, precioCompra, precioAlquiler, carroceria, kilometros, combustible, matricula, consumo, imagenRuta};
    }
}
