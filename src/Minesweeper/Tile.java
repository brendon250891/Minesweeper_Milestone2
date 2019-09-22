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
public class Tile implements ITile {
    /**
     * The x and y position of the tile.
     */
    private Point position;
    
    /**
     * The label of the tile.
     */
    private String tileLabel = "";
    
    /**
     * Flag indicating whether the tile is available for selection.
     */
    private boolean isAvailable = true;
    
    /**
     * Flag indicating whether the tile is flagged.
     */
    private boolean isFlagged = false;
    
    /**
     * Constructor
     * @param yPosition - The position of the tile along the y axis.
     * @param xPosition - The position of the tile along the x axis.
     */
    public Tile(int yPosition, int xPosition) {
        position = new Point(xPosition, yPosition);
    }
   
    /**
     * Gets the x axis position of the tile.
     * @return The tiles position along the x axis.
     */
    @Override
    public int getPositionX() {
        return position.x;
    }
    
    /**
     * Gets the y axis position of the tile.
     * @return The tiles position along the y axis.
     */
    @Override
    public int getPositionY() {
        return position.y;
    }
    
    /**
     * Gets the label of the tile.
     * @return The tiles label
     */
    @Override
    public String getLabel() {
        return tileLabel;
    }
    
    /**
     * Disables the tile once it has been selected.
     */
    @Override
    public void disableTile() {
        isAvailable = false;
    }
    
    /**
     * If the tiles label is empty then it is set to one.
     * Otherwise the label is incremented by one.
     */
    @Override
    public void incrementAdjacentMineCount() {
        if (tileLabel.equals("")) {
            tileLabel = "1";
        } else if (!tileLabel.equalsIgnoreCase("m")){
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
    @Override
    public void setToMine() {
        tileLabel = "M";
    }
    
    /**
     * Checks if the tile is a mine.
     * @return true if the tile is a mine.
     */
    @Override
    public boolean isAMine() {
        return tileLabel.equalsIgnoreCase("m");
    }
    
    /**
     * Flags the tile.
     */
    public void flagTile() {
        isFlagged = !isFlagged;
    }
    
    /**
     * Checks if the tile is flagged.
     * @return true if the tile is flagged.
     */
    @Override
    public boolean isFlagged() {
        return isFlagged;
    }
    
    /**
     * Checks if the tile is available.
     * @return true if the tile is available.
     */
    @Override
    public boolean isAvailable() {
        return isAvailable;
    }
}
