package Controller.Bord_Controllers;

import Controller.Player_Controllers.PlayerController;
import Model.player.Water;
import observers.*;

public class Water_Controller implements PlayerObserver {


    static Water_Controller waterController;
    PlayerController playerController = PlayerController.getInstance();
    Water water;

    public Water_Controller(){
        water = new Water();
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
        playerController.registerObserver((PlayerObserver) this);
    }
    public void update(PlayerObservable sb){
        water.updateWater(sb.getMaxWater(), sb.getWater());
    }

}
