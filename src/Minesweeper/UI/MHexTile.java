/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.UI;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;

/**
 *
 * @author brendon
 */
public class MHexTile extends MTile {
    
    public MHexTile(String position, int width, int height) {
        super(position, width, height);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        var hexagon = new Polygon();
        for (int i = 0; i < getTileWidth(); i++) {
            
        }
    }
}
