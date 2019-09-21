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
public class TileFactory {
    public static Tile makeTile(int yPosition, int xPosition) {
        return new Tile(yPosition, xPosition);
    }
}
