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
    public void whenABeginnerSizedMinefieldIsInstantiatedThenTheCorrectNumberOfTilesAreCreated() {
        minefield.setTilesSize(9, 9);
        
        Assert.assertEquals(9 * 9, minefield.instantiateMinefieldWithTiles());
    }
    
    @Test
    public void whenAnIntermediateSizedMinefieldIsInstantiatedThenTheCorrectNumberOfTilesAreCreated() {
        minefield.setTilesSize(16, 16);
        
        Assert.assertEquals(16 * 16, minefield.instantiateMinefieldWithTiles());
    }
    
    @Test
    public void whenAnExpertSizedMinefieldIsInstantiatedThenTheCorrectNumberOfTilesAreCreated() {
        minefield.setTilesSize(16, 30);
        
        Assert.assertEquals(16 * 30, minefield.instantiateMinefieldWithTiles());
    }
}
