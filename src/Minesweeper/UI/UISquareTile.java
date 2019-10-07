/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.UI;

import java.awt.Graphics;

/**
 *
 * @author brendon
 */
public class UISquareTile extends UITile {
    private int height = 70;
    
    private int width = 70;
    
    public UISquareTile(java.awt.Point position, int width, int height) {
        super(position, width, height);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, getTileWidth(), getTileHeight());
        g.setColor(getBackgroundColor());
        g.fillRect(0, 0, getTileWidth(), getTileHeight());
        g.setColor(getTileText().length() > 1 ? getBackgroundColor() : getForegroundColor(getTileText()));
        g.setFont(new java.awt.Font("plain", 0, 24));
        g.drawString(getTileText(), (getTileWidth() / 2) - 8, (getTileHeight() / 2) + 5);
    }
}
