/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.UI;

import java.awt.Point;

/**
 *
 * @author brendon
 */
public class ColorTile {
    public Point position;
    
    public String label = "";
    
    public Point[] connections = new Point[3];
    
    public ColorTile(Point position) {
        this.position = position;
    }
    
    public void addConnection(Point point) {
        boolean added = false;
        boolean hasFreeConnections = getFreeConnectionsCount() != 0;
        if (hasFreeConnections) {
            for (int i = 0; i < connections.length; i++) {
                if (connections[i] == null && !added) {
                    added = true;
                    connections[i] = point;
                }
            }
        }
    }
    
    public boolean hasFreeSpace() {
        int count = 0;
        for(int i = 0; i < connections.length; i++) {
            if(connections[i] == null) {
                count++;
            }
        }
        return count > 0;
    }
    
    private int getFreeConnectionsCount() {
        int count = 0;
        for (int i = 0; i < connections.length; i++) {
            if(connections[i] == null) {
                count++;
            }
        }
        return count;
    }
    
    @Override
    public String toString() {
        return String.format("x: %s, y: %s", position.x, position.y);
    }
}