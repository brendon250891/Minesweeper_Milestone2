/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.UI;

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;

/**
 *
 * @author brendon
 */
public class UIColorTile extends UISquareTile {
    
    private Point centerPoint = new Point();
    
    public UIColorTile(Point position, int width, int height) {
        super(position, width, height);
    }
    
    @Override
    public String toString() {
        return String.format("The position of this tile is: %s\nCenter: %s", this.getLocation(), centerPoint);
    }
    
    public void setCenterPoint() {
        centerPoint = new Point(this.getLocation().x + (getTileWidth() / 2), this.getLocation().y + (getTileHeight() / 2));
    }    
    
    public Point getCenterPoint() {
        return this.centerPoint;
    }
    
    public void setBackgroundColor(Color color) {
        this.setBackground(color);
    }
}
