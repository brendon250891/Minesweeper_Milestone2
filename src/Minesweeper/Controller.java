/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import Minesweeper.UI.ApplicationView;
import Minesweeper.UI.UITile;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author brendon
 */
public class Controller implements Callback {
    private ApplicationView view;
    
    private GameDifficulty gameDifficulty = GameDifficulty.BEGINNER;
    
    private GameType gameType = GameType.MINESWEEPER;
    
    private Minesweeper currentGame;
    
    public Controller(ApplicationView view) {
        this.view = view;
        setupGameSettingsMenu();
        setupRestartButton();
        setupGame();
        displayView();
    }
    
    @Override
    public void revealTile(Tile tile) {
        view.revealTile(tile.getPositionX(), tile.getPositionY(), tile.getLabel());
    }
    
    /**
     * Sets up the buttons in the game settings menu with event handlers.
     */
    private void setupGameSettingsMenu() {
        view.addGameSettingsMenuButtonEventHandler(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.toggleGameSettingsPanel();
            }
            
        });
        view.addGameSettingsSaveButtonEventHandler(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGameType(view.getSelectedGameType());
                setGameDifficulty(view.getSelectedGameDifficulty());
                setupGame();
                view.toggleGameSettingsPanel();
            }
        });
        view.addGameSettingsCancelButtonEventHandler(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.toggleGameSettingsPanel();
            }
        });
    }    
    
    /**
     * Sets the game type when the game settings are changed.
     * @param gameType - The selected game type to be set.
     */
    private void setGameType(String gameType) {
        switch (gameType.toLowerCase()) {
            case "hexagonal":
                this.gameType = GameType.HEXAGONAL;
                break;
            case "coloring problem":
                this.gameType = GameType.COLORING_PROBLEM;
                break;
            default:
                this.gameType = GameType.MINESWEEPER;
        }
    }
    
    /**
     * Sets the difficulty when game settings are changed.
     * @param difficulty - The difficulty to be set.
     */
    private void setGameDifficulty(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "intermediate":
                gameDifficulty = GameDifficulty.INTERMEDIATE;
                break;
            case "expert":
                gameDifficulty = GameDifficulty.EXPERT;
                break;
            default:
                gameDifficulty = GameDifficulty.BEGINNER;
        }
    }
    
    /**
     * Sets up the restart game button with an event handler.
     */
    private void setupRestartButton() {
        view.addRestartGameButtonEventHandler(new java.awt.event.ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               setupGame();
           }
        });
    }
    
    /**
     * Does any initial model setup that is required such as minefield setup.
     */
    private void setupGame() {
        switch (gameType) {
            case HEXAGONAL:
                currentGame = new HexagonalMinesweeper(gameDifficulty);
                break;
            default:
                currentGame = new Minesweeper(gameDifficulty);
                break;
        }
        currentGame.setDelegate(this);
        currentGame.startGame();
        setupView();
    }
    
    /**
     * Does any initial view setup that is required such as setting event handlers.
     */
    private void setupView() {
        setupViewMinefieldEventHandlers();
    }
    
    /**
     * Displays the view to the user.
     */
    private void displayView() {
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }  
   
    /**
     * Adds mouse click event handlers to the tiles displayed in the minefield.
     */
    private void setupViewMinefieldEventHandlers() {
        MouseListener clickEvents = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof UITile && e.getButton() == 1 && ((UITile)e.getSource()).isEnabled()) {
                    minefieldTileRightClicked((UITile)e.getSource());
                } else {
                    view.minefieldTileLeftClicked((UITile)e.getSource());
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
                if (e.getSource() instanceof UITile) {
                    view.minefieldTileWasEntered((UITile)e.getSource());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() instanceof UITile) {
                    view.minefieldTileWasExited((UITile)e.getSource());
                }
            }
        };        
        if (gameType == GameType.MINESWEEPER) {
            view.initialiseSquareTileGrid(gameDifficulty.width(), gameDifficulty.height(), clickEvents);
        } else {
            view.initialiseHexagonalTileGrid(gameDifficulty.width(), gameDifficulty.height(), clickEvents);
        }
    }
    
    private void minefieldTileRightClicked(UITile tile) {
        try {
            currentGame.checkTileSelection(tile.getPositionX(), tile.getPositionY());
        } catch (Exception e) {
            promptUser(e.getMessage());
        }
    }
    
    private void promptUser(String message) {
        var selection = JOptionPane.showConfirmDialog(null, message, "Game Over", JOptionPane.YES_NO_OPTION);
        if (selection == 0) {
            setupGame();
        } else {
            view.toggleMinefield(false);
        }
    }
    
    private void checkForAResult() {
       
    }
}
