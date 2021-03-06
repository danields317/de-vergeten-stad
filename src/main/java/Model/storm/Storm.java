package Model.storm;

import View.ViewManager;
import javafx.scene.image.Image;
import observers.StormObservable;
import observers.StormObserver;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.List;

/**
 * De Storm model regelt het bewegen van de storm en het sterker worden van de storm.
 * De storm heeft een locatie (x en y) een sterkte en een substerkte.
 *
 * @author ryanr
 * @author daniel
 */
public class Storm implements StormObservable{

    private int x;
    private int y;
    private int sterkte;
    private int subSterkte;
    private Image image;

    private List<StormObserver> observers = new ArrayList<>();

    public Storm(){
        x = 2;
        y = 2;
        sterkte = 2;
        subSterkte = 1;
        image = new Image("/placeholder.png");
    }

    public void beweegNoord(Stappen stappen){
        if (y-stappen.getNumber() >= 0){
            y -= stappen.getNumber();
        }else y = 0;
        notifyAllObservers();
    }
    public void beweegOost(Stappen stappen){
        if (x+stappen.getNumber() <= 4){
            x += stappen.getNumber();
        }else x = 4;
        notifyAllObservers();
    }
    public void beweegZuid(Stappen stappen){
        if (y+stappen.getNumber() <= 4){
            y += stappen.getNumber();
        }else y = 4;
        notifyAllObservers();
    }
    public void beweegWest(Stappen stappen){
        if (x-stappen.getNumber() >= 0){
            x -= stappen.getNumber();
        }else x = 0;
        notifyAllObservers();
    }

    /**
     * Aan de hand van de subSterkte van de storm wordt bepaald wat de sterkte is.
     * Dit is aan de hand van het spelregel boekje.
     */
    public void stormWordtSterker(){
        subSterkte++;
        if (subSterkte < 7){
            sterkte = 3;
        }else if (subSterkte < 11){
            sterkte = 4;
        }else if (subSterkte < 14){
            sterkte = 5;
        }else if (subSterkte < 16){
            sterkte = 6;
        }else {
            ViewManager.getInstance().loadEndGame(ViewManager.endConditions.STERFDOORSTORM);
        }
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

    public void setSterkte(int sterkte){
        this.sterkte = sterkte;
    }

    public void setSubSterkte(int sterkte){
        this.subSterkte = sterkte;
    }

    public void setLocatie(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Image getImage(){
        return image;
    }

    public void register(StormObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (StormObserver s : observers){
            s.update(this);
        }
    }
}
