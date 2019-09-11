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
    private String tileLabel = "";
    
    private int tilePositionX;
    
    private int tilePositionY;
    
    private boolean isTileInPlay = true;
    
    public Tile(int tilePositionX, int tilePositionY) {
        this.tilePositionX = tilePositionX;
        this.tilePositionY = tilePositionY;
    }
   
    public int getTilePositionX() {
        return tilePositionX;
    }
    
    public int getTilePositionY() {
        return tilePositionY;
    }
    
    /**
     * Sets the tile to selected and returns the tiles label.
     * @return The tiles label
     */
    public String selectTile() {
        isTileInPlay = false;
        return tileLabel;
    }
    
    /**
     * Sets the tile to be a mine.
     */
    public void setMineTile() {
        tileLabel = "M";
    }
    
    public boolean isMineTile() {
        return tileLabel.equalsIgnoreCase("m");
    }
    
    public void setTileAsFlagged() {
        tileLabel = "F";
        isTileInPlay = false;
    }
    
    public boolean isTileAvailableForSelection() {
        return isTileInPlay;
    }
}
