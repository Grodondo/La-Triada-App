package com.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.config.AppConfig;

public class MainView extends JFrame{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5636273723239309743L;

	public MainView() {

		initUI();

    }
	
	public void initUI() {
        setTitle("La Triada App");
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\icon.png").getImage());
        setLocationRelativeTo(null);
        //setUndecorated(true);
        
        
//        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
//        getRootPane().setContentPane(new JPanel(new BorderLayout()));
        getRootPane().getContentPane().add(new CustomTitleBar(), BorderLayout.NORTH);
	}
}
