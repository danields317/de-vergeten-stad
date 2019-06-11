package Controller.Bord_Controllers;

import Controller.Player_Controllers.Player_Controller;
import Model.player.Water;
import observers.*;

public class Water_Controller implements PlayerObserver {


    static Water_Controller waterController;
    Player_Controller playerController = Player_Controller.getInstance();
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
    public void registerObserver() {
        playerController.registerObserver(this);
    }
    public void update(PlayerObservable sb){ water.updateWater(sb);}

}
