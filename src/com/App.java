package com;

import javax.swing.SwingUtilities;

import com.config.AppConfig;
import com.database.*;
import com.views.*;


/**
 * Clase principal de la aplicación
 * 
 * Se encarga de iniciar la aplicación y mostrar la ventana principal.
 * 
 * @version 1.0
 */
public class App {

	public static void main(String[] args) {
		
		System.out.println("PROGRAMA FUNCIONANDO!");
		
        SwingUtilities.invokeLater(() -> {
            MainView app = new MainView();
            app.setVisible(true);
        });
        
        System.out.println(AppConfig.RESOURCES_URL);
		
        
	}

}
