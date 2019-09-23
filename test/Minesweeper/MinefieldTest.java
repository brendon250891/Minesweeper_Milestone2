/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import java.util.Timer;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Ignore;

/**
 *
 * @author brendon
 */
public class MinefieldTest {
    
    
    public MinefieldTest() {
    }
    
    @Test
    public void whenABeginnerGameIsCreatedThenTheCorrectMinefieldSizeIsSet() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        
        Assert.assertEquals(9 * 9, minefield.getSize());
    }
    
    @Test
    public void whenAnIntermediateGameIsCreatedThenTheCorrectMinefieldSizeIsSet() {
        Minefield minefield = new Minefield(GameDifficulty.INTERMEDIATE);
        
        Assert.assertEquals(16 * 16, minefield.getSize());
    }
    
    @Test
    public void whenAnExpertGameIsCreatedThenTheCorrectMinefieldSizeIsSet() {
        Minefield minefield = new Minefield(GameDifficulty.EXPERT);
        
        Assert.assertEquals(16 * 30, minefield.getSize());
    }
    
    @Test
    public void whenAnTileIsSelectedThenTheCorrectTileIsReturned() {        
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        
        var tile = minefield.getTile(0, 0);
        var tileTwo = minefield.getTile(5, 1);
        
        Assert.assertEquals(0, tile.getPositionX());
        Assert.assertEquals(0, tile.getPositionY());
        Assert.assertEquals(1, tileTwo.getPositionX());
        Assert.assertEquals(5, tileTwo.getPositionY());
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void whenAnInvalidTileXAndYPositionIsGivenThenAnIndexOutOfBoundsExceptionIsThrown() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        minefield.getTile(-1, GameDifficulty.BEGINNER.height());
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void whenAnInvalidTileXPositionIsGivenThenAnIndexOutOfBoundsExeceptionIsThrown() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        minefield.getTile(-1, 0);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void whenAnInvalidTileYPositionIsGiventThenAnIndexOutOfBoundsExceptionIsThrown() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        minefield.getTile(0, -1);
    }
    
    @Test
    public void whenAnInvalidTileYPositionIndexOutOfBoundsExceptionIsThrownTheCorrectMessageIsReturned() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        Exception exception = null;
        
        try {
            minefield.getTile(-1, 0);
        } catch(IndexOutOfBoundsException e) {
            exception = e;
        }
        
        Assert.assertNotNull(exception);
        Assert.assertEquals("Invalid Y Position Given: -1", exception.getMessage());
    }
    
    @Test
    public void whenAnInvalidTileXPositionIndexOutOfBoundsExceptionIsThrownTheCorrectMessageIsReturned() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        Exception exception = null;
        
        try {
            minefield.getTile(0, -1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        
        Assert.assertNotNull(exception);
        Assert.assertEquals("Invalid X Position Given: -1", exception.getMessage());
    }
    
    @Test
    public void whenAnInvalidTileXAndYPositionIndexOutOfBoundsExceptionIsThrownThenTheCorrectMessageIsReturned() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        Exception exception = null;
        
        try {
            minefield.getTile(GameDifficulty.BEGINNER.height(), -1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        
        Assert.assertNotNull(exception);
        Assert.assertEquals("Invalid X and Y Positions Given: -1, 9", exception.getMessage());
    }
}
