/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import java.awt.Point;

/**
 *
 * @author brendon
 */
public class Tile {
    private Point position;
    
    private String tileLabel = "";
    
    private boolean isAvailable = true;
    
    private boolean isFlagged = false;
    
    public Tile(int tilePositionX, int tilePositionY) {
        position = new Point(tilePositionX, tilePositionY);
    }
   
    public int getPositionX() {
        return position.x;
    }
    
    public int getPositionY() {
        return position.y;
    }
    
    /**
     * Sets the tile to selected and returns the tiles label.
     * @return The tiles label
     */
    public String revealTile() {
        isAvailable = false;
        return tileLabel;
    }
    
    public String getLabel() {
        return tileLabel;
    }
    
    public void disableTile() {
        isAvailable = false;
    }
    
    public void incrementLabel() {
        if (tileLabel.equals("")) {
            tileLabel = "1";
        } else {
            try {
                int labelAsInteger = Integer.parseInt(tileLabel);
                tileLabel = Integer.toString(++labelAsInteger);
            } catch (Exception e) {
                
            }
        }
    }
    
    /**
     * Sets the tile to be a mine.
     */
    public void setToMine() {
        tileLabel = "M";
    }
    
    public boolean isAMine() {
        return tileLabel.equalsIgnoreCase("m");
    }
    
    public void flagTile() {
        isFlagged = !isFlagged;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
}
