package View;

import Controller.Tile_Controllers.TileController;
import observers.BordObservable;
import observers.BordObserver;

public class TileView implements BordObserver{

    TileController tileController;

    public TileView(){
        tileController = TileController.getInstance();
        tileController.registerObserver(this);
    }

    public void update(BordObservable bo){

    }
}
