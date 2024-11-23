package com.config;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.models.Filtro;

/**
 * Contiene métodos compartidos por varias clases para su reutilización.
 * 
 * 
 * @version 1.0
 */
public class SharedMethods {

	/**
	 * @author [Carlos Arroyo Caballero]
	 * 
	 * @param imageIcon La imagen a escalar
	 * @param size Tamaño al que se escalará la imagen
	 * 
	 * @return La imagen escalada como un {@link ImageIcon}
	 */
	public static ImageIcon resizeImage(ImageIcon imageIcon, double size) {
		Image image = imageIcon.getImage();
		int width = (int) (imageIcon.getIconWidth() / size);
		int height = (int) (imageIcon.getIconHeight() / size);
		
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		return scaledIcon;
	}
	
}
