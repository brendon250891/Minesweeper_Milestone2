/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.Model;

/**
 * Interface that exposes methods of classes that implement this interface.
 * @author brendon
 */
public interface IMinesweeper {
    void startGame();
    void selectTile(int yPosition, int xPosition) throws Exception;
    void flagTile(int yPosition, int xPosition);
    void stopTimer();
}
