/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

/**
 *
 * @author brendon
 */
public class Tile {
    private String tileLabel;
    
    private boolean isTileInPlay = true;
    
    public Tile() {
        this.tileLabel = "";
    }
    
    /**
     * Sets the tile to selected and returns the tiles label.
     * @return The tiles label
     */
    public String selectTile() {
        this.isTileInPlay = false;
        return this.tileLabel;
    }
    
    /**
     * Sets the tile to be a mine.
     */
    public void setMineTile() {
        this.tileLabel = "M";
    }
    
    public void setTileAsFlagged() {
        this.tileLabel = "F";
        this.isTileInPlay = false;
    }
    
    public boolean isTileAvailableForSelection() {
        return this.isTileInPlay;
    }
}
