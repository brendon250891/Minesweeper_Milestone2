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
public class Minefield {
    private static Minefield minefield;
    
    private GameDifficulty gameDifficulty;
    
    private Tile[][] tiles;
    
    private boolean isAGameRunning = false;
    
    private Minefield() {
        this.gameDifficulty = GameDifficulty.BEGINNER;
        this.tiles = new Tile[this.gameDifficulty.width()][this.gameDifficulty.height()];
    }
    
    /**
     * Asks the class to return it's singleton instance if one exists.
     * Creates one otherwise
     * @return A singleton instance of the Minefield class.
     */
    public static Minefield getInstance() {
        if (minefield == null) {
            minefield = new Minefield();
        }
        return minefield;
    }
    
    /**
     * Sets the current game difficulty to the selection made by the user.
     * @param gameDifficulty - The difficulty to set.
     */
    public void setGameDifficulty(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        this.tiles = new Tile[this.gameDifficulty.width()][this.gameDifficulty.height()];
    }
    
    /**
     * Ask the minefield for its current game difficulty setting.
     * @return The current game difficulty setting.
     */
    public GameDifficulty getCurrentGameDifficulty() {
        return this.gameDifficulty;
    }
    /**
     * Gets the number of mines that the minefield contains.
     * @return The number of mines.
     */
    public int getMinefieldMineCount() {
        return this.gameDifficulty.mineCount();
    }
    
    /**
     * Gets how many tiles wide the minefield currently is.
     * @return The width of the minefield.
     */
    public int getMinefieldWidth() {
        return this.gameDifficulty.width();
    }
    
    /**
     * Gets how many tiles high the minefield currently is.
     * @return The height of the minefield.
     */
    public int getMinefieldHeight() {
        return this.gameDifficulty.height();
    }        
    
    public int getMinefieldTileCount() {
        return tiles.length * tiles[1].length;
    }
    
    public void startAGame() {
        this.isAGameRunning = true;
        generateMinefieldWithTiles();
    }
    
    public boolean isAGameRunning() {
        return this.isAGameRunning;
    }
    
    public boolean isMinefieldGeneratedCorrectForSelectedDifficulty() {
        return this.checkMinefieldForIntegrity();
    }
    
    public Tile getTile(int tilePositionX, int tilePositionY) {
        return this.tiles[tilePositionX][tilePositionY];
    }
    
    public void selectTile(Tile tile) {
        tile.selectTile();
    }
    
    private boolean checkMinefieldForIntegrity() {
        int tileCount = 0;
        for (int minefieldPositionX = 0; minefieldPositionX < minefield.getMinefieldWidth(); minefieldPositionX++) {
            for (int minefieldPositionY = 0; minefieldPositionY < minefield.getMinefieldHeight(); minefieldPositionY++) {
                if (this.tiles[minefieldPositionX][minefieldPositionY] == null) {
                    return false;
                }
                tileCount++;
            }
        }
        return tileCount == minefield.getMinefieldWidth() * minefield.getMinefieldHeight();
    }
    
    private void generateMinefieldWithTiles() {
        for (int minefieldPositionX = 0; minefieldPositionX < minefield.getMinefieldWidth(); minefieldPositionX++) {
            for (int minefieldPositionY = 0; minefieldPositionY < minefield.getMinefieldHeight(); minefieldPositionY++) {
                this.tiles[minefieldPositionX][minefieldPositionY] = new Tile();
            }
        }
    }
}
