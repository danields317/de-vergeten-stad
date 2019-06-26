package Model.Bord;

import Model.data.StaticData;
import observers.Player_Menu_Observable;
import observers.Player_Menu_Observer;

import java.util.ArrayList;
import java.util.List;

public class Player_Menu implements Player_Menu_Observable {
    private List<Player_Menu_Observer> observers = new ArrayList<Player_Menu_Observer>();
    private StaticData staticData = StaticData.getInstance();

    public void spelerBlokOpbouwen(){

    }

    @Override
    public void register(Player_Menu_Observer observer) {
        observers.add(observer);
    }


    @Override
    public void notifyAllObservers() {
        for (Player_Menu_Observer s : observers) {
            s.update(this);
        }
    }
}
