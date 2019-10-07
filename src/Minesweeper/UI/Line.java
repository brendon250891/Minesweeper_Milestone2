/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import javax.swing.JComponent;

/**
 *
 * @author brendon
 */
public class Line extends JComponent {
    private Point start = new Point();
    private Point end = new Point();
    
    public Line(Point start, Point end) {
        super();
        this.start = start;
        this.end = end;
        this.setSize(1000, 1000);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(start.x, start.y, end.x, end.y);
    }
}
