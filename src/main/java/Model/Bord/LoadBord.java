package Model.Bord;

import observers.LoadBordObservable;
import observers.LoadBordObserver;
import observers.LoginObserver;

import java.util.ArrayList;
import java.util.List;

public class LoadBord implements LoadBordObservable {

    // List of all Observers of this Observable Objects
    private List<LoadBordObserver> observers = new ArrayList<LoadBordObserver>();

    // Add an observer to the list
    public void register(LoadBordObserver observer){
        observers.add(observer);
    }

    // Signal all observers that something has changed.
    // Also send <<this>> object to the observers.
    public void notifyAllObservers(){
        for (LoadBordObserver s : observers) {
            s.update(this);
        }
    }
}
