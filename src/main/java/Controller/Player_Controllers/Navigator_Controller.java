package Controller.Player_Controllers;

import Model.player.*;
import javafx.scene.paint.Color;

public class Navigator_Controller extends PlayerController implements SpecialMove {

    /////////////////////////////////////// Constructor ///////////////////////////////////////

    public Navigator_Controller(String nickname ) {

        super( nickname, "Navigator", "PLACEHOLDER", 4, Color.YELLOW, "placeholder.png", Player.SpelerKlassen.NAVIGATOR);

    }
}
