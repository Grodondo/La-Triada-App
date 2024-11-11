package com.views;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.config.AppConfig;

public class MapView extends JFrame {

	public MapView() {
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Mapa de la Triada");
		setMinimumSize(new Dimension(200, 150));
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\icon.png").getImage());
		setLocationRelativeTo(null);
	}
}
