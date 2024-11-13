package com.views;

import javax.swing.*;
import com.config.AppConfig;
import java.awt.*;

public class inicio extends JFrame {

    private static final long serialVersionUID = 1L;

    public inicio() {
        setTitle("Aplicación de Ventas de Vehículos");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(44, 73, 104)); // Color de fondo azul de la interfaz
        setContentPane(mainPanel); // Establecer mainPanel como el contenedor principal

        // Crear el panel del banner superior
        JPanel bannerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bannerIcon = new ImageIcon(AppConfig.RESOURCES_URL + "images/bannerLT.png");
                g.drawImage(bannerIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        bannerPanel.setPreferredSize(new Dimension(getWidth(), 250));
        bannerPanel.setOpaque(false);

        // Añadir el banner en la parte superior
        mainPanel.add(bannerPanel, BorderLayout.NORTH);

        // Crear panel central con imagen de fondo (la imagen negra)
        JPanel centerPanel = new JPanel() {
            private ImageIcon backgroundImage = new ImageIcon(AppConfig.RESOURCES_URL + "images/negro.png");

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth() - 400;
                int height = (int) (getHeight() * 0.65);
                int x = (getWidth() - width) / 2 + 150; // Desplazamos la imagen negra a la derecha
                int y = (getHeight() - height) / 2 + 30; // Ajuste hacia arriba
                g.drawImage(backgroundImage.getImage(), x, y, width, height, this);
            }
        };
        centerPanel.setLayout(null); // Usamos un layout absoluto
        centerPanel.setOpaque(false);

        // Crear los botones como los tenías antes
        JButton marcaButton = new JButton("MARCA");
        JButton modeloButton = new JButton("MODELO");
        JButton carroceriaButton = new JButton("CARROCERIA");
        JButton resultadosButton = new JButton("...");

        ImageIcon lupaIcon = new ImageIcon(AppConfig.RESOURCES_URL + "images/lupaLT.png");
        Image scaledLupaImage = lupaIcon.getImage().getScaledInstance(80, 40, Image.SCALE_SMOOTH);
        JButton searchButton = new JButton();
        searchButton.setIcon(new ImageIcon(scaledLupaImage));
        searchButton.setOpaque(false); // Asegura que el botón no tenga un fondo
        searchButton.setContentAreaFilled(false); // Evita que el botón se rellene
        searchButton.setBorderPainted(false); // No dibuja el borde
        searchButton.setFocusPainted(false); // No dibuja el borde de enfoque
        searchButton.setMargin(new Insets(0, 0, 0, 0)); // Elimina cualquier margen que pueda afectar la imagen

        // Asegurarse de que los botones sean visibles desde el inicio
        JButton[] buttons = {marcaButton, modeloButton, carroceriaButton, resultadosButton};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setBackground(Color.WHITE);
            button.setForeground(new Color(44, 73, 104));
            button.setPreferredSize(new Dimension(250, 70));
        }

        // Ahora, añade los botones al panel con posiciones personalizadas
        marcaButton.setBounds(410, 210, 250, 70); // x, y, ancho, alto
        modeloButton.setBounds(670, 210, 250, 70); // x, y, ancho, alto
        carroceriaButton.setBounds(930, 210, 250, 70); // x, y, ancho, alto
        resultadosButton.setBounds(670, 300, 250, 70); // x, y, ancho, alto
        searchButton.setBounds(760, 380, 80, 40); // x, y, ancho, alto

        // Añadir los botones al panel
        centerPanel.add(marcaButton);
        centerPanel.add(modeloButton);
        centerPanel.add(carroceriaButton);
        centerPanel.add(resultadosButton);
        centerPanel.add(searchButton);

        // Añadir el panel central al panel principal
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Crear el panel de la flecha y moverla hacia la derecha
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon rightImage = new ImageIcon(AppConfig.RESOURCES_URL + "images/flechasSinFondo.png");

                // Desplazar la imagen hacia la derecha para crear el efecto de que atraviesa la pantalla
                int xOffset = 120; // Desplazar la flecha 100 píxeles hacia la derecha fuera de la pantalla
                g.drawImage(rightImage.getImage(), xOffset, 0, getWidth(), getHeight(), this);
            }
        };
        rightPanel.setPreferredSize(new Dimension(250, 600));
        rightPanel.setOpaque(false);

        // Añadir el panel de la flecha al panel principal en la parte derecha
        mainPanel.add(rightPanel, BorderLayout.EAST);

        // Configurar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            inicio app = new inicio();
            app.setVisible(true);
        });
    }
}
