/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brendon
 */
public class TileTest {
    
    public TileTest() {
    }
    
    @Test
    public void whenATileIsSetToAMineThenItsLabelIsSetToM() {
        var tile = TileFactory.makeTile(0, 0);
        tile.setToMine();
        
        Assert.assertEquals("M", tile.getLabel());
    }
    
    @Test
    public void whenATilesAdjacentMineCountIsIncrementedForTheFirstTimeThenOneIsReturned() {
        var tile = TileFactory.makeTile(0, 0);
        tile.incrementAdjacentMineCount();
        
        Assert.assertEquals("1", tile.getLabel());
    }
    
    @Test
    public void whenATilesAdjacentMineCountIsIncrementedThenItsLabelIsIncrementedByOne() {
        var tile = TileFactory.makeTile(0, 0);
        tile.incrementAdjacentMineCount();
        tile.incrementAdjacentMineCount();
        
        Assert.assertEquals("2", tile.getLabel());
    }
    
    @Test
    public void whenATileIsDisabledThenItIsNotAvailable() {
        var tile = TileFactory.makeTile(0, 0);
        tile.disableTile();
        
        Assert.assertFalse(tile.isAvailable());
    }
}
