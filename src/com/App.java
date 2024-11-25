package com;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	static  LoadingView app = new LoadingView();
	public static void main(String[] args) {
		
		System.out.println("PROGRAMA FUNCIONANDO!");
		
        SwingUtilities.invokeLater(() -> {
         
            app.setVisible(true);
            scheduleDelayTask1();
        });
        
        System.out.println(AppConfig.RESOURCES_URL);
		
        
	}
	public static void scheduleDelayTask1()
	{
	    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	   
		Runnable task2=() ->  app.setVisible(false);					
	    
	    service.scheduleAtFixedRate(task2, 1, 1, TimeUnit.SECONDS);
       
	}

}
