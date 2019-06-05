package model;


import Model.storm.Storm;
import Model.storm.StormEventBeweging;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author ryan
 * @author daniel
 */
public class StormTest {

    private Storm storm;

    @Before
    public void makeStorm(){
        storm = new Storm();
    }


    @Test
    public void StormBeweegNoordOneTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.NOORD, StormEventBeweging.Stappen.ONE);
        assertEquals(1, storm.getY());
    }
    @Test
    public void StormBeweegNoordTwoTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.NOORD, StormEventBeweging.Stappen.TWO);
        assertEquals(0, storm.getY());
    }
    @Test
    public void StormBeweegNoordThreeTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.NOORD, StormEventBeweging.Stappen.THREE);
        assertEquals(0, storm.getY());
    }
    @Test
    public void StormBeweegOostOneTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.OOST, StormEventBeweging.Stappen.ONE);
        assertEquals(3, storm.getX());
    }
    @Test
    public void StormBeweegOostTwoTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.OOST, StormEventBeweging.Stappen.TWO);
        assertEquals(4, storm.getX());
    }
    @Test
    public void StormBeweegOostThreeTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.OOST, StormEventBeweging.Stappen.THREE);
        assertEquals(4, storm.getX());
    }
    @Test
    public void StormBeweegZuidOneTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.ZUID, StormEventBeweging.Stappen.ONE);
        assertEquals(3, storm.getY());
    }
    @Test
    public void StormBeweegZuidTwoTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.ZUID, StormEventBeweging.Stappen.TWO);
        assertEquals(4, storm.getY());
    }
    @Test
    public void StormBeweegZuidThreeTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.ZUID, StormEventBeweging.Stappen.THREE);
        assertEquals(4, storm.getY());
    }
    @Test
    public void StormBeweegWestOneTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.WEST, StormEventBeweging.Stappen.ONE);
        assertEquals(1, storm.getX());
    }
    @Test
    public void StormBeweegWestTwoTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.WEST, StormEventBeweging.Stappen.TWO);
        assertEquals(0, storm.getX());
    }
    @Test
    public void StormBeweegWestThreeTest(){
        storm.beweegStorm(StormEventBeweging.Rightingen.WEST, StormEventBeweging.Stappen.THREE);
        assertEquals(0, storm.getX());
    }

}
