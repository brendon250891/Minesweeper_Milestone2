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
    
    private Callback delegate;
    
    public Minesweeper(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        minefield = new Minefield(gameDifficulty);
    }
    
    public void setDelegate(Controller controller) {
        delegate = controller;
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
        //var tile = minefield.getTileFromMinefield(tilePositionX, tilePositionY);
        //checkTileNeighbours(tile);
        //return tile.selectTile();
        return "";
    }
    
    public void checkTileSelection(int tilePositionX, int tilePositionY) throws Exception {
        var tile = minefield.getTile(tilePositionX, tilePositionY);
        if (tile.isAMine()) {
            tile.disableTile();
            delegate.revealTile(tile);
            throw new Exception("Oh Dear, You have hit a mine!\nDo you wish to play again?");
        } else if (tile.getLabel().equals("") && tile.isAvailable()) {
            tile.disableTile();
            delegate.revealTile(tile);
            revealNeighborhood(tile);
        } else {
            tile.disableTile();
            delegate.revealTile(tile);
        }
    }
    
    private void revealNeighborhood(Tile tile) {
        for (int positionX = tile.getPositionX() - 1; positionX < tile.getPositionX() + 2; positionX++) {
            for (int positionY = tile.getPositionY() - 1; positionY < tile.getPositionY() + 2; positionY++) {
                if (positionX >=0 && positionX < gameDifficulty.width() && positionY >= 0 && positionY < gameDifficulty.height()) {
                    try {
                        checkTileSelection(positionX, positionY); 
                    } catch (Exception e) {}
                }
            }
        }
    }
}
