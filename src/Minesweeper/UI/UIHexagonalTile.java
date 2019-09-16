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
        setPreferredSize(new Dimension(width, height));
        
    }

    private void setPoints() {
        xvalues = new int[] { getTileWidth() / 2, getTileWidth() - 1, getTileWidth() - 1, (getTileWidth() / 2) + 1, 1, 1};
        yvalues = new int[] {1, (getTileHeight() / 4), ((getTileHeight() / 4) * 3), getTileHeight() - 1, ((getTileHeight() / 4) * 3), (getTileHeight() / 4)};
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
        g.setFont(new java.awt.Font("plain", 0, 12));
        //if (!getTileText().equals("")) {
        
            g.drawString(String.format("y:%s, x:%s", getPositionY(), getPositionX()), (getTileWidth() / 2) - 12, (getTileHeight() / 2) + 5);
            g.drawString(getTileText(), (getTileWidth() / 2), (getTileHeight() / 2 - 5));
        //}
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getTileWidth(), getTileHeight());
    }
    
}