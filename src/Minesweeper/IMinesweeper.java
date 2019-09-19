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
public interface IMinesweeper {
    void selectTile(int yPosition, int xPosition);
    void setDelegate(Controller controller);
}
