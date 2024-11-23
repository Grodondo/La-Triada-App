package com.views;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Creates a panel with rounded corners
 * 
 * @author Carlos Arroyo Caballero
 * @see JPanel
 */
public class RoundedPanel extends JPanel {
    private int radius;

    public RoundedPanel(int radius) {
        this.radius = radius;
        setOpaque(false); // Ensure transparency for custom painting
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smooth rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded rectangle
        g2d.setColor(getBackground()); // Use the panel's background color
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
    }
}
