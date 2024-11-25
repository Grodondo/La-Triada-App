package com.views;
import javax.swing.*;
import com.config.AppConfig;
import com.database.DatabaseManager;
import com.models.Vehiculo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

/**
 * La clase <code>SellView</code> representa la interfaz gráfica de usuario (GUI) 
 * para la vista de venta de vehículos dentro de la aplicación. 
 * Permite al usuario seleccionar un vehículo, ingresar detalles de venta y confirmarlos.
 * * @author [Ismael Martin Boudiab] y [Carlos Arroyo Caballero]
 *  * @version 1.0
 */
public class SellView extends JFrame {


	private String tipo;
	
    /**
     * Método principal que inicia la vista de venta de vehículos. 
     * Configura la ventana de la aplicación, paneles, botones y campos de texto.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public SellView() {
        // Crear el marco de la aplicación
    	//JFrame frame = new JFrame("La Triada - Vender");
    	setTitle("La Triada App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Crear el menú (CustomTitleBar)
        CustomTitleBar customTitleBar = new CustomTitleBar(this, "Aplicación de Ventas de Vehículos");
        setJMenuBar(customTitleBar); // Añadir el menú sin alterar el diseño existente

        // Crear un panel principal para contener todo
        JPanel mainPanel = new JPanel(null); // Usamos diseño absoluto para posicionar elementos manualmente
        mainPanel.setBackground(new Color(29, 57, 96)); // Fondo azul oscuro
        add(mainPanel, BorderLayout.CENTER);
        
        // Añadir el título "VENDER" fuera de la imagen blanca y encima de ella
        JLabel titleLabel = new JLabel("VENDER");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Estilo y tamaño de la fuente
        titleLabel.setForeground(Color.BLACK); // Color del texto
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Alinear horizontalmente al centro
        titleLabel.setBounds(150, 10, 1250, 50); // Título fuera de la imagen blanca (subido a 10)
        mainPanel.add(titleLabel);

        // Crear el cuadro blanco como un panel con una imagen de fondo
        JPanel backgroundPanel = new JPanel(null) { // Usamos diseño absoluto también aquí
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen del cuadro blanco
                ImageIcon backgroundImage = new ImageIcon(AppConfig.RESOURCES_URL + "images/blanco.png"); // Cambia la ruta si es necesario
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setBounds(150, 55, 1250, 620); // Ajusta posición y tamaño del cuadro blanco
        backgroundPanel.setOpaque(false); // Permite transparencia si fuera necesario
        mainPanel.add(backgroundPanel);
        
        // Crear el panel derecho para la imagen de las flechas
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen de las flechas
                ImageIcon rightImage = new ImageIcon(AppConfig.RESOURCES_URL + "images/flechasSinFondo.png"); // Cambia la ruta si es necesario
                g.drawImage(rightImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        rightPanel.setOpaque(false); // Fondo transparente
        rightPanel.setBounds(1420, 80, 250, 600); // Ajusta posición inicial de las flechas
        mainPanel.add(rightPanel);

        // Crear un panel para los botones de vehículos
        JPanel buttonContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonContainerPanel.setOpaque(false);
        buttonContainerPanel.setBounds(200, 20, 850, 100);
        backgroundPanel.add(buttonContainerPanel);

        // Botones de vehículos
        JButton motoButton = createVehicleButton("MotoNegro.png", "MotoAzul.png");
        JButton cocheButton = createVehicleButton("CocheNegro.png", "CocheAzul.png");
        JButton camionButton = createVehicleButton("CamionNegro.png", "CamionAzul.png");

        // Añadidos los ActionListeners - [Carlos Arroyo]
     // --------------------------------------------------------------------------------------------- //
        {
        	motoButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Moto seleccionada");
	                cocheButton.setSelected(false);
	                camionButton.setSelected(false);
	                
	                tipo = "moto";
	            }
	        });
	        
	        cocheButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Coche seleccionada");
	                cocheButton.setSelected(false);
	                motoButton.setSelected(false);
	            
	                tipo = "coche";
	            }
	        });
	        
	        camionButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Camion seleccionada");
	                cocheButton.setSelected(false);
	                motoButton.setSelected(false);
	                
	                tipo = "camion";
	            }
	        });
        }
     // --------------------------------------------------------------------------------------------- //
        buttonContainerPanel.add(motoButton);
        buttonContainerPanel.add(cocheButton);
        buttonContainerPanel.add(camionButton);

        // Crear un panel para los campos de texto
        JPanel textFieldPanel = new JPanel(null); // Diseño absoluto
        textFieldPanel.setOpaque(false);
        textFieldPanel.setBounds(220, 120, 850, 400); // Centrar y ajustar el espacio para los campos (más a la izquierda)
        backgroundPanel.add(textFieldPanel);

        // Crear un array con los nombres de los placeholders
        String[] placeholders = {"MATRÍCULA", "MARCA", "MODELO", "CARROCERÍA", 
                                 "CONSUMO", "COMBUSTIBLE", "KILOMETRAJE", "PRECIO"};

        // Dimensiones y posición inicial
        int width = 400, height = 50;  // Ancho de cada JTextField
        int gap = 30;                 // Espaciado vertical entre los campos
        int xLeft = 0;                // Posición X para la columna izquierda (más a la izquierda)
        int xRight = 420;             // Posición X para la columna derecha (más a la izquierda)
        int yStart = 20;              // Posición inicial Y

        
        JTextField textFields[] = new JTextField[placeholders.length];
        
        // Iterar sobre los placeholders y añadirlos en dos columnas
        for (int i = 0; i < placeholders.length; i++) {
            textFields[i] = createPlaceholderTextField(placeholders[i]);

            // Alternar entre la columna izquierda y la derecha
            int x = (i < 4) ? xLeft : xRight; // Las primeras 4 en la izquierda, las siguientes en la derecha
            int y = yStart + (i % 4) * (height + gap); // Alinear verticalmente con espaciado

            textFields[i].setBounds(x, y, width, height);
            textFieldPanel.add(textFields[i]);
        }

        // Crear un panel para el botón Confirmar
        JPanel confirmButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmButtonPanel.setOpaque(false); // Fondo transparente
        confirmButtonPanel.setBounds(200, 500, 850, 60); // Ajustado a la parte inferior del panel

        // Crear el botón Confirmar
        JButton confirmButton = new JButton("Confirmar");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 22)); // Estilo y tamaño de la fuente
        confirmButton.setBackground(new Color(29, 57, 96)); // Usar el mismo azul del fondo
        confirmButton.setForeground(Color.WHITE); // Color del texto
        confirmButton.setPreferredSize(new Dimension(300, 40)); // Tamaño del botón más ancho

        // Añadida parte funcional el confirmButton - [Carlos Arroyo]
        // --------------------------------------------------------------------------------------------- //
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Vehiculo vehicle = null;
                String[] placeholders = {"MATRÍCULA", "MARCA", "MODELO", "CARROCERÍA", 
                        "CONSUMO", "COMBUSTIBLE", "KILOMETRAJE", "PRECIO"};
            	try {
            		vehicle = new Vehiculo(
            			textFields[0].getText(), // Matricula
            			tipo, 					 // Tipo
            			textFields[1].getText(), // Marca
            			textFields[2].getText(), // Modelo
            			textFields[3].getText(), // Carroceria
            			textFields[5].getText(),					 // Combustible
            			Double.parseDouble(textFields[4].getText()), // Consumo
            			1, 						 // Plazas
            			Double.parseDouble(textFields[6].getText()), // Kilometros
            			Double.parseDouble(textFields[7].getText()), // Precio compra
            			false
            		);
                    DatabaseManager.insertarVehiculo(vehicle);
                    
                    JOptionPane.showMessageDialog(
                        null, // Padre del diálogo (null para centrar en pantalla)
                        "Datos añadidos correctamente.", // Mensaje
		                "Datos añadidos", // Título del popup
		                JOptionPane.INFORMATION_MESSAGE // Tipo de mensaje 
                        );
                    
                    FileChooserButton.matricula = vehicle.getMatricula();
                    String directorioImagenes = AppConfig.PROJECT_PATH + "\\src\\com\\database\\imagenes\\";
                    String textoFileChooser = "Seleccionar imagen";
                    
                    FileChooserButton fileChooserButton = new FileChooserButton(textoFileChooser, directorioImagenes);
                    fileChooserButton.doClick();
                    
                    // Abrir SearchView y cerrar SellView
                    new SearchView().setVisible(true);
                    SellView.this.dispose();
                    
            	}catch (SQLException sqle) {
            		JOptionPane.showMessageDialog(
                        null, // Padre del diálogo (null para centrar en pantalla)
                        "Error al añadir los datos a la base de datos.", // Mensaje
                        "Error en la base de datos", // Título del popup
                        JOptionPane.ERROR_MESSAGE // Tipo de mensaje (ícono de error)
                     );
            	}catch(Exception ex) {
		            JOptionPane.showMessageDialog(
		                null, // Padre del diálogo (null para centrar en pantalla)
		                "Faltan datos por añadir. Por favor, revisa e intenta nuevamente.", // Mensaje
		                "Datos incompletos", // Título del popup
		                JOptionPane.WARNING_MESSAGE // Tipo de mensaje (ícono de advertencia)
			        );
		            
            	}
            
            	
                System.out.println("Confirmación realizada");
            }
        });
     
//        String directorioImagenes = AppConfig.PROJECT_PATH + "\\src\\com\\database\\imagenes\\";
//        String textoFileChooser = "Seleccionar imagen";
//        
//        FileChooserButton fileChooserButton = new FileChooserButton(textoFileChooser, directorioImagenes);
     // --------------------------------------------------------------------------------------------- //																																																																																																																																																																																											
        
        // Añadir el botón al panel
        confirmButtonPanel.add(confirmButton);
        backgroundPanel.add(confirmButtonPanel);

    }
    
    /**
     * Crea un botón de vehículo con funcionalidad de cambio de ícono.
     * Cuando el botón es presionado, cambia de color de negro a azul y viceversa.
     * 
     * @param blackIconPath Ruta de la imagen del ícono negro.
     * @param blueIconPath Ruta de la imagen del ícono azul.
     * @return El botón configurado con la funcionalidad de cambio de ícono.
     */
    private static JButton createVehicleButton(String blackIconPath, String blueIconPath) {
        ImageIcon blackIcon = new ImageIcon(new ImageIcon(AppConfig.RESOURCES_URL + "images/" + blackIconPath)
                .getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH));
        ImageIcon blueIcon = new ImageIcon(new ImageIcon(AppConfig.RESOURCES_URL + "images/" + blueIconPath)
                .getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH));

        JButton button = new JButton(blackIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        // Crear un array para almacenar los botones y permitir solo un botón azul a la vez
        JButton[] vehicleButtons = new JButton[3];

        button.addActionListener(e -> {
            // Si el botón ya está azul, lo volvemos a poner negro
            if (button.getIcon() == blueIcon) {
                button.setIcon(blackIcon);
            } else {
                // Desmarcar todos los otros botones
                for (JButton otherButton : vehicleButtons) {
                    if (otherButton != null && otherButton.getIcon() == blueIcon) {
                        otherButton.setIcon(blackIcon);  // Poner en negro los otros botones
                    }
                }
                // Marcar el botón actual como azul
                button.setIcon(blueIcon);
            }
        });

        // Almacenar el botón en el array
        vehicleButtons[0] = button;

        return button;
    }

    /**
     * Crea un <code>JTextField</code> con un placeholder personalizado.
     * El placeholder se elimina cuando el campo recibe foco y se restablece si el campo se queda vacío al perder el foco.
     * 
     * @param placeholder El texto que se muestra como placeholder.
     * @return El <code>JTextField</code> configurado con el placeholder.
     */
    private static JTextField createPlaceholderTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        
        // Configuración del texto y fondo
        textField.setFont(new Font("Arial", Font.PLAIN, 18)); // Estilo del texto
        textField.setForeground(Color.WHITE); // Color del texto (texto blanco)
        textField.setBackground(new Color(29, 57, 96)); // Fondo azul igual al de la ventana
        textField.setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto

        // Añadir funcionalidad para eliminar el placeholder al hacer clic
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.WHITE); // El texto que se escribe también será blanco
                }
            }

            public void focusLost(FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.WHITE);
                    textField.setText(placeholder);
                }
            }
        });

        return textField;
    }
}
