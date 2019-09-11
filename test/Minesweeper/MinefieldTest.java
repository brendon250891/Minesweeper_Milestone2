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
    
    public MinefieldTest() {
    }
    
    @Test
    public void whenABeginnerGameIsCreatedThenTheCorrectMinefieldSizeIsSet() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        
        Assert.assertEquals(9 * 9, minefield.getMinefieldSize());
    }
    
    @Test
    public void whenAnIntermediateGameIsCreatedThenTheCorrectMinefieldSizeIsSet() {
        Minefield minefield = new Minefield(GameDifficulty.INTERMEDIATE);
        
        Assert.assertEquals(16 * 16, minefield.getMinefieldSize());
    }
    
    @Test
    public void whenAnExpertGameIsCreatedThenTheCorrectMinefieldSizeIsSet() {
        Minefield minefield = new Minefield(GameDifficulty.EXPERT);
        
        Assert.assertEquals(16 * 30, minefield.getMinefieldSize());
    }
    
    @Test
    public void whenABeginnerGameIsCreatedThenTheCorrectNumberOfMinesAreSet() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        
        minefield.generateMinefield();
    }
    
    @Test
    public void whenAnTileIsSelectedThenTheCorrectTileIsReturned() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        
        minefield.generateMinefield();
        Tile tile = minefield.getTileFromMinefield(0, 0);
        
        Assert.assertEquals(0, tile.getTilePositionX());
        Assert.assertEquals(0, tile.getTilePositionY());
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void whenAnInvalidTileXAndYPositionIsGivenThenAnIndexOutOfBoundsExceptionIsThrown() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        minefield.getTileFromMinefield(-1, GameDifficulty.BEGINNER.height());
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void whenAnInvalidTileXPositionIsGivenThenAnIndexOutOfBoundsExeceptionIsThrown() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        minefield.getTileFromMinefield(-1, 0);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void whenAnInvalidTileYPositionIsGiventThenAnIndexOutOfBoundsExceptionIsThrown() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        minefield.getTileFromMinefield(0, -1);
    }
    
    @Test
    public void whenAnInvalidTileXPositionIndexOutOfBoundsExceptionIsThrownTheCorrectMessageIsReturned() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        Exception exception = null;
        
        try {
            minefield.getTileFromMinefield(-1, 0);
        } catch(IndexOutOfBoundsException e) {
            exception = e;
        }
        
        Assert.assertNotNull(exception);
        Assert.assertEquals("Invalid X Position Given: -1", exception.getMessage());
    }
    
    @Test
    public void whenAnInvalidTileYPositionIndexOutOfBoundsExceptionIsThrownTheCorrectMessageIsReturned() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        Exception exception = null;
        
        try {
            minefield.getTileFromMinefield(0, -1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        
        Assert.assertNotNull(exception);
        Assert.assertEquals("Invalid Y Position Given: -1", exception.getMessage());
    }
    
    @Test
    public void whenAnInvalidTileXAndYPositionIndexOutOfBoundsExceptionIsThrownThenTheCorrectMessageIsReturned() {
        Minefield minefield = new Minefield(GameDifficulty.BEGINNER);
        Exception exception = null;
        
        try {
            minefield.getTileFromMinefield(-1, GameDifficulty.BEGINNER.height());
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        
        Assert.assertNotNull(exception);
        Assert.assertEquals("Invalid X and Y Positions Given: -1, 9", exception.getMessage());
    }
}
