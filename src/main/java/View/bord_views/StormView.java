package View.bord_views;

import Controller.Tile_Controllers.StormController;
import observers.StormObservable;
import observers.StormObserver;

public class StormView implements StormObserver {

    static StormView stormView;

    public static StormView getInstance(){
        if (stormView == null){
            stormView = new StormView();
        }
        return stormView;
    }

    StormController stormController;
    StormObservable bo;

    public StormView(){
        stormController = StormController.getInstance();
        stormController.registerObserver(this);
    }

    public void update(StormObservable bo){
        this.bo = bo;
    }
}
