/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import Minesweeper.UI.ApplicationView;
import Minesweeper.UI.MTile;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author brendon
 */
public class Controller {
    private ApplicationView view;
    
    private GameDifficulty gameDifficulty = GameDifficulty.BEGINNER;
    
    private GameType gameType = GameType.MINESWEEPER;
    
    private Minesweeper currentGame;
    
    public Controller(ApplicationView view) {
        this.view = view;
        setupModel();
        setupView();
    }
    
    /**
     * Does any initial model setup that is required such as minefield setup.
     */
    private void setupModel() {
        switch (gameType) {
            case HEXAGONAL:
                break;
            default:
                currentGame = new Minesweeper(gameDifficulty);
                break;
        }
        currentGame.startGame();
    }
    
    /**
     * Does any initial view setup that is required such as setting event handlers.
     */
    private void setupView() {
        setupDropDownBoxChangedEventHandlers();
        setupViewMinefieldEventHandlers();
        displayView();
    }
    
    /**
     * Displays the view to the user.
     */
    private void displayView() {
        view.pack();
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }
    
    /**
     * Adds event handlers to the drop-down boxes that listen for item changed.
     */
    private void setupDropDownBoxChangedEventHandlers() {
        view.addGameTypeChangedEventHandler(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println(String.format("Selected Game Type: %s", e.getItem().toString()));
            }
        });
        
        view.addGameDifficultyChangedEventHandler(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selectedDifficulty = e.getItem().toString().toLowerCase();
                switch (selectedDifficulty) {
                    case "intermediate":
                        gameDifficulty = GameDifficulty.INTERMEDIATE;
                        break;
                    case "expert":
                        gameDifficulty = GameDifficulty.EXPERT;
                        break;
                    default:
                        gameDifficulty = GameDifficulty.BEGINNER;
                }
                setupGame();
                setupViewMinefieldEventHandlers();
            }
        });
    }
    
    private void setupGame() {
        currentGame = new Minesweeper(gameDifficulty);
        currentGame.startGame();
    }
   
    /**
     * Adds mouse click event handlers to the tiles displayed in the minefield.
     */
    private void setupViewMinefieldEventHandlers() {        
        String diff = gameDifficulty.toString();
        view.initialiseSquareTileGrid(gameDifficulty.width(), gameDifficulty.height(), new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof MTile && e.getButton() == 1) {
                    minefieldTileRightClicked((MTile)e.getSource());
                } else {
                    view.minefieldTileLeftClicked((MTile)e.getSource());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() instanceof MTile) {
                    view.minefieldTileWasEntered((MTile)e.getSource());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() instanceof MTile) {
                    view.minefieldTileWasExited((MTile)e.getSource());
                }
            }
        }, diff);
    }
    
    private void minefieldTileRightClicked(MTile tile) {
        var tilePosition = tile.getPositionInGrid().split(",");
        var tileText = currentGame.checkForAResult(Integer.parseInt(tilePosition[0].trim()),
                Integer.parseInt(tilePosition[1].trim()));
        view.minefieldTileRightClicked(tile, tileText);
        
        if (tileText.equalsIgnoreCase("m")) {
            JOptionPane.showMessageDialog(null, "Oh dear, you landed on a mine!");
        }
    }
    
    private void checkForAResult() {
       
    }
}
