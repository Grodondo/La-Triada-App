package com.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.WindowConstants;

import com.config.AppConfig;

import net.miginfocom.swing.MigLayout;

public class MainView extends JFrame{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5636273723239309743L;

	public MainView() {
        // Configuración básica de la ventana
//        setTitle("La Triada");
//        setSize(600, 800);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // Centrar la ventana       
//        Toolkit miPantalla=Toolkit.getDefaultToolkit();
//        Dimension size=miPantalla.getScreenSize();
//
//        int altura= size.height;
//        int ancho= size.width;
//        setBounds(ancho/4,altura/4,ancho/2,altura/2);

		initUI();
    }
	
	public void initUI() {
        setTitle("La Triada App");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\icon.png").getImage());
        setLocationRelativeTo(null);
        //setUndecorated(true);
        
        
//        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
//        getRootPane().setContentPane(new JPanel(new BorderLayout()));
        getRootPane().getContentPane().add(new BarraBusquedaPanel(), BorderLayout.NORTH);
	}
	
	public void windowSettings() {
	       //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\icon.png").getImage());
        setMinimumSize(new Dimension(650, 470));
        setTitle("App");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
                "fillx,hidemode 3",
                // columns
                "[fill]",
                // rows
                "[]" +
                        "[]" +
                        "[]"));
	}
}
