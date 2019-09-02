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
public enum GameDifficulty {
    BEGINNER(9, 9, 10),
    INTERMEDIATE(16, 16, 40),
    EXPERT(16, 30, 99);
    
    private final int mineCount;
    private final int width;
    private final int height;
    
    GameDifficulty(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
    }
    
    public int width() {
        return this.width;
    }
    
    public int height() {
        return this.height;
    }
    
    public int mineCount() {
        return this.mineCount;
    }  
}
