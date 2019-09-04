/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Ignore;

/**
 *
 * @author brendon
 */
public class MinefieldTest {
    
    private Minefield minefield = Minefield.getInstance();
    
    public MinefieldTest() {
    }
    
    @Test
    public void whenBeginnerGameDifficultyIsChosenThenTheCorrectBoardConfigurationIsSet() {
        minefield.setGameDifficulty(GameDifficulty.BEGINNER);
        
        Assert.assertEquals(GameDifficulty.BEGINNER, minefield.getCurrentGameDifficulty());
        Assert.assertEquals(9, minefield.getMinefieldWidth());
        Assert.assertEquals(9, minefield.getMinefieldHeight());
        Assert.assertEquals(10, minefield.getMinefieldMineCount());
    }
    
    @Test
    public void whenIntermediateGameDifficultyIsChosenThenTheCorrectBoardConfigurationIsSet() {
        minefield.setGameDifficulty(GameDifficulty.INTERMEDIATE);
        
        Assert.assertEquals(GameDifficulty.INTERMEDIATE, minefield.getCurrentGameDifficulty());
        Assert.assertEquals(16, minefield.getMinefieldWidth());
        Assert.assertEquals(16, minefield.getMinefieldHeight());
        Assert.assertEquals(40, minefield.getMinefieldMineCount());
    }
    
    @Test
    public void whenExpertGameDifficulyIsChosenThenTheCorrectBoardConfigurationIsSet() {
        minefield.setGameDifficulty(GameDifficulty.EXPERT);
        
        Assert.assertEquals(GameDifficulty.EXPERT, minefield.getCurrentGameDifficulty());
        Assert.assertEquals(16, minefield.getMinefieldWidth());
        Assert.assertEquals(30, minefield.getMinefieldHeight());
        Assert.assertEquals(99, minefield.getMinefieldMineCount());
    }
    
    @Test
    public void whenABeginnerGameIsStartedThenTheCorrectMinefieldSizeIsGenerated() {
        minefield.setGameDifficulty(GameDifficulty.BEGINNER);
        minefield.startAGame();
        
        Assert.assertTrue(minefield.isMinefieldGeneratedCorrectForSelectedDifficulty());
    }
    
    @Test
    public void whenAnIntermediateGameIsStartedThenTheCorrectMinefieldSizeIsGenerated() {
        minefield.setGameDifficulty(GameDifficulty.INTERMEDIATE);
        minefield.startAGame();
        
        Assert.assertTrue(minefield.isMinefieldGeneratedCorrectForSelectedDifficulty());
    }
    
    @Test
    public void whenAnExpertGameIsStartedThenTheCorrectMinefieldSizeIsGenerated() {
        minefield.setGameDifficulty(GameDifficulty.EXPERT);
        minefield.startAGame();
        
        Assert.assertTrue(minefield.isMinefieldGeneratedCorrectForSelectedDifficulty());
    }
    
    @Test
    public void whenAEmptyTileIsSelectedThenTheTilesLabelIsReturned() {
        Tile tile = minefield.getTile(0,0);
        minefield.selectTile(tile);
        
        Assert.assertEquals("",tile.getTileLabel());
    }
}
