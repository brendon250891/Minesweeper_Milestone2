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
    
    private int[][] tiles;
    
    private Minefield() {
        this.gameDifficulty = GameDifficulty.BEGINNER;
        this.tiles = new int[this.gameDifficulty.width()][this.gameDifficulty.height()];
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
        this.tiles = new int[this.gameDifficulty.width()][this.gameDifficulty.height()];
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
}
