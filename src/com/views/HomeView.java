package com.views;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.config.AppConfig;
import com.models.Vehiculo;

/**
 * La clase <code>HomeView</code> representa la vista principal de la aplicación
 * de ventas de vehículos. Esta interfaz muestra una ventana maximizada con un
 * banner superior, panel de filtros, y elementos gráficos.
 *
 * 
 * @author Pablo Orozco Carrasco
 * @version 2.0
 * @see CustomTitleBar
 */
public class HomeView extends JFrame {
	static JLabel motoCount = new JLabel("3", SwingConstants.CENTER);
	static JLabel carCount = new JLabel("5", SwingConstants.CENTER);
	static JLabel truckCount = new JLabel("4", SwingConstants.CENTER);

	// Configuración de la base de datos
	public HomeView() {
		// Crear el marco principal

		setMinimumSize(AppConfig.MINIMUM_WINDOW_SIZE);
		setSize(AppConfig.sizeWindow);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\icon.png").getImage());
		setLocationRelativeTo(null);

		setLayout(new BorderLayout());

		// Panel principal con el contenido
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(30, 60, 90));

		// Imagen de fondo superior
		JLabel backgroundImage = new JLabel(new ImageIcon(AppConfig.RESOURCES_URL + "images/" + "bannerLT.png"));
		backgroundImage.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(backgroundImage, BorderLayout.NORTH);

		// Panel central con los textos y disponibilidad
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(50, 70, 110));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		JLabel labelBienvenido = new JLabel("BIENVENIDO");
		labelBienvenido.setForeground(Color.WHITE);
		labelBienvenido.setFont(new Font("Arial", Font.BOLD, 50));
		labelBienvenido.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel labelDisponibilidad = new JLabel("DISPONIBILIDAD:");
		labelDisponibilidad.setForeground(Color.WHITE);
		labelDisponibilidad.setFont(new Font("Arial", Font.BOLD, 50));
		labelDisponibilidad.setAlignmentX(Component.CENTER_ALIGNMENT);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 0)));
		centerPanel.add(labelBienvenido);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 0)));
		centerPanel.add(labelDisponibilidad);

		// Panel de los íconos y números
		JPanel iconPanel = new JPanel(new BorderLayout());
		iconPanel.setBackground(new Color(50, 70, 110));
		iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.X_AXIS));

		// iconPanel.setPreferredSize(new Dimension(10, 3));
		// Ajusta el tamaño según
		// necesites
		// Moto
		JPanel motoPanel = new JPanel(new BorderLayout());
		motoPanel.setBackground(new Color(50, 70, 110));
		JLabel motoIcon = new JLabel(scaleIcon(AppConfig.RESOURCES_URL + "images/" + "MotoNegro.png", 250, 150));

		motoCount.setForeground(Color.WHITE);
		motoCount.setFont(new Font("Arial", Font.BOLD, 50));
		motoPanel.add(motoIcon, BorderLayout.CENTER);
		motoPanel.add(motoCount, BorderLayout.SOUTH);
		motoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		// Coche
		JPanel carPanel = new JPanel(new BorderLayout());
		carPanel.setBackground(new Color(50, 70, 110));
		JLabel carIcon = new JLabel(scaleIcon(AppConfig.RESOURCES_URL + "images/" + "CocheNegro.png", 250, 150));

		carCount.setForeground(Color.WHITE);
		carCount.setFont(new Font("Arial", Font.BOLD, 50));
		carPanel.add(carIcon, BorderLayout.CENTER);
		carPanel.add(carCount, BorderLayout.SOUTH);
		carPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		// Camión
		JPanel truckPanel = new JPanel(new BorderLayout());
		truckPanel.setBackground(new Color(50, 70, 110));
		JLabel truckIcon = new JLabel(scaleIcon(AppConfig.RESOURCES_URL + "images/" + "CamionNegro.png", 250, 150));
		truckCount.setForeground(Color.WHITE);
		truckCount.setFont(new Font("Arial", Font.BOLD, 50));
		truckPanel.add(truckIcon, BorderLayout.CENTER);
		truckPanel.add(truckCount, BorderLayout.SOUTH);
		truckPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		iconPanel.add(motoPanel);
		iconPanel.add(carPanel);
		iconPanel.add(truckPanel);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 0))); // Espaciado
		centerPanel.add(iconPanel);

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		JMenuBar CustomTitleBar = new CustomTitleBar(this, "Home");
		add(CustomTitleBar, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);

		// Mostrar la ventana
		setVisible(true);

	}

	public static void main(String[] args) {
		// Crear el marco principal
		HomeView h1 = new HomeView();
		h1.setVisible(true);

// Consultar la base de datos para obtener las cantidades

		motoCount.setText("" + Vehiculo.disponibilidadVehiculos("moto"));
		carCount.setText("" + Vehiculo.disponibilidadVehiculos("coche"));
		truckCount.setText("" + Vehiculo.disponibilidadVehiculos("camion"));
	}

	private static ImageIcon scaleIcon(String path, int width, int height) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage(); // Obtener la imagen original
		Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Escalar la imagen
		return new ImageIcon(scaledImg); // Devolver el icono escalado
	}
}
