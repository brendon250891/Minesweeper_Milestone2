/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.UI;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;

/**
 *
 * @author brendon
 */
public class UIHexagonalTile extends UITile {
    private final int SIDES = 6;
    
    private boolean oddRow = false;
    
    public UIHexagonalTile(Point position, int width, int height, boolean oddRow) {
        super(position, width, height);
        this.oddRow = oddRow;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        var hexagon = new Polygon();
        int[] xvalues = new int[] { ((getTileWidth() / 2) / 2), ((getTileWidth() / 2) / 2) + (getTileWidth() / 2), getTileWidth(), ((getTileWidth() / 2) / 2) + (getTileWidth() / 2), (getTileWidth() / 2) / 2, 0 };
        int[] yvalues = new int[] {0, 0, getTileHeight() / 2, getTileHeight(), getTileHeight(), getTileHeight() / 2};
        /*for (int i = 0; i < SIDES; i++) {
            int x = (int) ((getTileWidth() / 2)  * Math.cos(i * 2 * Math.PI / SIDES));
            int y = (int) ((getTileHeight() / 2) * Math.sin(i * 2 * Math.PI / SIDES));
            hexagon.addPoint(x, y);
        }*/
        g.setColor(getBackgroundColor());
        g.drawPolygon(xvalues, yvalues, SIDES);
        g.fillPolygon(xvalues, yvalues, SIDES);
        g.setColor(getForegroundColor(getTileText()));
        g.setFont(new java.awt.Font("plain", 0, 24));
        g.drawString(getTileText(), (getTileWidth() / 2) - 8, (getTileWidth() / 2) + 5);
        
    }
}