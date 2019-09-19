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

    private GameMode gameMode = GameMode.NORMAL;
    
    public HexagonalMinesweeper(IMinefield minefield, GameMode gameMode) {
        super(minefield);
        this.gameMode = gameMode;
    }
    
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
                    incrementAdjacentTileMineCounts(tile);
                    mineTilesSet++;
                }
            }
        }
    }
    
    /**
     * Increments adjacent mine counts for the hexagonal version as there are only 6 possible neighbors. 
     * Also as odd rows are offset they have different neighbors to even rows.
     * @param tile - The mine tile.
     */
    @Override
    protected void incrementAdjacentTileMineCounts(Tile tile) {
        for (int yPosition = tile.getPositionY() - 1; yPosition <= tile.getPositionY() + 1; yPosition++) {
            for (int xPosition = tile.getPositionX() - 1; xPosition <= tile.getPositionX() + 1; xPosition++) {
                if(yPosition >= 0 && yPosition < minefield.getHeight() && xPosition >= 0 && xPosition < minefield.getWidth()) {
                    var isOddRow = tile.getPositionX() % 2 == 1;
                    if (isOddRow && (yPosition == tile.getPositionY() - 1 && xPosition == tile.getPositionX() - 1)
                            || (yPosition == tile.getPositionY() - 1 && xPosition == tile.getPositionX() + 1)) {}
                    else if (!isOddRow && (yPosition == tile.getPositionY() + 1 && xPosition == tile.getPositionX() + 1)
                            || (yPosition == tile.getPositionY() - 1 && xPosition == tile.getPositionX() + 1)) {}
                    else {
                        minefield.getTile(yPosition, xPosition).incrementLabel();
                    }
                }
            }
        } 
    }
}
