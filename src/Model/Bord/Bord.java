package Model.Bord;

import observers.*;

import java.util.ArrayList;
import java.util.List;

public class Bord implements BordObservable {

    private int scorePlayer1 = 0; 		// Should be in player model - but out of time;
    private String uName = "me";
    private String pass = "notme";
    private String givenUsername;
    private String givenPassword;
    private boolean loginCorrect = false;

    // List of all Observers of this Observable Objects
    private List<BordObserver> observers = new ArrayList<BordObserver>();







    public void increaseScore(){
        System.out.println("Spelbord - score increased");
        scorePlayer1++;

        // SOMETHING Changed !!  - We need to notify the observers !
        notifyAllObservers();
    }

    // Add an observer to the list
    public void register(BordObserver observer){
        observers.add(observer);
    }

    // Signal all observers that something has changed.
    // Also send <<this>> object to the observers.
    public void notifyAllObservers(){
        for (BordObserver s : observers) {
            s.update(this);
        }
    }
}
