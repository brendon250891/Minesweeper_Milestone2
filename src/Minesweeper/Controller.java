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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import javax.swing.JOptionPane;

/**
 * The controller updates the model and view based on player interaction.
 * @author brendon
 */
public class Controller implements Callback {
    /**
     * The applications current view.
     */
    private ApplicationView view;
    
    /**
     * The currently selected difficulty.
     */
    private GameDifficulty gameDifficulty = GameDifficulty.BEGINNER;
    
    /**
     * The currently selected game type.
     */
    private GameType gameType = GameType.MINESWEEPER;
    
    /**
     * The current instance of the currently selected game type.
     */
    private IMinesweeper currentGame;
    
    /**
     * Constructor of the controller.
     * @param view - The view of the application to set.
     */
    public Controller(ApplicationView view) {
        this.view = view;
        setupGameSettingsMenu();
        setupRestartButton();
        setupGame();
        displayView();
    }
    
    /**
     * Updates the view to reveal a tile.
     * @param tile - The tile to reveal.
     */
    @Override
    public void revealTile(ITile tile) {
        view.revealTile(tile.getPositionY(), tile.getPositionX(), tile.getLabel());
    }
    
    /**
     * Updates the view to flag the tile.
     * @param tile - The tile to flag.
     */
    @Override
    public void flagTile(ITile tile) {
        view.flagTile(tile.getPositionY(), tile.getPositionX(), "F");
    }
    
    /**
     * Updates the timer with the time given.
     * @param time - The time to set the timer too.
     */
    @Override
    public void updateTimer(String time) {
        view.updatePlayerScore(time);
    }
    
    /**
     * Prompts the user with a message.
     * @param message - The message to display.
     */
    @Override
    public void promptUser(String message) {
        var selection = JOptionPane.showConfirmDialog(null, message, "Game Over", JOptionPane.YES_NO_OPTION);
        if (selection == 0) {
            setupGame();
        } else {
            view.toggleMinefield(false);
        }
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
            currentGame.stopTimer();
            setupGame();
        });
    }
    
    /**
     * Saves the currently selected game settings.
     */
    private void saveGameSettings() {
        setGameType(view.getSelectedGameType());
        setGameDifficulty(view.getSelectedGameDifficulty());
        currentGame.stopTimer();
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
                currentGame = new HexagonalMinesweeper(new Minefield(gameDifficulty), this, new Timer(), gameMode);
                break;
            default:
                currentGame = new Minesweeper(new Minefield(gameDifficulty), this, new Timer());                
                break;
        }
        setupView();
    }
    
    /**
     * Gets the currently selected game mode.
     * @param gameMode - The selected game mode as a string.
     * @return The selected game mode.
     */
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
                    minefieldTileLeftClicked((UITile)e.getSource());
                } else {
                    minefieldTileRightClicked((UITile)e.getSource());
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
                if (e.getSource() instanceof UITile && ((UITile)e.getSource()).isEnabled()) {
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
    
    /**
     * Handles the 
     * @param tile 
     */
    private void minefieldTileRightClicked(UITile tile) {
        currentGame.flagTile(tile.getPositionY(), tile.getPositionX());
    }
    
    private void minefieldTileLeftClicked(UITile tile) {
        try {
            currentGame.selectTile(tile.getPositionY(), tile.getPositionX());
        } catch (Exception e) {
            promptUser(e.getMessage());
        }
    }
}
