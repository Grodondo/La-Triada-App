package com;

import javax.swing.SwingUtilities;

import com.config.AppConfig;
import com.database.sample1;
import com.views.MainView;

public class App {

	public static void main(String[] args) {
		
		System.out.println("PROGRAMA FUNCIONANDO!");
		
        SwingUtilities.invokeLater(() -> {
            MainView app = new MainView();
            app.setVisible(true);
        });
        
        System.out.println(AppConfig.RESOURCES_URL);
		
        sample1.createTableVehiculos();
        sample1.createTableAlquilados();
        
	}

}
