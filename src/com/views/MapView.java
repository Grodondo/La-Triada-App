package com.views;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.viewer.WaypointRenderer;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.GeoPosition;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


import com.config.AppConfig;


public class MapView extends JFrame {
	
	public MapView() {
		initComponents();

      
		JXMapViewer mapViewer = createMap();
        add(mapViewer);

	}
	
	private JXMapViewer createMap() {
		   // Create a map viewer
        JXMapViewer mapViewer = new JXMapViewer();

        // Set up the tile factory (OpenStreetMap)
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // Set the initial zoom level and center position (Spain)
        GeoPosition spainCenter = new GeoPosition(40.4168, -3.7038); // Madrid coordinates
        mapViewer.setZoom(7);  // Set zoom level to show Spain
        mapViewer.setAddressLocation(spainCenter);

        // Enable interactions
        mapViewer.addMouseListener(new PanMouseInputListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));

        // Create waypoints (markers) for Madrid and Barcelona
        Set<CustomWaypoint> waypoints = new HashSet<>();
        waypoints.add(new CustomWaypoint(40.4168, -3.7038)); // Madrid
        waypoints.add(new CustomWaypoint(41.3851, 2.1734));  // Barcelona

        // Set up a WaypointPainter to render the markers
        WaypointPainter<CustomWaypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);
        waypointPainter.setRenderer(new WaypointRenderer<CustomWaypoint>() {
            @Override
            public void paintWaypoint(Graphics2D g, JXMapViewer map, CustomWaypoint wp) {
                g.setColor(Color.RED);
                Point2D point = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
                int x = (int) point.getX() - 5;
                int y = (int) point.getY() - 5;
                g.fillOval(x-5, y-5, 10, 10); // Draw a red dot as the marker
            }
        });

        // Combine painters and set them on the map
        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(waypointPainter);
        mapViewer.setOverlayPainter(new CompoundPainter<>(painters));
        
        return mapViewer;

	}

	private void initComponents() {
		setTitle("Mapa interactivo de la Triada");
		setMinimumSize(new Dimension(200, 150));
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(AppConfig.RESOURCES_URL + "images\\icon.png").getImage());
		setLocationRelativeTo(null);
	}
	
	private class CustomWaypoint implements Waypoint {
		private final GeoPosition position;
		
	    public CustomWaypoint(double latitude, double longitude) {
	        this.position= new GeoPosition(latitude, longitude);
	    }

		@Override
		public GeoPosition getPosition() {
			return position;
		}
	}
}
