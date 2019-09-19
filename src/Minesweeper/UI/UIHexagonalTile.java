/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author brendon
 */
public class UIHexagonalTile extends UITile {
    private final int SIDES = 6;
    
    private int[] xvalues = new int[SIDES];
    
    private int[] yvalues = new int[SIDES];
    
    private Point center;
    
    private int radius;
    
    public UIHexagonalTile(Point position, Point center, int width, int height) {
        super(position, width, height);
        this.center = center;
        this.radius = width / 2;
        setPoints();       
    }

    private void setPoints() {
        xvalues = new int[] { getTileWidth() / 2, getTileWidth(), getTileWidth(), (getTileWidth() / 2), 0, 0};
        yvalues = new int[] {0, (getTileHeight() / 4), ((getTileHeight() / 4) * 3), getTileHeight() - 1, ((getTileHeight() / 4) * 3), (getTileHeight() / 4)};
    }
    
    public int[] getXValues() {
        return xvalues;
    }
    
    public int[] getYValues() {
        return yvalues;
    }
    
    public Point getCenter() {
        return getPosition();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(java.awt.Color.BLACK);
        g.drawPolygon(xvalues, yvalues, SIDES);
        g.setColor(getBackgroundColor());
        g.fillPolygon(xvalues, yvalues, SIDES);
        g.setColor(getForegroundColor(getTileText()));
        g.setFont(new java.awt.Font("plain", 0, 24));
        g.drawString(getTileText(), (getTileWidth() / 2) - 6, (getTileHeight() / 2) + 6);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getTileWidth(), getTileHeight());
    }
    
}