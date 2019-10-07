/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
     * Flag to indicate if the tile is the first to be selected.
     */
    protected boolean firstSelection = true;
    
    protected Timer timer;
    
    /**
     * The delegate for the minesweeper class.
     */
    protected Callback delegate;
    
    /**
     * Constructor
     * @param minefield - The minefield instance to use.
     * @param delegate - The delegate of the minesweeper class.
     */
    public Minesweeper(IMinefield minefield, Callback delegate, Timer timer) {
        this.minefield = minefield;
        this.delegate = delegate;
        this.timer = timer;
    }
    
    @Override
    public void startGame() {
        randomlySelectMineTiles();
    }
    
    /**
     * Handles the selection of a tile.
     * @param yPosition - The position of the tile along the y axis.
     * @param xPosition - The position of the tile along the x axis.
     * @throws Exception If the tile selected is a mine an exception is thrown.
     */
    @Override
    public void selectTile(int yPosition, int xPosition) throws Exception {
        if (firstSelection) {
            firstSelection = false;
            startTimer();
        }
        ITile tile = minefield.getTile(yPosition, xPosition);
        if (!tile.isFlagged()) {
            if (tile.isAvailable() && tile.isAMine()) {
                delegate.revealTile(tile);
                stopTimer();
                throw new Exception("Oh Dear, You have hit a mine!\nDo you wish to play again?");
            } else if (tile.isAvailable()) {
                revealTile(tile);
            }
        }
    }
    
    @Override
    public void flagTile(int yPosition, int xPosition) {
        ITile tile = minefield.getTile(yPosition, xPosition);
        if (tile.isAvailable()) {
            tile.flagTile();
            delegate.flagTile(tile);    
        }
    }
    
    @Override
    public void stopTimer() {
        timer.cancel();
    }
    
    /**
     * When a tile is not adjacent to any mines, recursively reveal all other tiles non-adjacent to mines.
     * @param tile - The tile not adjacent to any mines.
     */
    protected void revealTile(ITile tile) {
        if (!tile.isFlagged() && !tile.getLabel().equals("") && !tile.isAMine()) {
            tile.disableTile();
            delegate.revealTile(tile);
        } else if (tile.getLabel().equals("") && tile.isAvailable() && !tile.isFlagged()) {
            for (int yPosition = tile.getPositionY() - 1; yPosition <= tile.getPositionY() + 1; yPosition++) {
                for (int xPosition = tile.getPositionX() - 1; xPosition <= tile.getPositionX() + 1; xPosition++) {
                    if (yPosition >= 0 && yPosition < minefield.getHeight() && xPosition >= 0 && xPosition < minefield.getWidth()) {
                        tile.disableTile();
                        delegate.revealTile(tile);
                        revealTile(minefield.getTile(yPosition, xPosition));
                    }
                }
            }
        }
        checkForWin();
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
            ITile tile = minefield.getTile(randomYPosition, randomXPosition);
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
    
    protected void checkForWin() {
        int tileCount = 0;
        for(int yPosition = 0; yPosition < minefield.getHeight(); yPosition++) {
            for (int xPosition = 0; xPosition < minefield.getWidth(); xPosition++) {
                ITile tile = minefield.getTile(yPosition, xPosition);
                if (tile.isAvailable()) {
                    tileCount++;
                }
            }
        }
        if (tileCount == minefield.getMineCount()) {
            stopTimer();
            delegate.promptUser("Congratulations! You have won!\nPlay again");
        }
    }
    
    protected void startTimer() {
        new Thread(new Runnable() {
            int time = 0;
            
            @Override
            public void run() {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        time++;
                        delegate.updateScore(String.format("%s", time));
                    }
                }, 0, 1000);
            }
        }).start();
    }
}
