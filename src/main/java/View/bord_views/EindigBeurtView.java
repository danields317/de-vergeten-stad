package View.bord_views;

import Controller.Player_Controllers.FunctieController;
import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.StormController;
import Controller.Tile_Controllers.TileController;
import Controller.firebase_controllers.UpdateFirebaseController;
import Model.data.StaticData;
import View.ViewManager;
import javafx.scene.control.Button;

import java.util.Map;

public class EindigBeurtView {

    public Button maakEindigbeurtKnop(){

        Button eindigBeurt = new Button("Beëindig\nBeurt");

        eindigBeurt.setPrefSize(152,57);
        eindigBeurt.setLayoutX(1392);
        eindigBeurt.setLayoutY(732);

        eindigBeurt.setOnMouseClicked(e -> {
            if (TileController.getInstance().checkFinish()){
                ViewManager.getInstance().loadEndGame(ViewManager.endConditions.VICTORYROYALE);
            } else {
                StormController stormController = StormController.getInstance();

                StaticData staticData = StaticData.getInstance();

                stormController.voerStormEventsUit();
                PlayerController playerController = PlayerController.getInstance();
                playerController.getPlayer().refillActions();

                (UpdateFirebaseController.getInstance()).updateFirebase();
                //update();

                ViewManager.getInstance().disableButtons();
            }
        });

        return eindigBeurt;
    }

}
