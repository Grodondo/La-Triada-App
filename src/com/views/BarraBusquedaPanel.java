package com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.config.AppConfig;

import resources.CustomColor;

public class BarraBusquedaPanel extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 100;
	

	public BarraBusquedaPanel() {

		setBackground(CustomColor.BLUE_B);

		initComponents();

	}

	private void initComponents() {

        JLabel titleLabel = new JLabel("Swing App", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        
//        JButton closeButton = new JButton("X");
//        closeButton.addActionListener(e -> System.exit(0));
		
		ImageIcon imageIcon = new ImageIcon(AppConfig.RESOURCES_URL + "images\\icon.png");
		ImageIcon imageMap = new ImageIcon(AppConfig.RESOURCES_URL + "images\\map.png");

		JButton iconButton = new JButton(imageIcon);
		JButton mapButton = new JButton(imageMap);

		JButton sellButton = new JButton("VENDER");
		JButton devuelveButton = new JButton("DEVOLVER");

		JMenu menuBuy = new JMenu();
		JMenuItem buttonBuyCar = new JMenuItem();
		JMenuItem buttonBuyBike = new JMenuItem();
		JMenuItem buttonBuyTruck = new JMenuItem();


		// ======== this ========
		
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		
		// ======== iconButton ========
		{
//			iconButton.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO
//				}
//			});
			iconButton.setPreferredSize(new Dimension(100, this.getHeight()));
			iconButton.setBorderPainted(false);
			iconButton.setContentAreaFilled(false);
			iconButton.setFocusPainted(false);
			iconButton.addActionListener(syso -> System.out.println("Icono pulsado"));

		}
		
		// ======== mapButton ========
		{
			mapButton.setPreferredSize(new Dimension(50, this.getHeight()));
			mapButton.setBorderPainted(false);
			mapButton.setContentAreaFilled(false);
			mapButton.setFocusPainted(false);
			mapButton.addActionListener(syso -> System.out.println("Mapa pulsado"));
		}
		// ======== Boton de compra ========
		{
			menuBuy.setText("COMPRAR/ALQUILAR");
			menuBuy.setMargin(new Insets(10, 10, 10, 10));
			menuBuy.setFont(new Font("Arial", Font.BOLD, 20));
			menuBuy.setFocusPainted(false);
			menuBuy.setBorderPainted(false);
			menuBuy.setBackground(CustomColor.BLUE_B);
			menuBuy.setForeground(Color.WHITE);
	

			// ---- Compra Coche  ----
			buttonBuyCar.setText("Coche");
			menuBuy.addActionListener(e -> e.toString());
			menuBuy.add(buttonBuyCar);
			
			// ---- Compra Moto ----
			buttonBuyBike.setText("Coche");
			menuBuy.addActionListener(e -> e.toString());
			menuBuy.add(buttonBuyCar);
			
			// ---- Compra camion  ----
			buttonBuyTruck.setText("Coche");
			menuBuy.addActionListener(e -> e.toString());
			menuBuy.add(buttonBuyCar);
		}
		
		// ======== Boton de venta ========
		sellButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		//sellButton.setPreferredSize(new Dimension(100, HEIGHT));
		sellButton.setMargin(new Insets(10, 10, 10, 10));
		sellButton.setFont(new Font("Arial", Font.BOLD, 20));
		sellButton.setFocusPainted(false);
		sellButton.setBorderPainted(false);
		sellButton.setBackground(CustomColor.BLUE_B);
		sellButton.setForeground(Color.WHITE);
		sellButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sellButton.setBackground(CustomColor.BLUE_A);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				sellButton.setBackground(CustomColor.BLUE_B);
			}
		});

		// ======== Boton de devolver ========
		{
			devuelveButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO
				}
			});
			devuelveButton.setMargin(new Insets(HEIGHT/4, 10, HEIGHT/4, 10));
			devuelveButton.setFont(new Font("Arial", Font.BOLD, 18));
			devuelveButton.setFocusPainted(false);
			devuelveButton.setBorderPainted(false);
			devuelveButton.setBackground(CustomColor.BLUE_B);
			devuelveButton.setForeground(Color.WHITE);
			devuelveButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					devuelveButton.setBackground(CustomColor.BLUE_A);
				}
	
				@Override
				public void mouseExited(MouseEvent e) {
					devuelveButton.setBackground(CustomColor.BLUE_B);
				}
			});
		}

		
		add(Box.createRigidArea(new Dimension(20, 0)));
		add(iconButton);
		add(Box.createRigidArea(new Dimension(20, 0)));
		add(menuBuy);
		add(sellButton);
		add(devuelveButton);
		add(Box.createRigidArea(new Dimension(100, 0)));
        add(titleLabel, BorderLayout.CENTER);
        add(Box.createRigidArea(new Dimension(100, 0)));
		add(Box.createHorizontalGlue());
		add(mapButton);
		add(Box.createRigidArea(new Dimension(60, 0)));
		

	}

}
