/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.Model;

import java.awt.Point;

/**
 * Interface that exposes methods of classes that implement this interface.
 * @author brendon
 */
public interface ITile {
    void setToMine();
    void incrementAdjacentMineCount();
    void disableTile();
    void flagTile();
    void setTileLabel(String color);
    String getLabel();
    boolean isAMine();
    boolean isFlagged();
    boolean isAvailable();
    int getPositionX();
    int getPositionY();
    Point getPosition();
}
