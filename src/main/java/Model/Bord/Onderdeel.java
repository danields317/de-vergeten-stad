package Model.Bord;

import Model.Tiles.PartTile;
import observers.OnderdeelObservable;
import observers.OnderdeelObserver;

import java.util.ArrayList;

/**
 * De Onderdeel klasse houdt bij of een onderdeel ontdekt is of opgepakt is.
 * ook wordt hier bij gehouden waar een onderdeel moet spawnen.
 */

public class Onderdeel implements OnderdeelObservable {

    private PartTile.Soorten soort;
    private int X = -1;
    private int Y = -1;

    private boolean opgepakt = false;

    private ArrayList<OnderdeelObserver> observers = new ArrayList<>();

    public Onderdeel(PartTile.Soorten soort){
        this.soort = soort;
    }

    @Override
    public void register(OnderdeelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (OnderdeelObserver b : observers){
            b.update(this);
        }
    }

    public PartTile.Soorten getSoort() {
        return soort;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
        notifyAllObservers();
    }

    public void pakOp(){
        opgepakt = true;
        notifyAllObservers();
    }

    public boolean isOpgepakt(){
        return opgepakt;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
        notifyAllObservers();
    }
}
