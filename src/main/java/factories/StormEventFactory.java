package factories;

import Model.storm.*;

public class StormEventFactory implements StormFactory {

    public static final String BRANDT = "BRANDT";
    public static final String STERKER = "STERKER";
    public static final String NOORD = "NOORD";
    public static final String OOST = "OOST";
    public static final String ZUID = "ZUID";
    public static final String WEST = "WEST";


    public StormEvent createStormEvent(String name) {
        if (BRANDT.equals(name)) {
            return new StormEventBrandt();
        } else if (STERKER.equals(name)) {
            return new StormEventSterker();
        } else {
            return null;
        }
    }

    public StormEvent createStormEventBeweging(String name, Stappen stappen) {
        if (NOORD.equals(name)) {
            return new StormEventNoord(stappen);
        } else if (OOST.equals(name)) {
            return new StormEventOost(stappen);
        } else if (ZUID.equals(name)) {
            return new StormEventZuid(stappen);
        } else if (WEST.equals(name)) {
            return new StormEventWest(stappen);
        } else {
            return null;
        }
    }
}
