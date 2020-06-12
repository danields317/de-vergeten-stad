package factories;

import Model.storm.Stappen;
import Model.storm.StormEvent;

public interface StormFactory {

    StormEvent createStormEvent(String name);

    StormEvent createStormEventBeweging(String name, Stappen stappen);
}
