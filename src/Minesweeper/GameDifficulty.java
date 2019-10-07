/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

/**
 * Enum containing all the information related to a minesweeper games difficulty.
 * @author brendon
 */
public enum GameDifficulty {
    COLOR(3, 3, 1),
    BEGINNER(9, 9, 10),
    INTERMEDIATE(16, 16, 40),
    EXPERT(16, 30, 99);
    
    /**
     * Mine count of the difficulty
     */
    private final int mineCount;
    
    /**
     * Width of the minefield for the difficulty.
     */
    private final int width;
    
    /**
     * Height of the minefield for the difficulty.
     */
    private final int height;
    
    /**
     * Constructor
     * @param width - Width of the minefield for the difficulty.
     * @param height - Height of the minefield for the difficulty.
     * @param mineCount - Mine count for the difficulty.
     */
    GameDifficulty(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
    }
    
    /**
     * Gets the width of the minefield.
     * @return The minefield's width
     */
    public int width() {
        return this.width;
    }
    
    /**
     * Gets the height of the minefield.
     * @return The minefield's height
     */
    public int height() {
        return this.height;
    }
    
    /**
     * Gets the minefields mine count.
     * @return The mine count of the minefield.
     */
    public int mineCount() {
        return this.mineCount;
    }  
}
