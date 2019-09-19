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
public class Minesweeper implements IMinesweeper {
    protected final IMinefield minefield;
    
    protected Callback delegate;
    
    public Minesweeper(IMinefield minefield) {
        this.minefield = minefield;
    }
    
    public void selectTile(int yPosition, int xPosition) {
        try {
            checkTileSelection(minefield.getTile(yPosition, xPosition));
        } catch (Exception e) {
            
        }
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
    
    public void flagSelectedTile(int yPosition, int xPosition) {
        var tile = minefield.getTile(yPosition, xPosition);
        tile.flagTile();
        delegate.revealTile(tile);
    }
    
    /**
     * 
     * @param tile
     * @throws Exception 
     */
    public void checkTileSelection(Tile tile) throws Exception {
        if (tile.isAMine()) {
            tile.disableTile();
            delegate.revealTile(tile);
            throw new Exception("Oh Dear, You have hit a mine!\nDo you wish to play again?");
        } else {
            revealNeighborhood(tile);
        }
    }
    
    /**
     * When a tile is not adjacent to any mines, recursively reveal all other tiles non-adjacent to mines.
     * @param tile - The tile not adjacent to any mines.
     */
    protected void revealNeighborhood(Tile tile) {
        if (!tile.getLabel().equals("") && !tile.isAMine()) {
            tile.disableTile();
            delegate.revealTile(tile);
        } else if (tile.getLabel().equals("") && tile.isAvailable()) {
            for (int yPosition = tile.getPositionY() - 1; yPosition <= tile.getPositionY() + 1; yPosition++) {
                for (int xPosition = tile.getPositionX() - 1; xPosition <= tile.getPositionX() + 1; xPosition++) {
                    if (yPosition >=0 && yPosition < minefield.getHeight() && xPosition >= 0 && xPosition < minefield.getWidth()) {
                        tile.disableTile();
                        delegate.revealTile(tile);
                        revealNeighborhood(minefield.getTile(yPosition, xPosition));
                    }
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
    protected void randomlySelectMineTiles() {
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
