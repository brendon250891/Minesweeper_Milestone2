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
public class Minefield implements IMinefield {

    /**
     * Multi-dimensional array that stores the tiles.
     */
    private ITile[][] tiles;
    
    /**
     * The difficulty of the minefield.
     */
    private final GameDifficulty gameDifficulty;

    /**
     * Default constructor.
     *
     * @param gameDifficulty - The difficulty of
     */
    public Minefield(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        tiles = new Tile[gameDifficulty.height()][gameDifficulty.width()];
    }
    
    /**
     * Adds a tile given it's x and y positions using the TileFactory class.
     * @param yPosition - The position of the tile along the y axis.
     * @param xPosition - The position of the tile along the x axis.
     */
    @Override
    public void addTile(int yPosition, int xPosition) {
        tiles[yPosition][xPosition] = TileFactory.makeTile(yPosition, xPosition);
    }
    
    /**
     * Get a tile from the minefield given its x and y position.
     *
     * @param xPosition - The position of the tile along the x axis.
     * @param yPosition - The position of the tile along the y axis.
     * @return The {@code Tile} at the given position.
     */
    @Override
    public ITile getTile(int yPosition, int xPosition) throws IndexOutOfBoundsException {
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
    
    /**
     * Gets the width of the minefield.
     * @return The width of the minefield.
     */
    @Override
    public int getWidth() {
        return gameDifficulty.width();
    }
    
    /**
     * Gets the height of the minefield.
     * @return The height of the minefield.
     */
    @Override
    public int getHeight() {
        return gameDifficulty.height();
    }
    
    /**
     * Gets the number of mines in the minefield.
     * @return The number of mines in the minefield.
     */
    @Override
    public int getMineCount() {
        return gameDifficulty.mineCount();
    }
}
