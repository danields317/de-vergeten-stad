package Model.Bord;

import observers.StormMeterObservable;
import observers.StormMeterObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bram de Jong
 * @version 1.0
 */
public class StormMeter implements StormMeterObservable {
    private List<StormMeterObserver> observers = new ArrayList<StormMeterObserver>();
    private int hoogte;

    /**
     * Deze functue bepaalt de hoogte als deze veranderd.
     * Met de hoogte die bepaalt wordt, verschuift de stormmeter.
     * @param stormsterkte
     */
    public void bepaalHoogte(int stormsterkte) {
        int hoogte = 515;
        //Voor elke stormsterke wordt de meter hoogte omhoog gegooid
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
