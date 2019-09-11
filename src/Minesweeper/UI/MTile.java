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
public class MTile extends JComponent {
    
    private int width = 0;
    
    private int height = 0;
    
    private String backgroundColor = "8,128,214";
    
    private String foregroundColor = "9, 68, 109";
            
    private String positionInMinefield = "";
    
    private String tileText = "";
   
    public MTile(String position, int width, int height) {
        this.positionInMinefield = position;
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
   
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, getTileWidth(), getTileHeight());
        g.setColor(getColor(this.backgroundColor));
        g.fillRect(0, 0, getTileWidth(), getTileHeight());
        g.setColor(getColor(this.foregroundColor));
        g.setFont(new java.awt.Font("plain", 0, 24));
        g.drawString(this.tileText, getTileWidth() / 2, getTileHeight() / 2);
    }
   
    
    public int getTileWidth() {
        return width;
    }
    
    public int getTileHeight() {
        return height;
    }
    
    public String getPositionInGrid() {
        return this.positionInMinefield;
    }
    
    public void setBackgroundColor(TileColor color) {
        this.backgroundColor = color.getColor();
    }
    
    private java.awt.Color getColor(String colorString) {
        String[] splitString = colorString.split(",");
        if (splitString.length == 3) {
            return new java.awt.Color(Integer.parseInt(splitString[0].trim()), 
                    Integer.parseInt(splitString[1].trim()), Integer.parseInt(splitString[2].trim()));
        }
        return java.awt.Color.WHITE;
    }
}
