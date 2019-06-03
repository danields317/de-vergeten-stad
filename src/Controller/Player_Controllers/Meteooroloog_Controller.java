package Controller.Player_Controllers;

import Model.player.*;
import javafx.scene.paint.Color;

public class Meteooroloog_Controller extends Player_controller implements SpecialMove{
    /////////////////////////////////////// Constructor ///////////////////////////////////////

    public Meteooroloog_Controller( String nickname ) {

        super( nickname, "Meteooroloog", "PLACEHOLDER", 4, Color.WHITE, "resources/placeholder.png" );

    }
}
