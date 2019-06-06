package View;

import Controller.Tile_Controllers.StormController;
import javafx.stage.Stage;
import observers.BordObservable;
import observers.BordObserver;

public class StormView implements BordObserver{

    StormController stormController;

    public StormView(){
        stormController = StormController.getInstance();
        stormController.registerObserver(this);
    }

    public void update(BordObservable bo){ }
}
