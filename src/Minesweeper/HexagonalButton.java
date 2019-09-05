/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JButton;

/**
 *
 * @author brendon
 */
public class HexagonalButton extends JButton {
    private final int SIDES = 6;
    private final int SIDE_LENGTH = 35;
    
    public HexagonalButton() {}
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Polygon hexagon = new Polygon();
        for (int i = 0; i < SIDES; i++) {
            hexagon.addPoint((int) (50 + SIDE_LENGTH * Math.cos(i * 2 * Math.PI / SIDES)), //calculation for side
                        (int) (50 + SIDE_LENGTH * Math.sin(i * 2 * Math.PI / SIDES)));   //calculation for side
        }
        g.setColor(new Color(4, 143, 242));
        g.fillPolygon(hexagon);
        g.drawPolygon(hexagon);
    }
}
