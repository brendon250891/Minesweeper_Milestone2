/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import java.util.Random;

/**
 *
 * @author brendon
 */
public class HexagonalMinesweeper extends Minesweeper {

    /**
     * The selected game mode.
     */
    private GameMode gameMode = GameMode.NORMAL;
    
    /**
     * Constructor 
     * @param minefield - The minefield instance to use.
     * @param delegate - The delegate of the minesweeper class.
     * @param gameMode - The game mode to set.
     */
    public HexagonalMinesweeper(IMinefield minefield, Callback delegate, GameMode gameMode) {
        super(minefield, delegate);
        this.gameMode = gameMode;
    }
    
    /**
     * handles the selection of a tile from the minefield.
     * @param yPosition - The position of the tile along the y axis.
     * @param xPosition - The position of the tile along the x axis.
     */
    @Override
    public void selectTile(int yPosition, int xPosition) {
        try {
            if (gameMode == GameMode.CORNER_TO_CORNER && checkTileIsAdjacentToRevealedTile(minefield.getTile(yPosition, xPosition))) {
                super.selectTile(yPosition, xPosition);
            } else {
                super.selectTile(yPosition, xPosition);
            }
        } catch (Exception e) {
            delegate.promptUser(e.getMessage());
        }
    }
    
    /**
     * Based on the rules of corner-to-corner minesweeper, a play can only select a tile that is adjacent to an already revealed tile.
     * This function checks to see if the tile that was selected is a valid choice.
     * @param tile - The tile that was selected.
     * @return true if the tile is valid, false otherwise.
     */
    private boolean checkTileIsAdjacentToRevealedTile(ITile tile) {
        var availableCount = 0;
        for (int yPosition = tile.getPositionY() - 1; yPosition <= tile.getPositionY() + 1; yPosition++) {
            for (int xPosition = tile.getPositionX() - 1; xPosition <= tile.getPositionX() + 1; xPosition++) {
                if(yPosition >= 0 && yPosition < minefield.getHeight() && xPosition >= 0 && xPosition < minefield.getWidth()) {
                    var isOddRow = tile.getPositionX() % 2 == 1;
                    if (isOddRow && yPosition == tile.getPositionY() - 1 && xPosition == tile.getPositionX() - 1) {}
                    else if (isOddRow && yPosition == tile.getPositionY() + 1 && xPosition == tile.getPositionX() - 1) {}
                    else if (!isOddRow && yPosition == tile.getPositionY() + 1 && xPosition == tile.getPositionX() + 1) {}
                    else if (!isOddRow && yPosition == tile.getPositionY() - 1 && xPosition == tile.getPositionX() + 1) {}
                    else if (!minefield.getTile(yPosition, xPosition).isAvailable()){
                        availableCount++;
                    }
                }
            }
        }
        return availableCount > 0;
    }
    
    /**
     * Overrides the base method to check for the game mode selected to select the correct mines.
     * Based on the rules of corner-to-corner minesweeper, the top left and bottom right corner tiles cannot be mines.
     */
    @Override
    protected void randomlySelectMineTiles() {
        if (gameMode == GameMode.NORMAL) {
            super.randomlySelectMineTiles();
        } else {
            randomlySelectCornerToCornerMineTiles();
        }
    }
    
    /**
     * Randomly selects tiles to be mines for the corner-to-corner game mode(excludes top left and bottom right);
     */
    private void randomlySelectCornerToCornerMineTiles() {
        Random random = new Random();
        int mineTilesSet = 0;
        while (mineTilesSet < minefield.getMineCount()) {
            var randomX = random.nextInt(minefield.getHeight() - 1);
            var randomY = random.nextInt(minefield.getWidth() - 1);
            if ((randomX != 0 && randomY != 0) || (randomX != minefield.getHeight() - 1 && randomY != minefield.getWidth() - 1)) {
                var tile = minefield.getTile(randomY, randomX);
                if (!tile.isAMine()) {
                    tile.setToMine();
                    incrementAdjacentMineCount(tile);
                    mineTilesSet++;
                }
            }
        }
    }
    
    /**
     * Increments adjacent mine counts for the hexagonal version as there are only 6 possible neighbors. 
     * As the grid is offset odd rows have different neighbors to even rows.
     * @param tile - The mine tile.
     */
    @Override
    protected void incrementAdjacentMineCount(ITile tile) {
        for (int yPosition = tile.getPositionY() - 1; yPosition <= tile.getPositionY() + 1; yPosition++) {
            for (int xPosition = tile.getPositionX() - 1; xPosition <= tile.getPositionX() + 1; xPosition++) {
                if(yPosition >= 0 && yPosition < minefield.getHeight() && xPosition >= 0 && xPosition < minefield.getWidth()) {
                    var isOddRow = tile.getPositionY() % 2 == 1;
                    if (isOddRow && yPosition == tile.getPositionY() - 1 && xPosition == tile.getPositionX() - 1) {}
                    else if (isOddRow && yPosition == tile.getPositionY() + 1 && xPosition == tile.getPositionX() - 1) {}
                    else if (!isOddRow && yPosition == tile.getPositionY() + 1 && xPosition == tile.getPositionX() + 1) {}
                    else if (!isOddRow && yPosition == tile.getPositionY() - 1 && xPosition == tile.getPositionX() + 1) {}
                    else {
                        minefield.getTile(yPosition, xPosition).incrementAdjacentMineCount();
                    }
                }
            }
        } 
    }
}
