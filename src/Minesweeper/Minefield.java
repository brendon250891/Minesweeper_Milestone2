/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import java.util.Random;

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
    
    private int mineCount = 0;

    /**
     * Default constructor.
     *
     * @param gameDifficulty - The difficulty of
     */
    public Minefield(GameDifficulty gameDifficulty) {
        tiles = new Tile[gameDifficulty.width()][gameDifficulty.height()];
        mineCount = gameDifficulty.mineCount();
    }
    
    /**
     * Get the size of the minefield
     * @return - The number of tiles in the minefield
     */
    public int getMinefieldSize() {
        return tiles.length * tiles[1].length;
    }

    /**
     * Instantiate the minefield tiles
     */
    public void generateMinefield() {
        for (int tilePositionX = 0; tilePositionX < tiles.length; tilePositionX++) {
            for (int tilePositionY = 0; tilePositionY < tiles[1].length; tilePositionY++) {
                tiles[tilePositionX][tilePositionY] = new Tile(tilePositionX, tilePositionY);
            }
        }
        randomlySelectMineTiles();
    }
    
    /**
     * Randomly allocates mine tiles to the minefield.
     */
    private void randomlySelectMineTiles() {
        Random random = new Random();
        int mineTilesSet = 0;
        while (mineTilesSet < mineCount) {
            var tile = getTile(random.nextInt(tiles.length - 1), random.nextInt(tiles[1].length - 1));
            if (!tile.isAMine()) {
                tile.setToMine();
                incrementAdjacentTileMineCounts(tile);
                mineTilesSet++;
            }
        }
    }
    
    private void incrementAdjacentTileMineCounts(Tile tile) {
        for (int positionX = tile.getPositionX() - 1; positionX < tile.getPositionX() + 2; positionX++) {
            for (int positionY = tile.getPositionY() - 1; positionY < tile.getPositionY() + 2; positionY++) {
                if (positionX >= 0 && positionX < tiles.length && positionY >= 0 && positionY < tiles[1].length) {
                    getTile(positionX, positionY).incrementLabel();
                }
            }
        }
    }

    /**
     * Get a tile from the minefield given its x and y position.
     *
     * @param tilePositionX - The position of the tile along the x axis.
     * @param tilePositionY - The position of the tile along the y axis.
     * @return The tile at the given position.
     */
    public Tile getTile(int tilePositionX, int tilePositionY) throws IndexOutOfBoundsException {
        if ((tilePositionX < 0 || tilePositionX >= tiles.length) && (tilePositionY < 0 || tilePositionY >= tiles[1].length)) {
            throw new IndexOutOfBoundsException(String.format("Invalid X and Y Positions Given: %s, %s", tilePositionX, tilePositionY));
        }
        if (tilePositionX < 0 || tilePositionX >= tiles.length) {
            throw new IndexOutOfBoundsException("Invalid X Position Given: " + tilePositionX);
        }
        if (tilePositionY < 0 || tilePositionY >= tiles[1].length) {
            throw new IndexOutOfBoundsException("Invalid Y Position Given: " + tilePositionY);
        }
        return tiles[tilePositionX][tilePositionY];
    }
}
