/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author brendon
 */
public class MinefieldTest {
    
    private Minefield minefield = Minefield.getInstance();
    
    public MinefieldTest() {
    }
    
    @Test
    public void whenABeginnerGameOfMinesweeperIsStartedThenTheCorrectBoardConfigurationIsSet() {
        minefield.setGameDifficulty(GameDifficulty.BEGINNER);
        
        Assert.assertEquals(GameDifficulty.BEGINNER, minefield.getCurrentGameDifficulty());
        Assert.assertEquals(9, minefield.getMinefieldWidth());
        Assert.assertEquals(9, minefield.getMinefieldHeight());
        Assert.assertEquals(10, minefield.getMinefieldMineCount());
        Assert.assertEquals(9 * 9, minefield.getMinefieldTileCount());
    }
    
    @Test
    public void whenAnIntermediateGameOfMinesweeperIsStartedThenTheCorrectBoardConfigurationIsSet() {
        minefield.setGameDifficulty(GameDifficulty.INTERMEDIATE);
        
        Assert.assertEquals(GameDifficulty.INTERMEDIATE, minefield.getCurrentGameDifficulty());
        Assert.assertEquals(16, minefield.getMinefieldWidth());
        Assert.assertEquals(16, minefield.getMinefieldHeight());
        Assert.assertEquals(40, minefield.getMinefieldMineCount());
        Assert.assertEquals(16 * 16, minefield.getMinefieldTileCount());
    }
    
    @Test
    public void whenAnExpertGameOfMinesweeperIsStartedThenTheCorrectBoardConfigurationIsSet() {
        minefield.setGameDifficulty(GameDifficulty.EXPERT);
        
        Assert.assertEquals(GameDifficulty.EXPERT, minefield.getCurrentGameDifficulty());
        Assert.assertEquals(16, minefield.getMinefieldWidth());
        Assert.assertEquals(30, minefield.getMinefieldHeight());
        Assert.assertEquals(99, minefield.getMinefieldMineCount());
        Assert.assertEquals(16 * 30, minefield.getMinefieldTileCount());
    }
}
