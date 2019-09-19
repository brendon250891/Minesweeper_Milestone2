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
public interface IMinefield {
    Tile getTile(int yPosition, int xPosition);
    int getWidth();
    int getHeight();
    int getMineCount();
}
