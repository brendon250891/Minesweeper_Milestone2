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
    
    private IMinesweeper currentGame;
    
    public Controller(ApplicationView view) {
        this.view = view;
        setupGameSettingsMenu();
        setupRestartButton();
        setupGame();
        displayView();
    }
    
    @Override
    public void revealTile(ITile tile) {
        view.revealTile(tile.getPositionY(), tile.getPositionX(), tile.getLabel());
    }
    
    /**
     * Sets up the buttons in the game settings menu with event handlers.
     */
    private void setupGameSettingsMenu() {
        view.addGameSettingsMenuButtonEventHandler((ActionEvent e) -> {
            view.toggleGameSettingsPanel();
        });
        
        view.addGameSettingsSaveButtonEventHandler((ActionEvent e) -> {
            saveGameSettings();
            view.toggleGameSettingsPanel();
        });
        
        view.addGameSettingsCancelButtonEventHandler((ActionEvent e) -> {
            view.toggleGameSettingsPanel();
        });
        
        view.addGameTypeComboBoxChangedEvent((ItemEvent e) -> {
            var gameModeEnabled = false;
            if (e.getItem().toString().equalsIgnoreCase("hexagonal")) {
                gameModeEnabled = true;
            }
            view.changeGameModeEnabled(gameModeEnabled);
        });
    }    
    
    /**
     * Sets up the restart game button with an event handler.
     */
    private void setupRestartButton() {
        view.addRestartGameButtonEventHandler((ActionEvent e) -> {
            setupGame();
        });
    }
    
    private void saveGameSettings() {
        setGameType(view.getSelectedGameType());
        setGameDifficulty(view.getSelectedGameDifficulty());
        
        setupGame();
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
     * Does any initial model setup that is required such as minefield setup.
     */
    private void setupGame() {
        switch (gameType) {
            case HEXAGONAL:
                var gameMode = getSelectedGameMode(view.getSelectedGameMode());
                currentGame = new HexagonalMinesweeper(new Minefield(gameDifficulty), gameMode);
                break;
            default:
                currentGame = new Minesweeper(new Minefield(gameDifficulty));                
                break;
        }
        currentGame.setDelegate(this);
        setupView();
    }
    
    private GameMode getSelectedGameMode(String gameMode) {
        return gameMode.equalsIgnoreCase("normal") ? GameMode.NORMAL : GameMode.CORNER_TO_CORNER;
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
                    minefieldTileLeftClicked((UITile)e.getSource());
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
            view.initialiseSquareTileGrid(gameDifficulty.height(), gameDifficulty.width(), clickEvents);
        } else {
            view.initialiseHexagonalTileGrid(gameDifficulty.height(), gameDifficulty.width(), clickEvents);
            if (getSelectedGameMode(view.getSelectedGameMode()) == GameMode.CORNER_TO_CORNER) {
                try {
                    currentGame.selectTile(0, 0);
                } catch (Exception e) {
                    promptUser(e.getMessage());
                }
            }
        }
    }
    
    private void minefieldTileRightClicked(UITile tile) {
        try {
            currentGame.selectTile(tile.getPositionX(), tile.getPositionY());
        } catch (Exception e) {
            promptUser(e.getMessage());
        }
    }
    
    private void minefieldTileLeftClicked(UITile tile) {
        //currentGame.flagSelectedTile(tile.getPositionY(), tile.getPositionX());
    }
    
    public void promptUser(String message) {
        var selection = JOptionPane.showConfirmDialog(null, message, "Game Over", JOptionPane.YES_NO_OPTION);
        if (selection == 0) {
            setupGame();
        } else {
            view.toggleMinefield(false);
        }
    }  
}
