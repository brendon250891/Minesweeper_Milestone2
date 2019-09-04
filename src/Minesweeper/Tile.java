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
public class Tile {
    private String tileLabel;
    
    private boolean isTileInPlay = true;
    
    public Tile() {
        this.tileLabel = "";
    }
    
    public String getTileLabel() {
        return this.tileLabel;
    }
    
    public void selectTile() {
        this.isTileInPlay = false;
    }
}
