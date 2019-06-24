package Controller.Player_Controllers;

import Model.player.*;
import javafx.scene.paint.Color;

public class Waterdrager_Controller extends PlayerController {
    public Waterdrager_Controller( String nickname ) {

        super( nickname, "Waterdrager", "PLACEHOLDER", 5, Color.BLUE, "placeholder.png", Player.SpelerKlassen.WATERDRAGER);

    }
}
