package com.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainView extends JFrame{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5636273723239309743L;

	public MainView() {
        // Configuración básica de la ventana
        setTitle("La Triada");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // Imagen de cabecera (Logo)
        JLabel logoLabel = new JLabel(new ImageIcon("resources/images/logo.jpg"));
        panelPrincipal.add(logoLabel, BorderLayout.NORTH);

        // Panel central con botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 3, 10, 10)); // 4 filas, 3 columnas, 10px de separación

        // Botones de selección de categorías
        JButton cochesButton = new JButton("COCHES");
        JButton motosButton = new JButton("MOTOS");
        JButton camionesButton = new JButton("CAMIONES");
        panelBotones.add(cochesButton);
        panelBotones.add(motosButton);
        panelBotones.add(camionesButton);

        // Buscador
        JPanel panelBuscador = new JPanel(new BorderLayout());
        JTextField buscadorField = new JTextField(20);
        JButton buscarButton = new JButton(new ImageIcon("ruta/a/tu/icono/buscar.png")); // Añadir icono
        panelBuscador.add(buscadorField, BorderLayout.CENTER);
        panelBuscador.add(buscarButton, BorderLayout.EAST);
        panelBotones.add(panelBuscador);

        // Botones de acciones
        JButton ventaButton = new JButton("VENTA");
        JButton compraButton = new JButton("COMPRA");
        JButton alquilerButton = new JButton("ALQUILER");
        JButton devolverButton = new JButton("DEVOLVER");
        panelBotones.add(ventaButton);
        panelBotones.add(compraButton);
        panelBotones.add(alquilerButton);
        panelBotones.add(devolverButton);

        // Agregar el panel de botones al centro
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        // Agregar el panel principal a la ventana
        add(panelPrincipal);
    }
}
