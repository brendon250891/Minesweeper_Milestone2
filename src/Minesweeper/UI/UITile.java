/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.UI;


import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author brendon
 */
public class UITile extends JComponent {
    
    private int width = 0;
    
    private int height = 0;
    
    private String backgroundColor = "8,128,214";
    
    private String foregroundColor = "9, 68, 109";
            
    private Point position;
    
    private String tileText = "";
   
    public UITile(Point position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }
    
    
    /**
     * Sets the text of the tile to some input.
     * @param text - The input to set on the tile.
     */
    public void setTileText(String text) {
        this.tileText = text;
    }
    
    public String getTileText() {
        return tileText;
    }
    
    public int getTileWidth() {
        return width;
    }
    
    public int getTileHeight() {
        return height;
    }
    
    public int getPositionX() {
        return position.x;
    }
    
    public int getPositionY() {
        return position.y;
    }
    
    public void setBackgroundColor(TileColor color) {
        this.backgroundColor = color.getColor();
    }
    
    public java.awt.Color getBackgroundColor() {
        return getColor(backgroundColor);
    }
    
    private java.awt.Color getColor(String colorString) {
        String[] splitString = colorString.split(",");
        if (splitString.length == 3) {
            return new java.awt.Color(Integer.parseInt(splitString[0].trim()), 
                    Integer.parseInt(splitString[1].trim()), Integer.parseInt(splitString[2].trim()));
        }
        return java.awt.Color.WHITE;
    }
    
    public java.awt.Color getForegroundColor(String text) {
        switch (text.toLowerCase()) {
            case "1":
                return java.awt.Color.BLACK;
            case "2":
                return java.awt.Color.BLUE;
            case "3":
                return java.awt.Color.CYAN;
            case "4":
                return java.awt.Color.DARK_GRAY;
            case "5":
                return java.awt.Color.GREEN;
            case "6":
                return java.awt.Color.MAGENTA;
            case "7":
                return java.awt.Color.ORANGE;
            case "8":
                return java.awt.Color.PINK;
            default:
                return java.awt.Color.red;
        }
    }
}
