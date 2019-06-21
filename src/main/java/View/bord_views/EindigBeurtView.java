package View.bord_views;

import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.StormController;
import Controller.Tile_Controllers.TileController;
import Controller.firebase_controllers.UpdateFirebaseController;
import View.ViewManager;
import javafx.scene.control.Button;

public class EindigBeurtView {

    public Button maakEindigbeurtKnop(){

        Button eindigBeurt = new Button("Beëindig\nBeurt");

        eindigBeurt.setPrefSize(152,57);
        eindigBeurt.setLayoutX(1392);
        eindigBeurt.setLayoutY(732);

        eindigBeurt.setOnMouseClicked(e -> {
            if (TileController.getInstance().checkFinish()){
                EndgameView endgameView = new EndgameView(ViewManager.endConditions.VICTORYROYALE);
            } else {
                StormController stormController = StormController.getInstance();
                stormController.voerStormEventsUit();
                PlayerController playerController = PlayerController.getInstance();
                playerController.getPlayer().refillActions();
                (UpdateFirebaseController.getInstance()).updateFirebase();
                //update();
            }
        });

        return eindigBeurt;
    }

}
