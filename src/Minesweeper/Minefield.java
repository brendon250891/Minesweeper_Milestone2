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
    
    private final GameDifficulty gameDifficulty;

    /**
     * Default constructor.
     *
     * @param gameDifficulty - The difficulty of
     */
    public Minefield(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        generateTiles();
    }
    
    /**
     * Get a tile from the minefield given its x and y position.
     *
     * @param xPosition - The position of the tile along the x axis.
     * @param yPosition - The position of the tile along the y axis.
     * @return The {@code Tile} at the given position.
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
        return (Tile)tiles[yPosition][xPosition];
    }
    
    public int getWidth() {
        return gameDifficulty.width();
    }
    
    public int getHeight() {
        return gameDifficulty.height();
    }
    
    public int getMineCount() {
        return gameDifficulty.mineCount();
    }
    
    /**
     * Get the size of the minefield
     * @return - The number of tiles in the minefield
     */
    public int getMinefieldSize() {
        return tiles.length * tiles[1].length;
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
    
    private void generateTiles() {
        tiles = new Tile[getHeight()][getWidth()];
        for (int yPosition = 0; yPosition < getHeight(); yPosition++) {
            for (int xPosition = 0; xPosition < getWidth(); xPosition++) {
                tiles[yPosition][xPosition] = TileFactory.getTile(yPosition, xPosition);
            }
        }
    }
}
