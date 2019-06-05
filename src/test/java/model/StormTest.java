package model;


import Model.storm.Storm;
import Model.storm.StormEvent;
import Model.storm.StormEventBeweging;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        storm.beweegStorm(StormEventBeweging.Richtingen.NOORD, StormEventBeweging.Stappen.ONE);
        assertEquals(1, storm.getY());
    }
    @Test
    public void StormBeweegNoordTwoTest(){
        storm.beweegStorm(StormEventBeweging.Richtingen.NOORD, StormEventBeweging.Stappen.TWO);
        assertEquals(0, storm.getY());
    }
    @Test
    public void StormBeweegNoordThreeTest(){
        storm.beweegStorm(StormEventBeweging.Richtingen.NOORD, StormEventBeweging.Stappen.THREE);
        assertEquals(0, storm.getY());
    }
    @Test
    public void StormBeweegOostOneTest(){
        storm.beweegStorm(StormEventBeweging.Richtingen.OOST, StormEventBeweging.Stappen.ONE);
        assertEquals(3, storm.getX());
    }
    @Test
    public void StormBeweegOostTwoTest(){
        storm.beweegStorm(StormEventBeweging.Richtingen.OOST, StormEventBeweging.Stappen.TWO);
        assertEquals(4, storm.getX());
    }
    @Test
    public void StormBeweegOostThreeTest(){
        storm.beweegStorm(StormEventBeweging.Richtingen.OOST, StormEventBeweging.Stappen.THREE);
        assertEquals(4, storm.getX());
    }
    @Test
    public void StormBeweegZuidOneTest(){
        storm.beweegStorm(StormEventBeweging.Richtingen.ZUID, StormEventBeweging.Stappen.ONE);
        assertEquals(3, storm.getY());
    }
    @Test
    public void StormBeweegZuidTwoTest(){
        storm.beweegStorm(StormEventBeweging.Richtingen.ZUID, StormEventBeweging.Stappen.TWO);
        assertEquals(4, storm.getY());
    }
    @Test
    public void StormBeweegZuidThreeTest(){
        storm.beweegStorm(StormEventBeweging.Richtingen.ZUID, StormEventBeweging.Stappen.THREE);
        assertEquals(4, storm.getY());
    }
    @Test
    public void StormBeweegWestOneTest(){
        storm.beweegStorm(StormEventBeweging.Richtingen.WEST, StormEventBeweging.Stappen.ONE);
        assertEquals(1, storm.getX());
    }
    @Test
    public void StormBeweegWestTwoTest(){
        storm.beweegStorm(StormEventBeweging.Richtingen.WEST, StormEventBeweging.Stappen.TWO);
        assertEquals(0, storm.getX());
    }
    @Test
    public void StormBeweegWestThreeTest(){
        storm.beweegStorm(StormEventBeweging.Richtingen.WEST, StormEventBeweging.Stappen.THREE);
        assertEquals(0, storm.getX());
    }

    @Test
    public void StormBeweegWestOneZuidThree(){
        storm.beweegStorm(StormEventBeweging.Richtingen.WEST, StormEventBeweging.Stappen.ONE);
        storm.beweegStorm(StormEventBeweging.Richtingen.ZUID, StormEventBeweging.Stappen.THREE);
        assertEquals(1, storm.getX());
        assertEquals(4, storm.getY());
    }

    @Test
    public void StormTestBeweegMetEvent(){
        ArrayList<StormEvent> stormEvents = new ArrayList<>();
        stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.NOORD, StormEventBeweging.Stappen.TWO));
        stormEvents.add(new StormEvent(StormEvent.Namen.BRANDT));
        storm.voerStormEventsUit(stormEvents);
        assertEquals(0, storm.getY());
    }

    @Test
    public void StormWordtSterker(){
        ArrayList<StormEvent> stormEvents = new ArrayList<>();
        stormEvents.add(new StormEvent(StormEvent.Namen.BRANDT));
        stormEvents.add(new StormEvent(StormEvent.Namen.STERKER));
        storm.voerStormEventsUit(stormEvents);
        assertEquals(2, storm.getSubSterkte());
        assertEquals(3, storm.getSterkte());
    }

}
