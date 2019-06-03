package Controller.Player_Controllers;

import javafx.scene.paint.Color;
import Model.player.*;


public class Klimmer_Controller extends Player_controller implements SpecialMove {


    /////////////////////////////////////// Constructor ///////////////////////////////////////

    public Klimmer_Controller( String nickname ) {

        super( nickname, "Klimmer", "PLACEHOLDER", 3, Color.BLACK, "resources/placeholder.png" );

    }
}
