package Controller.Bord_Controllers;

import Controller.Player_Controllers.PlayerController;
import Model.player.Water;
import observers.*;


/**
 * @author Jason
 * @version 1.0
 * @since 24-06-2019
 */
public class Water_Controller implements PlayerObserver {


    static Water_Controller waterController;
    PlayerController playerController = PlayerController.getInstance();
    Water water;

    /**
     * <p>
     *     Maakt het water model aan
     * </p>
     * @since 1.0
     */
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

    /**
     * <p>
     *     Registreet de water view als observer van het water model
     * </p>
     * @param sbv
     * @since 1.0
     */
    public void registerObserver(WaterObserver sbv) {
        water.register(sbv);
    }

    /**
     * <p>
     *     Registreet zich zelf als PlayerObserver
     * </p>
     * @since 1.0
     */
    public void registerObserver() {
        playerController.registerObserver((PlayerObserver) this);
    }

    /**
     * <p>
     *     Zodra de playerObserver een update binnen krijgt roept hij het water model aan om te zorgen dat de image in de view verandert
     * </p>
     * @param sb
     * @since 1.0
     */
    public void update(PlayerObservable sb){
        water.updateWater(sb.getMaxWater(), sb.getWater());
    }

}
