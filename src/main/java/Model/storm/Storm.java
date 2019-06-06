package Model.storm;

import observers.BordObservable;
import observers.BordObserver;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ryanr
 * @author daniel
 */
public class Storm implements BordObservable{

    private int x;
    private int y;
    private int sterkte;
    private int subSterkte;

    private List<BordObserver> observers = new ArrayList<>();

    public Storm(){
        x = 2;
        y = 2;
        sterkte = 2;
        subSterkte = 1;
    }

    public void beweegNoord(StormEventBeweging.Stappen stappen){
        if (y-stappen.getNumber() >= 0){
            y -= stappen.getNumber();
        }else y = 0;
        notifyAllObservers();
    }
    public void beweegOost(StormEventBeweging.Stappen stappen){
        if (x+stappen.getNumber() <= 4){
            x += stappen.getNumber();
        }else x = 4;;
        notifyAllObservers();
    }
    public void beweegZuid(StormEventBeweging.Stappen stappen){
        if (y+stappen.getNumber() <= 4){
            y += stappen.getNumber();
        }else y = 4;
        notifyAllObservers();
    }
    public void beweegWest(StormEventBeweging.Stappen stappen){
        if (x-stappen.getNumber() >= 0){
            x -= stappen.getNumber();
        }else x = 0;
        notifyAllObservers();
    }

    public void stormWordtSterker(){
        System.out.println("Storm subSterkte increased");
        subSterkte++;
        if (subSterkte < 7){
            sterkte = 3;
        }else if (subSterkte < 11){
            sterkte = 4;
        }else if (subSterkte < 14){
            sterkte = 5;
        }else if (subSterkte < 16){
            sterkte = 6;
        }else return;                        //dood()
        notifyAllObservers();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSubSterkte(){
        return subSterkte;
    }

    public int getSterkte(){
        return sterkte;
    }

    public void register(BordObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (BordObserver s : observers){
            s.update(this);
        }
    }

}
