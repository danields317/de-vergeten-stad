package Controller.Player_Controllers;

import javafx.scene.paint.Color;
import Model.player.*;


public class Klimmer_Controller extends PlayerController implements SpecialMove {


    /////////////////////////////////////// Constructor ///////////////////////////////////////

    public Klimmer_Controller( String nickname ) {

        super( nickname, "Klimmer", "PLACEHOLDER", 3, Color.BLACK, "placeholder.png", Player.SpelerKlassen.KLIMMER);

    }
}
