package com.config;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class contains shared methods that are used in multiple classes.
 * 
 * 
 * @version 1.0
 */
public class SharedMethods {

	
	/**
	 * @author Carlos Arroyo Caballero
	 * @param imageIcon The image to scale
	 * @param size size to scale.
	 * @return The scaled image as ImageIcon
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
