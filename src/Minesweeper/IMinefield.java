/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

/**
 * Interface that exposes methods of classes that implement this interface.
 * @author brendon
 */
public interface IMinefield {
    ITile getTile(int yPosition, int xPosition);
    void addTile(int yPosition, int xPosition);
    int getWidth();
    int getHeight();
    int getMineCount();
}
