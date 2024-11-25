package com.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Clase que representa un botón personalizado para seleccionar archivos de imagen.
 * 
 * @author [Carlos Arroyo Caballero]
 * @version 1.0
 */
public class FileChooserButton extends JButton {

	public static String matricula = "";
	
    private String imageDirectoryPath;
    private String selectedImagePath;

	/**
	 * Constructor de la clase {@link FileChooserButton}.
	 * 
	 * @param buttonText         Texto del botón
	 * @param imageDirectoryPath Ruta del directorio donde se guardarán las imágenes
	 */
    public FileChooserButton(String buttonText, String imageDirectoryPath) {
        super(buttonText); // Set the button's text
        this.imageDirectoryPath = imageDirectoryPath;;

        // Configure the button's appearance
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setBackground(new Color(29, 57, 96));
        this.setForeground(Color.WHITE);

        // Add the action listener for file selection
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFileSelection();
            }
        });
    }

	/**
	 * Método que se encarga de manejar la selección de un archivo de imagen.
	 */
    private void handleFileSelection() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar una imagen");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Añana un filtro para mostrar solo archivos de imagen
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                String name = f.getName().toLowerCase();
                return f.isDirectory() || name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg");
            }

            @Override
            public String getDescription() {
                return "Imágenes (*.png, *.jpg, *.jpeg)";
            }
        });

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Asegurarse de que el directorio de imágenes exista
                File imageDirectory = new File(imageDirectoryPath);
                if (!imageDirectory.exists()) {
                    imageDirectory.mkdirs();
                }

                // Copiar el archivo seleccionado al directorio de imágenes
                //String newFileName = System.currentTimeMillis() + "_" + selectedFile.getName();
                String newFileName = matricula + ".jpg";
                System.out.println(newFileName);
                File destinationFile = new File(imageDirectory, newFileName);
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                selectedImagePath = destinationFile.getAbsolutePath();
                JOptionPane.showMessageDialog(null, "Imagen cargada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String getSelectedImagePath() {
        return selectedImagePath;
    }
}
