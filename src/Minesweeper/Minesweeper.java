/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

/**
 * Class that handles the logic for a normal game of minesweeper
 * @author brendon
 */
public class Minesweeper {
    private final Minefield minefield;
    
    private final GameDifficulty gameDifficulty;
    
    public Minesweeper(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        minefield = new Minefield(gameDifficulty);
    }
    
    public void startGame() {
        minefield.generateMinefield();
    }
    
    /**
     * Randomly set the mine tiles for the game.
     * @param tilePositionX - The tiles X position.
     * @param tilePositionY - The tiles Y position.
     * @return  1 if the game is won, 0 if the game is lost and -1 if no result.
     */
    public String checkForAResult(int tilePositionX, int tilePositionY) {
        System.out.println(String.format("selected x: %s, selected y: %s", tilePositionX, tilePositionY));
        var tile = minefield.getTileFromMinefield(tilePositionX, tilePositionY);
        //checkTileNeighbours(tile);
        return tile.selectTile();
    }
    
    private void checkTileNeighbours(Tile tile) {
        for (int positionX = tile.getTilePositionX() - 1; positionX < tile.getTilePositionX() + 2; positionX++) {
            for (int positionY = tile.getTilePositionY() - 1; positionY < tile.getTilePositionY() + 2; positionY++) {
                if (tile.getTilePositionX() >= 0 && tile.getTilePositionX() < gameDifficulty.width()
                    && tile.getTilePositionY() >= 0 && tile.getTilePositionY() < gameDifficulty.height()) {
                }
            }
        }
    }
}
