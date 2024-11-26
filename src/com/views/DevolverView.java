

package com.views;

import javax.swing.*;
import javax.swing.border.LineBorder;

import com.App;
import com.config.AppConfig;
import com.database.DatabaseManager;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Devolver view 
 * 
 * @author Pablo Orozco Carrasco
 * @version 2.0
 * @see CustomTitleBar
 */
public class DevolverView extends JFrame {
	 
	
	/**
	 * Procesar devolucion.
	 *
	 * @param matricula the matricula
	 * @param bottomPanel the bottom panel
	 */
	private static void procesarDevolucion(String matricula, JPanel bottomPanel) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            // Conexión a la base de datos SQLite
	            conn = DriverManager.getConnection("jdbc:sqlite:database.db");

	            // Consulta para buscar el alquiler del vehículo
	            String sql = "SELECT * FROM alquilado WHERE matricula = ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, matricula);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                // Datos del alquiler
	                String fechaInicio = rs.getString("fecha_inicio");
	                String fechaFinEsperada = rs.getString("fecha_fin");
	                String alquilerId = rs.getString("matricula");

	                // Obtener la fecha y hora actual
	                LocalDate fechaActual = LocalDate.now();

	                // Mostrar información al usuario
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	                LocalDate fechaInicioDt = LocalDate.parse(fechaInicio);
	                LocalDate fechaFinEsperadaDt = LocalDate.parse(fechaFinEsperada);

	                long horasTotales = ChronoUnit.DAYS.between(fechaInicioDt, fechaActual);
	                long horasTarde = ChronoUnit.DAYS.between(fechaFinEsperadaDt, fechaActual);

	                // Actualizar la base de datos
	                DatabaseManager.eliminarVehiculoAlquilado(matricula);

	                // Mostrar el resultado
	                if (fechaActual.isAfter(fechaFinEsperadaDt)) {
	                    JOptionPane.showMessageDialog(bottomPanel,
	                            "Vehículo devuelto.\nTiempo alquilado: " + horasTotales + " dias.\nDevuelto tarde por " + horasTarde + " horas.",
	                            "Devolución Tarde", JOptionPane.WARNING_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(bottomPanel,
	                            "Vehículo devuelto.\nTiempo alquilado: " + horasTotales + " dias.\nDevuelto a tiempo.",
	                            "Devolución Exitosa", JOptionPane.INFORMATION_MESSAGE);
	                }

	            } else {
	                JOptionPane.showMessageDialog(bottomPanel, "No hay registros activos para esta matrícula.", "No encontrado", JOptionPane.WARNING_MESSAGE);
	            }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(bottomPanel, "Error al buscar en la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pstmt != null) pstmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	  }
	  
  	/**
  	 * Instantiates a new devolver view.
  	 */
  	public DevolverView() {
	
		  setTitle("La Tienda - Devolver");
		  setMinimumSize(AppConfig.MINIMUM_WINDOW_SIZE);
		  setSize(AppConfig.sizeWindow);
		  setExtendedState(JFrame.MAXIMIZED_BOTH);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  setLocationRelativeTo(null);

		
		  JPanel mainPanel = new JPanel();
		  mainPanel.setBackground(new Color(30, 60, 90));
		  mainPanel.setLayout(new BorderLayout());
		  add(mainPanel);

		
		  JMenuBar CustomTitleBar = new CustomTitleBar(this, "Devolver");
		  add(CustomTitleBar, BorderLayout.NORTH);

		
		  JPanel centerPanel = new JPanel();
		  centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); 
		  centerPanel.setPreferredSize(new Dimension(500, 300));
		  centerPanel.setBackground(new Color(30, 60, 90));
		  
		  
		  JPanel bottomPanel = new JPanel();
		  bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));
		  bottomPanel.setPreferredSize(new Dimension(400, 400)); 
		  bottomPanel.setVisible(false);
		  bottomPanel.setBackground(Color.WHITE);
		  bottomPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		  JLabel placeholder = new JLabel("");
		  placeholder.setFont(new Font("Arial", Font.BOLD, 24));
		  placeholder.setForeground(Color.BLACK);
		  placeholder.setAlignmentX(Component.CENTER_ALIGNMENT);
		 
		  bottomPanel.add(placeholder,BorderLayout.CENTER);
		  

		  JLabel devolverLabel = new JLabel("DEVOLVER");
		  devolverLabel.setFont(new Font("Arial", Font.BOLD, 24));
		  devolverLabel.setForeground(Color.WHITE);
		  devolverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		  centerPanel.add(Box.createVerticalStrut(20));
		  centerPanel.add(devolverLabel);

		  JLabel matriculaLabel = new JLabel("MATRICULA");
		  matriculaLabel.setFont(new Font("Arial", Font.BOLD, 18));
		  matriculaLabel.setForeground(Color.WHITE);
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

		
		  ImageIcon imageIconR = new ImageIcon(AppConfig.RESOURCES_URL + "images/flechasSinFondo.png"); 
		  JLabel imageLabelR = new JLabel(imageIconR);
		

		
		  imageLabelR.setHorizontalAlignment(SwingConstants.RIGHT); 
		
	
		  mainPanel.add(centerPanel, BorderLayout.CENTER);
		  mainPanel.add(imageLabelR, BorderLayout.EAST); 
		  
		  mainPanel.add(bottomPanel,BorderLayout.AFTER_LAST_LINE);
		

		  confirmButton.addActionListener(e -> {
			
			 
			    centerPanel.setBackground(Color.WHITE);
			    devolverLabel.setForeground(Color.BLACK);
			   
			    matriculaLabel.setForeground(Color.BLACK);
			  //  bottomPanel.setVisible(true);
			  imageLabelR.setVisible(false);
			    String matricula = matriculaField.getText().trim();
                if (matricula.isEmpty()) {
                    JOptionPane.showMessageDialog(bottomPanel, "Por favor, introduce una matrícula", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                	procesarDevolucion(matricula, bottomPanel);
                }
                
                if(centerPanel.getBackground()==Color.WHITE && devolverLabel.getForeground()==Color.BLACK) {
                	 centerPanel.setBackground(new Color(30, 60, 90));
                	 devolverLabel.setForeground(Color.WHITE);
                	 matriculaLabel.setForeground(Color.WHITE);
                	  imageLabelR.setVisible(true);
                	 
                }
			});
	        
	    }

	  
}
