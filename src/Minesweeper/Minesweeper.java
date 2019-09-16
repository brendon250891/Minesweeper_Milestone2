/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import java.util.Random;

/**
 * Class that handles the logic for a normal game of minesweeper
 * @author brendon
 */
public class Minesweeper {
    protected final Minefield minefield;
    
    protected final GameDifficulty gameDifficulty;
    
    private final int EDGES = 8;
    
    protected Callback delegate;
    
    public Minesweeper(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        minefield = new Minefield(gameDifficulty);
    }
    
    /**
     * Assigns a delegate to the class that handles revealing tiles on the UI.
     * @param controller - The class that implements the interface.
     */
    public void setDelegate(Controller controller) {
        delegate = controller;
    }
    
    /**
     * Initiates the start of a game.
     */
    public void startGame() {
        generateMinefield();
    }
    
    /**
     * 
     * @param yPosition
     * @param xPosition
     * @throws Exception 
     */
    public void checkTileSelection(int yPosition, int xPosition) throws Exception {
        var tile = minefield.getTile(yPosition, xPosition);
        if (tile.isAMine()) {
            tile.disableTile();
            delegate.revealTile(tile);
            throw new Exception("Oh Dear, You have hit a mine!\nDo you wish to play again?");
        } else if (tile.getLabel().equals("") && tile.isAvailable()) {
            tile.disableTile();
            delegate.revealTile(tile);
            revealNeighborhood(tile);
        } else if (!tile.isAMine()) {
            tile.disableTile();
            delegate.revealTile(tile);
        }
    }
    
    /**
     * When a tile is not adjacent to any mines, recursively reveal all other tiles non-adjacent to mines.
     * @param tile - The tile not adjacent to any mines.
     */
    protected void revealNeighborhood(Tile tile) {
        System.out.println("Hit minesweeper reveal");
        for (int yPosition = tile.getPositionY() - 1; yPosition <= tile.getPositionY() + 1; yPosition++) {
            for (int xPosition = tile.getPositionX() - 1; xPosition <= tile.getPositionX() + 1; xPosition++) {
                if (yPosition >=0 && yPosition < gameDifficulty.height() && xPosition >= 0 && xPosition < gameDifficulty.width()) {
                    try {
                        checkTileSelection(yPosition, xPosition); 
                    } catch (Exception e) {}
                }
            }
        }
    }
    
    /**
     * Generates the minefield, based on set difficulty.
     */
    private void generateMinefield() {
        for (int yPosition = 0; yPosition < gameDifficulty.height(); yPosition++) {
            for (int xPosition = 0; xPosition < gameDifficulty.width(); xPosition++) {
                minefield.addTile(yPosition, xPosition);
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
        while (mineTilesSet < gameDifficulty.mineCount()) {
            var tile = minefield.getTile(random.nextInt(gameDifficulty.height() - 1), random.nextInt(gameDifficulty.width() - 1));
            if (!tile.isAMine()) {
                tile.setToMine();
                incrementAdjacentTileMineCounts(tile);
                mineTilesSet++;
            }
        }
    }
    
    /**
     * When a mine is set, increments all adjacent tile labels by 1.
     * @param tile - The tile that was set as a mine.
     */
    protected void incrementAdjacentTileMineCounts(Tile tile) {
        for (int yPosition = tile.getPositionY() - 1; yPosition <= tile.getPositionY() + 1; yPosition++) {
            for (int xPosition = tile.getPositionX() - 1; xPosition <= tile.getPositionX() + 1; xPosition++) {
                if (yPosition >= 0 && yPosition < gameDifficulty.height() && xPosition >= 0 && xPosition < gameDifficulty.width()) {
                    minefield.getTile(yPosition, xPosition).incrementLabel();
                }
            }
        }
    }
}
