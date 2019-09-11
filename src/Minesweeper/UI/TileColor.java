/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.UI;

/**
 *
 * @author brendon
 */
public enum TileColor {
    NORMAL("8, 128, 214"),
    HOVER("4, 143, 242"),
    CLICKED("201, 223, 239");

    private final String rbgValue;

    TileColor(String rbgValue) {
        this.rbgValue = rbgValue;
    }

    public String getColor() {
        return this.rbgValue;
    }
}
