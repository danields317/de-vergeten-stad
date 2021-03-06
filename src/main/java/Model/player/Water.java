package Model.player;

import observers.*;

import java.util.ArrayList;
import java.util.List;

public class Water implements WaterObservable {
    private String imgWater;

    // List of all Observers of this Observable Objects
    private List<WaterObserver> observers = new ArrayList<WaterObserver>();

    public void updateWater(int maxWater, int water){

        imgWater = "/veldfles/Fles" + water + "_" + maxWater + ".png" ;

        /*if (water < 0 || water > maxWater || maxWater < 3 || maxWater > 5) {
            imgWater = "/placeholder.png" ;
        }*/

        notifyAllObservers();
    }

    public String getImgWater() {
        return imgWater;
    }


    // Add an observer to the list

    public void register(WaterObserver observer){
        observers.add(observer);
    }

    // Signal all observers that something has changed.
    // Also send <<this>> object to the observers.
    public void notifyAllObservers(){
        for (WaterObserver s : observers) {
            s.update(this);
        }
    }

}
