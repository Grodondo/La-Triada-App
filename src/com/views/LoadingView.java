package com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.config.AppConfig;
import com.config.SharedMethods;



/**
 * @author Pablo Orozco Carrasco
 * 
 * @version 1.0
 */
public class LoadingView extends JFrame  {

	
	
	static HomeView searchView = new HomeView();
	static LoadingView frame = new LoadingView();
	
	/**
	 * Delay para el cambio a home view
	 */
	public static void scheduleDelayTask1()
	{
	    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	    Runnable task1 = () -> searchView.setVisible(true);
		;					
	    service.scheduleAtFixedRate(task1, 12, 1, TimeUnit.SECONDS);
	  
       
	}
	
	/**
	 * Instantiates a new loading view.
	 */
	public LoadingView() {
		
		 setTitle("Loading view");
		 setExtendedState(JFrame.MAXIMIZED_BOTH);
		  setMinimumSize(AppConfig.MINIMUM_WINDOW_SIZE);
		  setSize(AppConfig.sizeWindow);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  setLocationRelativeTo(null);
		  
		  JPanel mainPanel = new JPanel();
		  mainPanel.setBackground(new Color(30, 60, 90));
		  mainPanel.setLayout(new BorderLayout());
		  add(mainPanel);
		  ImageIcon imageIcon = SharedMethods.resizeImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\iconoTexto.png"), 1);
		
		  JButton loading = new JButton(imageIcon);
		  loading.setFont(new Font("Arial", Font.BOLD, 16));
		  loading.setBackground(new Color(15, 35, 60));
		  loading.setForeground(Color.WHITE);
		  loading.setFocusPainted(false);
		  loading.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		  mainPanel.add(loading);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * The main method.
	 *
	 * 
	 */
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            frame.setVisible(true);
            scheduleDelayTask1();

        });
    }

}
