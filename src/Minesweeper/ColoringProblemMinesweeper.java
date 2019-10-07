/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author brendon
 */
public class ColoringProblemMinesweeper extends Minesweeper {

    private LinkedHashMap<Point, Point[]> connections = new LinkedHashMap<>();

    private Point[] bombPoints = new Point[2];

    private int bombColor = 0;

    private final String[] colors = {"Blue", "Green", "Red"};

    public ColoringProblemMinesweeper(IMinefield minefield, Callback delegate, Timer timer) {
        super(minefield, delegate, timer);
        instantiateConnections();
    }

    @Override
    protected void randomlySelectMineTiles() {
        generateNeighborhood();
        linkTiles();
        Random random = new Random();
        int bombConnection = random.nextInt(connections.size());
        var keys = connections.keySet().toArray();
        bombPoints[0] = (Point) keys[bombConnection];
        bombPoints[1] = connections.get(bombPoints[0])[0];
        // select bomb color and color other tiles.
        colorTiles();
    }

    @Override
    public void selectTile(int yPosition, int xPosition) throws Exception {
        if (firstSelection) {
            firstSelection = false;
            startTimer();
        } 
        ITile tile = minefield.getTile(yPosition, xPosition);
        tile.disableTile();
        delegate.revealTile(tile);
        checkForWin();
    }

    @Override
    public void flagTile(int yPosition, int xPosition) {

    }

    @Override
    public void stopTimer() {
        timer.cancel();
    }

    @Override
    public void checkForWin() {
        if (!minefield.getTile(bombPoints[0].y, bombPoints[0].x).isAvailable() && !minefield.getTile(bombPoints[1].y, bombPoints[1].x).isAvailable()) {
            delegate.promptUser("You have chosen the bomb link\nDo you want to play again?");
        } else {
            int tilesRemaining = 0;
            for (int y = 0; y < minefield.getHeight(); y++) {
                for (int x = 0; x < minefield.getWidth(); x++) {
                    if (minefield.getTile(y, x).isAvailable()) {
                        tilesRemaining++;
                    }
                }
            }
            if (tilesRemaining == 1) {
                delegate.promptUser("You have won!\nDo you want to play again");
            }
        }
    }

    private void instantiateConnections() {
        for (int y = 0; y < minefield.getHeight(); y++) {
            for (int x = 0; x < minefield.getWidth(); x++) {
                connections.put(minefield.getTile(y, x).getPosition(), new Point[3]);
            }
        }
    }

    private void generateNeighborhood() {
        for (int i = 0; i < minefield.getHeight(); i++) {
            for (int j = 0; j < minefield.getWidth(); j++) {
                boolean added = false;
                ITile tile = minefield.getTile(j, i);
                while (!added) {
                    Point randomPoint = new Point(getNumberBetween(j - 1, j + 1), getNumberBetween(i - 1, i + 1));
                    ITile connectionTile = minefield.getTile(randomPoint.y, randomPoint.x);
                    if (tile.getPosition() != randomPoint && hasRoom(tile.getPosition()) && hasRoom(connectionTile.getPosition())) {
                        var tilePoints = connections.get(tile.getPosition());
                        var connectionPoints = connections.get(connectionTile.getPosition());
                        tilePoints[getNextAvailablePosition(tilePoints)] = connectionTile.getPosition();
                        connectionPoints[getNextAvailablePosition(connectionPoints)] = tile.getPosition();
                        added = true;
                    }
                }
            }
        }
    }

    private void linkTiles() {
        connections.forEach((Point key, Point[] values) -> {
            delegate.createLinks(key, values);
        });
    }

    private int getNumberBetween(int left, int right) {
        Random random = new Random();
        int number = random.nextInt(right - left);
        return number > 0 ? number + left : number;
    }

    private boolean hasRoom(Point p) {
        int availableSlots = 0;
        var values = connections.get(p);
        for (var point : values) {
            if (point == null) {
                availableSlots++;
            }
        }
        return availableSlots > 0;
    }

    private int getNextAvailablePosition(Point[] points) {
        int index = 0;
        for (var point : points) {
            if (point != null) {
                index++;
            }
        }
        return index;
    }

    private void colorTiles() {
        Random random = new Random();
        var keys = connections.keySet().toArray();
        int keyColor = random.nextInt(colors.length);
        minefield.getTile(((Point) keys[0]).y, ((Point) keys[0]).x).setTileLabel(colors[keyColor]);
        for (var points : connections.get((Point) keys[0])) {
            if (points != null) {
                int connectedColor = random.nextInt(colors.length);
                while (connectedColor == keyColor) {
                    connectedColor = random.nextInt(colors.length);
                }
                minefield.getTile(points.y, points.x).setTileLabel(colors[connectedColor]);
            }
        }
        for (int i = 1; i < keys.length; i++) {
            ITile keyTile = minefield.getTile(((Point) keys[i]).y, ((Point) keys[i]).x);
            keyColor = findColor(keyTile.getLabel());
            Point[] connected = connections.get((Point) keys[i]);
            for (int j = 1; j < connected.length; j++) {
                if (connected[j] != null) {
                    int connectedColor = random.nextInt(colors.length);
                    while (connectedColor == keyColor) {
                        connectedColor = random.nextInt(colors.length);
                    }
                    minefield.getTile(connected[j].y, connected[j].x).setTileLabel(colors[connectedColor]);
                }
            }
        }
//        connections.forEach((Point pointOne, Point[] pointTwo) -> {
//            if (pointOne == bombPoints[0] && pointTwo[0] == bombPoints[1]) {
//                bombColor = random.nextInt(colors.length);
//                minefield.getTile(pointOne.y, pointOne.x).setTileLabel(colors[bombColor]);
//                minefield.getTile(pointTwo[0].y, pointTwo[0].x).setTileLabel(colors[bombColor]);
//            } else {
//                int color = random.nextInt(colors.length);
//                minefield.getTile(pointOne.y, pointOne.x).setTileLabel(colors[color]);
//                for (var p : pointTwo) {
//                    if (p != null) {
//                        int connectedColor = random.nextInt(colors.length);
//                        while (connectedColor == color) {
//                            connectedColor = random.nextInt(colors.length);
//                        }
//                        minefield.getTile(p.y, p.x).setTileLabel(colors[connectedColor]);
//                    }
//                }                
//            }
//        });
    }

    private int findColor(String color) {
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals(color)) {
                return i;
            }
        }
        return -1;
    }
}
