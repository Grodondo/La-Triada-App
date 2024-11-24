package com.views;

import javax.swing.*;
import com.config.AppConfig;
import java.awt.*;

public class VenderApp {

    public static void main(String[] args) {
        // Crear el marco de la aplicación
        JFrame frame = new JFrame("La Triada - Vender");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Crear el panel principal con GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(29, 57, 96)); // Color de fondo basado en la imagen

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Crear y configurar el título "VENDER"
        JLabel titleLabel = new JLabel("VENDER");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Ajustar el tamaño y estilo de la fuente
        titleLabel.setForeground(Color.BLACK); // Cambiar a color negro para el texto

        // Posicionar el título en la parte superior central
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(20, 0, 20, 0); // Espaciado alrededor del título
        mainPanel.add(titleLabel, gbc);

        // Crear el panel que contiene la imagen de fondo (cuadro blanco)
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen de fondo (cuadro blanco)
                ImageIcon backgroundImage = new ImageIcon(AppConfig.RESOURCES_URL + "images/blanco.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setPreferredSize(new Dimension(1250, 700)); // Tamaño ajustado del cuadro blanco
        backgroundPanel.setOpaque(false);

        // Configuración de constraints para colocar el backgroundPanel en el centro, movido a la izquierda
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 150, 0, 0); // Mover ligeramente a la izquierda
        mainPanel.add(backgroundPanel, gbc);

        // Crear el panel derecho para la imagen de las flechas
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon rightImage = new ImageIcon(AppConfig.RESOURCES_URL + "images/flechasSinFondo.png");
                g.drawImage(rightImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        rightPanel.setPreferredSize(new Dimension(250, 600));
        rightPanel.setOpaque(false);

        // Configuración de constraints para colocar el rightPanel de manera independiente a la derecha
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(0, 0, 0, -130); // Mover más a la derecha
        mainPanel.add(rightPanel, gbc);

        // Crear un nuevo panel para contener los botones dentro de la imagen blanca
        JPanel buttonContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // FlowLayout para los botones en línea
        buttonContainerPanel.setOpaque(false); // No cambiar esta línea si no quieres fondo en el panel

        // Crear y escalar los botones con íconos más grandes y más anchos (120x80 píxeles)
        JButton motoButton = new JButton(new ImageIcon(new ImageIcon(AppConfig.RESOURCES_URL + "images/MotoNegro.png")
                .getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH)));
        JButton cocheButton = new JButton(new ImageIcon(new ImageIcon(AppConfig.RESOURCES_URL + "images/CocheNegro.png")
                .getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH)));
        JButton camionButton = new JButton(new ImageIcon(new ImageIcon(AppConfig.RESOURCES_URL + "images/CamionNegro.png")
                .getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH)));

        // Configurar los botones (sin bordes)
        motoButton.setBorderPainted(false);
        motoButton.setContentAreaFilled(false);
        cocheButton.setBorderPainted(false);
        cocheButton.setContentAreaFilled(false);
        camionButton.setBorderPainted(false);
        camionButton.setContentAreaFilled(false);

        // Añadir los botones al panel de botones
        buttonContainerPanel.add(motoButton);
        buttonContainerPanel.add(cocheButton);
        buttonContainerPanel.add(camionButton);

        // Añadir el panel de botones al panel de fondo blanco
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(buttonContainerPanel, BorderLayout.CENTER); // Añadir los botones centrados en el backgroundPanel

        // Añadir el panel principal al marco
        frame.add(mainPanel);

        // Configurar la ventana
        frame.setVisible(true);
    }
}

