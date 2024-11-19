package com.config;

import java.awt.Dimension;

/**
 * This class contains shared constant values that are used in multiple classes.
 * 
 * @author Carlos Arroyo Caballero
 * @version 1.0
 */
public class AppConfig {
	public static Dimension sizeWindow = new Dimension(900, 600);
	
	public final static String PROJECT_PATH = System.getProperty("user.dir");
	public final static String RESOURCES_URL = System.getProperty("user.dir") + "\\src\\resources\\";
    public final static String APP_NAME = "La Triada App";
    public final static String COPYRIGHT_DETAILS = "© 2022 X";
    public final static String HOME_URL = "https://github.com/Grodondo/La-Triada-App.git";
    public final static Dimension MINIMUM_WINDOW_SIZE = new Dimension(900, 600);
    public final static String DEFAULT_BLUE_THEME = "Blue";
    public final static String DEFAULT_DARK_THEME = "Dark";
    public final static String DEFAULT_LIGHT_THEME = "Light";
}
