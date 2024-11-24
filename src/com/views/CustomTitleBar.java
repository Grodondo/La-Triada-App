package com.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import com.config.*;

import resources.CustomColor;

/**
 * Barra be busqueda personalizada para la aplicacion
 * 
 * @author [Carlos Arroyo Caballero]
 * @version 1.0
 */
public class CustomTitleBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private static final int HEIGHT = 100;
	
	private JFrame frame;
	private String titleLabel;
	
	/**
	 * Constructor 
	 * 
	 * @param frame      La ventana a la que se le añade la barra de busqueda
	 * @param titleLabel El tittulo de la barra de busqueda/ventana
	 */
	public CustomTitleBar(JFrame frame, String titleLabel) {

		this.frame = frame;
		this.titleLabel = titleLabel;
		
		setBackground(CustomColor.BLUE_B);

		initComponents();

	}

	/**
	 *  Inicializa los componentes de la barra de busqueda
	 */
	private void initComponents() {
		
		 setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();

		 JLabel titleLabel = new JLabel(this.titleLabel, JLabel.CENTER);
		 titleLabel.setForeground(Color.WHITE);
        
//        JButton closeButton = new JButton("X");
//        closeButton.addActionListener(e -> System.exit(0));
		
		ImageIcon imageIcon = SharedMethods.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconoTexto.png"), 6);
		ImageIcon imageMap = SharedMethods.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconos\\IconoMapa.png"), 6);

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
		
		
		// ======== Title label ========
        {
           titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        }
		
		// ======== iconButton ========
		{
			iconButton.setPreferredSize(new Dimension(200, HEIGHT));
			iconButton.setBorderPainted(false);
			iconButton.setContentAreaFilled(false);
			iconButton.setFocusPainted(false);
			iconButton.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							AppConfig.sizeWindow = frame.getSize();
							frame.dispose();
							
							MainView searchView = new MainView();
							searchView.setVisible(true);
						}
	                           
	                });
		}
		
		// ======== mapButton ========
		{
			mapButton.setPreferredSize(new Dimension(200, HEIGHT));
			mapButton.setBorderPainted(false);
			mapButton.setContentAreaFilled(false);
			mapButton.setFocusPainted(false);
			mapButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	MapView searchView = new MapView();
		            	//searchView.setDefaultCloseOperation(ABORT);
						searchView.setVisible(true);
		            }
		        });
		}
		// ======== Boton de compra ========
		{
			menuBuy.setText("COMPRAR/ALQUILAR");
			menuBuy.setPreferredSize(new Dimension(220, HEIGHT));
			menuBuy.setFont(new Font("Arial", Font.BOLD, 20));
			menuBuy.setFocusPainted(false);
			menuBuy.setBorderPainted(false);
			menuBuy.setBackground(CustomColor.BLUE_B);
			menuBuy.setForeground(Color.WHITE);
			//menuBuy.setMargin(null);


			// ---- Compra Coche  ----
			{
				buttonBuyCar.setText("Coche");
				buttonBuyCar.setPreferredSize(new Dimension(220, 40));
				buttonBuyCar.setFont(new Font("Arial", Font.BOLD, 20));
				buttonBuyCar.setBackground(CustomColor.BLUE_B);
				buttonBuyCar.setForeground(Color.WHITE);

				
				buttonBuyCar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						AppConfig.sizeWindow = frame.getSize();
						frame.dispose();
						
						SearchView searchView = new SearchView("car");
						searchView.setVisible(true);
					}
                           
                });
				
				menuBuy.add(buttonBuyCar);
			}
			
			// ---- Compra Moto ----
			{
				buttonBuyBike.setText("Moto");
				buttonBuyBike.setPreferredSize(new Dimension(220, 40));
				buttonBuyBike.setFont(new Font("Arial", Font.BOLD, 20));
				buttonBuyBike.setBackground(CustomColor.BLUE_B);
				buttonBuyBike.setForeground(Color.WHITE);
	
				buttonBuyBike.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						AppConfig.sizeWindow = frame.getSize();
						frame.dispose();
						
						SearchView searchView = new SearchView("bike");
						searchView.setVisible(true);
					}
	                       
	            });
				
				menuBuy.add(buttonBuyBike);
			}
			
			// ---- Compra camion  ----
			{
				buttonBuyTruck.setText("Camión");
				buttonBuyTruck.setPreferredSize(new Dimension(220, 40));
				buttonBuyTruck.setFont(new Font("Arial", Font.BOLD, 20));
				buttonBuyTruck.setBackground(CustomColor.BLUE_B);
				buttonBuyTruck.setForeground(Color.WHITE);
	
	            buttonBuyTruck.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	AppConfig.sizeWindow = frame.getSize();
	                    frame.dispose();
	                    
	                    SearchView searchView = new SearchView("truck");
	                    searchView.setVisible(true);
	                }
	            });
				
				menuBuy.add(buttonBuyTruck);
			}
		}
		// ======== Boton de venta ========
		{
			sellButton.setPreferredSize(new Dimension(200, HEIGHT));
			sellButton.setMargin(new Insets(HEIGHT/4, 10, HEIGHT/4, 10));
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
			
			sellButton.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							AppConfig.sizeWindow = frame.getSize();
							frame.dispose();
							
							SellView sellView = new SellView();
							sellView.setVisible(true);
						}
	                           
	                });
		}
		
		// ======== Boton de devolver ========
		{
			devuelveButton.setPreferredSize(new Dimension(200, HEIGHT));
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
			
			devuelveButton.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							AppConfig.sizeWindow = frame.getSize();
							frame.dispose();
							
							DevolverView devolverView = new DevolverView();
							devolverView.setVisible(true);
						}
	                           
	                });
		}

		
        gbc.gridx = 0;
        gbc.gridy = 0;
        //gbc.insets = new Insets(0, 10, 0, 10);
        add(iconButton, gbc);

        gbc.gridx = 1;
        add(menuBuy, gbc);

        gbc.gridx = 2;
        add(sellButton, gbc);

        gbc.gridx = 3;
        add(devuelveButton, gbc);

        gbc.gridx = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(titleLabel, gbc);

        gbc.gridx = 5;
        gbc.weightx = 0;
        add(mapButton, gbc);		

	}

}
