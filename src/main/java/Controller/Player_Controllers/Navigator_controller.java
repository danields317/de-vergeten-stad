package Controller.Player_Controllers;

import Model.player.*;
import javafx.scene.paint.Color;

public class Navigator_controller extends Player_controller implements SpecialMove {

    /////////////////////////////////////// Constructor ///////////////////////////////////////

    public Navigator_controller( String nickname ) {

        super( nickname, "Navigator", "PLACEHOLDER", 4, Color.YELLOW, "placeholder.png");

    }
}
