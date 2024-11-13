package com.views;

import javax.swing.*;

import com.App;
import com.config.AppConfig;

import java.awt.*;

public class DevolverView extends JFrame {
	  public DevolverView() {
	
	        setTitle("La Tienda - Devolver");
	        setSize(1080, 720);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	     
	        JPanel mainPanel = new JPanel();
	        mainPanel.setBackground(new Color(30, 60, 90));
	        mainPanel.setLayout(new BorderLayout());
	        add(mainPanel);

	      
	       JMenuBar CustomTitleBar = new CustomTitleBar (this,"Devolver");

	        
	       add(CustomTitleBar, BorderLayout.NORTH);

	       
	        JPanel centerPanel = new JPanel();
	        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
	        centerPanel.setBounds(1000, 1000,400, 400);
	       
	       
	        ImageIcon backroundImWhite=new ImageIcon(AppConfig.RESOURCES_URL+"images/blanco.jpg");
	        centerPanel.setBackground(Color.WHITE);
	        
	        JLabel devolverLabel = new JLabel("DEVOLVER");
	        devolverLabel.setFont(new Font("Arial", Font.BOLD, 24));
	        devolverLabel.setForeground(Color.BLACK);
	        devolverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        centerPanel.add(Box.createVerticalStrut(20)); 
	        centerPanel.add(devolverLabel);

	      
	        JLabel matriculaLabel = new JLabel("MATRICULA");
	        matriculaLabel.setFont(new Font("Arial", Font.BOLD, 18));
	        matriculaLabel.setForeground(Color.BLACK);
	        matriculaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        centerPanel.add(Box.createVerticalStrut(20));
	        centerPanel.add(matriculaLabel);

	       
	        JTextField matriculaField = new JTextField();
	        matriculaField.setMaximumSize(new Dimension(200, 30));
	        matriculaField.setFont(new Font("Arial", Font.PLAIN, 16));
	        centerPanel.add(matriculaField);

	     
	        JButton confirmButton = new JButton("CONFIRMAR");
	        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
	        confirmButton.setBackground(new Color(15, 35, 60)); 
	        confirmButton.setForeground(Color.WHITE);
	        confirmButton.setFocusPainted(false);
	        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	        centerPanel.add(Box.createVerticalStrut(20)); 
	        centerPanel.add(confirmButton);

	     
	        mainPanel.add(centerPanel, BorderLayout.CENTER);
	    }

	   


	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            DevolverView frame = new DevolverView();
	            frame.setVisible(true);
	        });
	    }
}
