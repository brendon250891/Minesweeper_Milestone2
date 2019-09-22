/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

/**
 * Interface that is used as a delegate.
 * @author brendon
 */
public interface Callback {
    /**
     * Tells the controller to update the view by revealing the passed tile.
     * @param tile - The tile to reveal in the minefield of the view.
     */
    public void revealTile(ITile tile);
    
    /**
     * Prompts the player with a message.
     * @param message - The message to display to the player.
     */
    public void promptUser(String message);
}
