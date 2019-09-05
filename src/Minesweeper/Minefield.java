/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

/**
 * Class responsible for storing and retrieving tiles that it contains. 
 * @author brendon
 */
public class Minefield {
    /**
     * Singleton instance of the class.
     */
    private static Minefield minefield = null;
    
    /**
     * Multi-dimensional array that stores the tiles.
     */
    private Tile[][] tiles = new Tile[0][0];
    
    /**
     * Default private constructor.
     */
    private Minefield() {}
    
    /**
     * Asks the class to return it's singleton instance. 
     * Creates an instance if it's the first run.
     * @return A singleton instance of the Minefield class.
     */
    public static Minefield getInstance() {
        if (minefield == null) {
            minefield = new Minefield();
        }
        return minefield;
    }
    
    /**
     * Sets the size of the minefield to be played.
     * @param tilesWidth - The tile width of the field.
     * @param tilesHeight - The tile height of the field.
     */
    public void setTilesSize(int tilesWidth, int tilesHeight) {
        tiles = new Tile[tilesWidth][tilesHeight];
    }
    
    public int instantiateMinefieldWithTiles() {
        return this.generateMinefieldWithTiles();
    }
    
    /**
     * Gets a tile from the minefield given its x and y position.
     * @param tilePositionX - The position of the tile along the x axis.
     * @param tilePositionY - The position of the tile along the y axis.
     * @return The tile at the given position.
     */
    public Tile getTileFromMinefield(int tilePositionX, int tilePositionY) {
        return tiles[tilePositionX][tilePositionY];
    }
    
    /**
     * Generates the minefield with tiles based on the set difficulty.
     */
    private int generateMinefieldWithTiles() {
        int tileCount = 0;
        for (Tile[] tile : tiles) {
            for (Tile t : tile) {
                t = new Tile();
                tileCount++;
            }
        }
        return tileCount;
    }
}
