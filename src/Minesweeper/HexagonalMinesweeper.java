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
public class HexagonalMinesweeper extends Minesweeper {

    public HexagonalMinesweeper(GameDifficulty gameDifficulty) {
        super(gameDifficulty);
    }

    // TODO: Mode that goes from top left corner to bottom right corner.
    // constraints:
    //      - Top left corner is revealed.
    //      - can only select tiles that are joined to already revealed tiles.
    @Override
    protected void incrementAdjacentTileMineCounts(Tile tile) {
        System.out.println(1 % 2);
        for (int yPosition = tile.getPositionY() - 1; yPosition <= tile.getPositionY() + 1; yPosition++) {
            for (int xPosition = tile.getPositionX() - 1; xPosition <= tile.getPositionX() + 1; xPosition++) {
                if(yPosition >= 0 && yPosition < gameDifficulty.height() && xPosition >= 0 && xPosition < gameDifficulty.width()) {
                    if ((tile.getPositionX() % 2 == 0) && (yPosition == tile.getPositionY() + 1 && xPosition == tile.getPositionX() - 1)
                            || (yPosition == tile.getPositionY() + 1 && xPosition == tile.getPositionX() + 1)) {}
                    else if ((tile.getPositionX() % 2 == 1) && (yPosition == tile.getPositionY() - 1 && xPosition == tile.getPositionX() - 1)
                            || (yPosition == tile.getPositionY() - 1 && xPosition == tile.getPositionX() + 1)) {}
                    else {
                        minefield.getTile(yPosition, xPosition).incrementLabel();
                    }
                }
            }
        } 
    }// cant be x + 1, y - 1 and x + 1, y + 1;
    
    @Override
    public void checkTileSelection(int yPosition, int xPosition) throws Exception {
        for (int i = 0; i < gameDifficulty.height(); i++) {
            for (int j = 0; j < gameDifficulty.width(); j++) {
                delegate.revealTile(minefield.getTile(j, i));
            }
        }
    }

    @Override
    public void revealNeighborhood(Tile tile) {
        for (int yPosition = tile.getPositionY() - 1; yPosition <= tile.getPositionY() + 1; yPosition++) {
            for (int xPosition = tile.getPositionX() - 1; xPosition <= tile.getPositionX() + 1; xPosition++) {
                if ((yPosition != tile.getPositionY() - 1 && xPosition != tile.getPositionX() + 1) 
                        && (yPosition != tile.getPositionY() + 1 && xPosition != tile.getPositionX() + 1) 
                        && (yPosition >= 0 && yPosition < gameDifficulty.height() && xPosition >= 0 && xPosition < gameDifficulty.width())) {
                    try {
                        checkTileSelection(yPosition, xPosition);
                    } catch (Exception e) {}
                }
            }
        }
    }
}
