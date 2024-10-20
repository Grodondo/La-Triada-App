package com;

import javax.swing.SwingUtilities;

import com.database.DatabaseConnection;
import com.views.MainView;

public class App {

	public static void main(String[] args) {
		
		System.out.println("PROGRAMA FUNCIONANDO!");

		DatabaseConnection.connect("sample");
		
        SwingUtilities.invokeLater(() -> {
            MainView app = new MainView();
            app.setVisible(true);
        });
		
	}

}
