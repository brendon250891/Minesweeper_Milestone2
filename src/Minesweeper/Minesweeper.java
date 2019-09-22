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
    /**
     * The minefield that the minesweeper game uses.
     */
    protected final IMinefield minefield;
    
    /**
     * The delegate for the minesweeper class.
     */
    public Callback delegate;
    
    /**
     * Constructor
     * @param minefield - The minefield instance to use.
     * @param delegate - The delegate of the minesweeper class.
     */
    public Minesweeper(IMinefield minefield, Callback delegate) {
        this.minefield = minefield;
        this.delegate = delegate;
        generateMinefield();
    }
    
    /**
     * Handles the selection of a tile.
     * @param yPosition - The position of the tile along the y axis.
     * @param xPosition - The position of the tile along the x axis.
     * @throws Exception If the tile selected is a mine an exception is thrown.
     */
    @Override
    public void selectTile(int yPosition, int xPosition) throws Exception {
        var tile = minefield.getTile(yPosition, xPosition);
        if (tile.isAvailable() && tile.isAMine()) {
            delegate.revealTile(tile);
            throw new Exception("Oh Dear, You have hit a mine!\nDo you wish to play again?");
        } else if (tile.isAvailable()) {
            revealTile(tile);
        }
    }
   
    /**
     * Flags the selected tile making it unable to be revealable.
     * @param yPosition - The position of the tile along the y axis.
     * @param xPosition - The position of the tile along the x axis.
     */
    public void flagSelectedTile(int yPosition, int xPosition) {
        var tile = minefield.getTile(yPosition, xPosition);
        //tile.flagTile();
        delegate.revealTile(tile);
    }
    
    /**
     * When a tile is not adjacent to any mines, recursively reveal all other tiles non-adjacent to mines.
     * @param tile - The tile not adjacent to any mines.
     */
    protected void revealTile(ITile tile) {
        if (!tile.getLabel().equals("") && !tile.isAMine()) {
            tile.disableTile();
            delegate.revealTile(tile);
        } else if (tile.getLabel().equals("") && tile.isAvailable()) {
            for (int yPosition = tile.getPositionY() - 1; yPosition <= tile.getPositionY() + 1; yPosition++) {
                for (int xPosition = tile.getPositionX() - 1; xPosition <= tile.getPositionX() + 1; xPosition++) {
                    if (yPosition >=0 && yPosition < minefield.getHeight() && xPosition >= 0 && xPosition < minefield.getWidth()) {
                        tile.disableTile();
                        delegate.revealTile(tile);
                        revealTile(minefield.getTile(yPosition, xPosition));
                    }
                }
            }
        }
    }
    
    /**
     * Intialises the minefield with tiles.
     */
    private void generateMinefield() {
        for (int yPosition = 0; yPosition < minefield.getHeight(); yPosition++) {
            for (int xPosition = 0; xPosition < minefield.getWidth(); xPosition++) {
                minefield.addTile(yPosition, xPosition);
            }
        }
        randomlySelectMineTiles();
    }
    
    /**
     * Randomly selects the tiles that are to be mines.
     */
    protected void randomlySelectMineTiles() {
        Random rnd = new Random();
        int minesAdded = 0;
        while (minesAdded < minefield.getMineCount()) {
            int randomXPosition = rnd.nextInt(minefield.getWidth() - 1);
            int randomYPosition = rnd.nextInt(minefield.getHeight() - 1);
            var tile = minefield.getTile(randomYPosition, randomXPosition);
            if (!tile.isAMine()) {
                minesAdded++;
                tile.setToMine();
                incrementAdjacentMineCount(tile);
            }
        }
    }
    
    /**
     * When a tile is selected to be a mine its adjacent tiles mine counts are incremented.
     * @param tile - The tile that is a mine.
     */
    protected void incrementAdjacentMineCount(ITile tile) {
        for (int yPosition = tile.getPositionY() - 1; yPosition <= tile.getPositionY() + 1; yPosition++) {
            for (int xPosition = tile.getPositionX() - 1; xPosition <= tile.getPositionX() + 1; xPosition++) {
                if (yPosition >= 0 && yPosition < minefield.getHeight() && xPosition >= 0 && xPosition < minefield.getWidth()) {
                    minefield.getTile(yPosition, xPosition).incrementAdjacentMineCount(); 
                }
            }
        }
    }
}
