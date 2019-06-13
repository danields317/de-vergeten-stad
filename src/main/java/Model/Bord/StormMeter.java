package Model.Bord;

import observers.StormMeterObservable;
import observers.StormMeterObserver;
import java.util.ArrayList;
import java.util.List;

public class StormMeter implements StormMeterObservable {
    private List<StormMeterObserver> observers = new ArrayList<StormMeterObserver>();
    private int hoogte;

    public void bepaalHoogte(int stormsterkte) {
        int hoogte = 515;
        for (int i = 1; i < stormsterkte; i++){
            hoogte = hoogte - 35;
        }
        this.hoogte = hoogte;
        notifyAllObservers();
    }

    public int getHoogte() {
        return hoogte;
    }

    @Override
    public void register(StormMeterObserver observer) {
        observers.add(observer);
    }


    @Override
    public void notifyAllObservers() {
        for (StormMeterObserver s : observers){
            s.update(this);
        }
    }
}
