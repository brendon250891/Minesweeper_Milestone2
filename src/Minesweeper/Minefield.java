/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

/**
 * Class responsible for storing and retrieving tiles for a game of minesweeper.
 *
 * @author brendon
 */
public class Minefield {

    /**
     * Multi-dimensional array that stores the tiles.
     */
    private Tile[][] tiles = new Tile[0][0];

    /**
     * Default constructor.
     *
     * @param gameDifficulty - The difficulty of
     */
    public Minefield(GameDifficulty gameDifficulty) {
        tiles = new Tile[gameDifficulty.height()][gameDifficulty.width()];
    }
    
    /**
     * Get the size of the minefield
     * @return - The number of tiles in the minefield
     */
    public int getMinefieldSize() {
        return tiles.length * tiles[1].length;
    }

    /**
     * Get a tile from the minefield given its x and y position.
     *
     * @param xPosition - The position of the tile along the x axis.
     * @param yPosition - The position of the tile along the y axis.
     * @return The tile at the given position.
     */
    public Tile getTile(int yPosition, int xPosition) throws IndexOutOfBoundsException {
        if ((xPosition < 0 || xPosition > tiles[1].length) && (yPosition < 0 || yPosition > tiles.length)) {
            throw new IndexOutOfBoundsException(String.format("Invalid X and Y Positions Given: %s, %s", xPosition, yPosition));
        }
        if (xPosition < 0 || xPosition > tiles[1].length) {
            throw new IndexOutOfBoundsException("Invalid X Position Given: " + xPosition);
        }
        if (yPosition < 0 || yPosition > tiles.length) {
            throw new IndexOutOfBoundsException("Invalid Y Position Given: " + yPosition);
        }
        return tiles[yPosition][xPosition];
    }
    
    public void addTile(int yPosition, int xPosition) throws IndexOutOfBoundsException {
        if ((xPosition < 0 || xPosition > tiles[1].length) && (yPosition < 0 || yPosition > tiles.length)) {
            throw new IndexOutOfBoundsException(String.format("Invalid X and Y Positions Given: %s, %s", xPosition, yPosition));
        }
        if (xPosition < 0 || xPosition > tiles[1].length) {
            throw new IndexOutOfBoundsException("Invalid X Position Given: " + xPosition);
        }
        if (yPosition < 0 || yPosition > tiles.length) {
            throw new IndexOutOfBoundsException("Invalid Y Position Given: " + yPosition);
        }
        tiles[yPosition][xPosition] = new Tile(yPosition, xPosition);
    }
}
