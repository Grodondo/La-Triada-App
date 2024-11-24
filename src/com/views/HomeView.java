package com.views;

import javax.swing.*;
import java.awt.*;
import com.config.AppConfig;

/**
 * La clase <code>HomeView</code> representa la vista principal de la aplicación de ventas de vehículos.
 * Esta interfaz muestra una ventana maximizada con un banner superior, panel de filtros, y elementos gráficos.
 */
public class HomeView extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase <code>HomeView</code>.
     * Establece el diseño de la ventana, paneles, imágenes y componentes gráficos.
     */
    public HomeView() {
        setTitle("Aplicación de Ventas de Vehículos");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(44, 73, 104)); // Color de fondo azul de la interfaz
        setContentPane(mainPanel); // Establecer mainPanel como el contenedor principal

        // Crear el CustomTitleBar (el menú)
        CustomTitleBar customTitleBar = new CustomTitleBar(this, "Aplicación de Ventas de Vehículos");
        setJMenuBar(customTitleBar); // Establecer el CustomTitleBar como el menú de la ventana

        // Crear panel del banner superior
        JPanel bannerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bannerIcon = new ImageIcon(AppConfig.RESOURCES_URL + "images/bannerLT.png");
                g.drawImage(bannerIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        bannerPanel.setOpaque(false);
        bannerPanel.setPreferredSize(new Dimension(getWidth(), 250)); // Establecer tamaño del banner
        mainPanel.add(bannerPanel, BorderLayout.NORTH); // Colocar el banner al norte

        // Crear el panel central con imagen de fondo (la imagen negra)
        JPanel centerPanel = new JPanel() {
            private ImageIcon backgroundImage = new ImageIcon(AppConfig.RESOURCES_URL + "images/negro.png");

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Mantener el tamaño original sin estirarlo
                int width = getWidth() - 600; // Mantener la imagen centrada y sin estirarse
                int height = (int) (getHeight() * 0.65); // Ajuste de altura
                int x = (getWidth() - width) / 2 + 30; // Desplazamos la imagen negra a la derecha
                int y = (getHeight() - height) / 2 + 50; // Ajuste hacia arriba
                g.drawImage(backgroundImage.getImage(), x, y, width, height, this);
            }
        };
        centerPanel.setOpaque(false);
        centerPanel.setLayout(null); // Usamos un layout nulo para colocar los elementos en posiciones específicas
        mainPanel.add(centerPanel, BorderLayout.CENTER); // Colocar en el centro

        // Crear panel para las flechas a la derecha de la imagen negra
        JPanel arrowPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Asegúrate de que la ruta de la imagen sea correcta
                try {
                    ImageIcon arrowIcon = new ImageIcon(AppConfig.RESOURCES_URL + "images/flechasSinFondo.png");
                    if (arrowIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                        g.drawImage(arrowIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                    } else {
                        System.out.println("Error al cargar la imagen de flechas.");
                    }
                } catch (Exception e) {
                    System.out.println("Error al cargar la imagen de flechas: " + e.getMessage());
                }
            }
        };

        // Hacer que el panel sea visible con un borde para comprobar su posición
        arrowPanel.setOpaque(false);
        arrowPanel.setPreferredSize(new Dimension(500, 400)); // Aumentar la altura de la imagen de flechas
        arrowPanel.setBounds(1380, 0, 300, 400); // Ubicar el panel de flechas a la derecha de la imagen negra
        centerPanel.add(arrowPanel);

        // Crear los JTextField para filtrado
        JTextField marcaField = new JTextField("Marca...");
        JTextField modeloField = new JTextField("Modelo...");
        JTextField carroceriaField = new JTextField("Carrocería...");
        JTextField resultadosField = new JTextField("Filtrar resultados...");
        
        // Hacer los JTextField más altos
        JTextField[] textFields = {marcaField, modeloField, carroceriaField, resultadosField};
        for (JTextField field : textFields) {
            field.setFont(new Font("Arial", Font.PLAIN, 18));
            field.setBackground(Color.WHITE);
            field.setForeground(new Color(44, 73, 104));
            field.setPreferredSize(new Dimension(250, 50)); // Aumenté la altura de los campos
            field.setBorder(BorderFactory.createLineBorder(new Color(44, 73, 104), 2)); // Bordes azules
        }

        // Colocar los JTextFields en las posiciones
        marcaField.setBounds(410, 190, 250, 50);
        modeloField.setBounds(670, 190, 250, 50);
        carroceriaField.setBounds(930, 190, 250, 50);
        resultadosField.setBounds(670, 270, 250, 50);

        // Agregar los JTextFields al panel central
        centerPanel.add(marcaField);
        centerPanel.add(modeloField);
        centerPanel.add(carroceriaField);
        centerPanel.add(resultadosField);

        // Crear el botón de la lupa y colocarlo en su posición original
        ImageIcon lupaIcon = new ImageIcon(AppConfig.RESOURCES_URL + "images/lupaLT.png");
        Image scaledLupaImage = lupaIcon.getImage().getScaledInstance(80, 40, Image.SCALE_SMOOTH);
        JButton searchButton = new JButton();
        searchButton.setIcon(new ImageIcon(scaledLupaImage));
        searchButton.setOpaque(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setMargin(new Insets(0, 0, 0, 0));

        // Establecer la posición del botón de búsqueda
        searchButton.setBounds(750, 350, 90, 40);
        centerPanel.add(searchButton);

        // Hacer visible la ventana
        setVisible(true);
    }

    /**
     * Método principal que ejecuta la aplicación y muestra la vista principal.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeView app = new HomeView();
            app.setVisible(true);
        });
    }
}
