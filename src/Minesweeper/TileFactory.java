/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

/**
 * Factory class that creates new instances of Tiles.
 * @author brendon
 */
public class TileFactory {
    /**
     * Makes a new instance of a Tile.
     * @param yPosition - The position of the tile along the y axis.
     * @param xPosition - The position of the tile along the x axis.
     * @return The created tile object.
     */
    public static Tile makeTile(int yPosition, int xPosition) {
        return new Tile(yPosition, xPosition);
    }
}
