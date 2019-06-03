package Controller.Player_Controllers;

import Model.player.*;
import javafx.scene.paint.Color;

public class Verkenner_Controller extends Player_controller implements SpecialMove {

    /////////////////////////////////////// Constructor ///////////////////////////////////////

    public Verkenner_Controller( String nickname ) {

        super( nickname, "Verkenner", "PLACEHOLDER", 4, Color.GREEN, "resources/placeholder.png" );

    }
}
