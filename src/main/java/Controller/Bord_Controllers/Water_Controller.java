package Controller.Bord_Controllers;

import Model.player.Water;
import observers.SpelerObservable;
import observers.SpelerObserver;
import observers.WaterObserver;

public class Water_Controller implements SpelerObserver {


    static Water_Controller waterController;
    Water water;

    public Water_Controller(){
    }


    // Singleton Pattern.
    // now we can call: SpelbordController.getInstance()  from everywhere
    // AND it guarantees there is only 1 instance.
    public static Water_Controller getInstance() {
        if (waterController == null) {
            waterController = new Water_Controller();
        }
        return waterController;
    }


    public void registerObserver(WaterObserver sbv) {
        water.register(sbv);
    }

    public void update(SpelerObservable sb){ water.updateWater(sb);}

}
